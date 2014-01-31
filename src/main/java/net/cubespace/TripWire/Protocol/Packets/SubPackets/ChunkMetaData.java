package net.cubespace.TripWire.Protocol.Packets.SubPackets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.DefinedPacket;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ChunkMetaData extends DefinedPacket {
    private int chunkX;
    private int chunkZ;
    private int primaryBitMap;
    private int addBitMap;

    @Override
    public void read(ByteBuf buf) {
        chunkX = buf.readInt();
        chunkZ = buf.readInt();
        primaryBitMap = buf.readUnsignedShort();
        addBitMap = buf.readUnsignedShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(chunkX);
        buf.writeInt(chunkZ);
        buf.writeShort(primaryBitMap);
        buf.writeShort(addBitMap);
    }
}
