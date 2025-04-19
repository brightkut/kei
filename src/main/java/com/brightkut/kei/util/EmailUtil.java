package com.brightkut.kei.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Component
@ConditionalOnProperty(
        name = "kei.mail.enabled",
        havingValue = "true",
        matchIfMissing = false
)
public class EmailUtil {
    private static final Logger log = LoggerFactory.getLogger(EmailUtil.class);

    private final String sender;
    private final JavaMailSender javaMailSender;
    private final Executor taskExecutor;

    public EmailUtil(
            @Value("${spring.mail.username}") String sender,
            @Qualifier("taskExecutor") Executor taskExecutor,
            JavaMailSender javaMailSender
    ) {
        this.sender = sender;
        this.javaMailSender = javaMailSender;
        this.taskExecutor = taskExecutor;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class EmailDetail {
        private String recipient;
        private String emailSubject;
        private String emailBody;
    }

    public void sendSimpleMail(EmailDetail details)
    {
        try {
            log.info("Send a simple mail without attachment file to sender: {}, recipient: {}", sender, details.getRecipient());
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getEmailBody());
            mailMessage.setSubject(details.getEmailSubject());

            javaMailSender.send(mailMessage);
        }

        catch (Exception exception) {
            log.error("Error while sending the mail");
            throw new RuntimeException(exception);
        }
    }

    public CompletableFuture<Void> sendSimpleMailAsync(EmailDetail details) {
        return CompletableFuture.runAsync(() -> {
            try {
                log.info("Sending a simple mail(async) without attachment file to sender: {}, recipient: {}", sender, details.getRecipient());
                SimpleMailMessage mailMessage = new SimpleMailMessage();

                mailMessage.setFrom(sender);
                mailMessage.setTo(details.getRecipient());
                mailMessage.setText(details.getEmailBody());
                mailMessage.setSubject(details.getEmailSubject());

                javaMailSender.send(mailMessage);
            } catch (Exception exception) {
                log.error("Error while sending the mail", exception);
                throw new RuntimeException(exception);
            }
        }, taskExecutor);
    }
}
