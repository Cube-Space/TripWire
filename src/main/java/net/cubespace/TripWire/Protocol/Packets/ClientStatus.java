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
public class ClientStatus extends DefinedPacket {
    private byte payload;

    @Override
    public void read(ByteBuf buf) {
        payload = buf.readByte();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeByte(payload);
    }
}
