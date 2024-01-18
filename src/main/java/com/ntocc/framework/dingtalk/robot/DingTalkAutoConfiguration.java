package com.ntocc.framework.dingtalk.robot;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.enums.PropertyChangeType;
import com.ctrip.framework.apollo.model.ConfigChange;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author dreamyao
 */
@Configuration
@EnableConfigurationProperties({DingTalkProperties.class})
public class DingTalkAutoConfiguration {

    private static final String DING_TALK_ENABLED_KEY = "dingtalk.webhook.enabled";
    private DingTalkProperties dingTalkProperties;

    public DingTalkAutoConfiguration(DingTalkProperties dingTalkProperties) {
        this.dingTalkProperties = dingTalkProperties;
    }

    @Bean
    @ConditionalOnMissingBean(value = DingtalkRobot.class)
    public DingtalkRobotFactoryBean dingtalkRobotFactoryBean() {
        DingtalkRobotFactoryBean factory = new DingtalkRobotFactoryBean();
        factory.setDingTalkProperties(dingTalkProperties);
        factory.setOkHttpClient(okHttpClient());
        refreshDingTalkEnabled();
        return factory;
    }

    @Bean
    @ConditionalOnClass(value = OkHttpClient.class)
    @ConditionalOnMissingBean(value = OkHttpClient.class)
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool())
                // 设置连接超时
                .connectTimeout(15, TimeUnit.SECONDS)
                // 设置读超时
                .readTimeout(20, TimeUnit.SECONDS)
                // 设置写超时
                .writeTimeout(20, TimeUnit.SECONDS)
                // 是否自动重连
                .retryOnConnectionFailure(true)
                .build();
    }

    /**
     * 刷新钉钉机器人开关
     */
    private void refreshDingTalkEnabled() {

        Config apolloConfig = ConfigService.getAppConfig();
        apolloConfig.addChangeListener(changeEvent -> {

            Set<String> changedKeys = changeEvent.changedKeys();
            for (String changeKey : changedKeys) {

                if (changeKey.contains(DING_TALK_ENABLED_KEY)) {

                    ConfigChange change = changeEvent.getChange(changeKey);
                    if (Objects.equals(change.getChangeType(), PropertyChangeType.ADDED) || Objects.equals(change.getChangeType(), PropertyChangeType.MODIFIED)) {
                        dingTalkProperties.setEnabled(Boolean.valueOf(change.getNewValue()));
                    }
                }
            }
        });
    }
}