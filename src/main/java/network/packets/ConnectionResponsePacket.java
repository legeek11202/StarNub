package network.packets;

import network.StarboundStream;
import datatypes.VLQ;
import datatypes.Variant;

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
public class ConnectionResponsePacket extends Packet {

	public byte PacketId()
	{
		return 1;
	}

	private boolean	Success;
	private long	ClientId;
	private String	RejectionReason;
	private boolean CelestialInformation;
	private int 	OrbitalLevels;
	private int	 	ChunkSize;
	private int		XYCoordinateMin;
	private int		XYCoordinateMax;
	private int		ZCoordinateMin;
	private int		ZCoordinateMax;
	private VLQ		NumberofSectors;
	private String	SectorId;
	private String 	SectorName;
	private long	SectorSeed;
	private String	SectorPrefix;
	private Variant Parameters;
	private Variant SectorConfig;
	
	public ConnectionResponsePacket()
	{
		Unknown = new byte[0];
	}


	/**
	 * @return the celestialInformation
	 */
	public boolean isCelestialInformation()
	{
		return CelestialInformation;
	}

	/**
	 * @param celestialInformation the celestialInformation to set
	 */
	public void setCelestialInformation(boolean celestialInformation)
	{
		CelestialInformation = celestialInformation;
	}

	/**
	 * @return the orbitalLevels
	 */
	public int getOrbitalLevels()
	{
		return OrbitalLevels;
	}

	/**
	 * @param orbitalLevels the orbitalLevels to set
	 */
	public void setOrbitalLevels(int orbitalLevels)
	{
		OrbitalLevels = orbitalLevels;
	}

	/**
	 * @return the chunkSize
	 */
	public int getChunkSize()
	{
		return ChunkSize;
	}

	/**
	 * @param chunkSize the chunkSize to set
	 */
	public void setChunkSize(int chunkSize)
	{
		ChunkSize = chunkSize;
	}

	/**
	 * @return the xYCoordinateMin
	 */
	public int getXYCoordinateMin()
	{
		return XYCoordinateMin;
	}

	/**
	 * @param xYCoordinateMin the xYCoordinateMin to set
	 */
	public void setXYCoordinateMin(int xYCoordinateMin)
	{
		XYCoordinateMin = xYCoordinateMin;
	}

	/**
	 * @return the xYCoordinateMax
	 */
	public int getXYCoordinateMax()
	{
		return XYCoordinateMax;
	}

	/**
	 * @param xYCoordinateMax the xYCoordinateMax to set
	 */
	public void setXYCoordinateMax(int xYCoordinateMax)
	{
		XYCoordinateMax = xYCoordinateMax;
	}

	/**
	 * @return the zCoordinateMin
	 */
	public int getZCoordinateMin()
	{
		return ZCoordinateMin;
	}

	/**
	 * @param zCoordinateMin the zCoordinateMin to set
	 */
	public void setZCoordinateMin(int zCoordinateMin)
	{
		ZCoordinateMin = zCoordinateMin;
	}

	/**
	 * @return the zCoordinateMax
	 */
	public int getZCoordinateMax()
	{
		return ZCoordinateMax;
	}

	/**
	 * @param zCoordinateMax the zCoordinateMax to set
	 */
	public void setZCoordinateMax(int zCoordinateMax)
	{
		ZCoordinateMax = zCoordinateMax;
	}

	/**
	 * @return the numberofSectors
	 */
	public VLQ getNumberofSectors()
	{
		return NumberofSectors;
	}

	/**
	 * @param numberofSectors the numberofSectors to set
	 */
	public void setNumberofSectors(VLQ numberofSectors)
	{
		NumberofSectors = numberofSectors;
	}

	/**
	 * @return the sectorId
	 */
	public String getSectorId()
	{
		return SectorId;
	}

	/**
	 * @param sectorId the sectorId to set
	 */
	public void setSectorId(String sectorId)
	{
		SectorId = sectorId;
	}

	/**
	 * @return the sectorName
	 */
	public String getSectorName()
	{
		return SectorName;
	}

	/**
	 * @param sectorName the sectorName to set
	 */
	public void setSectorName(String sectorName)
	{
		SectorName = sectorName;
	}

	/**
	 * @return the sectorSeed
	 */
	public long getSectorSeed()
	{
		return SectorSeed;
	}

	/**
	 * @param sectorSeed the sectorSeed to set
	 */
	public void setSectorSeed(long sectorSeed)
	{
		SectorSeed = sectorSeed;
	}

	/**
	 * @return the sectorPrefix
	 */
	public String getSectorPrefix()
	{
		return SectorPrefix;
	}

	/**
	 * @param sectorPrefix the sectorPrefix to set
	 */
	public void setSectorPrefix(String sectorPrefix)
	{
		SectorPrefix = sectorPrefix;
	}

	/**
	 * @return the parameters
	 */
	public Variant getParameters()
	{
		return Parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Variant parameters)
	{
		Parameters = parameters;
	}

	/**
	 * @return the sectorConfig
	 */
	public Variant getSectorConfig()
	{
		return SectorConfig;
	}

	/**
	 * @param sectorConfig the sectorConfig to set
	 */
	public void setSectorConfig(Variant sectorConfig)
	{
		SectorConfig = sectorConfig;
	}

	@Override
	public void Read(StarboundStream stream)
	{
        Success = stream.readBoolean();
        ClientId = stream.readVLQ();
        RejectionReason = stream.readString();
        CelestialInformation = stream.readBoolean();
        if(CelestialInformation) 
        {
    	OrbitalLevels = stream.readUnsignedInt();
    	ChunkSize = stream.readUnsignedInt();
    	XYCoordinateMin = stream.readUnsignedInt();
    	XYCoordinateMax = stream.readUnsignedInt();
    	ZCoordinateMin = stream.readUnsignedInt();
    	ZCoordinateMax = stream.readUnsignedInt();
    	NumberofSectors = stream.readVLQ();
    	SectorId = stream.readString();
    	SectorName = stream.readString();
    	SectorSeed = stream.readLong();
    	SectorPrefix = stream.readString();
    	Parameters = stream.readVariant();
    	SectorConfig = stream.readVariant();
        }
	}

	@Override
	public void Write(StarboundStream stream)
	{
        stream.writeBoolean(Success);
        stream.writeVLQ(ClientId);
        stream.writeString(RejectionReason);
        if(CelestialInformation) 
        {
        stream.writeBoolean(CelestialInformation);
    	stream.writeInt(OrbitalLevels);
    	stream.writeInt(ChunkSize);
    	stream.writeInt(XYCoordinateMin);
    	stream.writeInt(XYCoordinateMax);
    	stream.writeInt(ZCoordinateMin);
    	stream.writeInt(ZCoordinateMax);
    	stream.writeVLQ(NumberofSectors);
    	stream.writeString(SectorId);
    	stream.writeString(SectorName);
    	stream.writeLong(SectorSeed);
    	stream.writeString(SectorPrefix);
    	stream.writeVariant(Parameters);
    	stream.writeVariant(SectorConfig);
        }
		
	}
}
