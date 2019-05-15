package com.ruokit.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruokit.main.domain.MailContent;
import com.ruokit.main.service.mail.MailService;

@Controller
@RequestMapping("/mail")
public class MailSendController {

  private static final Logger logger = LoggerFactory.getLogger(MailSendController.class);
  private String viewDirectory = "/mail";

  @RequestMapping("/sendSubmit.do")
  public String getTestMail(@ModelAttribute("content") MailContent content, Model m) {
    MailService mailService = new MailService();
    content.setReceiver("methere12@naver.com");
    String result = mailService.sendMail(content);
    m.addAttribute("result", result);
    return viewDirectory + "/sendResult";
  }

  @RequestMapping("/sendScreen.do")
  public String getMailSender(Model m) {
    return viewDirectory + "/sendScreen";
  }


}
