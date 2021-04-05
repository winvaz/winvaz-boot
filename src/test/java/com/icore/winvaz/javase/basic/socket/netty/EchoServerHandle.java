package com.icore.winvaz.javase.basic.socket.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2021/3/29 17:47
 * @Version 1.0.0
 */
// 标记一个ChannelHandler可以被多个Channel安全地共享
@ChannelHandler.Sharable
public class EchoServerHandle extends ChannelInboundHandlerAdapter {

    /**
     * 对于每个传入的消息都要调用
     *
     * @param ctx
     * @param msg
     * @throws
     * @author wdq
     * @create 2021/3/29 17:50
     * @Return void
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 将消息记录到控制台
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("Server received：" + byteBuf.toString(StandardCharsets.UTF_8));
        // 将接受到的消息写给发送者，不冲刷出站信息。
        ctx.write(byteBuf);
    }

    /**
     * 通知ChannelInboundHandler最后一次对channelRead()的调用时当前批量读取的最后一天消息。
     *
     * @param ctx
     * @throws
     * @author wdq
     * @create 2021/3/29 17:57
     * @Return void
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        // 将未决消息(暂存于ChannelOutboundBuffer中的消息，在下一次调用flush()或writeAndFlush()方法时将会尝试写出套接字)冲刷到远程节点，并且关闭Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }
}
