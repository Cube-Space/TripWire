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
public class KeepAlive extends DefinedPacket {
    private int randomId;

    @Override
    public void read(ByteBuf buf) {
        randomId = buf.readInt();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(randomId);
    }
}
