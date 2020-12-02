package com.icore.winvaz.javase.basic.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * @Author wdq
 * @Create 2019-04-08 15:10
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        // 1.创建客户端引导类/辅助类
        Bootstrap bootstrap = new Bootstrap();
        // 2.创建一个NioEventLoopGroup对象实例
        NioEventLoopGroup group = new NioEventLoopGroup();

        // 3.指定线程组
        bootstrap.group(group)
                // 4.指定线程模型
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });
        // 5.尝试建立连接
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8000).sync();
        // 6.等待连接关闭(阻塞，直到Channel关闭
        // channelFuture.channel().closeFuture().sync();
        while (true) {
            channelFuture.channel().writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }
}
