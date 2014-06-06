package util.timers;

/**
 * This class will sleep the thread in which it was called from.
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 17 May 2014
 */

public class ThreadSleep {

    public ThreadSleep() {
    }

    /**
     * This method will sleep the thread in which it was called from.
     *
     * @param timer A integer that represents the amount of seconds to sleep the
     *              thread.
     * @version 1.0, 17 May 2014 (Incomplete)
     */

    public void timer(int timer) {
        try {
            Thread.sleep(timer * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
