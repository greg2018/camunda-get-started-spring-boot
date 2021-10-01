package org.camunda.bpm.getstarted.loanapproval.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.getstarted.loanapproval.rest.dto.OrderMessagePayload;
import org.camunda.bpm.getstarted.loanapproval.rest.dto.UpdateCustomerFullNameModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GregDemoReceiveMessageFromMQAdapter {
	
	private static Logger logger=LoggerFactory.getLogger(GregDemoReceiveMessageFromMQAdapter.class);
	
	 @Autowired
	  private ProcessEngine camunda;
	
	
	 @RabbitListener(bindings = @QueueBinding( //
		      value = @Queue(value = "p2p_start_event", durable = "true"), //
		      exchange = @Exchange(value = "adam_p2p_topic", type = "topic", durable = "true"), //
		      key = "p2p_start_event_key"))
		  @Transactional  
		  public void receiveMessageByKey(String orderId) {
			  logger.info("++++++GregDemoReceiveMessageFromMQAdapter->receiveMessageByKey->\"p2p_start_event_key->orderId="+orderId);	
		  }

	 
	 @RabbitListener(bindings = @QueueBinding( //
		      value = @Queue(value = "p2p_service_event", durable = "true"), //
		      exchange = @Exchange(value = "adam_p2p_topic", type = "topic", durable = "true"), //
		      key = "p2p_service_event_routingkey"))
		  @Transactional  
		  public void receiveMessageByKey_not_p2p_start_event(String orderId) {
			  logger.info("++++++GregDemoReceiveMessageFromMQAdapter->receiveMessageByKey->receiveMessageByKey->p2p_service_event_routingkey->orderId="+orderId);	
		  }
	 
	 
	 @RabbitListener(bindings = @QueueBinding( //
		      value = @Queue(value = "p2p_start_by_msg", durable = "true"), //
		      exchange = @Exchange(value = "adam_p2p_topic", type = "topic", durable = "true"), //
		      key = "p2p_start_event_routingkey"))
	  @Transactional  
	  public void startP2PProcess(String orderId) {	
		 logger.info("++++++startP2PProcess->orderId="+orderId);
		 int amount=9988; 
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put(ProcessConstants.VAR_NAME_orderId, orderId) ;
         map.put(ProcessConstants.VAR_NAME_amount, amount);        
		 MessageCorrelationResult result = camunda.getRuntimeService().createMessageCorrelation("START_P2P_Message")
				  .processInstanceBusinessKey(orderId)
				  .setVariable("orderId", orderId)
				  .setVariables(map)
				  .correlateWithResult();		
		 
		 ProcessInstance instance=result.getProcessInstance();
		 instance.getBusinessKey();
		 instance.getProcessInstanceId();
		 instance.getProcessDefinitionId();
		 logger.info("businessKey | ProcessDefinitionId | getProcessInstanceId() " +instance.getBusinessKey()+" | "+ instance.getProcessDefinitionId()+" |" + instance.getCaseInstanceId());
		Map<String, Object> map2= camunda.getRuntimeService().getVariables(instance.getProcessInstanceId());
		map2.keySet().forEach(key->logger.info("++++++ key/value="+key+" | "+map2.get(key)));
		 
		
	  }
	 
	 @RabbitListener(bindings = @QueueBinding( //
		      value = @Queue(value = "p2p_order_payload", durable = "true"), //
		      exchange = @Exchange(value = "adam_p2p_topic", type = "topic", durable = "true"), //
		      key = "p2p_order_payload_routingkey"))
	  @Transactional  
	  public void receiveOrderPayload(OrderMessagePayload  orderMessagePayload) {
		  logger.info("++++++GregDemoReceiveMessageFromMQAdapter->receiveOrderPayload->p2p_order_payload_routingkey:");	
		  logger.info(orderMessagePayload.toString());
	  }
	 
	 
	 @RabbitListener(bindings = @QueueBinding( //
		      value = @Queue(value = "p2p_updatecustomerfullnamemodel", durable = "true"), //
		      exchange = @Exchange(value = "adam_p2p_topic", type = "topic", durable = "true"), //
		      key = "p2p_updatecustomerfullnamemodel_routingkey"))
	  @Transactional  
	//  public void receiveUpdateCustomerFullNameModel(UpdateCustomerFullNameModel  updateCustomerFullNameModel) {
	  public void receiveUpdateCustomerFullNameModel(byte[]  bytes) throws JsonParseException, JsonMappingException, IOException {
		 ObjectMapper objectMapper = new ObjectMapper();
		 
		
		  logger.info("++++++GregDemoReceiveMessageFromMQAdapter->receiveUpdateCustomerFullNameModel->p2p_updatecustomerfullnamemodel_routingkey:");	
		  String jsonArray=new String(bytes);
		  logger.info(jsonArray);
		 UpdateCustomerFullNameModel  updateCustomerFullNameModel=objectMapper.readValue(bytes, UpdateCustomerFullNameModel.class);
		logger.info("++++++updateCustomerFullNameModel.toString()is: "+ updateCustomerFullNameModel.toString());
	  }

}
