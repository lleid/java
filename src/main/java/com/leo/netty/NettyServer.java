package com.leo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //创建一个线程组，接收客户端请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //创建一个线程组，处理网络请求
        EventLoopGroup workGroup = new NioEventLoopGroup();

        //创建服务器启动助手来复制参数
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)//设置两个线程组
                .channel(NioServerSocketChannel.class)//使用NioServerSocketChannel作为服务器端通过的实现
                .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列中等待连接的个数
                .childOption(ChannelOption.SO_KEEPALIVE, true)//保持连接活跃
                .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道初始化对象
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyServerHandler());//往pipeline添加自定义的handler类
                    }
                });
        System.out.println("...Server ready...");
        ChannelFuture sync = serverBootstrap.bind(9999).sync();//绑定端口，绑定方法是异步的，synv同步阻塞
        System.out.println("...Server start...");

        sync.channel().closeFuture().sync();//关闭通道
        bossGroup.shutdownGracefully();//关闭线程组
        workGroup.shutdownGracefully();//关闭线程组
    }
}
