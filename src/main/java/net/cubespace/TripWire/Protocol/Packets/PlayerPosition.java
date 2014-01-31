package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Protocol;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PlayerPosition extends DefinedPacket {
    private double x;
    private double y;
    private double headY;
    private double feetY;
    private double z;
    private boolean onGround;

    @Override
    public void read(ByteBuf buf, Protocol.ProtocolDirection direction) {
        x = buf.readDouble();

        if(direction.toString().equals("TO_SERVER")) {
            feetY = buf.readDouble();
            headY = buf.readDouble();
        } else {
            y = buf.readDouble();
        }

        z = buf.readDouble();
        onGround = buf.readBoolean();
    }

    @Override
    public void write(ByteBuf buf, Protocol.ProtocolDirection direction) {
        buf.writeDouble(x);

        if(direction.toString().equals("TO_SERVER")) {
            buf.writeDouble(feetY);
            buf.writeDouble(headY);
        } else {
            buf.writeDouble(y);
        }

        buf.writeDouble(z);
        buf.writeBoolean(onGround);
    }
}

