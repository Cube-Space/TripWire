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
public class AttachEntity extends DefinedPacket {
    private int entityId;
    private int vehicleEntityId;
    private boolean leash;

    @Override
    public void read(ByteBuf buf) {
        entityId = buf.readInt();
        vehicleEntityId = buf.readInt();
        leash = buf.readBoolean();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeInt(vehicleEntityId);
        buf.writeBoolean(leash);
    }
}

