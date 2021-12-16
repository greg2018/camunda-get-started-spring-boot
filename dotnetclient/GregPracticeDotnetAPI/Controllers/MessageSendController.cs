
using System;
using GregPracticeDotnetAPI.DTOs;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
namespace GregPracticeDotnetAPI.Controllers
{

    [ApiController]
    public class MessageSendController : ControllerBase
    {


        private readonly ILogger<UserController> _logger;

		public  MessageSendController( ILoggerFactory loggerFactory)
		{
			_logger = loggerFactory.CreateLogger<UserController>();
			
		}      
        
        [HttpPost]
        [Route("api/message")]
        public AppUser CreateUser(AppUser user)
        {
               _logger.LogInformation("+++++++++++"+user.FirstName);
               _logger.Log(LogLevel.Information,"+++++Info=>"+ user.FirstName);
                
              // MessageSender.sendMessage();
                _logger.LogInformation("+++++Sending message from instance method");
               string queueName="hello";
                queueName="CustomerQueue";
               

                _logger.LogInformation("+++++ComplexMessageSender Sending message from instance method");
                ComplexMessageSender  complexMessageSender=new  ComplexMessageSender(
                    "localhost",
                    queueName,
                    "guest", 
                    "guest"
                );

                complexMessageSender.sendComplexMessage(user.FirstName);

                 return user;
        }


        [HttpPost]
        [Route("api/message2")]
        public AppUser CreateUser2(AppUser user)
        {
               _logger.LogInformation("+++++++++++"+user.FirstName);
               _logger.Log(LogLevel.Information,"+++++Info=>"+ user.FirstName);
                
              // MessageSender.sendMessage();
                _logger.LogInformation("+++++Sending message from instance method");
               string queueName="hello";
                queueName="CustomerQueue";
               

                _logger.LogInformation("+++++ComplexMessageSender Sending message from instance method");
                ComplexMessageSender  complexMessageSender=new  ComplexMessageSender(
                    "localhost",
                    queueName,
                    "guest", 
                    "guest"
                );

                complexMessageSender.sendComplexMessageTwo(user.FirstName);

                 return user;
        }


        [HttpPost]
        [Route("api/message3")]
        public  JobMessageRequestWrapper sendOrchestratorMessage( JobMessageRequestWrapper  jobMessageRequestWrapper)
        {
               _logger.LogInformation("+++++++++++"+jobMessageRequestWrapper.jobId);
               _logger.Log(LogLevel.Information,"+++++Info=>"+ jobMessageRequestWrapper.processInstanceId);
                
              // MessageSender.sendMessage();
                _logger.LogInformation("+++++Sending message from instance method");
               string queueName="hello";
                queueName="CustomerQueue";
               

                _logger.LogInformation("+++++ComplexMessageSender Sending message from instance method");
                ComplexMessageSender  complexMessageSender=new  ComplexMessageSender(
                    "localhost",
                    queueName,
                    "guest", 
                    "guest"
                );

                complexMessageSender.sendComplexMessageThree(jobMessageRequestWrapper);

                 return jobMessageRequestWrapper;
        }

    }
}