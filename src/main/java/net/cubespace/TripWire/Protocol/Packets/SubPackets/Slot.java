package net.cubespace.TripWire.Protocol.Packets.SubPackets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.DefinedPacket;
import org.jnbt.EndTag;
import org.jnbt.NBTInputStream;
import org.jnbt.NBTOutputStream;
import org.jnbt.Tag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Slot extends DefinedPacket {
    private int itemId;
    private byte itemAmount;
    private short itemDamageValue;
    private short nbtOptionalLength;
    private List<Tag> nbtOptionalData = new ArrayList<>();

    public void read(ByteBuf buf) {
        itemId = buf.readShort();
        if (itemId != -1) {
            itemAmount = buf.readByte();
            itemDamageValue = buf.readShort();
            nbtOptionalLength = buf.readShort();

            if (nbtOptionalLength != -1) {
                ByteArrayInputStream bais = new ByteArrayInputStream(buf.readBytes(nbtOptionalLength).array());

                try (NBTInputStream nbtInputStream = new NBTInputStream(bais)) {
                    Tag currentTag;
                    do {
                        currentTag = nbtInputStream.readTag();
                        nbtOptionalData.add(currentTag);
                    } while(!(currentTag instanceof EndTag));
                } catch (IOException e) { }
            }
        }
    }

    public void write(ByteBuf buf) {
        buf.writeShort(itemId);

        if (itemId != -1) {
            buf.writeByte(itemAmount);
            buf.writeShort(itemDamageValue);
            buf.writeShort(nbtOptionalLength);

            if (nbtOptionalData.size() > 0) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                try (NBTOutputStream nbtOutputStream = new NBTOutputStream(baos)) {
                    for (Tag entry : nbtOptionalData) {
                        nbtOutputStream.writeTag(entry);
                    }

                    buf.writeBytes(baos.toByteArray());
                } catch (IOException e) { }
            }
        }
    }
}
