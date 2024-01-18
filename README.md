# 钉钉机器人SDK

### 依赖

```xml
<dependency>
    <groupId>com.g7.framework</groupId>
    <artifactId>dingtalk-spring-boot-autoconfigure</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### Quick Start

apollo配置中心配置
dingtalk.webhook.url=http://xxxxxxxxxxxx

* 注入DingtalkRobot

```java
@Autowired
private DingtalkRobot robot;

 @Test
public void testSendMarkdownMessage() {
    robot.markdown("xxx", Lists.newArrayList(
            "xxx : xxx",
            "xxx : 2300",
            "xxx（5分钟）: 32%",
            "xxx : 0.35",
            "xxx : 2018-12-17 18:56:14"
    ));
}
```

本SDK支持:

* 文本消息 (text)
* MARKDOWN消息 (markdown)
* 链接消息 (link)


更多细节可以参考钉钉官方文档
```
https://open-doc.dingtalk.com/docs/doc.htm?treeId=257&articleId=105735&docType=1
```

