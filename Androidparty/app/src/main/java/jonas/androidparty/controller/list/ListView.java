package jonas.androidparty.controller.list;

import java.util.List;

import jonas.androidparty.response.Server;

/**
 * class created by jonasseputis on 18/11/18
 */
public interface ListView {

    void retrieveServersList(List<Server> servers);
}
