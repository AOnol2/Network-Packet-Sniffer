# Network Packet Sniffer and Analyzer

## Project Overview
The **Network Packet Sniffer and Analyzer** is a Java application that captures and analyzes network packets in real time. It uses the jpcap library to intercept and display information on packets passing through a selected network interface, making it an excellent tool for basic network analysis and learning about packet structures.

## Features
- Real-Time Packet Capture on selected network interfaces
- Protocol Filtering: Displays only relevant details for TCP, UDP, and ICMP packets
- Packet Inspection: Displays source and destination IPs, ports, and protocol type
- Statistics: Packet count by protocol and other metrics (planned feature)

## Installation & Requirements
1. Install [JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
2. Download [jpcap library](http://netresearch.ics.uci.edu/kfujii/jpcap/doc/).
3. Place `jpcap.jar` in the `lib/` directory of this project and include it in your build path.

## How to Run
1. Compile the program:
   ```bash
   javac -cp .:lib/jpcap.jar src/PacketSniffer.java
