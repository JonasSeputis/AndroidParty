package jonas.androidparty.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jonas.androidparty.R;
import jonas.androidparty.networking.model.response.Server;

/**
 * class created by jonasseputis on 18/11/18
 */
public class ServersListAdapter extends RecyclerView.Adapter<ServersListAdapter.ViewHolder> {

    private List<Server> serversList;

    @Override
    public ServersListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_object, parent, false);
        return new ServersListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServersListAdapter.ViewHolder holder, int position) {
        holder.bind(serversList.get(position));
    }

    @Override
    public int getItemCount() {
        if (serversList == null) {
            return 0;
        }
        return serversList.size();
    }

    public void setServersList(List<Server> serversList) {
        this.serversList = serversList;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.server_name_field)
        public TextView serverItem;
        @BindView(R.id.server_distance_item)
        public TextView distanceItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Server serverObject) {
            serverItem.setText(serverObject.getServerName());
            distanceItem.setText(String.format("%s km", String.valueOf(serverObject.getDistance())));
        }
    }
}
