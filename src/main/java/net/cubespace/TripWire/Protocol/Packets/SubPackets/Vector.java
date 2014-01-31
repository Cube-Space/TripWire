package net.cubespace.TripWire.Protocol.Packets.SubPackets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.DefinedPacket;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Vector extends DefinedPacket {
    private int x;
    private int y;
    private int z;

    public void read(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    public void write(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }
}
