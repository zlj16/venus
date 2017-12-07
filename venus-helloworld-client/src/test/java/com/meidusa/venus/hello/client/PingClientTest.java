package com.meidusa.venus.hello.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

import junit.framework.Assert;

import com.meidusa.toolkit.util.StringUtil;
import com.meidusa.venus.io.network.VenusBIOConnection;
import com.meidusa.venus.io.packet.AbstractVenusPacket;
import com.meidusa.venus.io.packet.DummyAuthenPacket;
import com.meidusa.venus.io.packet.ErrorPacket;
import com.meidusa.venus.io.packet.HandshakePacket;
import com.meidusa.venus.io.packet.OKPacket;
import com.meidusa.venus.io.packet.PacketConstant;
import com.meidusa.venus.io.packet.PingPacket;
import com.meidusa.venus.io.packet.PongPacket;

public class PingClientTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		AtomicLong sequence = new AtomicLong(0);
		for(int j=0;j<1;j++){
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress("10.32.36.22",16800));
			VenusBIOConnection conn =	new VenusBIOConnection(socket,System.currentTimeMillis());
			byte[] tmp = conn.read();
			HandshakePacket packet = new HandshakePacket();
			packet.init(tmp);
			
			DummyAuthenPacket dummy = new DummyAuthenPacket();
			
			socket.getOutputStream().write(dummy.toByteArray());
			
			tmp = conn.read();
			
			OKPacket ok = new OKPacket();
			
			ok.init(tmp);
			
			for(int i=0;i<1;i++){
				PingPacket request = new PingPacket();
				request.clientRequestId = sequence.getAndIncrement();
				
				conn.write(request.toByteArray());
				tmp = conn.read();
				
				AbstractVenusPacket venusPacket = null;
				switch (AbstractVenusPacket.getType(tmp)) {
				case PacketConstant.PACKET_TYPE_ERROR:
					venusPacket = new ErrorPacket();
					venusPacket.init(tmp);
					break;
				case PacketConstant.PACKET_TYPE_SERVICE_RESPONSE:
					JsonVenusResponsePacket response  = new JsonVenusResponsePacket();
					venusPacket = response;
					venusPacket.init(tmp);
					Assert.assertEquals(request.clientRequestId, response.clientRequestId);
					break;
					
				case PacketConstant.PACKET_TYPE_OK:
				    OKPacket okpacket = new OKPacket();
				    okpacket.init(tmp);
				    venusPacket = okpacket;
				    Assert.assertEquals(request.clientRequestId, okpacket.clientRequestId);
				    break;
				case PacketConstant.PACKET_TYPE_PONG:
				    PongPacket pong = new PongPacket();
				    pong.init(tmp);
				    venusPacket = pong;
				    Assert.assertEquals(request.clientRequestId, pong.clientRequestId);
				    break;
				default:
					System.out.println("error type"+StringUtil.dumpAsHex(tmp, tmp.length));
					break;
				}
				
				System.out.println(venusPacket);
			}
		}
	}

}
