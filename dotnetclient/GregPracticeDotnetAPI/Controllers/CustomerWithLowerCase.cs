using System;
using System.Collections.Generic;

namespace GregPracticeDotnetAPI.Controllers
{
    public class CustomerWithLowerCase
    {
        public Guid id { get; set; }
        public string firstName { get; set; }
        public string lastName { get; set; }
        public DateTime? birthday { get; set; }
        public int? age { get; set; }
        
    }
}