package net.cubespace.TripWire.Protocol.Packets;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.TripWire.Protocol.Packets.SubPackets.Vector;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SoundEffect extends DefinedPacket {
    private String sound;
    private Vector vector;
    private float volume;
    private int pitch;

    @Override
    public void read(ByteBuf buf) {
        sound = readString(buf);
        vector = new Vector();
        vector.read(buf);
        volume = buf.readFloat();
        pitch = buf.readUnsignedByte();
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(sound, buf);
        vector.write(buf);
        buf.writeFloat(volume);
        buf.writeByte(pitch);
    }
}

