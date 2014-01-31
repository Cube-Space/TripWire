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
public class TimeUpdate extends DefinedPacket {
    private long ticks;
    private long timeOfDay;

    @Override
    public void read(ByteBuf buf) {
        ticks = buf.readLong();
        timeOfDay = buf.readLong();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeLong(ticks);
        buf.writeLong(timeOfDay);
    }
}

