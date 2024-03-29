package com.bibliotheque.batch.mail;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

import com.sun.mail.smtp.SMTPMessage;

@Component
public class SendingMail {


    public static void sendMessage(String subject, String text, String adresseMail) {

        final Properties propUrl = new Properties();
        InputStream stream = null;

        final String username = "bibliotheque.oc17@gmail.com";
        final String password = "dfgk bybm bgqk ltjo";

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.transport.protocol", "smtp");

        Session session = Session.getInstance(properties);

        // 2 -> Création du message
        MimeMultipart content = new MimeMultipart("related");
        MimeBodyPart htmlPart = new MimeBodyPart();

        try {

            htmlPart.setText(text,"UTF-8", "html");
            content.addBodyPart(htmlPart);

        } catch (MessagingException pEX){
            pEX.printStackTrace();
        }

        SMTPMessage message = new SMTPMessage(session);

        try {
            message.setContent(content);
            message.setSubject(subject);
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(adresseMail));
            message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(username));
        } catch (MessagingException pEX){

        }

        // 3 -> Envoi du message
        Transport transport = null;
        try{

            transport = session.getTransport("smtp");
            transport.connect(username, password);
            transport.sendMessage(message, new Address[]{
                    new InternetAddress(adresseMail), new InternetAddress(username)});

        } catch (MessagingException pEX){
            pEX.printStackTrace();
        } finally {
            try {
                if (transport != null){
                    transport.close();
                }
            } catch (MessagingException pEX){
                pEX.printStackTrace();
            }
        }
    }
}