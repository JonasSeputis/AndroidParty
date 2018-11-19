package jonas.androidparty.controller.list;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import jonas.androidparty.base.BasePresenter;
import jonas.androidparty.networking.sheduler.Main;
import jonas.androidparty.networking.model.response.Server;

/**
 * class created by jonasseputis on 18/11/18
 */
public class ListPresenter extends BasePresenter<ListView> {

    private ListRepository listRepository;
    private Scheduler scheduler;

    @Inject
    public ListPresenter(ListRepository listRepository, @Main Scheduler scheduler) {
        this.listRepository = listRepository;
        this.scheduler = scheduler;
    }

    public void retrieveServers() {
        serverObservable();
    }

    private void serverObservable() {
        getServerObservable().subscribe();
    }

    private Observable<List<Server>> getServerObservable() {
        return listRepository.getServers()
                .observeOn(scheduler)
                .doOnSubscribe(subscriptions::add)
                .doOnNext(servers -> {
                    Log.d("List", servers.toString());
                    if (hasView())
                        getView().retrieveServersList(servers);
                })
                .doOnError(e -> {
                });
    }
}
