package io.wkm.jcartadministrationback.util;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EmailUtil {
    @Resource
    private MailSender mailSender;

    @Async
    public void sendEmail(String from,
                          String to,
                          String content,
                          String title){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);
        mailSender.send(simpleMailMessage);
    }
}
