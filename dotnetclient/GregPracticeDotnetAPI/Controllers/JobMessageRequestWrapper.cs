namespace GregPracticeDotnetAPI.Controllers
{
    public class JobMessageRequestWrapper
    {
        public int jobId                    { get; set; }
        public string processInstanceId     { get; set; }
        public string jobTypeId             { get; set; }
        public string actionType            { get; set; }

        public string topic                 { get; set; }
        public string routingKey            { get; set; }
       
       
    }
}