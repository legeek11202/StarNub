package org.starnub.network.packets;

import org.starnub.datatypes.Variant;
import org.starnub.network.StarboundStream;

/**
 * Packet Class.
 * <p>
 * Credit goes to: <br>
 * SirCmpwn - (https://github.com/SirCmpwn/StarNet) <br>
 * Mitch528 - (https://github.com/Mitch528/SharpStar) <br>
 * Starbound-Dev - (http://starbound-dev.org/)
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1.0, 24 May 2014
 * 
 */
public class ClientConnectPacket extends Packet {

	public byte PacketId()
	{
		return 7;
	}

	private String	AssetDigest;
	private Variant	Claim;
	private byte[]	UUID;
	private String	PlayerName;
	private String	Species;
	private byte[]	Shipworld;
	private String	Account;

	public ClientConnectPacket()
	{
	}

	public ClientConnectPacket(String assetDigest, Variant claim, byte[] uuid, String playerName, String species, byte[] shipworld, String account)
	{
		AssetDigest = assetDigest;
		Claim = claim;
		UUID = uuid;
		PlayerName = playerName;
		Species = species;
		Shipworld = shipworld;
		Account = account;
	}

	@Override
	public void Read(StarboundStream stream)
	{
        AssetDigest = stream.readString();
        try { Claim = stream.readVariant(); } catch (Exception e) { e.printStackTrace(); }
        boolean uuid = stream.readBoolean();
        if (uuid)
            UUID = stream.readInt8Array(16);//TODO Verify
        PlayerName = stream.readString();
        Species = stream.readString();
        Shipworld = stream.readInt8Array();
        Account = stream.readString();
	}

	@Override
	public void Write(StarboundStream stream)
	{
        stream.writeString(AssetDigest);
        stream.writeVariant(Claim);
        stream.writeBoolean(UUID != null);
        if (UUID != null)
            stream.writeInt8Array(UUID, false);
        stream.writeString(PlayerName);
        stream.writeString(Species);
        stream.writeInt8Array(Shipworld);
        stream.writeString(Account);
	}
}
