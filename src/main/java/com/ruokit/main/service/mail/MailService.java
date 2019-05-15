package com.ruokit.main.service.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruokit.main.ResultCode;
import com.ruokit.main.domain.MailContent;

public class MailService {

  private static final Logger logger = LoggerFactory.getLogger(MailService.class);

  private String FROM = "bright@kti.co.kr";
  private String FROMNAME = "¹ÚÁø¿ë";

  // private String TO = "methere12@naver.com";

  private String SMTP_USERNAME = "bh9578";
  private String SMTP_PASSWORD = "#wngus1245";

  private String configSet = "ConfigSet";
  private String HOST = "smtp.gmail.com";

  private int PORT = 587;

  // private String subject = "Amazon SES test (SMTP interface accessed using Java)";

  private String body = String.join(System.getProperty("line.separator"),
      "<h1>Amazon SES SMTP Email Test</h1>", "<p>This email was sent with Amazon SES using the ",
      "<a href='https://github.com/javaee/javamail'>Javamail Package</a>",
      " for <a href='https://www.java.com'>Java</a>.");

  public String sendMail(MailContent content) {

    ResultCode code;
    if (content == null) {
      logger.debug("Mail Content is Null..");
      code = ResultCode.FAIL;
      return code.getText();
    }


    Properties props = System.getProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.port", PORT);
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");

    Session session = Session.getDefaultInstance(props);
    MimeMessage msg = new MimeMessage(session);

    try {
      msg.setFrom(new InternetAddress(FROM, FROMNAME));
      msg.setRecipient(Message.RecipientType.TO, new InternetAddress(content.getReceiver()));
      msg.setSubject(content.getSubject());
      msg.setContent(content.getContent(), "text/html; charset=utf-8");
      msg.setHeader("X-SES-CONFIGURATION-SET", configSet);
    } catch (UnsupportedEncodingException e) {
      logger.debug("Mail InternetAddress Setting Exception..");
    } catch (MessagingException e) {
      logger.debug("Mail Message Setting Exception..");
    }

    Transport transport = null;
    try {
      transport = session.getTransport();
      transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
      transport.sendMessage(msg, msg.getAllRecipients());
      code = ResultCode.SUCCESS;

    } catch (MessagingException e1) {
      logger.debug("Mail Sending Exception..");
      code = ResultCode.FAIL;
    } finally {
      try {
        if (transport != null) {
          transport.close();
        }
      } catch (MessagingException e) {
        logger.debug("Mail Transport Object Close Exception..");
      }
    }

    return code.getText();
  }
}
