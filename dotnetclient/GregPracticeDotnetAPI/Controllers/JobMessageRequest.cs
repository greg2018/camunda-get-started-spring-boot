namespace GregPracticeDotnetAPI.Controllers
{
    public class JobMessageRequest
    {
        public int jobId { get; set; }
        public string processInstanceId { get; set; }
        public string jobTypeId { get; set; }
        public string actionType { get; set; }
       
       
    }
}