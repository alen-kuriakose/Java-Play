package config;

//import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class EmailService {
    public void sendEmail()  {
        try {
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(25);
            email.setSSLOnConnect(true);
            email.addTo("alen.kuriakose@tarento.com", "Alen");
            email.setFrom("me@apache.org", "Me");
            email.setSubject("Test");
            email.setMsg("userName");
            email.setStartTLSEnabled(true);
            email.setAuthentication("alenkuriakosecp@gmail.com", "khmfbneusvfspfhq");
            email.send();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }
}
