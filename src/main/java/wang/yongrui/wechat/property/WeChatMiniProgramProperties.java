/**
 * 
 */
package wang.yongrui.wechat.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

/**
 * @author Wang Yongrui
 *
 */
@Configuration
@ConfigurationProperties
@PropertySource({ "classpath:weChatMiniProgram.properties" })
@Getter
public class WeChatMiniProgramProperties {

	@Value("${weChatMiniProgram.appId}")
	private String appId;

	@Value("${weChatMiniProgram.appSecret}")
	private String appSecret;

}
