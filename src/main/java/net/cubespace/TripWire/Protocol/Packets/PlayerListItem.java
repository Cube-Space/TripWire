package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PlayerListItem extends DefinedPacket {
    private String username;
    private boolean online;
    private short ping;

    @Override
    public void read(ByteBuf buf) {
        username = readString(buf);
        online = buf.readBoolean();
        ping = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(username, buf);
        buf.writeBoolean(online);
        buf.writeShort(ping);
    }
}
