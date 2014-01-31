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
public class StatusResponse extends DefinedPacket {
    private String response;

    @Override
    public void read(ByteBuf buf) {
        response = readString(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(response, buf);
    }
}
