package jonas.androidparty.controller.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bluelinelabs.conductor.Controller;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jonas.androidparty.AndroidApp;
import jonas.androidparty.R;
import jonas.androidparty.activity.MainActivity;
import jonas.androidparty.adapter.ServersListAdapter;
import jonas.androidparty.networking.account.AccountHelper;
import jonas.androidparty.networking.model.response.Server;

/**
 * class created by jonasseputis on 18/11/18
 */
public class ListController extends Controller implements ListView,
        RecyclerView.OnChildAttachStateChangeListener, ImageButton.OnClickListener {

    private ServersListAdapter serversAdapter;
    private List<Server> servers;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.overlay_layout)
    View overlayView;
    @BindView(R.id.logout_button)
    ImageButton logoutButton;

    @Inject
    ListPresenter presenter;
    @Inject
    AccountHelper accountHelper;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View root = inflater.inflate(R.layout.controller_list, container, false);
        ButterKnife.bind(this, root);
        DaggerListComponent.builder().iApplicationComponent(AndroidApp.getAppComponent(getActivity())).build().inject(this);
        presenter.setView(this);
        presenter.retrieveServers();

        if (serversAdapter == null)
            serversAdapter = new ServersListAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (recyclerView != null && recyclerView.getAdapter() == null)
            recyclerView.setAdapter(serversAdapter);

        serversAdapter.setServersList(servers);
        serversAdapter.notifyDataSetChanged();

        recyclerView.addOnChildAttachStateChangeListener(this);

        logoutButton.setOnClickListener(this);
        return root;
    }

    @Override
    public void retrieveServersList(List<Server> servers) {
        this.servers = servers;
        if (servers != null)
            updateServersList(servers);
    }

    private void updateServersList(List<Server> servers) {
        serversAdapter.setServersList(servers);
        serversAdapter.notifyDataSetChanged();
    }

    @Override
    public void onChildViewAttachedToWindow(View view) {
        hideOverlay();
    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {

    }

    private void hideOverlay() {
        overlayView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        accountHelper.removeAccount();
        ((MainActivity) getActivity()).setLoginRoot();
    }
}
