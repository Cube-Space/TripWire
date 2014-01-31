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
public class MultiBlockChange extends DefinedPacket {
    private int chunkX;
    private int chunkZ;
    private short affectedBlocks;
    private int dataSize;
    private byte[] changeData;

    @Override
    public void read(ByteBuf buf) {
        chunkX = buf.readInt();
        chunkZ = buf.readInt();
        affectedBlocks = buf.readShort();
        dataSize = buf.readInt();
        changeData = buf.readBytes(dataSize).array();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(chunkX);
        buf.writeInt(chunkZ);
        buf.writeShort(affectedBlocks);
        buf.writeInt(changeData.length);
        buf.writeBytes(changeData);
    }
}

