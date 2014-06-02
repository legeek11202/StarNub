package network.handlers;

import io.netty.channel.ChannelHandlerContext;

import java.net.InetAddress;
import java.util.UUID;

public class TempClient {
	
	private String playername;
	private InetAddress	connectingIp;
	private UUID uuid;
	private ChannelHandlerContext ctx;
	
	public TempClient(String playername, UUID connectingUuid, InetAddress connectingIp, ChannelHandlerContext ctx)
	{
		this.playername = playername;
		this.connectingIp = connectingIp;
		this.uuid = connectingUuid;
		this.ctx = ctx;
		
	}
	/**
	 * @return the playerName
	 */
	public String getPlayerName()
	{
		return playername;
	}
	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName)
	{
		this.playername = playerName;
	}
	/**
	 * @return the connectingIp
	 */
	public InetAddress getConnectingIp()
	{
		return connectingIp;
	}
	/**
	 * @param connectingIp the connectingIp to set
	 */
	public void setConnectingIp(InetAddress connectingIp)
	{
		this.connectingIp = connectingIp;
	}
	/**
	 * @return the uuid
	 */
	public UUID getUuid()
	{
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}
	/**
	 * @return the ctx
	 */
	public ChannelHandlerContext getCtx()
	{
		return ctx;
	}
	/**
	 * @param ctx the ctx to set
	 */
	public void setCtx(ChannelHandlerContext ctx)
	{
		this.ctx = ctx;
	}
	
	
}
