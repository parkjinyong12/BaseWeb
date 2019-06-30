package com.ruokit.main.service.mail;

import com.ruokit.main.ResultCode;
import com.ruokit.main.model.mail.MailContent;

public interface MailService {
  public ResultCode sendMail(MailContent content);
}
