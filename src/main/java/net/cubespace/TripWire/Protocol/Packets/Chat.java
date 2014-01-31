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
public class Chat extends DefinedPacket {
    private String message;

    @Override
    public void read(ByteBuf buf, Protocol.ProtocolDirection direction) {
        message = readString(buf);
    }

    @Override
    public void write(ByteBuf buf, Protocol.ProtocolDirection direction) {
        writeString(message, buf);
    }
}
