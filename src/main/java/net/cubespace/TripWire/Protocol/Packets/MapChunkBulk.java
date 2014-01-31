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
public class MapChunkBulk extends DefinedPacket {
    private short amountChunks;
    private int sizeOfBytes;
    private boolean updateLight;
    private byte[] chunkData;
    private MetaData[] metaData;

    public class MetaData {
        private int chunkX;
        private int chunkZ;
        private int primaryBitMap;
        private int addBitMap;

        public void read(ByteBuf buf) {
            chunkX = buf.readInt();
            chunkZ = buf.readInt();
            primaryBitMap = buf.readUnsignedShort();
            addBitMap = buf.readUnsignedShort();
        }

        public void write(ByteBuf buf) {
            buf.writeInt(chunkX);
            buf.writeInt(chunkZ);
            buf.writeShort(primaryBitMap);
            buf.writeShort(addBitMap);
        }
    }

    @Override
    public void read(ByteBuf buf) {
        amountChunks = buf.readShort();
        sizeOfBytes = buf.readInt();
        updateLight = buf.readBoolean();
        chunkData = buf.readBytes(sizeOfBytes).array();

        metaData = new MetaData[amountChunks];
        for(int i = 0; i < amountChunks; i++) {
            MetaData metaData1 = new MetaData();
            metaData1.read(buf);
            metaData[i] = metaData1;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(amountChunks);
        buf.writeInt(sizeOfBytes);
        buf.writeBoolean(updateLight);
        buf.writeBytes(chunkData);

        for(int i = 0; i < amountChunks; i++) {
            metaData[i].write(buf);
        }
    }
}

