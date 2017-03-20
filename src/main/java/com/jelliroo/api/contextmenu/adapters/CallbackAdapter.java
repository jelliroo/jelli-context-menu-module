package com.jelliroo.api.contextmenu.adapters;


import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created on 3/5/2017 by roger
 * Convenience adapter
 */

public abstract class CallbackAdapter implements ActionMode.Callback {
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}
