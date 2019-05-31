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
  
  private String smtpHost;
  private String smtpPort;
  private String smtpUserName;
  private String smtpPassword;  
  private String sender;
  private String senderName;
  
  public MailService(String smtpHost, String smtpPort, String smtpUserName, String smtpPassword, String sender, String senderName) {          
      this.smtpHost = smtpHost;
      this.smtpPort = smtpPort;
      this.smtpUserName = smtpUserName;
      this.smtpPassword = smtpPassword;      
      this.sender = sender;
      this.senderName = senderName;
  }
    
  public String sendMail(MailContent content) {

    ResultCode code;
    if (content == null) {
      logger.debug("Mail Content is Null..");
      code = ResultCode.FAIL;
      return code.getText();
    }
    
    Properties props = System.getProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.port", smtpPort);
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");

    Session session = Session.getDefaultInstance(props);
    MimeMessage msg = new MimeMessage(session);

    try {
      msg.setFrom(new InternetAddress(sender, senderName));
      msg.setRecipient(Message.RecipientType.TO, new InternetAddress(content.getReceiver()));
      msg.setSubject(content.getSubject());
      msg.setContent(content.getContent(), "text/html; charset=utf-8");
      msg.setHeader("X-SES-CONFIGURATION-SET", "ConfigSet");
    } catch (UnsupportedEncodingException e) {
      logger.debug("Mail InternetAddress Setting Exception..");
    } catch (MessagingException e) {
      logger.debug("Mail Message Setting Exception..");
    }

    Transport transport = null;
    try {
      transport = session.getTransport();
      transport.connect(smtpHost, smtpUserName, smtpPassword);
      transport.sendMessage(msg, msg.getAllRecipients());
      code = ResultCode.SUCCESS;

    } catch (MessagingException e1) {
      e1.printStackTrace();
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
