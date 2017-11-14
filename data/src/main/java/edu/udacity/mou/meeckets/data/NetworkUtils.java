package edu.udacity.mou.meeckets.data;

/**
 * Created by mou on 11/13/17.
 */

public class NetworkUtils {

    public static boolean fakeRequest(int failRate) {
        return fakeRequest(failRate, 1000);
    }

    public static boolean fakeRequest(int failRate, int meanTime) {
        int minTime = meanTime / 2;
        int waitTime = (int) Math.round(Math.random() * meanTime + minTime);

        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Math.random() > (failRate / 100.0);
    }
}
