package net.cubespace.TripWire.Protocol.Packets.SubPackets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.DefinedPacket;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MetaData extends DefinedPacket {
    private MetadataEntry[] metadata = new MetadataEntry[25];

    public void read(ByteBuf buf) {
        do {
            int item = buf.readUnsignedByte();
            if (item == 0x7F) break;
            int index = item & 0x1F;
            int type = item >> 5;

            switch(type) {
                case 0:
                    metadata[index] = new MetadataEntry(type, index, buf.readByte());
                    break;
                case 1:
                    metadata[index] = new MetadataEntry(type, index, buf.readShort());
                    break;
                case 2:
                    metadata[index] = new MetadataEntry(type, index, buf.readInt());
                    break;
                case 3:
                    metadata[index] = new MetadataEntry(type, index, buf.readFloat());
                    break;
                case 4:
                    metadata[index] = new MetadataEntry(type, index, readString(buf));
                    break;
                case 5:
                    Slot newSlot = new Slot();
                    newSlot.read(buf);
                    metadata[index] = new MetadataEntry(type, index, newSlot);
                    break;
                case 6:
                    Vector vector = new Vector();
                    vector.read(buf);
                    metadata[index] = new MetadataEntry(type, index, vector);
                    break;
            }
        } while (true);
    }

    public void write(ByteBuf buf) {
        for(int i = 0; i < metadata.length; i++) {
            if(metadata[i] == null) continue;

            MetadataEntry entry = metadata[i];
            int ix = (entry.getType() << 5 | entry.getIndex() & 31) & 255;
            buf.writeByte(ix);

            switch(entry.getType()) {
                case 0:
                    buf.writeByte((byte) entry.getValue());
                    break;
                case 1:
                    buf.writeShort((short) entry.getValue());
                    break;
                case 2:
                    buf.writeInt((int) entry.getValue());
                    break;
                case 3:
                    buf.writeFloat((float) entry.getValue());
                    break;
                case 4:
                    writeString((String) entry.getValue(), buf);
                    break;
                case 5:
                    ((Slot) entry.getValue()).write(buf);
                    break;
                case 6:
                    ((Vector) entry.getValue()).write(buf);
                    break;
            }
        }

        buf.writeByte(127);
    }
}
