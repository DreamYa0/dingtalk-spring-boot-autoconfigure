package com.ntocc.framework.dingtalk.robot.bo;

/**
 * @author dreamyao
 */
public class LinkBody {

    private String title;
    private String text;
    private String picUrl;
    private String messageUrl;

    public LinkBody(String title, String text, String messageUrl, String picUrl) {
        this.title = title;
        this.text = text;
        this.messageUrl = messageUrl;
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getMessageUrl() {
        return messageUrl;
    }
}
