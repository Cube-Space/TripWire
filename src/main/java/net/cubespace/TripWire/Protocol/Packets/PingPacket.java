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
public class PingPacket extends DefinedPacket {
    private long time;

    @Override
    public void read(ByteBuf buf) {
        time = buf.readLong();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeLong(time);
    }
}
