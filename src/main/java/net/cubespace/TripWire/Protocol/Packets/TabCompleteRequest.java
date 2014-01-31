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
public class TabCompleteRequest extends DefinedPacket {
    private String cursor;

    @Override
    public void read(ByteBuf buf) {
        cursor = readString(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(cursor, buf);
    }
}
