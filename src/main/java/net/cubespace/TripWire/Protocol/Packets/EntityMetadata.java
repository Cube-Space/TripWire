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
public class EntityMetadata extends DefinedPacket {
    private int entityId;
    private MetaData metaData;

    @Override
    public void read(ByteBuf buf) {
        entityId = buf.readInt();
        metaData = new MetaData();
        metaData.read(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
        metaData.write(buf);
    }
}

