package net.cubespace.TripWire.Protocol;

import com.google.common.base.Preconditions;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import lombok.RequiredArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.Chat;
import net.cubespace.TripWire.Protocol.Packets.ChunkData;
import net.cubespace.TripWire.Protocol.Packets.ClientSettings;
import net.cubespace.TripWire.Protocol.Packets.ClientStatus;
import net.cubespace.TripWire.Protocol.Packets.DefinedPacket;
import net.cubespace.TripWire.Protocol.Packets.EncryptionRequest;
import net.cubespace.TripWire.Protocol.Packets.EncryptionResponse;
import net.cubespace.TripWire.Protocol.Packets.Entity;
import net.cubespace.TripWire.Protocol.Packets.EntityEquipment;
import net.cubespace.TripWire.Protocol.Packets.EntityHeadLook;
import net.cubespace.TripWire.Protocol.Packets.EntityLook;
import net.cubespace.TripWire.Protocol.Packets.EntityLookAndRelativeMove;
import net.cubespace.TripWire.Protocol.Packets.EntityMetadata;
import net.cubespace.TripWire.Protocol.Packets.EntityProperties;
import net.cubespace.TripWire.Protocol.Packets.EntityRelativeMove;
import net.cubespace.TripWire.Protocol.Packets.EntityTeleport;
import net.cubespace.TripWire.Protocol.Packets.EntityVelocity;
import net.cubespace.TripWire.Protocol.Packets.Handshake;
import net.cubespace.TripWire.Protocol.Packets.HeldItemChange;
import net.cubespace.TripWire.Protocol.Packets.KeepAlive;
import net.cubespace.TripWire.Protocol.Packets.Kick;
import net.cubespace.TripWire.Protocol.Packets.Login;
import net.cubespace.TripWire.Protocol.Packets.LoginRequest;
import net.cubespace.TripWire.Protocol.Packets.LoginSuccess;
import net.cubespace.TripWire.Protocol.Packets.MapChunkBulk;
import net.cubespace.TripWire.Protocol.Packets.Particle;
import net.cubespace.TripWire.Protocol.Packets.PingPacket;
import net.cubespace.TripWire.Protocol.Packets.PlayerBlockPlacement;
import net.cubespace.TripWire.Protocol.Packets.PlayerListItem;
import net.cubespace.TripWire.Protocol.Packets.PlayerOnGround;
import net.cubespace.TripWire.Protocol.Packets.PlayerPosition;
import net.cubespace.TripWire.Protocol.Packets.PlayerPositionAndLook;
import net.cubespace.TripWire.Protocol.Packets.PluginMessage;
import net.cubespace.TripWire.Protocol.Packets.Respawn;
import net.cubespace.TripWire.Protocol.Packets.ScoreboardDisplay;
import net.cubespace.TripWire.Protocol.Packets.ScoreboardObjective;
import net.cubespace.TripWire.Protocol.Packets.ScoreboardScore;
import net.cubespace.TripWire.Protocol.Packets.SoundEffect;
import net.cubespace.TripWire.Protocol.Packets.SpawnMob;
import net.cubespace.TripWire.Protocol.Packets.SpawnPosition;
import net.cubespace.TripWire.Protocol.Packets.StatusRequest;
import net.cubespace.TripWire.Protocol.Packets.StatusResponse;
import net.cubespace.TripWire.Protocol.Packets.TabCompleteRequest;
import net.cubespace.TripWire.Protocol.Packets.TabCompleteResponse;
import net.cubespace.TripWire.Protocol.Packets.Team;
import net.cubespace.TripWire.Protocol.Packets.TimeUpdate;
import net.cubespace.TripWire.Protocol.Packets.UpdateHealth;
import net.cubespace.TripWire.Protocol.Packets.UseBed;
import net.cubespace.TripWire.Protocol.Packets.UseEntity;
import net.md_5.bungee.protocol.BadPacketException;

import java.lang.reflect.Constructor;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public enum Protocol {
    // Undef
    HANDSHAKE {
        {
            TO_SERVER.registerPacket(0x00, Handshake.class);
        }
    },
    // 0
    GAME {
        {
            TO_CLIENT.registerPacket(0x00, KeepAlive.class);
            TO_CLIENT.registerPacket(0x01, Login.class);
            TO_CLIENT.registerPacket(0x02, Chat.class);
            TO_CLIENT.registerPacket(0x03, TimeUpdate.class);
            TO_CLIENT.registerPacket(0x04, EntityEquipment.class);
            TO_CLIENT.registerPacket(0x05, SpawnPosition.class);
            TO_CLIENT.registerPacket(0x06, UpdateHealth.class);
            TO_CLIENT.registerPacket(0x07, Respawn.class);
            TO_CLIENT.registerPacket(0x08, PlayerPositionAndLook.class);
            TO_CLIENT.registerPacket(0x09, HeldItemChange.class);
            TO_CLIENT.registerPacket(0x0A, UseBed.class);
            TO_CLIENT.registerPacket(0x0F, SpawnMob.class);
            TO_CLIENT.registerPacket(0x12, EntityVelocity.class);
            TO_CLIENT.registerPacket(0x14, Entity.class);
            TO_CLIENT.registerPacket(0x15, EntityRelativeMove.class);
            TO_CLIENT.registerPacket(0x16, EntityLook.class);
            TO_CLIENT.registerPacket(0x17, EntityLookAndRelativeMove.class);
            TO_CLIENT.registerPacket(0x19, EntityHeadLook.class);
            TO_CLIENT.registerPacket(0x18, EntityTeleport.class);
            TO_CLIENT.registerPacket(0x1C, EntityMetadata.class);
            TO_CLIENT.registerPacket(0x20, EntityProperties.class);
            TO_CLIENT.registerPacket(0x21, ChunkData.class);
            TO_CLIENT.registerPacket(0x26, MapChunkBulk.class);
            TO_CLIENT.registerPacket(0x29, SoundEffect.class);
            TO_CLIENT.registerPacket(0x2A, Particle.class);
            TO_CLIENT.registerPacket(0x38, PlayerListItem.class);
            TO_CLIENT.registerPacket(0x3A, TabCompleteResponse.class);
            TO_CLIENT.registerPacket(0x3B, ScoreboardObjective.class);
            TO_CLIENT.registerPacket(0x3C, ScoreboardScore.class);
            TO_CLIENT.registerPacket(0x3D, ScoreboardDisplay.class);
            TO_CLIENT.registerPacket(0x3E, Team.class);
            TO_CLIENT.registerPacket(0x3F, PluginMessage.class);
            TO_CLIENT.registerPacket(0x40, Kick.class);

            TO_SERVER.registerPacket(0x00, KeepAlive.class);
            TO_SERVER.registerPacket(0x01, Chat.class);
            TO_SERVER.registerPacket(0x02, UseEntity.class);
            TO_SERVER.registerPacket(0x03, PlayerOnGround.class);
            TO_SERVER.registerPacket(0x04, PlayerPosition.class);
            TO_SERVER.registerPacket(0x06, PlayerPositionAndLook.class);
            TO_SERVER.registerPacket(0x08, PlayerBlockPlacement.class);
            TO_SERVER.registerPacket(0x14, TabCompleteRequest.class);
            TO_SERVER.registerPacket(0x15, ClientSettings.class);
            TO_SERVER.registerPacket(0x16, ClientStatus.class);
            TO_SERVER.registerPacket(0x17, PluginMessage.class);
        }
    },
    // 1
    STATUS {
        {
            TO_CLIENT.registerPacket(0x00, StatusResponse.class);
            TO_CLIENT.registerPacket(0x01, PingPacket.class);

            TO_SERVER.registerPacket(0x00, StatusRequest.class);
            TO_SERVER.registerPacket(0x01, PingPacket.class);
        }
    },
    //2
    LOGIN {
        {
            TO_CLIENT.registerPacket(0x00, Kick.class);
            TO_CLIENT.registerPacket(0x01, EncryptionRequest.class);
            TO_CLIENT.registerPacket(0x02, LoginSuccess.class);

            TO_SERVER.registerPacket(0x00, LoginRequest.class);
            TO_SERVER.registerPacket(0x01, EncryptionResponse.class);
        }
    };

    public static final int MAX_PACKET_ID = 0xFF;

    public final ProtocolDirection TO_SERVER = new ProtocolDirection("TO_SERVER");
    public final ProtocolDirection TO_CLIENT = new ProtocolDirection("TO_CLIENT");

    @RequiredArgsConstructor
    public class ProtocolDirection {

        private final String name;
        private final TObjectIntMap<Class<? extends DefinedPacket>> packetMap = new TObjectIntHashMap<>(MAX_PACKET_ID);
        private final Class<? extends DefinedPacket>[] packetClasses = new Class[MAX_PACKET_ID];
        private final Constructor<? extends DefinedPacket>[] packetConstructors = new Constructor[MAX_PACKET_ID];

        public boolean hasPacket(int id) {
            return id < MAX_PACKET_ID && packetConstructors[id] != null;
        }

        @Override
        public String toString() {
            return name;
        }

        public final DefinedPacket createPacket(int id) {
            if (id > MAX_PACKET_ID) {
                throw new BadPacketException("Packet with id " + id + " outside of range ");
            }
            if (packetConstructors[id] == null) {
                throw new BadPacketException("No packet with id " + id);
            }

            try {
                return packetClasses[id].newInstance();
            } catch (ReflectiveOperationException ex) {
                throw new BadPacketException("Could not construct packet with id " + id, ex);
            }
        }

        protected final void registerPacket(int id, Class<? extends DefinedPacket> packetClass) {
            try {
                packetConstructors[id] = packetClass.getDeclaredConstructor();
            } catch (NoSuchMethodException ex) {
                throw new BadPacketException("No NoArgsConstructor for packet class " + packetClass);
            }
            packetClasses[id] = packetClass;
            packetMap.put(packetClass, id);
        }

        protected final void unregisterPacket(int id) {
            packetMap.remove(packetClasses[id]);
            packetClasses[id] = null;
            packetConstructors[id] = null;
        }

        final int getId(Class<? extends DefinedPacket> packet) {
            Preconditions.checkArgument(packetMap.containsKey(packet), "Cannot get ID for packet " + packet);

            return packetMap.get(packet);
        }
    }
}
