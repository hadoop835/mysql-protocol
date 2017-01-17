package com.seaboat.mysql.protocol;

import java.nio.ByteBuffer;

import com.seaboat.mysql.protocol.util.BufferUtil;

/**
 * 
 * <pre><b>ping command packet.</b></pre>
 * @author 
 * <pre>seaboat</pre>
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * @version 1.0
 * @see http://dev.mysql.com/doc/internals/en/com-ping.html
 */

public class PingPacket extends MySQLPacket {
	// payload length is 1,packet id is 0,payload is 0e
	public static final byte[] PING = new byte[] { 1, 0, 0, 0, 14 };

	public byte payload;
	
	@Override
	public int calcPacketSize() {
		return 1;
	}

	@Override
	protected String getPacketInfo() {
		return "MySQL Ping Packet";
	}

	@Override
	public void read(byte[] data) {
		MySQLMessage mm = new MySQLMessage(data);
		packetLength = mm.readUB3();
		packetId = mm.read();
		payload = mm.read();
	}

	@Override
	public void write(ByteBuffer buffer) {
		int size = calcPacketSize();
		BufferUtil.writeUB3(buffer, size);
		buffer.put(packetId);
		buffer.put(payload);
	}

}