using System;

    public class  JobMessageResponse
    {
        public int jobId { get; set; }

        public string processInstanceId { get; set; }

        public string jobTypeId { get; set; }

        public string actionType { get; set; }

        public bool resultInd { get; set; }

        public string description { get; set; }


    }
