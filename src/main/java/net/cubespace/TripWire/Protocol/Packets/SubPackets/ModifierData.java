package net.cubespace.TripWire.Protocol.Packets.SubPackets;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.DefinedPacket;

import java.util.UUID;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
@Data
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ModifierData extends DefinedPacket {
    private UUID uuid;
    private double amount;
    private byte operation;

    @Override
    public void read(ByteBuf buf) {
        uuid = UUID.nameUUIDFromBytes(buf.readBytes(16).array());
        amount = buf.readDouble();
        operation = buf.readByte();
    }

    public void write(ByteBuf buf) {
        buf.writeBytes(uuid.toString().getBytes());
        buf.writeDouble(amount);
        buf.writeByte(operation);
    }
}
