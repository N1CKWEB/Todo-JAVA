package SistemaAgendaElectronica.Servicios;

import javax.mail.internet.AddressException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarCorreoElectronico {

    private final String remitente;
    private final String password;
    private final Properties props;

    public EnviarCorreoElectronico(String remitente, String password) {
        this.remitente = remitente;
        this.password = password;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // o 465 si usas SSL
    }

    public void enviarGmail(String asunto, String cuerpo, String destinatario) throws AddressException, MessagingException {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(remitente));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(destinatario));
        message.setSubject(asunto);
        message.setText(cuerpo);

        Transport.send(message);
    }
}