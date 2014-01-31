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
public class SetExp extends DefinedPacket {
    private float barPercentage;
    private short level;
    private short totalExp;

    @Override
    public void read(ByteBuf buf) {
        barPercentage = buf.readFloat();
        level = buf.readShort();
        totalExp = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeFloat(barPercentage);
        buf.writeShort(level);
        buf.writeShort(totalExp);
    }
}

