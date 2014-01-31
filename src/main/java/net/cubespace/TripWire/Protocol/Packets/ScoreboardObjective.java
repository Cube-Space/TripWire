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
public class ScoreboardObjective extends DefinedPacket {
    private String name;
    private String text;
    private byte action;

    @Override
    public void read(ByteBuf buf) {
        name = readString(buf);
        text = readString(buf);
        action = buf.readByte();
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(name, buf);
        writeString(text, buf);
        buf.writeByte(action);
    }
}
