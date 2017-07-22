package io.github.ukiran75.mailService;

import io.github.ukiran75.entity.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Implementation of MailService
 */

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    /**
     * Method to send the mail which is taking the alert object to parse
     * @param object
     */
    public void sendEmail(Object object) {

        Alert alert = (Alert) object;
        MimeMessagePreparator preparator = getMessagePreparator(alert);

        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }


    /**
     * Method to return create the structure of the mail(i.e mail recepient,mail body,mail subject
     * @param alert
     * @return MineMessagePreparator object
     */
    private MimeMessagePreparator getMessagePreparator(final Alert alert) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress("ukiran75@gmail.com"));
                mimeMessage.setText("Dear User,\n"
                        + "You have a HIGH alert raised for the vehicle with \n VIN: " + alert.getVin() +
                        ", \n and the cause of tthe alert is \n REASON: " + alert.getAlertReason());
                mimeMessage.setSubject("HIGH Alert from Vehicle Tracker");
            }
        };
        return preparator;
    }
}
