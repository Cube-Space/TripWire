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
public class Kick extends DefinedPacket {
    private String message;

    @Override
    public void read(ByteBuf buf) {
        message = readString(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(message, buf);
    }
}
