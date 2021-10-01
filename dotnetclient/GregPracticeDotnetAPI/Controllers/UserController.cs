using System;
using GregPracticeDotnetAPI.DTOs;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace GregPracticeDotnetAPI.Controllers
{
    [ApiController]
   // [Route("[controller]")]
    public class UserController : ControllerBase
    {
              private readonly ILogger<UserController> _logger;

		public UserController( ILoggerFactory loggerFactory)
		{
			_logger = loggerFactory.CreateLogger<UserController>();
			
		}      
        [HttpGet]
        [Route("api/user")]
        public AppUser GetUser()
        {
            AppUser user = new AppUser();
            user.FirstName = "Greg";
            user.LastName = "Qiu";
            Address address=new Address();
            address.StreetNo="8888";
            address.StreetName="Edwyna Drive";
            user.PersonalAddess=address;
            return user;
        }

        [HttpPost]
        [Route("api/user")]
        public AppUser CreateUser(AppUser user)
        {
               _logger.LogInformation("+++++++++++"+user.FirstName);
               _logger.Log(LogLevel.Information,"+++++Info=>"+ user.FirstName);
              // _logger.LogInformation("+++++++++++"+user.ToString());
               // Console.WriteLine(user);
               Console.WriteLine("+++++++user.FirstName="+user.FirstName);

                    return user;
        }
    }
}