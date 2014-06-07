package network.packets;

import network.Packet;
import network.StarboundStream;

/**
 * Packet Class.
 * <p>
 * Credit goes to: <br>
 * SirCmpwn - (https://github.com/SirCmpwn/StarNet) <br>
 * Mitch528 - (https://github.com/Mitch528/SharpStar) <br>
 * Starbound-Dev - (http://starbound-dev.org/)
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 24 May 2014
 */
public class HeartbeatPacket extends Packet {

    /**
     * The Server sends steps incremented by one, the client responds every 3, increments of 3.
     */
    private long CurrentStep;

    @Override
    public byte getPacketId() {
        return 48;
    }

    /**
     * @return the currentStep
     */
    public long getCurrentStep() {
        return CurrentStep;
    }

    /**
     * @param currentStep the currentStep to set
     */
    public void setCurrentStep(long currentStep) {
        CurrentStep = currentStep;
    }

    @Override
    public void Read(StarboundStream stream) {
        CurrentStep = stream.readVLQ().getValue();
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeVLQ(CurrentStep);
    }
}
