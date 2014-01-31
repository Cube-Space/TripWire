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
public class Entity extends DefinedPacket {
    private int entityId;

    @Override
    public void read(ByteBuf buf) {
        entityId = buf.readInt();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
    }
}

