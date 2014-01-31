package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.SubPackets.MetaData;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpawnMob extends DefinedPacket {
    private int entityId;
    private int mobId;
    private int x;
    private int y;
    private int z;
    private byte pitch;
    private byte headPitch;
    private byte yaw;
    private short veloX;
    private short veloY;
    private short veloZ;
    private MetaData metaData;

    @Override
    public void read(ByteBuf buf) {
        entityId = readVarInt(buf);
        mobId = buf.readUnsignedByte();
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        pitch = buf.readByte();
        headPitch = buf.readByte();
        yaw = buf.readByte();
        veloX = buf.readShort();
        veloY = buf.readShort();
        veloZ = buf.readShort();

        metaData = new MetaData();
        metaData.read(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        writeVarInt(entityId, buf);
        buf.writeByte(mobId);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(pitch);
        buf.writeByte(headPitch);
        buf.writeByte(yaw);
        buf.writeShort(veloX);
        buf.writeShort(veloY);
        buf.writeShort(veloZ);

        metaData.write(buf);
    }
}

