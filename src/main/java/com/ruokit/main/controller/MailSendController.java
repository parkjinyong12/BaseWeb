package com.ruokit.main.controller;

import java.util.Properties;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruokit.main.ResultCode;
import com.ruokit.main.mail.model.MailContent;
import com.ruokit.main.mail.service.MailService;

@Controller
@RequestMapping("/mail")
public class MailSendController {

  private static final Logger logger = LoggerFactory.getLogger(MailSendController.class);
  private String viewDirectory = "/mail";
   
  @Resource(name="appConfiguration")
  private Properties properties;
    
  @RequestMapping("/sendSubmit.do")
  public String getTestMail(@ModelAttribute("content") MailContent content, Model m) {
    
    String sender = properties.getProperty("mail.sender");
    String senderName = properties.getProperty("mail.sender.name");
    String smtpHost = properties.getProperty("mail.smtp.host");
    String smtpPort = properties.getProperty("mail.smtp.port");
    String smtpUserName = properties.getProperty("mail.smtp.user.name");
    String smtpPassword = properties.getProperty("mail.smtp.user.password");
           
    MailService mailService = new MailService(smtpHost, smtpPort, smtpUserName, smtpPassword, sender, senderName);
      
    content.setReceiver("methere12@naver.com");   
    ResultCode result = mailService.sendMail(content);
    
    m.addAttribute("result", result.getText());    
    logger.info("mail send result : " + result.getText());
    
    return viewDirectory + "/sendResult";
  }

  @RequestMapping("/sendScreen.do")
  public String getMailSender(Model m) {
    return viewDirectory + "/sendScreen";
  }
}
