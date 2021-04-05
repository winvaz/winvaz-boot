package com.icore.winvaz.javase.basic.socket.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Author wdq
 * @Create 2019-04-08 14:42
 */
public class NettyServer {
    public static void main(String[] args) {
        // 1.创建服务端启动引导类/辅助类
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 2.boss用于接收连接，worker用于具体处理
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            serverBootstrap
                    // 3.给引导类配置两大线程组，确定了线程模型
                    .group(boss, worker)
                    // 4.指定I/O模型
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) {
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                    // 5. 可以自定义客户端消息的业务处理逻辑
                                    System.out.println(msg);
                                }
                            });
                        }
                    });
            // 6.绑定端口，调用sync()阻塞知道绑定完成。
            ChannelFuture channelFuture = serverBootstrap.bind(8000).sync();
            // 7.阻塞等待直到服务器Channel关闭(closeFuture()方法获取Channel 的CloseFuture对象,然后调用sync()方法)
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 8.关闭资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
