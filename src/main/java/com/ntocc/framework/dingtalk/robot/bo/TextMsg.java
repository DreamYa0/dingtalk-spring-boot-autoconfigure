package com.ntocc.framework.dingtalk.robot.bo;


/**
 * @author dreamyao
 */
public class TextMsg {

    private String msgtype = "text";
    private TextBody text;
    private At at;

    public TextMsg(TextBody text, At at) {
        this.text = text;
        this.at = at;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public TextBody getText() {
        return text;
    }

    public At getAt() {
        return at;
    }
}
