package jonas.androidparty.networking.sheduler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * class created by jonasseputis on 18/11/18
 */
@Module
public class SchedulersModule {
    private final static long KEEP_ALIVE_TIME = 1L;
    private final static int MAXIMUM_POOL_SIZE = 5;
    private final static int CORE_POOL_SIZE = 5;

    @Main
    @Provides
    public Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Network
    @Provides
    public Scheduler provideNetworkScheduler() {
        return Schedulers.from(new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>()
        ));
    }
}
