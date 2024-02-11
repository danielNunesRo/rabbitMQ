package com.ms.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.email.dtos.EmailRecordDto;
import com.ms.email.model.EmailModel;
import com.ms.email.services.EmailServices;

@Component
public class EmailConsumer {
	
	final EmailServices emailService;
	
	
	public EmailConsumer(EmailServices emailService) {
		this.emailService = emailService;
	}



	@RabbitListener(queues = "${broker.queue.email.name}")
	public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto) {
		var emailModel = new EmailModel();
		BeanUtils.copyProperties(emailRecordDto, emailModel);
		emailService.sendMail(emailModel);
	}
	
}
