package by.demoqa.util.email;

import com.qaprosoft.carina.core.foundation.crypto.CryptoTool;
import com.qaprosoft.carina.core.foundation.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;

public class EmailUtil {
    private static final Logger log = LoggerFactory.getLogger(EmailUtil.class);
    private Message message = null;
    protected static String SMTP_HOST = null;
    protected static String SMTP_Port = null;
    protected static String SMTP_AUTH_USER = null;
    protected static String SMTP_AUTH_PWD = null;
    protected static String EMAIL_FROM = null;
    protected static String IMAP_SERVER = null;
    protected static Authenticator auth;
    protected static Session session;
    public static final int LAST_MESSAGE = -1;
    protected CryptoTool cryptoTool = new CryptoTool();

    public EmailUtil() {

        SMTP_HOST = R.EMAIL.get("mail.smtp.host");
        SMTP_Port = R.EMAIL.get("mail.smtp.port");
        SMTP_AUTH_USER = cryptoTool.decrypt(R.EMAIL.get("mail.smtp.user"));
        SMTP_AUTH_PWD = cryptoTool.decrypt(R.EMAIL.get("mail.smtp.pass"));
        EMAIL_FROM = cryptoTool.decrypt(R.EMAIL.get("mail.smtp.from"));
        IMAP_SERVER = R.EMAIL.get("mail.imap.server");

        auth = new EmailAuthenticator(SMTP_AUTH_USER,
                SMTP_AUTH_PWD);
        session = Session.getDefaultInstance(R.EMAIL.getProperties(), auth);
        session.setDebug(Boolean.parseBoolean(R.EMAIL.get("mail.set.debag")));
    }

    public Message readEmail(String type, int folder, int messageNumber) {
        try {
            Store store = session.getStore();
            store.connect(IMAP_SERVER, SMTP_AUTH_USER, SMTP_AUTH_PWD);
            Folder inbox = store.getFolder(type);
            inbox.open(folder);
            int count = inbox.getMessageCount();
            log.info("NUMBER OF MESSAGES: " + count);
            Message message = null;
            if (messageNumber == LAST_MESSAGE) {
                message = inbox.getMessage(count);
                return message;
            } else {
                message = inbox.getMessage(messageNumber);
                return message;
            }
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public boolean sendMessage(final String emailTo, final String text, final String subject) {
        boolean result = false;
        try {
            InternetAddress email_from = new InternetAddress(EMAIL_FROM);
            InternetAddress email_to = new InternetAddress(emailTo);
            message = new MimeMessage(session);
            Multipart mmp = new MimeMultipart();
            MimeBodyPart bodyPart = new MimeBodyPart();

            message.setFrom(email_from);
            message.setRecipient(Message.RecipientType.TO, email_to);
            message.setSubject(subject);
            bodyPart.setContent(text, "text/plain; charset=utf-8");
            mmp.addBodyPart(bodyPart);
            message.setContent(mmp);
            Transport.send(message);
            result = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getNumberOfMessage() {
        return readEmail("INBOX", Folder.READ_ONLY, LAST_MESSAGE).getMessageNumber();
    }

    public String getContentMessage(int messageNumber) {
        try {
            Message messageCont = readEmail("INBOX", Folder.READ_ONLY, messageNumber);
            if (messageCont.isMimeType("text/*")) return messageCont.getContent().toString();
            else if (messageCont.isMimeType("multipart/*")){
                return ((Multipart) messageCont.getContent()).getBodyPart(0).getContent().toString();
            }
            else throw new RuntimeException("Unhandled MIME type: " + messageCont.getContentType());
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSubjectMessage(int messageNumber) {
        try {
            String subject = readEmail("INBOX", Folder.READ_ONLY, messageNumber).getSubject();
            log.info("SUBJECT: {}", subject);
            return subject;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteMessage(int messageNumber) {
        try {
            Message messageToDelete = readEmail("INBOX", Folder.READ_WRITE, messageNumber);
            messageToDelete.setFlag(Flags.Flag.DELETED, true);
            log.info("Message number {} {}", messageToDelete.getMessageNumber(), " has been deleted");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
