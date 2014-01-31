package net.cubespace.TripWire;

import io.netty.channel.Channel;
import net.cubespace.TripWire.Injector.InitInjector;
import net.cubespace.TripWire.Listener.LoginListener;
import net.cubespace.TripWire.Reflection.BungeeReflection;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class TripWirePlugin extends Plugin {
    private static TripWirePlugin instance;
    private static int buildNumber;

    public void onEnable() {
        instance = this;

        try {
            String[] split = getProxy().getVersion().split("-");
            buildNumber = Integer.parseInt(split[split.length - 1]);
        } catch (NumberFormatException e) {
            String[] split = getProxy().getVersion().split(":");
            buildNumber = Integer.parseInt(split[split.length - 1]);
        }

        getLogger().info("Running on BungeeCord Build #" + buildNumber);

        getProxy().getScheduler().schedule(this, new Runnable() {
            @Override
            public void run() {
                if (BungeeReflection.isNettyEnabled()) {
                    try {
                        Collection<Channel> listeners = BungeeReflection.getListeners();

                        for (Channel listener : listeners) {
                            InitInjector.inject(listener);
                        }
                    } catch (Exception e) {
                        getLogger().severe("Could not inject ChannelInitializer");
                    }
                }
            }
        }, 500, TimeUnit.MILLISECONDS);

        getProxy().getPluginManager().registerListener(this, new LoginListener());
    }

    public static TripWirePlugin getInstance() {
        return instance;
    }

    public static int getBuildNumber() {
        return buildNumber;
    }
}
