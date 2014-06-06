package network.handlers;

import java.lang.reflect.Field;

/**
 * This will track packet statistics.
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 26 May 2014
 */
public class PacketStats {

    volatile int ProtocolVersionPacket;
    volatile int ConnectionResponsePacket;
    volatile int DisconnectResponsePacket;
    volatile int HandshakeChallengePacket;
    volatile int ChatReceivedPacket;
    volatile int UniverseTimeUpdatePacket;
    volatile int CelestialResponsePacket;
    volatile int ClientConnectPacket;
    volatile int ClientDisconnectPacket;
    volatile int HandshakeResponsePacket;
    volatile int WarpCommandPacket;
    volatile int ChatSentPacket;
    volatile int CelestialRequestPacket;
    volatile int ClientContextUpdatePacket;
    volatile int WorldStartPacket;
    volatile int WorldStopPacket;
    volatile int TileArrayUpdatePacket;
    volatile int TileUpdatePacket;
    volatile int TileLiquidUpdatePacket;
    volatile int TileDamageUpdatePacket;
    volatile int TileModificationFailurePacket;
    volatile int GiveItemPacket;
    volatile int SwapContainerResultPacket;
    volatile int EnvironmentUpdatePacket;
    volatile int EntityInteractResultPacket;
    volatile int ModifyTileListPacket;
    volatile int DamageTilePacket;
    volatile int DamageTileGroupPacket;
    volatile int RequestDropPacket;
    volatile int SpawnEntityPacket;
    volatile int EntityInteractPacket;
    volatile int ConnectWirePacket;
    volatile int DisconnectAllWiresPacket;
    volatile int OpenContainerPacket;
    volatile int CloseContainerPacket;
    volatile int SwapContainerPacket;
    volatile int ItemApplyContainerPacket;
    volatile int StartCraftingContainerPacket;
    volatile int StopCraftingContainerPacket;
    volatile int BurnContainerPacket;
    volatile int ClearContainerPacket;
    volatile int WorldClientStateUpdatePacket;
    volatile int EntityCreatePacket;
    volatile int EntityUpdatePacket;
    volatile int EntityDestroyPacket;
    volatile int DamageNotificationPacket;
    volatile int StatusEffectRequestPacket;
    volatile int UpdateWorldPropertiesPacket;
    volatile int HeartbeatPacket;

    public String packetStats() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(this.getClass().getName());
        result.append(" Object {");
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for (Field field : fields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                //requires access to private field:
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }
}

