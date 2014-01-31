package net.cubespace.TripWire.Listener;

import io.netty.channel.Channel;
import net.cubespace.TripWire.Injector.InjectedClient;
import net.cubespace.TripWire.TripWirePlugin;
import net.md_5.bungee.ServerConnection;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.netty.ChannelWrapper;

import java.lang.reflect.Field;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class LoginListener implements Listener {
    private Field channelField;
    private Field serverConnectionField;

    public LoginListener() {
        try {
            channelField = ServerConnection.class.getDeclaredField("ch");
            channelField.setAccessible(true);

            serverConnectionField = UserConnection.class.getDeclaredField("server");
            serverConnectionField.setAccessible(true);
        } catch (NoSuchFieldException e) {

        }
    }

    @EventHandler
    public void onLogin(final ServerConnectEvent event) {
        TripWirePlugin.getInstance().getProxy().getScheduler().runAsync(TripWirePlugin.getInstance(), new Runnable() {
            @Override
            public void run() {
                Channel channel = null;
                while(channel == null) {
                    try {
                        ServerConnection serverConnection = (ServerConnection) serverConnectionField.get(event.getPlayer());
                        if(serverConnection == null) continue;

                        channel = ((ChannelWrapper) channelField.get(serverConnection)).getHandle();
                    } catch (IllegalAccessException e) { }

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {

                    }
                }

                new InjectedClient("DownStream", channel);
                TripWirePlugin.getInstance().getLogger().info(channel.toString());
            }
        });
    }
}
