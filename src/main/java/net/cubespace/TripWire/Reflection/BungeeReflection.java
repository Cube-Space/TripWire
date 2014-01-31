package net.cubespace.TripWire.Reflection;

import io.netty.channel.Channel;
import net.md_5.bungee.BungeeCord;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class BungeeReflection {
    private static boolean isNetty = false;
    private static Collection<Channel> listeners;

    static {
        BungeeCord bungeeCord = BungeeCord.getInstance();

        try {
            Field field = bungeeCord.getClass().getDeclaredField("listeners");
            field.setAccessible(true);

            listeners = (Collection<Channel>) field.get(bungeeCord);

            isNetty = true;
        } catch (NoSuchFieldException|IllegalAccessException e) {
            bungeeCord.getLogger().warning("Could not find Netty Listeners");
        }
    }

    public static Collection<Channel> getListeners() {
        return listeners;
    }

    public static Boolean isNettyEnabled() {
        return isNetty;
    }
}
