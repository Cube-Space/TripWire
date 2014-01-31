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
public class SpawnExpOrb extends DefinedPacket {
    private int entityId;
    private Vector vector;
    private short amount;

    @Override
    public void read(ByteBuf buf) {
        entityId = readVarInt(buf);

        vector = new Vector();
        vector.read(buf);

        amount = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        writeVarInt(entityId, buf);

        vector.write(buf);

        buf.writeShort(amount);
    }
}

