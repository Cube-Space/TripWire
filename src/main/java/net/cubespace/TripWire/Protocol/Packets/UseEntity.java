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
public class UseEntity extends DefinedPacket {
    private int targetEntityId;
    private byte mouseClick;

    @Override
    public void read(ByteBuf buf) {
        targetEntityId = buf.readInt();
        mouseClick = buf.readByte();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(targetEntityId);
        buf.writeByte(mouseClick);
    }
}

