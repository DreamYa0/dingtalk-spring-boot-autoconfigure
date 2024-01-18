package com.ntocc.framework.dingtalk.robot;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.lang.Nullable;

/**
 * @author dreamyao
 */
public class DingtalkRobotFactoryBean extends AbstractFactoryBean<DingtalkRobot> {

    private DingTalkProperties dingTalkProperties;
    private OkHttpClient okHttpClient;

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return DingtalkRobot.class;
    }

    @Override
    protected DingtalkRobot createInstance() throws Exception {
        return new DingtalkRobot(dingTalkProperties, okHttpClient);
    }

    public DingTalkProperties getDingTalkProperties() {
        return dingTalkProperties;
    }

    public void setDingTalkProperties(DingTalkProperties dingTalkProperties) {
        this.dingTalkProperties = dingTalkProperties;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }
}
