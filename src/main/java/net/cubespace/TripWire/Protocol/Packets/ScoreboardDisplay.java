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
public class ScoreboardDisplay extends DefinedPacket {
    private byte position;
    private String name;

    @Override
    public void read(ByteBuf buf) {
        position = buf.readByte();
        name = readString(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeByte(position);
        writeString(name, buf);
    }
}
