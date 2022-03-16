package cn.iocoder.springboot.lab67.nettyclientdemo.messagehandler.auth;

import cn.iocoder.springboot.lab67.nettyclientdemo.client.NettyChannelManager;
import cn.iocoder.springboot.lab67.nettyclientdemo.message.auth.AuthRequest;
import cn.iocoder.springboot.lab67.nettyclientdemo.message.auth.AuthResponse;
import cn.iocoder.springboot.lab67.nettycommondemo.codec.Invocation;
import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.MessageHandler;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author zhr_java@163.com
 * @date 2022/3/16 13:25
 */
@Component
public class AuthRequestHandler implements MessageHandler<AuthRequest> {

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, AuthRequest authRequest) {
        // <1> 如果未传递 accessToken
        if (StringUtils.isEmpty(authRequest.getAccessToken())) {
            AuthResponse authResponse = new AuthResponse().setCode(1).setMessage("认证 accessToken 未传入");
            channel.writeAndFlush(new Invocation(AuthResponse.TYPE, authResponse));
            return;
        }

        // <2> ... 此处应有一段

        // <3> 将用户和 Channel 绑定
        // 考虑到代码简化，我们先直接使用 accessToken 作为 User
        nettyChannelManager.addUser(channel, authRequest.getAccessToken());

        // <4> 响应认证成功
        AuthResponse authResponse = new AuthResponse().setCode(0);
        channel.writeAndFlush(new Invocation(AuthResponse.TYPE, authResponse));
    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }

}
