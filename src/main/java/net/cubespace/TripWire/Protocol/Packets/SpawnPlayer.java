package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.SubPackets.MetaData;
import net.cubespace.TripWire.Protocol.Packets.SubPackets.Vector;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpawnPlayer extends DefinedPacket {
    private int entityId;
    private String playerUUID;
    private String playerName;
    private Vector vector;
    private byte yaw;
    private byte pitch;
    private short item;
    private MetaData metaData;

    @Override
    public void read(ByteBuf buf) {
        entityId = readVarInt(buf);
        playerUUID = readString(buf);
        playerName = readString(buf);

        vector = new Vector();
        vector.read(buf);

        yaw = buf.readByte();
        pitch = buf.readByte();
        item = buf.readShort();

        metaData = new MetaData();
        metaData.read(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        writeVarInt(entityId, buf);
        writeString(playerUUID, buf);
        writeString(playerName, buf);

        vector.write(buf);

        buf.writeByte(yaw);
        buf.writeByte(pitch);
        buf.writeShort(item);

        metaData.write(buf);
    }
}

