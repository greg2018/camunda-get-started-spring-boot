package org.camunda.bpm.getstarted.loanapproval.message.model;

import java.io.Serializable;

public class JobMessageRequest  implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Integer 	jobId;
    private String 	processInstanceId;
    private String  	jobTypeId ;
    private String	 actionType;

      public Integer getJobId() {
        return jobId;
      }
    
      public void setJobId(Integer jobId) {
        this.jobId = jobId;
      }
    
      public String getProcessInstanceId() {
        return processInstanceId;
      }
    
      public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
      }
    
      public String getJobTypeId() {
        return jobTypeId;
      }
    
      public void setJobTypeId(String jobTypeId) {
        this.jobTypeId = jobTypeId;
      }
    
      public String getActionType() {
        return actionType;
      }
    
      public void setActionType(String actionType) {
        this.actionType = actionType;
      }

      @Override
      public String toString() {
        return "AdamOrchestratingMsgIn [jobId="
            + jobId
            + ", processInstanceId="
            + processInstanceId
            + ", jobTypeId="
            + jobTypeId
            + ", actionType="
            + actionType
            + "]";
      }
      
      
      
}
