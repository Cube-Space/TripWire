package net.cubespace.TripWire.Injector;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import net.cubespace.TripWire.Protocol.Packets.DefinedPacket;
import net.cubespace.TripWire.Protocol.Protocol;
import net.cubespace.TripWire.TripWirePlugin;
import net.md_5.bungee.netty.PipelineUtils;
import net.md_5.bungee.protocol.BadPacketException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class InjectedClient extends ByteToMessageDecoder {
    private static Method DECODE_BUFFER;
    private static Method ENCODE_BUFFER;

    private String prefix;
    private boolean server = true;
    private String protocolStage = "HANDSHAKE";

    //BungeeCord Encoder & Decoder
    private ByteToMessageDecoder decoder;
    private MessageToByteEncoder<DefinedPacket> encoder;

    public InjectedClient(final String prefix, Channel channel) {
        this.prefix = prefix;

        decoder = (ByteToMessageDecoder) channel.pipeline().get(PipelineUtils.PACKET_DECODER);
        encoder = (MessageToByteEncoder<DefinedPacket>) channel.pipeline().get(PipelineUtils.PACKET_ENCODER);

        if (decoder == null)
            throw new IllegalArgumentException("Unable to find BungeeCord decoder in " + channel.pipeline());
        if (encoder == null)
            throw new IllegalArgumentException("Unable to find BungeeCord encoder in " + channel.pipeline());

        if (DECODE_BUFFER == null) {
            try {
                DECODE_BUFFER = decoder.getClass().getDeclaredMethod("decode", ChannelHandlerContext.class, ByteBuf.class, List.class);
                DECODE_BUFFER.setAccessible(true);
            } catch (NoSuchMethodException e) {
                TripWirePlugin.getInstance().getLogger().severe("Could not get decode Method from BungeeCord Decoder");
            }
        }

        if (ENCODE_BUFFER == null) {
            try {
                ENCODE_BUFFER = encoder.getClass().getDeclaredMethod("encode", ChannelHandlerContext.class, net.md_5.bungee.protocol.DefinedPacket.class, ByteBuf.class);
                ENCODE_BUFFER.setAccessible(true);
            } catch (NoSuchMethodException e) {
                TripWirePlugin.getInstance().getLogger().severe("Could not get encode Method from BungeeCord Encoder");
            }
        }

        MessageToByteEncoder<DefinedPacket> protocolEncoder = new MessageToByteEncoder<DefinedPacket>() {
            @Override
            protected void encode(ChannelHandlerContext ctx, DefinedPacket packet, ByteBuf output) throws Exception {
                TripWirePlugin.getInstance().getLogger().info(prefix + " <- " + packet.toString());
                ByteBuf packetBuffer = ctx.alloc().buffer();
                ENCODE_BUFFER.invoke(encoder, ctx, packet, packetBuffer);
                output.writeBytes(getBytes(packetBuffer));
            }
        };


        channel.pipeline().addBefore(PipelineUtils.PACKET_DECODER, "tripwire-decoder", this);
        channel.pipeline().addAfter(PipelineUtils.PACKET_ENCODER, "tripwire-writer", protocolEncoder);
    }

    private byte[] getBytes(ByteBuf buffer) {
        byte[] data = new byte[buffer.readableBytes()];

        buffer.readBytes(data);
        return data;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> packets) throws Exception {
        try {
            Field serverBooleanField = decoder.getClass().getDeclaredField("server");
            serverBooleanField.setAccessible(true);
            server = serverBooleanField.getBoolean(decoder);
        } catch (NoSuchFieldException | IllegalAccessException e) {
        }

        try {
            Field protocolField = decoder.getClass().getDeclaredField("protocol");
            protocolField.setAccessible(true);

            Object prot = protocolField.get(decoder);
            protocolStage = prot.toString();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        ByteBuf copy = byteBuf.copy();

        Protocol.ProtocolDirection prot = (server) ? Protocol.valueOf(protocolStage).TO_SERVER : Protocol.valueOf(protocolStage).TO_CLIENT;

        int packetId = DefinedPacket.readVarInt(byteBuf);

        if (prot.hasPacket(packetId)) {
            net.cubespace.TripWire.Protocol.Packets.DefinedPacket packet = prot.createPacket(packetId);
            //TripWirePlugin.getInstance().getLogger().info(prefix + " -> " + String.format("0x%2s", Integer.toHexString(packetId)).replace(' ', '0'));
            packet.read(byteBuf, prot);

            if (byteBuf.readableBytes() != 0) {
                throw new BadPacketException("Did not read all bytes from packet " + packet.getClass() + " " + packetId + " Direction " + prot);
            }

            ByteBuf by = channelHandlerContext.alloc().buffer();
            DefinedPacket.writeVarInt(packetId, by);
            packet.write(by, prot);

            //Be sure to call the internal Decoder
            DECODE_BUFFER.invoke(decoder, channelHandlerContext, by, packets);
        } else {
            TripWirePlugin.getInstance().getLogger().info("Unknow PacketID " + String.format("0x%2s", Integer.toHexString(packetId)).replace(' ', '0'));
            byteBuf.skipBytes(byteBuf.readableBytes());

            DECODE_BUFFER.invoke(decoder, channelHandlerContext, copy, packets);
        }
    }
}
