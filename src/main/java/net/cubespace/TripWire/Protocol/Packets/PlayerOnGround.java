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
public class PlayerOnGround extends DefinedPacket {
    private boolean onGround;

    @Override
    public void read(ByteBuf buf) {
        onGround = buf.readBoolean();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeBoolean(onGround);
    }
}

