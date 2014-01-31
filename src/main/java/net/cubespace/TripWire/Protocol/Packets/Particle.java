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
public class Particle extends DefinedPacket {
    private String particleName;
    private float x;
    private float y;
    private float z;
    private float offsetX;
    private float offsetY;
    private float offsetZ;
    private float data;
    private int amount;

    @Override
    public void read(ByteBuf buf) {
        particleName = readString(buf);
        x = buf.readFloat();
        y = buf.readFloat();
        z = buf.readFloat();
        offsetX = buf.readFloat();
        offsetY = buf.readFloat();
        offsetZ = buf.readFloat();
        data = buf.readFloat();
        amount = buf.readInt();
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(particleName, buf);
        buf.writeFloat(x);
        buf.writeFloat(y);
        buf.writeFloat(z);
        buf.writeFloat(offsetX);
        buf.writeFloat(offsetY);
        buf.writeFloat(offsetZ);
        buf.writeFloat(data);
        buf.writeInt(amount);
    }
}

