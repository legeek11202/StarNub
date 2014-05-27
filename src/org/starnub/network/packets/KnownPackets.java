package org.starnub.network.packets;

import java.util.HashMap;
import java.util.Map;

/**
 * KnownPackets Enumerator
 * <p>
 * Credit goes to:
 * <br>
 * Starbound-Dev - Packet(http://starbound-dev.org/)
 * 
 * @author Daniel Packet(Underbalanced) Packet(StarNub.org)
 * @version 1.0, 17 May 2014 Packet(Incomplete)
 * 
 */

public enum KnownPackets {
	
        ProtocolVersionPacket(PassThroughPacket.class, 0),
        ConnectionResponsePacket(PassThroughPacket.class, 1),
        DisconnectResponsePacket(PassThroughPacket.class, 2),
        HandshakeChallengePacket(PassThroughPacket.class, 3),
        ChatReceivedPacket(ChatReceivedPacket.class, 4),
        UniverseTimeUpdatePacket(PassThroughPacket.class, 5),
        CelestialResponsePacket(PassThroughPacket.class, 6),
        ClientConnectPacket(ClientConnectPacket.class, 7),
        ClientDisconnectPacket(PassThroughPacket.class, 8),
        HandshakeResponsePacket(PassThroughPacket.class, 9),
        WarpCommandPacket(PassThroughPacket.class, 10),
        ChatSentPacket(ChatSentPacket.class, 11),
        CelestialRequestPacket(PassThroughPacket.class, 12),
        ClientContextUpdatePacket(PassThroughPacket.class, 13),
        WorldStartPacket(PassThroughPacket.class, 14),
        WorldStopPacket(PassThroughPacket.class, 15),
        TileArrayUpdatePacket(PassThroughPacket.class, 16),
        TileUpdatePacket(PassThroughPacket.class, 17),
        TileLiquidUpdatePacket(PassThroughPacket.class, 18),
        TileDamageUpdatePacket(PassThroughPacket.class, 19),
        TileModificationFailurePacket(PassThroughPacket.class, 20),
        GiveItemPacket(PassThroughPacket.class, 21),
        SwapContainerResultPacket(PassThroughPacket.class, 22),
        EnvironmentUpdatePacket(PassThroughPacket.class, 23),
        EntityInteractResultPacket(PassThroughPacket.class, 24),
        ModifyTileListPacket(PassThroughPacket.class, 25),
        DamageTilePacket(PassThroughPacket.class, 26),
        DamageTileGroupPacket(PassThroughPacket.class, 27),
        RequestDropPacket(PassThroughPacket.class, 28),
        SpawnEntityPacket(PassThroughPacket.class, 29),
        EntityInteractPacket(PassThroughPacket.class, 30),
        ConnectWirePacket(PassThroughPacket.class, 31),
        DisconnectAllWiresPacket(PassThroughPacket.class, 32),
        OpenContainerPacket(PassThroughPacket.class, 33),
        CloseContainerPacket(PassThroughPacket.class, 34),
        SwapContainerPacket(PassThroughPacket.class, 35),
        ItemApplyContainerPacket(PassThroughPacket.class, 36),
        StartCraftingContainerPacket(PassThroughPacket.class, 37),
        StopCraftingContainerPacket(PassThroughPacket.class, 38),
        BurnContainerPacket(PassThroughPacket.class, 39),
        ClearContainerPacket(PassThroughPacket.class, 40),
        WorldClientStateUpdatePacket(PassThroughPacket.class, 41),
        EntityCreatePacket(PassThroughPacket.class, 42),
        EntityUpdatePacket(PassThroughPacket.class, 43),
        EntityDestroyPacket(PassThroughPacket.class, 44),
        DamageNotificationPacket(PassThroughPacket.class, 45),
        StatusEffectRequestPacket(PassThroughPacket.class, 46),
        UpdateWorldPropertiesPacket(PassThroughPacket.class, 47),
        HeartbeatPacket(PassThroughPacket.class, 48);
        
        
        private final Class<? extends Packet> packetType;
        private int packetId;

        private KnownPackets(Class<? extends Packet> packetType, int packetId) {
            this.packetType = packetType;
            this.packetId = packetId;
            
        }

        public Packet makeNewPacket() throws Exception 
        {	
        	return this.packetType.newInstance();
        }
        
        
    	private int KnownPackets;
        
    	public int getKnownPackets() {
    	   return this.KnownPackets;
    	 }
  
        /**
         * A mapping between the integer code and its corresponding Status to facilitate lookup by code.
         */
        private static Map<Integer, KnownPackets> pId_pType_Map;
     

     
        public static KnownPackets getKnownPackets(int i) {
            if (pId_pType_Map == null) {
                initMappingPacket();
            }
            return pId_pType_Map.get(i);
        }
     
        private static void initMappingPacket() {
        	pId_pType_Map = new HashMap<Integer, KnownPackets>();
            for (KnownPackets s : values()) {
            	pId_pType_Map.put(s.packetId, s);
            }
        }
     
        public int getPacketIdPacket() {
            return packetId;
        }
	}