package com.leo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        //创建一个线程组
        EventLoopGroup group = new NioEventLoopGroup();
        //创建客户端启动助手，完成相关配置
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group)//谁知线程组
                .channel(NioSocketChannel.class)//配置客户端通道的实现类
                .handler(new ChannelInitializer<SocketChannel>() {//创建一个通道初始化对象
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyClientHandler());//往pipeline中添加自定义handler
                    }
                });

        System.out.println("...Client is ready...");
        //启动客户端去连接服务器，异步非阻塞，connect是异步的，它会立马返回一个future对象，sync同步阻塞等待主线程
        ChannelFuture sync = bootstrap.connect("127.0.0.1", 9999).sync();
        sync.channel().closeFuture().sync();//关闭连接，异步非阻塞
    }
}
