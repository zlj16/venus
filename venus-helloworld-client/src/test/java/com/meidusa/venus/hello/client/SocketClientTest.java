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

public class SocketClientTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		AtomicLong sequence = new AtomicLong(0);
		for(int j=0;j<10;j++){
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress("127.0.0.1",16800));
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
				JsonVenusRequestPacket request = new JsonVenusRequestPacket();
				request.clientRequestId = sequence.getAndIncrement();
				request.serviceVersion = 1;
				request.apiName = "HelloService.getHello";
				request.params = "{\"name\":\"Jack\"}";
				
				conn.write(request.toByteArray());
				tmp = conn.read();
				
				AbstractVenusPacket venusPacket = null;
				switch (AbstractVenusPacket.getType(tmp)) {
				case PacketConstant.PACKET_TYPE_ERROR:
					ErrorPacket error = new ErrorPacket();
					error.init(tmp);
					venusPacket = error;
					Assert.assertEquals(request.clientRequestId, error.clientRequestId);
					break;
				case PacketConstant.PACKET_TYPE_SERVICE_RESPONSE:
					JsonVenusResponsePacket response  = new JsonVenusResponsePacket();
					venusPacket = response;
					venusPacket.init(tmp);
					System.out.println(response.result);
					Assert.assertEquals(request.clientRequestId, response.clientRequestId);
					break;
					
				case PacketConstant.PACKET_TYPE_OK:
				    OKPacket okpacket = new OKPacket();
				    okpacket.init(tmp);
				    venusPacket = okpacket;
				    Assert.assertEquals(request.clientRequestId, okpacket.clientRequestId);
				    break;
				default:
					System.out.println("error type"+StringUtil.dumpAsHex(tmp, tmp.length));
					break;
				}
				
				System.out.println(venusPacket);
			}
			socket.close();
		}
		
		try {
			Thread.sleep(10000000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ok!!!!");
	}

}
