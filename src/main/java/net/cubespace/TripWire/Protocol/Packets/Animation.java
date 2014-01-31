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
public class Animation extends DefinedPacket {
    private int entityId;
    private short animation;

    @Override
    public void read(ByteBuf buf) {
        entityId = readVarInt(buf);
        animation = buf.readUnsignedByte();
    }

    @Override
    public void write(ByteBuf buf) {
        writeVarInt(entityId, buf);
        buf.writeByte(animation);
    }
}

