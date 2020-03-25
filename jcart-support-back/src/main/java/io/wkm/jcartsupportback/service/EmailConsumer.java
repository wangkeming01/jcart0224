package io.wkm.jcartsupportback.service;

import io.wkm.jcartsupportback.mq.EmailEvent;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RocketMQMessageListener(topic = "email-topic-1",consumerGroup = "comsumer-group-1")
public class EmailConsumer implements RocketMQListener<EmailEvent> {
    @Resource
    private MailSender mailSender;
    @Override
    public void onMessage(EmailEvent emailEvent) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailEvent.getFrom());
        simpleMailMessage.setTo(emailEvent.getTo());
        simpleMailMessage.setSubject(emailEvent.getTitle());
        simpleMailMessage.setText(emailEvent.getContent());
        mailSender.send(simpleMailMessage);
    }
}
