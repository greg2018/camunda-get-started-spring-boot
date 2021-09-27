package org.camunda.bpm.getstarted.loanapproval.adapter;

import org.camunda.bpm.getstarted.loanapproval.rest.dto.GregDemoSendMessageToRabbitMQRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


}
