package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Protocol;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Team extends DefinedPacket {

    private String name;
    /**
     * 0 - create, 1 remove, 2 info update, 3 player add, 4 player remove.
     */
    private byte mode;
    private String displayName;
    private String prefix;
    private String suffix;
    private boolean friendlyFire;
    private short playerCount;
    private String[] players;

    /**
     * Packet to destroy a team.
     *
     * @param name
     */
    public Team(String name) {
        this();
        this.name = name;
        this.mode = 1;
    }

    @Override
    public void read(ByteBuf buf, Protocol.ProtocolDirection direction) {
        name = readString(buf);
        mode = buf.readByte();
        if (mode == 0 || mode == 2) {
            displayName = readString(buf);
            prefix = readString(buf);
            suffix = readString(buf);
            friendlyFire = buf.readBoolean();
        }
        if (mode == 0 || mode == 3 || mode == 4) {
            int len = buf.readShort();
            players = new String[len];
            for (int i = 0; i < len; i++) {
                players[i] = readString(buf);
            }
        }
    }

    @Override
    public void write(ByteBuf buf, Protocol.ProtocolDirection direction) {
        writeString(name, buf);
        buf.writeByte(mode);
        if (mode == 0 || mode == 2) {
            writeString(displayName, buf);
            writeString(prefix, buf);
            writeString(suffix, buf);
            buf.writeBoolean(friendlyFire);
        }
        if (mode == 0 || mode == 3 || mode == 4) {
            buf.writeShort(players.length);

            for (String player : players) {
                writeString(player, buf);
            }
        }
    }
}
