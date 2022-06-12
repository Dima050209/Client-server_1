import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacketBuilderTest {

    @Test
    void shouldHandlePackage(){
        Command command = new Command(CommandType.CREATE_PRODUCT,"user1", new byte[]{1, 2, 3});
        PacketBuilder packetBuilder = new PacketBuilder();
        byte[] packet = packetBuilder.encode(command);
        Command decodedCommand = packetBuilder.decode(packet);

        Assertions.assertEquals(command, decodedCommand);
    }
    @Test
    void shouldThrowException(){
        Command command = new Command(CommandType.CREATE_PRODUCT,"user1", new byte[]{1, 2, 3});
        PacketBuilder packetBuilder = new PacketBuilder();
        byte[] packet = packetBuilder.encode(command);
        packet[14] = 0x0;
        packet[15] = 0x0;
        Assertions.assertThrows(RuntimeException.class, () ->  packetBuilder.decode(packet));
    }
}