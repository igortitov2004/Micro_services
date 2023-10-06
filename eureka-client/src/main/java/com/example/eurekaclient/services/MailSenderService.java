package com.example.eurekaclient.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender mailSender;
    public void sendEmail(String toEmail, String subject, String body) throws MessagingException, FileNotFoundException {
//        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(toEmail);
        messageHelper.setSubject(subject);
        messageHelper.setText(body);
//        FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
//        messageHelper.addAttachment("Инфа.txt", file);
        mailSender.send(mimeMessage);
        System.out.println("Письмо отправлено!");
    }
}
