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
public class EntityRelativeMove extends DefinedPacket {
    private int entityId;
    private byte x;
    private byte y;
    private byte z;

    @Override
    public void read(ByteBuf buf) {
        entityId = buf.readInt();
        x = buf.readByte();
        y = buf.readByte();
        z = buf.readByte();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeByte(x);
        buf.writeByte(y);
        buf.writeByte(z);
    }
}

