package com.callum.secretSanta.service;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailService {

    public EmailService() {
    }

    public void sendEmail(String targetEmail, String matchedEmail, String matchedUser){
        final String username = System.getenv("USERNAME");
        final String password = System.getenv("PASSWORD");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        try {
            Multipart multipart = new MimeMultipart();

            message.setFrom(new InternetAddress(username));

            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(targetEmail));
            message.setSubject("Your Secret Santa");

            multipart.addBodyPart(createBody(matchedUser, matchedEmail));

            multipart.addBodyPart(createImage());

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private MimeBodyPart createBody(String matchedUser, String matchedEmail) throws MessagingException{
        String msg = "<h1 style=\"color:red;\">Your secret santa is " + matchedUser + "</h1><h1>The recipients email is: " + matchedEmail + "</h1><img src=\"cid:image\">";
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");
        return mimeBodyPart;
    }

    private MimeBodyPart createImage() throws MessagingException {
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(
                "src/main/resources/santa.jpg");

        mimeBodyPart.setDataHandler(new DataHandler(fds));
        mimeBodyPart.setHeader("Content-ID", "<image>");
        return mimeBodyPart;
    }
}
