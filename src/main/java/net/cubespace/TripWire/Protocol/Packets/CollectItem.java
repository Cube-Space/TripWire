package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CollectItem extends DefinedPacket {
    private int collectedEntityId;
    private int collectorEntityId;

    @Override
    public void read(ByteBuf buf) {
        collectedEntityId = buf.readInt();
        collectorEntityId = buf.readInt();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(collectedEntityId);
        buf.writeInt(collectorEntityId);
    }
}
