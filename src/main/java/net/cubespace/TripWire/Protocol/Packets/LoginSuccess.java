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
public class LoginSuccess extends DefinedPacket {
    private String uuid;
    private String username;

    @Override
    public void read(ByteBuf buf) {
        uuid = readString(buf);
        username = readString(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(uuid, buf);
        writeString(username, buf);
    }
}
