package org.starnub.network.packets;

import org.starnub.datatypes.Variant;

/**
 * Packet Class.
 * <p>
 * Credit goes to: <br>
 * SirCmpwn - (https://github.com/SirCmpwn/StarNet) <br>
 * Mitch528 - (https://github.com/Mitch528/SharpStar) <br>
 * Starbound-Dev - (http://starbound-dev.org/)
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1.0, 17 May 2014 (Incomplete)
 * 
 */
public class ClientConnectPacket {

	public byte PacketId()
	{
		return 7;
	}

	public String	AssetDigest;
	public Variant	Claim;
	public byte[]	UUID;
	public String	PlayerName;
	public String	Species;
	public byte[]	Shipworld;
	public String	Account;

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
}
