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
public class PluginMessage extends DefinedPacket {
    private String tag;
    private byte[] data;

    @Override
    public void read(ByteBuf buf) {
        tag = readString(buf);
        data = readArray(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(tag, buf);
        writeArray(data, buf);
    }
}
