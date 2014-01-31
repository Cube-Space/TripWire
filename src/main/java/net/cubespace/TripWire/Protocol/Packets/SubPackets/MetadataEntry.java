package net.cubespace.TripWire.Protocol.Packets.SubPackets;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
@AllArgsConstructor
@Data
public class MetadataEntry {
    private int type;
    private int index;
    private Object value;
}
