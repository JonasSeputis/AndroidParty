package jonas.androidparty.controller.list;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import jonas.androidparty.base.BaseRepository;
import jonas.androidparty.networking.AppApi;
import jonas.androidparty.networking.account.AccountHelper;
import jonas.androidparty.response.Server;

/**
 * class created by jonasseputis on 18/11/18
 */
public class ListRepository extends BaseRepository {

    @Inject
    public ListRepository(AppApi appApi, AccountHelper accountHelper) {
        super(appApi, accountHelper);
    }

    public Observable<List<Server>> getServers() {
        return appApi.servers(token());
    }
}
