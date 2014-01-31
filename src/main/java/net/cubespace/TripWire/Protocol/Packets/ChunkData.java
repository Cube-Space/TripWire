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
public class ChunkData extends DefinedPacket {
    private int chunkX;
    private int chunkZ;
    private boolean groundUp;
    private int primaryBitMap;
    private int addBitMap;
    private int sizeOfBytes;
    private byte[] chunkData;

    @Override
    public void read(ByteBuf buf) {
        chunkX = buf.readInt();
        chunkZ = buf.readInt();
        groundUp = buf.readBoolean();
        primaryBitMap = buf.readUnsignedShort();
        addBitMap = buf.readUnsignedShort();
        sizeOfBytes = buf.readInt();
        chunkData = buf.readBytes(buf.readableBytes()).array();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(chunkX);
        buf.writeInt(chunkZ);
        buf.writeBoolean(groundUp);
        buf.writeShort(primaryBitMap);
        buf.writeShort(addBitMap);
        buf.writeInt(sizeOfBytes);
        buf.writeBytes(chunkData);
    }
}

