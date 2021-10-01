using System;
using RabbitMQ.Client;
using System.Text;

namespace GregPracticeDotnetAPI.Controllers
{
    public class MessageSender
    {
        private readonly string _hostname;
        private readonly string _password;
        private readonly string _queueName;
        private readonly string _username;
        private IConnection _connection;

        public  MessageSender(
            string hostname,
            string queueName,
            string username,
            string password
        )
        {
            _hostname = hostname;
            _queueName = queueName;
            
            _username = username;
            _password = password;

           // CreateConnection();
        }
        public static void sendMessage()
        {
            var factory = new ConnectionFactory() { HostName = "localhost" };
            using(var connection = factory.CreateConnection())
            using(var channel = connection.CreateModel())
            {
                channel.QueueDeclare(queue: "hello", durable: false, exclusive: false, autoDelete: false, arguments: null);

                string message = "Hello World! from GregPracticeDotnetAPI 2";
                var body = Encoding.UTF8.GetBytes(message);
                channel.BasicPublish(exchange: "", routingKey: "hello", basicProperties: null, body: body);      
            }
        }

        public void sendMessageTwo()
        {
           // var factory = new ConnectionFactory() { HostName = "localhost" };
          //  using(var connection = factory.CreateConnection())
          //  using(var connection = CreateConnection())
          if (_connection == null){
               CreateConnection();
          }
            using(var channel = _connection.CreateModel())
            {
                channel.QueueDeclare(queue: "hello", durable: false, exclusive: false, autoDelete: false, arguments: null);

                string message = "Hello World! from GregPracticeDotnetAPI 2";
                var body = Encoding.UTF8.GetBytes(message);
                channel.BasicPublish(exchange: "", routingKey:  _queueName, basicProperties: null, body: body);      
            }
        }

        private void CreateConnection()
        {
            try
            {
                var factory = new ConnectionFactory
                {
                    HostName = _hostname,
                    UserName = _username,
                    Password = _password
                };
                _connection = factory.CreateConnection();
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Could not create connection: {ex.Message}");
            }
        }
            
     }
}