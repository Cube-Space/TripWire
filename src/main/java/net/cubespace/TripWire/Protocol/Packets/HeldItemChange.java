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
public class HeldItemChange extends DefinedPacket {
    private byte slot;

    @Override
    public void read(ByteBuf buf) {
        slot = buf.readByte();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeByte(slot);
    }
}

