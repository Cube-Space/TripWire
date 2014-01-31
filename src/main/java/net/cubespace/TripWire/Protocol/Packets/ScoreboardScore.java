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
public class ScoreboardScore extends DefinedPacket {
    private String itemName;
    private byte action;
    private String scoreName;
    private int value;

    @Override
    public void read(ByteBuf buf, Protocol.ProtocolDirection direction) {
        itemName = readString(buf);
        action = buf.readByte();
        if (action != 1) {
            scoreName = readString(buf);
            value = readVarInt(buf);
        }
    }

    @Override
    public void write(ByteBuf buf, Protocol.ProtocolDirection direction) {
        writeString(itemName, buf);
        buf.writeByte(action);
        if (action != 1) {
            writeString(scoreName, buf);
            writeVarInt(value, buf);
        }
    }
}
