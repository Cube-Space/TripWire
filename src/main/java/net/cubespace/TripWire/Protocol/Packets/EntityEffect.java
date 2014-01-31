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
public class EntityEffect extends DefinedPacket {
    private int entityId;
    private byte effectId;
    private byte amplifier;
    private short duration;

    @Override
    public void read(ByteBuf buf) {
        entityId = buf.readInt();
        effectId = buf.readByte();
        amplifier = buf.readByte();
        duration = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeByte(effectId);
        buf.writeByte(amplifier);
        buf.writeShort(duration);
    }
}

