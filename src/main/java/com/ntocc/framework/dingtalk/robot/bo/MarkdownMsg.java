package com.ntocc.framework.dingtalk.robot.bo;


/**
 * @author dreamyao
 */
public class MarkdownMsg {

    private String msgtype = "markdown";
    private MarkdownBody markdown;
    private At at;


    public MarkdownMsg(MarkdownBody markdown, At at) {
        this.markdown = markdown;
        this.at = at;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public MarkdownBody getMarkdown() {
        return markdown;
    }

    public At getAt() {
        return at;
    }
}
