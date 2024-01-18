package com.ntocc.framework.dingtalk.robot.bo;

/**
 * @author dreamyao
 */
public class MarkdownBody {

    private String title;
    private String text;

    public MarkdownBody(String title, MarkdownText text) {
        this.title = title;
        this.text = text.toString();
    }

    public MarkdownBody(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
