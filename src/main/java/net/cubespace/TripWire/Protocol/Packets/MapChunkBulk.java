package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.SubPackets.ChunkMetaData;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MapChunkBulk extends DefinedPacket {
    private short amountChunks;
    private int sizeOfBytes;
    private boolean updateLight;
    private byte[] chunkData;
    private List<ChunkMetaData> metaData = new ArrayList<>();

    @Override
    public void read(ByteBuf buf) {
        amountChunks = buf.readShort();
        sizeOfBytes = buf.readInt();
        updateLight = buf.readBoolean();
        chunkData = buf.readBytes(sizeOfBytes).array();

        for(int i = 0; i < amountChunks; i++) {
            ChunkMetaData metaData1 = new ChunkMetaData();
            metaData1.read(buf);
            metaData.add(metaData1);
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(metaData.size());
        buf.writeInt(sizeOfBytes);
        buf.writeBoolean(updateLight);
        buf.writeBytes(chunkData);

        for(ChunkMetaData entry : metaData) {
            entry.write(buf);
        }
    }
}

