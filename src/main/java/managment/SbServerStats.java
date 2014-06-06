package managment;


public class SbServerStats {

    /* Time Trackers */
    private static long snOnlineTime; /* Java time stamp in milliseconds */
    private static long sbOnlineTime; /* Divide by /1000 for Unix time stamp */
    private static long lastAutoRestart;

    /* For Ever Tracker */ //TODO Load from file
    private static int sbAutoRestarts = 0;
    private static int sbCrashes = 0;
    private static int sbUnresponsive = 0;
    //TODO Player joins
    //TODO Unique players seen

    /* Since Wrapper Start */
    private static int sbAutoRestartsWrap = 0;
    private static int sbCrashesWrap = 0;
    private static int sbUnresponsiveWrap = 0;

    /* Since SB Last Auto Restart */
    private static int sbCrashesTemp = 0;
    private static int sbUnresponsiveTemp = 0;


    public SbServerStats() {
    }

    private void statsLoader() {

    }

    private void statsSaver() {

    }

    public long getSnOnlineTime() {
        return snOnlineTime;
    }

    public long getSbOnlineTime() {
        return sbOnlineTime;
    }

    public long getSnUptime() {
        return System.currentTimeMillis() - snOnlineTime;
    } //TODO Formatting

    public long getSbUptime() {
        return System.currentTimeMillis() - sbOnlineTime;
    } //TODO Formatting

    public long getLastAutoRestart() {
        return lastAutoRestart;
    } //TODO FORMATING

    public int getSbAutoRestarts() {
        return sbAutoRestarts;
    }

    public int getSbAutoRestartsWrap() {
        return sbAutoRestartsWrap;
    }

    public int getSbCrashes() {
        return sbCrashes;
    }

    public int getSbCrashesWrap() {
        return sbCrashesWrap;
    }

    public int getSbCrashesTemp() {
        return sbCrashesTemp;
    }

    public int getSbUnresponsive() {
        return sbUnresponsive;
    }

    public int getSbUnresponsiveWrap() {
        return sbUnresponsiveWrap;
    }

    public int getSbUnresponsiveTemp() {
        return sbUnresponsiveTemp;
    }

    public void setSnOnlineTime() {
        snOnlineTime = System.currentTimeMillis();
    }

    public void setSbOnlineTime() {
        sbOnlineTime = System.currentTimeMillis();
    }

    public void setLastAutoRestart() {
        lastAutoRestart = System.currentTimeMillis();
    }

    public void addSbAutoRestarts() {
        sbAutoRestarts += 1;
        sbAutoRestartsWrap += 1;
    }

    public void addSbCrashes() {
        sbCrashes += 1;
        sbCrashesWrap += 1;
        sbCrashesTemp += 1;
    }

    public void addSbUnresponsive() {
        sbUnresponsive += 1;
        sbUnresponsiveWrap += 1;
        sbUnresponsiveTemp += 1;
    }

    public void resetTempStats() {
        sbCrashesTemp = 0;
        sbUnresponsiveTemp = 0;
        sbOnlineTime = System.currentTimeMillis();
    }
}
