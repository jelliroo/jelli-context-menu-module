package com.jelliroo.api.contextmenu.listeners;

import java.util.List;

/**
 * Created on 3/6/2017 by roger
 */

public interface ActionModeListener {

    void onContextMenuClosed();

    void onContextMenuOpened();

    boolean onContextMenuItemSelected(List selectedItems, int actionCode);

    void onItemSelected(Object object);

}
