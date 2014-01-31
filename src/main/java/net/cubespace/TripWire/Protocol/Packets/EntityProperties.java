package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.SubPackets.EntityProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EntityProperties extends DefinedPacket {
    private int entityId;
    private int lengthOfArray;
    private EntityProperty[] entityProperty;

    @Override
    public void read(ByteBuf buf) {
        entityId = buf.readInt();
        lengthOfArray = buf.readInt();

        entityProperty = new EntityProperty[lengthOfArray];
        for(int i = 0; i < lengthOfArray; i++) {
            EntityProperty entityProperty1 = new EntityProperty();
            entityProperty1.read(buf);
            entityProperty[i] = entityProperty1;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeInt(entityProperty.length);

        for(int i = 0; i < entityProperty.length; i++) {
            entityProperty[i].write(buf);
        }
    }
}

