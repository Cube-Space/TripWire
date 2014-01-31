package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.SubPackets.Slot;
import net.cubespace.TripWire.Protocol.Packets.SubPackets.Vector;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PlayerBlockPlacement extends DefinedPacket {
    private Vector vector;
    private byte direction;
    private Slot heldItem;
    private byte cursorX;
    private byte cursorY;
    private byte cursorZ;

    @Override
    public void read(ByteBuf buf) {
        vector = new Vector();
        vector.read(buf);

        direction = buf.readByte();

        heldItem = new Slot();
        heldItem.read(buf);

        cursorX = buf.readByte();
        cursorY = buf.readByte();
        cursorZ = buf.readByte();
    }

    @Override
    public void write(ByteBuf buf) {
        vector.write(buf);

        buf.writeByte(direction);

        heldItem.write(buf);

        buf.writeByte(cursorX);
        buf.writeByte(cursorY);
        buf.writeByte(cursorZ);
    }
}

