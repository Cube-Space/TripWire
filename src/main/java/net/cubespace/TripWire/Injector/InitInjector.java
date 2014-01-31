package net.cubespace.TripWire.Injector;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import net.cubespace.TripWire.TripWirePlugin;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class InitInjector {
    // Handle connected channels
    final static ChannelInboundHandler endInitProtocol = new ChannelInitializer<Channel>() {
        @Override
        protected void initChannel(Channel channel) throws Exception {
            TripWirePlugin.getInstance().getLogger().info("New Client connected");
            new InjectedClient("Upstream", channel);
        }
    };

    // This is executed before Minecraft's channel handler
    final static ChannelInboundHandler beginInitProtocol = new ChannelInitializer<Channel>() {
        @Override
        protected void initChannel(Channel channel) throws Exception {
            // Our only job is to add init protocol
            channel.pipeline().addLast(endInitProtocol);
        }
    };

    // Add our handler to newly created channels
    final static ChannelHandler connectionHandler = new ChannelInboundHandlerAdapter() {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            Channel channel = (Channel) msg;

            // Prepare to initialize ths channel
            channel.pipeline().addFirst(beginInitProtocol);
            ctx.fireChannelRead(msg);
        }
    };

    public static void inject(Channel channel) {
        channel.pipeline().addFirst(connectionHandler);
    }
}
