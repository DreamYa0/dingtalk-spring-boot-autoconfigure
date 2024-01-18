package com.ntocc.framework.dingtalk.robot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ntocc.framework.dingtalk.robot.bo.At;
import com.ntocc.framework.dingtalk.robot.bo.LinkBody;
import com.ntocc.framework.dingtalk.robot.bo.LinkMsg;
import com.ntocc.framework.dingtalk.robot.bo.MarkdownBody;
import com.ntocc.framework.dingtalk.robot.bo.MarkdownMsg;
import com.ntocc.framework.dingtalk.robot.bo.MarkdownText;
import com.ntocc.framework.dingtalk.robot.bo.TextBody;
import com.ntocc.framework.dingtalk.robot.bo.TextMsg;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dreamyao
 */
public class DingtalkRobot {

    private static final Logger logger = LoggerFactory.getLogger(DingtalkRobot.class);
    private static final Gson GSON = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private DingTalkProperties dingTalkProperties;
    private OkHttpClient okHttpClient;

    public DingtalkRobot(DingTalkProperties dingTalkProperties, OkHttpClient okHttpClient) {
        this.dingTalkProperties = dingTalkProperties;
        this.okHttpClient = okHttpClient;
    }

    /**
     * 发送文本消息
     * @param content 消息内容
     */
    public void sendText(String content) {
        At at = new At(new ArrayList<>(), false);
        sendText(content, at);
    }

    /**
     * 发送文本消息
     * @param content 消息内容
     * @param at      被@人信息
     */
    public void sendText(String content, At at) {

        List<String> atMobiles = at.getAtMobiles();
        StringBuilder currentContent = new StringBuilder(content);

        for (String mobile : atMobiles) {
            currentContent.insert(0, "@" + mobile + " ");
        }

        TextBody textBody = new TextBody(currentContent.toString());
        TextMsg textMsg = new TextMsg(textBody, at);
        send(GSON.toJson(textMsg));
    }

    /**
     * 发送文本消息
     * @param content  消息内容
     * @param atMobile 被@人的手机号
     */
    public void sendText(String content, String atMobile) {
        At at = new At(atMobile);
        sendText(content, at);
    }

    /**
     * 发送代链接的消息
     * @param title      消息标题
     * @param text       消息内容。如果太长只会部分展示
     * @param messageUrl 点击消息跳转的URL
     */
    public void sendLink(String title, String text, String messageUrl) {
        sendLink(title, text, messageUrl, "");
    }

    /**
     * 发送代链接的消息
     * @param title      消息标题
     * @param text       消息内容。如果太长只会部分展示
     * @param messageUrl 点击消息跳转的URL
     * @param picUrl     图片URL
     */
    public void sendLink(String title, String text, String messageUrl, String picUrl) {
        LinkBody linkBody = new LinkBody(title, text, messageUrl, picUrl);
        LinkMsg linkMsg = new LinkMsg(linkBody);

        send(GSON.toJson(linkMsg));
    }

    /**
     * 发送 markdown 消息
     * @param title 首屏会话透出的展示内容
     * @param text  markdown格式的消息
     */
    public void sendMarkdown(String title, List<String> text) {
        At at = new At(new ArrayList<>(), false);
        sendMarkdown(title, text, at);
    }

    /**
     * 发送 markdown 消息
     * @param title 首屏会话透出的展示内容
     * @param text  markdown格式的消息
     * @param at    被@人信息
     */
    public void sendMarkdown(String title, List<String> text, At at) {

        StringBuilder currentTitle = new StringBuilder(title);
        List<String> atMobiles = at.getAtMobiles();
        for (String mobile : atMobiles) {
            currentTitle.insert(0, "@" + mobile + " ");
        }

        MarkdownText markdownText = new MarkdownText(currentTitle.toString(), text);
        MarkdownBody markdownBody = new MarkdownBody(title, markdownText);
        MarkdownMsg markdownMsg = new MarkdownMsg(markdownBody, at);
        send(GSON.toJson(markdownMsg));
    }

    /**
     * 发送 markdown 消息
     * @param title    首屏会话透出的展示内容
     * @param text     markdown格式的消息
     * @param atMobile 被@人的手机号
     */
    public void sendMarkdown(String title, List<String> text, String atMobile) {
        At at = new At(atMobile);
        sendMarkdown(title, text, at);
    }

    /**
     * 发送 markdown 消息
     * @param title 首屏会话透出的展示内容
     * @param text  markdown格式的消息
     */
    public void sendMarkdown(String title, String text) {
        At at = new At(new ArrayList<>(), false);
        sendMarkdown(title, text, at);
    }

    /**
     * 发送 markdown 消息
     * @param title 首屏会话透出的展示内容
     * @param text  markdown格式的消息
     * @param at    被@人信息
     */
    public void sendMarkdown(String title, String text, At at) {

        MarkdownBody markdownBody = new MarkdownBody(title, text);
        MarkdownMsg markdownMsg = new MarkdownMsg(markdownBody, at);
        send(GSON.toJson(markdownMsg));
    }

    private void send(String payload) {

        if (dingTalkProperties.getEnabled()) {

            Request.Builder builder = new Request.Builder();
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), payload);
            Request request = builder.url(dingTalkProperties.getUrl()).post(requestBody).build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    logger.info("send message to ding talk failed, message is {}", payload, e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                }
            });

        }
    }
}
