package co.com.regimp.modelos;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    public boolean enviarCorreo(String de, String clave, String para, String mensaje, String asunto) {
        boolean enviado = false;
        try {
            String host = "smtp-mail.outlook.com";
            Properties prop = System.getProperties();
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.user", de);
            prop.put("mail.smtp.password", clave);
            prop.put("mail.smtp.port", 587);
            prop.put("mail.smtp.auth", "true");

            Session sesion = Session.getDefaultInstance(prop, null);
            MimeMessage message = new MimeMessage(sesion);
            message.setFrom(new InternetAddress(de));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject(asunto);
            message.setText(mensaje);
            Transport transport = sesion.getTransport("smtp");
            transport.connect(host,de,clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            enviado = true;
        } catch (Exception e) {
           String minino = e.getMessage();
            System.out.println(minino);
        }
        return enviado;
    }
}
