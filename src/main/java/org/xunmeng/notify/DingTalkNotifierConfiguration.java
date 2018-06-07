package org.xunmeng.notify;

import de.codecentric.boot.admin.config.NotifierConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
        prefix = "spring.boot.admin.notify.dingtalk",
        name = {"webhook-token"}
)
@AutoConfigureBefore({NotifierConfiguration.NotifierListenerConfiguration.class, NotifierConfiguration.CompositeNotifierConfiguration.class})
public class DingTalkNotifierConfiguration {
    public DingTalkNotifierConfiguration() {
    }

    @Value("${spring.boot.admin.notify.dingtalk.webhook-token}")
    private String webHookToken;

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = "spring.boot.admin.notify.dingtalk")
    public DingTalkStatusChangeNotifier dingTalkNotifier() {
        return new DingTalkStatusChangeNotifier(webHookToken);
    }
}
