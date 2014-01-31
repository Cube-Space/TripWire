package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DestroyEntities extends DefinedPacket {
    private byte amount;
    private List<Integer> entityIds = new ArrayList<>();

    @Override
    public void read(ByteBuf buf) {
        amount = buf.readByte();

        for(int i = 0; i < amount; i++) {
            entityIds.add(buf.readInt());
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeByte(entityIds.size());

        for(int entry : entityIds) {
            buf.writeInt(entry);
        }
    }
}

