package org.camunda.bpm.getstarted.loanapproval.adapter;

import org.camunda.bpm.getstarted.loanapproval.message.model.JobMessageResponse;
import org.camunda.bpm.getstarted.loanapproval.rest.dto.GregDemoSendMessageToRabbitMQRequest;
import org.camunda.bpm.getstarted.loanapproval.rest.dto.OrderMessagePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class GregDemoSendMessageToRabbitMQAdater {
	private static Logger logger=LoggerFactory.getLogger(GregDemoSendMessageToRabbitMQAdater.class);
	
	@Autowired
	  protected RabbitTemplate rabbitTemplate;
	  
	  
	  public void sendMessageToRabbitMQ(GregDemoSendMessageToRabbitMQRequest gregDemoSendMessageToRabbitMQRequest) throws Exception {
	    String orderId =gregDemoSendMessageToRabbitMQRequest.getOrderId();
	    String exchange =gregDemoSendMessageToRabbitMQRequest.getExchange();
	 //   String routingKey = "createShipment";
	    String routingKey=gregDemoSendMessageToRabbitMQRequest.getRoutingKey(); 
	    logger.info("+++++++orderId="+ orderId 
			    					  + " | " +" exchange= " + exchange  
			    					  + " | " + " routingKey=" + routingKey); 
	    rabbitTemplate.convertAndSend(exchange, routingKey, orderId);
	  }
	  
	  
	  
	  public void sendMessagePayLoadToRabbitMQ(GregDemoSendMessageToRabbitMQRequest gregDemoSendMessageToRabbitMQRequest) throws Exception {
		    String orderId =gregDemoSendMessageToRabbitMQRequest.getOrderId();
		    String exchange =gregDemoSendMessageToRabbitMQRequest.getExchange();
		 //   String routingKey = "createShipment";
		    String routingKey=gregDemoSendMessageToRabbitMQRequest.getRoutingKey(); 
		    logger.info("+++++++orderId="+ orderId 
				    					  + " | " +" exchange= " + exchange  
				    					  + " | " + " routingKey=" + routingKey); 
		    
		    OrderMessagePayload  orderMessagePayload=new  OrderMessagePayload();
		    orderMessagePayload.setOrderId(gregDemoSendMessageToRabbitMQRequest.getOrderId());
		    orderMessagePayload.setAmount(gregDemoSendMessageToRabbitMQRequest.getAmount());
		    logger.info("++++++sendMessagePayLoadToRabbitMQ()...");
		    logger.info(orderMessagePayload.toString());
		    rabbitTemplate.convertAndSend(exchange, routingKey,  orderMessagePayload);
		  }


	  public void sendMessagePayLoadToRabbitMQByBytes(GregDemoSendMessageToRabbitMQRequest gregDemoSendMessageToRabbitMQRequest) throws Exception {
		    String orderId =gregDemoSendMessageToRabbitMQRequest.getOrderId();
		    String exchange =gregDemoSendMessageToRabbitMQRequest.getExchange();
		 //   String routingKey = "createShipment";
		    String routingKey=gregDemoSendMessageToRabbitMQRequest.getRoutingKey(); 
		    logger.info("+++++++orderId="+ orderId 
				    					  + " | " +" exchange= " + exchange  
				    					  + " | " + " routingKey=" + routingKey); 
		    
		    OrderMessagePayload  orderMessagePayload=new  OrderMessagePayload();
		    orderMessagePayload.setOrderId(gregDemoSendMessageToRabbitMQRequest.getOrderId());
		    orderMessagePayload.setAmount(gregDemoSendMessageToRabbitMQRequest.getAmount());
		    logger.info("++++++sendMessagePayLoadToRabbitMQ()...");
		    logger.info(orderMessagePayload.toString());
		    ObjectMapper objectMapper = new ObjectMapper();
		    String jsonString = objectMapper.writeValueAsString(orderMessagePayload);
		    rabbitTemplate.convertAndSend(exchange, routingKey,  jsonString.getBytes());
		  }
	  
	  /*
	   * Send message to job manager , data manager etc.
	   */
	  public void sendAdamOrchestratingMsg(JobMessageResponse jobMessageResponse) throws Exception {
		    logger.info("+++++++adamOrchestratingMsgOut="+ jobMessageResponse.toString());
		    String exchange="adam_p2p_topic";
		    String routingKey="adam_orchestrating_out_routing_key";
		    ObjectMapper objectMapper = new ObjectMapper();
		    String jsonString = objectMapper.writeValueAsString(jobMessageResponse);
		    rabbitTemplate.convertAndSend(exchange, routingKey,  jsonString.getBytes());
	 }

	  
}
