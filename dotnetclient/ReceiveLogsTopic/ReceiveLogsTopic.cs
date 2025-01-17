﻿using System;
using System.Text;
using Newtonsoft.Json;
using RabbitMQ.Client;
using RabbitMQ.Client.Events;


class ReceiveLogsTopic
{
    public static void Main(string[] args)
    {
        var factory = new ConnectionFactory() { HostName = "localhost" };
        using(var connection = factory.CreateConnection())
        using(var channel = connection.CreateModel())
        {
           //var queueName="to_job_manager_queue";
           // string test_routingKey="to_job_manager_routing_key";
           var queueName="To_Event_Log_Queue";
           string test_routingKey="To_Event_Log_Key";
            
           channel.ExchangeDeclare(exchange: "adam_p2p_topic", durable: true, type: "topic");
            channel.QueueDeclare(queue: queueName,   durable: true, exclusive: false, autoDelete: false,
                                 arguments: null);
            //var queueName = channel.QueueDeclare().QueueName;
            
            Console.WriteLine("++++++queueName="+queueName);

            string test_topic="adam_p2p_topic";
           // string test_routingKey="p2p_order_dotnet_payload_routingkey";
          
            channel.QueueBind(queue: queueName,   exchange: test_topic,  routingKey: test_routingKey);
            Console.WriteLine(" [*] Waiting for messages. To exit press CTRL+C");

            var consumer = new EventingBasicConsumer(channel);
            consumer.Received += (model, ea) =>
            {
                var body = ea.Body.ToArray();
                var message = Encoding.UTF8.GetString(body);
                var routingKey = ea.RoutingKey;
                Console.WriteLine(" [x] Received '{0}':'{1}'",  routingKey,  message);
                var content =  message ;
                var jobMessageResponse = JsonConvert.DeserializeObject<JobMessageResponse>(content);
               // Console.WriteLine("++++++orderId is: "+orderMessagePayload.orderId );
                //Console.WriteLine("++++++amount is: "+orderMessagePayload.amount );
                Console.WriteLine("++++++ jobId is: "+jobMessageResponse.jobId );
                 Console.WriteLine("++++++ processInstanceId is: "+jobMessageResponse.processInstanceId );
            };
            channel.BasicConsume(queue: queueName, autoAck: true,consumer: consumer);

            Console.WriteLine(" Press [enter] to exit.");
            Console.ReadLine();
        }
    }
}