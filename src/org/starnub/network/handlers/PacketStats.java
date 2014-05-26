package org.starnub.network.handlers;

import java.lang.reflect.Field;

/**
 * This will track packet statistics.
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1.0, 26 May 2014
 *        
 *
 */
public class PacketStats {

	static int ProtocolVersionPacket;
	static int ConnectionResponsePacket;
	static int DisconnectResponsePacket;
	static int HandshakeChallengePacket;
	static int ChatReceivedPacket;
	static int UniverseTimeUpdatePacket;
	static int CelestialResponsePacket;
	static int ClientConnectPacket;
	static int ClientDisconnectPacket;
	static int HandshakeResponsePacket;
	static int WarpCommandPacket;
	static int ChatSentPacket;
	static int CelestialRequestPacket;
	static int ClientContextUpdatePacket;
	static int WorldStartPacket;
	static int WorldStopPacket;
	static int TileArrayUpdatePacket;
	static int TileUpdatePacket;
	static int TileLiquidUpdatePacket;
	static int TileDamageUpdatePacket;
	static int TileModificationFailurePacket;
	static int GiveItemPacket;
	static int SwapContainerResultPacket;
	static int EnvironmentUpdatePacket;
	static int EntityInteractResultPacket;
	static int ModifyTileListPacket;
	static int DamageTilePacket;
	static int DamageTileGroupPacket;
	static int RequestDropPacket;
	static int SpawnEntityPacket;
	static int EntityInteractPacket;
	static int ConnectWirePacket;
	static int DisconnectAllWiresPacket;
	static int OpenContainerPacket;
	static int CloseContainerPacket;
	static int SwapContainerPacket;
	static int ItemApplyContainerPacket;
	static int StartCraftingContainerPacket;
	static int StopCraftingContainerPacket;
	static int BurnContainerPacket;
	static int ClearContainerPacket;
	static int WorldClientStateUpdatePacket;
	static int EntityCreatePacket;
	static int EntityUpdatePacket;
	static int EntityDestroyPacket;
	static int DamageNotificationPacket;
	static int StatusEffectRequestPacket;
	static int UpdateWorldPropertiesPacket;
	static int HeartbeatPacket;
	
    public String packetStats() 
    {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
          result.append("  ");
          try {
            result.append( field.getName() );
            result.append(": ");
            //requires access to private field:
            result.append( field.get(this) );
          } catch ( IllegalAccessException ex ) {
            System.out.println(ex);
          }
          result.append(newLine);
        }
        result.append("}");

        return result.toString();
      }
}

