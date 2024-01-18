package com.ntocc.framework.dingtalk.robot.bo;


import java.util.List;

/**
 * @author dreamyao
 */
public class MarkdownText {

    private String title;
    private List<String> text;

    public MarkdownText(String title, List<String> text) {
        this.title = title;
        this.text = text;
    }

    @Override
    public String toString() {
        return "#### " + title + "\n\n" + String.join("\n\n", text);
    }
}
