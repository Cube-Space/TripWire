package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.SubPackets.Vector;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EntityTeleport extends DefinedPacket {
    private int entityId;
    private Vector vector;
    private byte yaw;
    private byte pitch;

    @Override
    public void read(ByteBuf buf) {
        entityId = buf.readInt();
        vector = new Vector();
        vector.read(buf);
        yaw = buf.readByte();
        pitch = buf.readByte();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
        vector.write(buf);
        buf.writeByte(yaw);
        buf.writeByte(pitch);
    }
}

