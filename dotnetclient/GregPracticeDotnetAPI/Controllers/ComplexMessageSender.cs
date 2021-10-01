using System;
using RabbitMQ.Client;
using Newtonsoft.Json;
using System.Text;
using Microsoft.Extensions.Logging;

namespace GregPracticeDotnetAPI.Controllers
{
    public class ComplexMessageSender
    {
        private readonly string _hostname;
        private readonly string _password;
        private readonly string _queueName;
        private readonly string _username;
        private IConnection _connection;

     //private readonly ILogger<UserController> _logger= _logger = new LoggerFactory().CreateLogger<ComplexMessageSender>();

	   public  ComplexMessageSender(
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
           
        }

        public void sendComplexMessage(string message)
        {
          if (_connection == null){
               CreateConnection();
          }
           // using(var channel = _connection.CreateModel())
           var channel = _connection.CreateModel();
            {
                channel.QueueDeclare(queue:  _queueName, durable: false, exclusive: false, autoDelete: false, arguments: null);
                //var body = Encoding.UTF8.GetBytes(message);
                Customer customer=new  Customer();
                customer.Id=Guid.NewGuid();
                customer.FirstName="Greg1688";
                customer.LastName="Qiu1688";

                var json = JsonConvert.SerializeObject(customer);
                var body = Encoding.UTF8.GetBytes(json);


                channel.BasicPublish(exchange: "", routingKey:  _queueName, basicProperties: null, body: body);      
            }
        }



       public void sendComplexMessageTwo(string message)
        {
          if (_connection == null){
               CreateConnection();
          }
           // using(var channel = _connection.CreateModel())
           var channel = _connection.CreateModel();
            {
               // channel.QueueDeclare(queue:  _queueName, durable: false, exclusive: false, autoDelete: false, arguments: null);
                //var body = Encoding.UTF8.GetBytes(message);
                CustomerWithLowerCase customer=new CustomerWithLowerCase();
                customer.id=Guid.NewGuid();
                customer.firstName="Greg1689";
                customer.lastName="Qiu1689";
                customer.birthday=DateTime.Now;
                 customer.age=100;

                var json = JsonConvert.SerializeObject(customer);
                var body = Encoding.UTF8.GetBytes(json);

                string dest_topic="adam_p2p_topic";
                string dest_rotingkey="p2p_updatecustomerfullnamemodel_routingkey";
                channel.BasicPublish(exchange: dest_topic, routingKey: dest_rotingkey, basicProperties: null, body: body);      
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