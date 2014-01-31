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
public class SpawnPainting extends DefinedPacket {
    private int entityId;
    private String name;
    private Vector vector;
    private int direction;

    @Override
    public void read(ByteBuf buf) {
        entityId = readVarInt(buf);
        name = readString(buf);

        vector = new Vector();
        vector.read(buf);

        direction = buf.readInt();
    }

    @Override
    public void write(ByteBuf buf) {
        writeVarInt(entityId, buf);
        writeString(name, buf);

        vector.write(buf);

        buf.writeInt(direction);
    }
}

