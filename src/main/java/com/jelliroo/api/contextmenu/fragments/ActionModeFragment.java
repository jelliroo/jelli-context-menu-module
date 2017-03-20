package com.jelliroo.api.contextmenu.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.jelliroo.api.contextmenu.helper.ActionModeHelper;
import com.jelliroo.api.contextmenu.interfaces.ActionModeHelperInterface;
import com.jelliroo.api.contextmenu.listeners.ActionModeListener;

/**
 * Created on 3/9/2017 by roger
 */

@SuppressWarnings("unused")
public abstract class ActionModeFragment extends Fragment implements ActionModeListener, ActionModeHelperInterface {

    private ActionModeHelper actionModeHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionModeHelper = new ActionModeHelper(this.getActivity());
        actionModeHelper.setActionModeListener(this);
        actionModeHelper.setActionModeHelperInterface(this);
        actionModeHelper.init();
    }

    private void toggleSelection(int pos) {
        actionModeHelper.toggleSelection(pos);
    }

    public final void toggleSelectAll(){
        actionModeHelper.toggleSelectAll();
    }

    protected final void updateActionModeTitle() {
        actionModeHelper.updateActionModeTitle();
    }

    protected final void setRecyclerView(final RecyclerView recyclerView) {
        actionModeHelper.setRecyclerView(recyclerView);
    }

    protected final void setRecyclerView(final int recyclerViewId) {
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(recyclerViewId);
        actionModeHelper.setRecyclerView(recyclerView);
    }

    protected final void setMenuId(int menuId) {
        actionModeHelper.setMenuId(menuId);
    }

    protected final void exitActionMode(){
        actionModeHelper.exitActionMode();
    }

    protected final void setSingularItemTitle(String singularItemTitle) {
        actionModeHelper.setSingularItemTitle(singularItemTitle);
    }

    protected final void setPluralItemTitle(String pluralItemTitle) {
        actionModeHelper.setPluralItemTitle(pluralItemTitle);
    }

    protected void setMultiSelectEnabled(boolean multiSelectEnabled) {
        actionModeHelper.setMultiSelectEnabled(multiSelectEnabled);
    }

    public void setSingleSelectEnabled(boolean singleSelectEnabled) {
        actionModeHelper.setSingleSelectEnabled(singleSelectEnabled);
    }

    protected RecyclerView getRecyclerView(){
        return actionModeHelper.getRecyclerView();
    }
}
