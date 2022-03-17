package cn.iocoder.springboot.lab67.nettyserverdemo.config;

import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.MessageDispatcher;
import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.MessageHandlerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhr
 */
@Configuration
public class NettyServerConfig {

    @Bean
    public MessageDispatcher messageDispatcher() {
        return new MessageDispatcher();
    }

    @Bean
    public MessageHandlerContainer messageHandlerContainer() {
        return new MessageHandlerContainer();
    }

}
