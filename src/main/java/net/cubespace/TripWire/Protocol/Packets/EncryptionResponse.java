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
public class EncryptionResponse extends DefinedPacket {
    private byte[] sharedSecret;
    private byte[] verifyToken;

    @Override
    public void read(ByteBuf buf) {
        sharedSecret = readArray(buf);
        verifyToken = readArray(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        writeArray(sharedSecret, buf);
        writeArray(verifyToken, buf);
    }
}
