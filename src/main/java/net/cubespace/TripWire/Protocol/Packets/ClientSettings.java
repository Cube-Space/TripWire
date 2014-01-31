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
public class ClientSettings extends DefinedPacket {
    private String locale;
    private byte viewDistance;
    private byte chatFlags;
    private boolean unknown;
    private byte difficulty;
    private byte showCape;

    @Override
    public void read(ByteBuf buf) {
        locale = readString(buf);
        viewDistance = buf.readByte();
        chatFlags = buf.readByte();
        unknown = buf.readBoolean();
        difficulty = buf.readByte();
        showCape = buf.readByte();
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(locale, buf);
        buf.writeByte(viewDistance);
        buf.writeByte(chatFlags);
        buf.writeBoolean(unknown);
        buf.writeByte(difficulty);
        buf.writeByte(showCape);
    }
}
