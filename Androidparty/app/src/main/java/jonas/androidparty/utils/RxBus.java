package jonas.androidparty.utils;

/**
 * class created by jonasseputis on 18/11/18
 */
public class RxBus {

    private static RxBus instance;

    private RxBus() {

    }

    public static RxBus instanceOf() {
        if (instance == null) {
            instance = new RxBus();
        }
        return instance;
    }
}
