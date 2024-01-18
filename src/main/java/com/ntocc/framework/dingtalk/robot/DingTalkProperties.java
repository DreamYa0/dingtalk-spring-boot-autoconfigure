package com.ntocc.framework.dingtalk.robot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dreamyao
 * @title
 * @date 2019-05-23 14:29
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "dingtalk.webhook")
public class DingTalkProperties {

    private String url;
    private Boolean enabled = true;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
