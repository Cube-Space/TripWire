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
public class EntityEquipment extends DefinedPacket {
    private int entityId;
    private short slot;
    private byte[] itemData;

    @Override
    public void read(ByteBuf buf) {
        entityId = buf.readInt();
        slot = buf.readShort();
        itemData = buf.readBytes(buf.readableBytes()).array();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeShort(slot);
        buf.writeBytes(itemData);
    }
}

