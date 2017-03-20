package com.jelliroo.api.contextmenu.interfaces;

import android.support.v7.widget.RecyclerView;

import com.jelliroo.api.contextmenu.adapters.RecyclerViewAdapter;

/**
 * Created on 3/20/2017 by roger
 */

public interface ActionModeHelperInterface {

    RecyclerViewAdapter<? extends RecyclerView.ViewHolder, ?, ?> getAdapter();

}
