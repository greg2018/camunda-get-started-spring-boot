package org.camunda.bpm.getstarted.loanapproval.rest.controller;

import org.camunda.bpm.getstarted.loanapproval.adapter.GregDemoSendMessageToRabbitMQAdater;
import org.camunda.bpm.getstarted.loanapproval.message.model.JobMessageResponse;
import org.camunda.bpm.getstarted.loanapproval.rest.dto.CommonResponse;
import org.camunda.bpm.getstarted.loanapproval.rest.dto.GregDemoSendMessageToRabbitMQRequest;
import org.camunda.bpm.getstarted.loanapproval.rest.dto.LoanAppRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class GregDemoSendMessageToRabbitMQController {
	
	private static Logger logger=LoggerFactory.getLogger(GregDemoSendMessageToRabbitMQController.class);
	
	@Autowired
	GregDemoSendMessageToRabbitMQAdater gregDemoSendMessageToRabbitMQAdater;
	
	 @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	  public ResponseEntity<?> sendMessageToRabbitMQ(@RequestBody GregDemoSendMessageToRabbitMQRequest gregDemoSendMessageToRabbitMQRequest) throws Exception {	 
		 logger.info(gregDemoSendMessageToRabbitMQRequest.toString());
	      CommonResponse commonResponse=new CommonResponse();
	      commonResponse.setResultFlag(true);
	      commonResponse.setMessage("success");	  
	      logger.info(commonResponse.getMessage());
	      
	      gregDemoSendMessageToRabbitMQAdater.sendMessageToRabbitMQ(gregDemoSendMessageToRabbitMQRequest);
	      return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
	  }
	 
	 
	 @PostMapping(path = "payload", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	  public ResponseEntity<?> sendMessagePayLoadToRabbitMQ(@RequestBody GregDemoSendMessageToRabbitMQRequest gregDemoSendMessageToRabbitMQRequest) throws Exception {	 
		 logger.info(gregDemoSendMessageToRabbitMQRequest.toString());
	      CommonResponse commonResponse=new CommonResponse();
	      commonResponse.setResultFlag(true);
	      commonResponse.setMessage("success");	  
	      logger.info(commonResponse.getMessage());
	      
	      gregDemoSendMessageToRabbitMQAdater.sendMessagePayLoadToRabbitMQ(gregDemoSendMessageToRabbitMQRequest);
	      return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
	  }
	 
	 @PostMapping(path = "payload2", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	  public ResponseEntity<?> sendMessagePayLoadToRabbitMQByBytes(@RequestBody GregDemoSendMessageToRabbitMQRequest gregDemoSendMessageToRabbitMQRequest) throws Exception {	 
		 logger.info(gregDemoSendMessageToRabbitMQRequest.toString());
	      CommonResponse commonResponse=new CommonResponse();
	      commonResponse.setResultFlag(true);
	      commonResponse.setMessage("success");	  
	      logger.info(commonResponse.getMessage());
	      
	      gregDemoSendMessageToRabbitMQAdater.sendMessagePayLoadToRabbitMQByBytes(gregDemoSendMessageToRabbitMQRequest);
	      return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
	  }

	 //Send message to adam job manager,data manager
          @PostMapping(
              path = "adam/orchestrating",
              consumes = {MediaType.APPLICATION_JSON_VALUE},
              produces = {MediaType.APPLICATION_JSON_VALUE})
          public ResponseEntity<?> sendAdamOrchestratingMsg( @RequestBody JobMessageResponse jobMessageResponse) throws Exception {
            logger.info(jobMessageResponse.toString());
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setResultFlag(true);
            commonResponse.setMessage("success");
            gregDemoSendMessageToRabbitMQAdater.sendAdamOrchestratingMsg(jobMessageResponse);
            return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
          }
}
