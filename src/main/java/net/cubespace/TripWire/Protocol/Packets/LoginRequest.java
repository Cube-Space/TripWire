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
public class LoginRequest extends DefinedPacket {
    private String data;

    @Override
    public void read(ByteBuf buf) {
        data = readString(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(data, buf);
    }
}
