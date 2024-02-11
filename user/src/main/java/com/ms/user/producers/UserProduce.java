package com.ms.user.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.user.dtos.EmailDto;
import com.ms.user.models.UserModel;

@Component
public class UserProduce {

	final RabbitTemplate rabbitTemplate;

	public UserProduce(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@Value(value = "${broker.queue.email.name}")
	private String routingKey;
	
	public void publishMessageEmail(UserModel userModel) {
		var emailDto = new EmailDto();
		emailDto.setUserId(userModel.getUserId());
		emailDto.setEmailTo(userModel.getEmail());
		emailDto.setSubject("Cadastro realizado com sucesso!");
		emailDto.setText(userModel.getName() + ", seja bem vindo!\nAgradecemos seu cadastrado, aproveite nossa plataforma");
		
		rabbitTemplate.convertAndSend("", routingKey, emailDto);
	}
	
	
	
}
