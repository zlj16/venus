package com.meidusa.venus.hello;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.meidusa.toolkit.common.util.Tuple;
import com.meidusa.toolkit.net.Connection;
import com.meidusa.toolkit.net.MessageHandler;
import com.meidusa.toolkit.net.util.InetAddressUtil;
import com.meidusa.toolkit.util.TimeUtil;
import com.meidusa.venus.io.network.VenusFrontendConnection;
import com.meidusa.venus.io.packet.AbstractServicePacket;
import com.meidusa.venus.io.packet.PacketConstant;
import com.meidusa.venus.io.packet.PingPacket;
import com.meidusa.venus.io.packet.PongPacket;
import com.meidusa.venus.io.packet.VenusRouterPacket;

public class DebugHandler implements MessageHandler<VenusFrontendConnection, Tuple<Long, byte[]>> {

	 @Override
	    public void handle(final VenusFrontendConnection conn, Tuple<Long, byte[]> data) {
	        long waitTime = TimeUtil.currentTimeMillis() - data.left;

	        byte[] message = data.right;
	        final long received = System.currentTimeMillis();

	        int type = AbstractServicePacket.getType(message);
	        VenusRouterPacket routerPacket = null;
	        byte serializeType = conn.getSerializeType();
	        String sourceIp = conn.getHost();
	        if (PacketConstant.PACKET_TYPE_ROUTER == type) {
	            routerPacket = new VenusRouterPacket();
	            routerPacket.original = message;
	            routerPacket.init(message);
	            type = AbstractServicePacket.getType(routerPacket.data);
	            message = routerPacket.data;
	            serializeType = routerPacket.serializeType;
                sourceIp = InetAddressUtil.intToAddress(routerPacket.srcIP);
	        }

	        final byte packetSerializeType = serializeType;
	        final String finalSourceIp = sourceIp;
	        switch (type) {
	            case PacketConstant.PACKET_TYPE_PING:
	                PingPacket ping = new PingPacket();
	                ping.init(message);
	                PongPacket pong = new PongPacket();
	                AbstractServicePacket.copyHead(ping, pong);
	                postMessageBack(conn, null, ping, pong);
	                   System.out.println("receive ping packet from " + conn.getHost() + ":" + conn.getPort());
	                break;

	            // ignore this packet
	            case PacketConstant.PACKET_TYPE_PONG:
	                break;

	        }
	 }

    public void postMessageBack(Connection conn, VenusRouterPacket routerPacket, AbstractServicePacket source, AbstractServicePacket result) {
        if (routerPacket == null) {
            conn.write(result.toByteBuffer());
        } else {
            routerPacket.data = result.toByteArray();
            conn.write(routerPacket.toByteBuffer());
        }
    }
}
