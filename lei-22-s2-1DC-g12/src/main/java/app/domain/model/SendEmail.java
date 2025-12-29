package app.domain.model;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * The interface Send email.
 */
public interface SendEmail {

    /**
     * Send email.
     *
     * @param userEmail    the user email
     * @param emailMessage the email message
     * @param emailSubject the email subject
     */
    static void SendEmail(String userEmail, String emailMessage, String emailSubject)  {


        // Recipient's email ID needs to be mentioned.
        String to = userEmail;

        // Sender's email ID needs to be mentioned
        String from = "vaccinationcenterLAPR2@gmail.com";

        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");


        // Get the default Session object.

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, "vaccination2022");

            }

        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(emailSubject);

            // Now set the actual message
            message.setText(emailMessage);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }



    }
}
