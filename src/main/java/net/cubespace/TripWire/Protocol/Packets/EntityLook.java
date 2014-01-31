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
public class EntityLook extends DefinedPacket {
    private int entityId;
    private byte yaw;
    private byte pitch;

    @Override
    public void read(ByteBuf buf) {
        entityId = buf.readInt();
        yaw = buf.readByte();
        pitch = buf.readByte();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeByte(yaw);
        buf.writeByte(pitch);
    }
}

