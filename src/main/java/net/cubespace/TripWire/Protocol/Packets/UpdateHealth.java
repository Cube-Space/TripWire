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
public class UpdateHealth extends DefinedPacket {
    private float health;
    private short food;
    private float saturation;

    @Override
    public void read(ByteBuf buf) {
        health = buf.readFloat();
        food = buf.readShort();
        saturation = buf.readFloat();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeFloat(health);
        buf.writeShort(food);
        buf.writeFloat(saturation);
    }
}

