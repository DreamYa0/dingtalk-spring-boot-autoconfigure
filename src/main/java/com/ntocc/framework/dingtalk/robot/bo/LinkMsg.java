package com.ntocc.framework.dingtalk.robot.bo;


/**
 * @author dreamyao
 */
public class LinkMsg {

    private String msgtype = "link";
    private LinkBody link;

    public LinkMsg(LinkBody link) {
        this.link = link;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public LinkBody getLink() {
        return link;
    }
}
