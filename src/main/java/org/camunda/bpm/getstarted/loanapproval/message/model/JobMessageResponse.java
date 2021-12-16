package org.camunda.bpm.getstarted.loanapproval.message.model;

import java.io.Serializable;

public class JobMessageResponse  implements Serializable {

  private Integer jobId;
  private String processInstanceId;
  private String jobTypeId;
  private String actionType;
  private Boolean resultInd;
  private String description;

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

  public Boolean getResultInd() {
    return resultInd;
  }

  public void setResultInd(Boolean resultInd) {
    this.resultInd = resultInd;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "AdamOrchestratingMsgOut [jobId="
        + jobId
        + ", processInstanceId="
        + processInstanceId
        + ", jobTypeId="
        + jobTypeId
        + ", actionType="
        + actionType
        + ", resultInd="
        + resultInd
        + ", description="
        + description
        + "]";
  }
}
