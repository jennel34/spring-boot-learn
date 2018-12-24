package com.connext.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Map;

public class EmailInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    //    ID
    private long id;
    //    发送人
    private String msgFrom;
    //    收件人
    private String msgTo;
    //    密送
    private String bcc;
    //    抄送
    private String cc;
    //    主题
    private String subject;
    //    消息
    private String text;
    //    附件ID
    private String fileIds;
    //    图片ID
    private String imgIds;
    //    附件
    private Map<String, Object> attachmentList;
    //    图片
    private Map<String, Object> imagesList;
    //    状态
    private String status;
    //    响应信息
    private String resInfo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsgFrom() {
        return msgFrom;
    }

    public void setMsgFrom(String msgFrom) {
        this.msgFrom = msgFrom;
    }

    public String getMsgTo() {
        return msgTo;
    }

    public void setMsgTo(String msgTo) {
        this.msgTo = msgTo;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public String getImgIds() {
        return imgIds;
    }

    public void setImgIds(String imgIds) {
        this.imgIds = imgIds;
    }

    public Map<String, Object> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(Map<String, Object> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public Map<String, Object> getImagesList() {
        return imagesList;
    }

    public void setImagesList(Map<String, Object> imagesList) {
        this.imagesList = imagesList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResInfo() {
        return resInfo;
    }

    public void setResInfo(String resInfo) {
        this.resInfo = resInfo;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
