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
public class EntityVelocity extends DefinedPacket {
    private int entityId;
    private short veloX;
    private short veloY;
    private short veloZ;

    @Override
    public void read(ByteBuf buf) {
        entityId = buf.readInt();
        veloX = buf.readShort();
        veloY = buf.readShort();
        veloZ = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeShort(veloX);
        buf.writeShort(veloY);
        buf.writeShort(veloZ);
    }
}

