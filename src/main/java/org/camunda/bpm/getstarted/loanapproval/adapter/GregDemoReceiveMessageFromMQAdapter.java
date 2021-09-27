package org.camunda.bpm.getstarted.loanapproval.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GregDemoReceiveMessageFromMQAdapter {
	private static Logger logger=LoggerFactory.getLogger(GregDemoReceiveMessageFromMQAdapter.class);
	
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

}
