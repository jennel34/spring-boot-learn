package com.connext.service;

import com.connext.entity.EmailInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.Map;

@Service
public class EmailService {
    private  static Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public boolean send(String title,String text,String toMail){
        EmailInfo emailInfo = new EmailInfo();
        emailInfo.setSubject(title);
        emailInfo.setText(text);
        emailInfo.setMsgTo(toMail);
        emailInfo.setMsgFrom("jennelxwy@163.com");
        return send(emailInfo);
    }

    private boolean send(EmailInfo emailInfo) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

//            发送人
            if(null != emailInfo.getMsgFrom()){
                helper.setFrom(emailInfo.getMsgFrom());
            }

//            密送
            if(null != emailInfo.getBcc()){
                helper.setBcc(StringUtils.split(emailInfo.getBcc(),","));
            }

//            抄送
            if(null != emailInfo.getCc()){
                helper.setCc(StringUtils.split(emailInfo.getCc(),","));
            }

//            收件人
            if(null != emailInfo.getMsgTo()){
                if(emailInfo.getMsgTo().indexOf(",") > -1){
                    helper.setTo(StringUtils.split(emailInfo.getMsgTo(),","));
                }else{
                    String[] mailTo = {emailInfo.getMsgTo()};
                    helper.setTo(mailTo);
                }
            }

//            邮件主题
            helper.setSubject(emailInfo.getSubject());
//            邮件的内容
            helper.setText(emailInfo.getText(),true);
//            附件
            if(null != emailInfo.getAttachmentList()){
                for (Map.Entry<String , Object> entry:
                     emailInfo.getAttachmentList().entrySet()) {
                    if(entry.getValue() instanceof File){
                        helper.addAttachment(MimeUtility.encodeText(entry.getKey()),(File)entry.getValue());
                    }
                }
            }

//            图片
            if(null != emailInfo.getImagesList()){
                for (Map.Entry<String , Object> entry:
                     emailInfo.getImagesList().entrySet()) {
                    if(entry.getValue() instanceof  File){
                        helper.addInline(entry.getKey(), (File) entry.getValue());
                    }
                }
            }

//            发送邮件
            mailSender.send(mimeMessage);
            return true;
        }catch (Exception e){
            logger.error("send email error.information{},异常信息{}",emailInfo,e);
            return false;
        }
    }
}
