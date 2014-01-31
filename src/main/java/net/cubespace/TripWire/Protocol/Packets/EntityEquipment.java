package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.SubPackets.Slot;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EntityEquipment extends DefinedPacket {
    private int entityId;
    private short slot;
    private Slot itemData;

    @Override
    public void read(ByteBuf buf) {
        entityId = buf.readInt();
        slot = buf.readShort();

        itemData = new Slot();
        itemData.read(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeShort(slot);
        itemData.write(buf);
    }
}

