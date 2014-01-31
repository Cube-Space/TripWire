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
public class TabCompleteResponse extends DefinedPacket {
    private String[] commands;

    @Override
    public void read(ByteBuf buf) {
        commands = readStringArray(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        writeStringArray(commands, buf);
    }
}
