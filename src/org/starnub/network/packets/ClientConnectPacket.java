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


	/**
	 * @return the assetDigest
	 */
	public String getAssetDigest()
	{
		return AssetDigest;
	}

	/**
	 * @param assetDigest the assetDigest to set
	 */
	public void setAssetDigest(String assetDigest)
	{
		AssetDigest = assetDigest;
	}

	/**
	 * @return the claim
	 */
	public Variant getClaim()
	{
		return Claim;
	}

	/**
	 * @param claim the claim to set
	 */
	public void setClaim(Variant claim)
	{
		Claim = claim;
	}

	/**
	 * @return the UUID
	 */
	public byte[] getUUID()
	{
		return UUID;
	}

	/**
	 * @param UUID the UUID to set
	 */
	public void setUUID(byte[] UUID)
	{
		UUID = UUID;
	}

	/**
	 * @return the playerName
	 */
	public String getPlayerName()
	{
		return PlayerName;
	}

	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName)
	{
		PlayerName = playerName;
	}

	/**
	 * @return the species
	 */
	public String getSpecies()
	{
		return Species;
	}

	/**
	 * @param species the species to set
	 */
	public void setSpecies(String species)
	{
		Species = species;
	}

	/**
	 * @return the shipworld
	 */
	public byte[] getShipworld()
	{
		return Shipworld;
	}

	/**
	 * @param shipworld the shipworld to set
	 */
	public void setShipworld(byte[] shipworld)
	{
		Shipworld = shipworld;
	}

	/**
	 * @return the account
	 */
	public String getAccount()
	{
		return Account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account)
	{
		Account = account;
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
            UUID = stream.readInt8Array(16);
        PlayerName = stream.readString();
        Species = stream.readString();
        Shipworld = stream.readVLQIntArray();
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
        stream.writeVLQIntArray(Shipworld);
        stream.writeString(Account);
	}
}
