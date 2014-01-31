package net.cubespace.TripWire.Protocol.Packets.SubPackets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.DefinedPacket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EntityProperty extends DefinedPacket {
    private String key;
    private double value;
    private short listLength;
    private List<ModifierData> modifierData = new ArrayList<>();

    @Override
    public void read(ByteBuf buf) {
        key = readString(buf);
        value = buf.readDouble();
        listLength = buf.readShort();

        if(listLength > 0) {
            for(int i = 0; i < listLength; i++) {
                ModifierData modifierData1 = new ModifierData();
                modifierData1.read(buf);
                modifierData.add(modifierData1);
            }
        }
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(key, buf);
        buf.writeDouble(value);
        buf.writeShort(modifierData.size());

        if(modifierData.size() > 0) {
            for(ModifierData entry : modifierData) {
                entry.write(buf);
            }
        }
    }
}
