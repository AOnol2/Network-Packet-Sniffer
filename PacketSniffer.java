import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.*;

public class PacketSniffer {

    // Display available network interfaces
    public static void listNetworkInterfaces() {
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        System.out.println("Available Network Interfaces:");
        for (int i = 0; i < devices.length; i++) {
            System.out.println(i + ": " + devices[i].name + " - " + devices[i].description);
        }
    }

    // Capture packets on a specified interface
    public static void capturePackets(int interfaceIndex) throws Exception {
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        JpcapCaptor captor = JpcapCaptor.openDevice(devices[interfaceIndex], 65535, true, 20);

        // Loop to capture packets
        System.out.println("Capturing packets...");
        captor.loopPacket(-1, new PacketHandler());
    }

    // Inner class to handle packet capture and filtering
    private static class PacketHandler implements jpcap.PacketReceiver {
        @Override
        public void receivePacket(Packet packet) {
            // Display basic information for each packet
            if (packet instanceof IPPacket) {
                IPPacket ipPacket = (IPPacket) packet;
                System.out.println("Protocol: " + ipPacket.protocol);
                System.out.println("Source IP: " + ipPacket.src_ip);
                System.out.println("Destination IP: " + ipPacket.dst_ip);

                // Display protocol-specific details
                if (ipPacket instanceof TCPPacket) {
                    TCPPacket tcpPacket = (TCPPacket) ipPacket;
                    System.out.println("Source Port: " + tcpPacket.src_port);
                    System.out.println("Destination Port: " + tcpPacket.dst_port);
                } else if (ipPacket instanceof UDPPacket) {
                    UDPPacket udpPacket = (UDPPacket) ipPacket;
                    System.out.println("Source Port: " + udpPacket.src_port);
                    System.out.println("Destination Port: " + udpPacket.dst_port);
                }
                System.out.println("-------------------------------------------------");
            }
        }
    }

    public static void main(String[] args) {
        try {
            listNetworkInterfaces();
            System.out.print("Enter the interface number to capture packets: ");
            int interfaceIndex = new java.util.Scanner(System.in).nextInt();
            capturePackets(interfaceIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
