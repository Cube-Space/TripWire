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
public class SpawnObject extends DefinedPacket {
    private int entityId;
    private byte type;
    private Vector vector;
    private byte pitch;
    private byte yaw;
    private int additionalData;
    private short veloX;
    private short veloY;
    private short veloZ;

    @Override
    public void read(ByteBuf buf) {
        entityId = readVarInt(buf);
        type = buf.readByte();
        vector = new Vector();
        vector.read(buf);
        pitch = buf.readByte();
        yaw = buf.readByte();
        additionalData = buf.readInt();

        if(additionalData > 0) {
            veloX = buf.readShort();
            veloY = buf.readShort();
            veloZ = buf.readShort();
        }
    }

    @Override
    public void write(ByteBuf buf) {
        writeVarInt(entityId, buf);
        buf.writeByte(type);
        vector.write(buf);
        buf.writeByte(pitch);
        buf.writeByte(yaw);
        buf.writeInt(additionalData);

        if(additionalData > 0) {
            buf.writeShort(veloX);
            buf.writeShort(veloY);
            buf.writeShort(veloZ);
        }
    }
}

