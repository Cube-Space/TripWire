package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.SubPackets.Vector;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpawnPosition extends DefinedPacket {
    private Vector vector;

    @Override
    public void read(ByteBuf buf) {
        vector = new Vector();
        vector.read(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        vector.write(buf);
    }
}

