package com.rsystems.ayush;
 
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
public class EmailAttachmentSender {
 
    public static void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message, String[] attachFiles)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
 
    /**
     * Test sending e-mail with attachments
     */
    public static void main(String[] args) {
        // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "ram2020shyam20@gmail.com";
        String password = "India89@@";
 
        // message info
        String mailTo = "shyam2020ram20@gmail.com";
        String subject = "New email with attachments";
        String message = "I have some attachments for you.";
 
        // attachments
        String[] attachFiles = new String[3];
        attachFiles[0] = "F:\\emailable-report.html";
        attachFiles[1] = "F:\\emailable-report11.html";
        attachFiles[2] = "F:\\emailable-report22.html";
 
        try {
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                subject, message, attachFiles);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }
}

/*
 * //Recipient's email ID needs to be mentioned. String to =
 * "lovanya.chaudhary@rsystems.com"; //Sender's email ID needs to be mentioned
 * String from = "lovanya.chaudhary@rsystems.com"; //Assuming you are sending
 * email from localhost String host = "SEZ92CIBC.india.rsystems.com"; //Get
 * system properties Properties properties = System.getProperties(); //Setup
 * mail server properties.setProperty("mail.smtp.host", host); //Get the default
 * Session object. Session session = Session.getDefaultInstance(properties);
 * 
 * try{ // Create a default MimeMessage object. MimeMessage message = new
 * MimeMessage(session); // Set From: header field of the header.
 * message.setFrom(new InternetAddress(from)); // Set To: header field of the
 * header. message.addRecipient(Message.RecipientType.TO, new
 * InternetAddress(to));
 * 
 * // Set Subject: header field message.setSubject("Report in email!!!");
 * 
 * // Create the message part BodyPart messageBodyPart = new MimeBodyPart();
 * 
 * // Fill the message
 * messageBodyPart.setText("Sample try for the email report");
 * 
 * // Create a multipar message Multipart multipart = new MimeMultipart();
 * 
 * // Set text message part multipart.addBodyPart(messageBodyPart);
 * 
 * // Part two is attachment messageBodyPart = new MimeBodyPart(); String
 * filename =
 * "F:\\Eclipse 2012\\automation-tool\\Automation-Poc\\test-output\\emailable-report.html"
 * ; DataSource source = new FileDataSource(filename);
 * messageBodyPart.setDataHandler(new DataHandler(source));
 * messageBodyPart.setFileName(filename);
 * multipart.addBodyPart(messageBodyPart);
 * 
 * // Send the complete message parts message.setContent(multipart );
 * 
 * // Send message Transport.send(message);
 * System.out.println("Sent message successfully...."); }catch
 * (MessagingException mex) { mex.printStackTrace(); }
 */
