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
public class UseBed extends DefinedPacket {
    private int entityId;
    private int x;
    private short y;
    private int z;

    @Override
    public void read(ByteBuf buf) {
        entityId = buf.readInt();
        x = buf.readInt();
        y = buf.readUnsignedByte();
        z = buf.readInt();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeShort(y);
        buf.writeByte(z);
    }
}

