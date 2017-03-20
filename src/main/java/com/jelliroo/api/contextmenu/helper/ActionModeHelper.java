package com.jelliroo.api.contextmenu.helper;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.jelliroo.api.contextmenu.R;
import com.jelliroo.api.contextmenu.adapters.OnGestureAdapter;
import com.jelliroo.api.contextmenu.adapters.OnItemTouchAdapter;
import com.jelliroo.api.contextmenu.adapters.SupportCallbackAdapter;
import com.jelliroo.api.contextmenu.interfaces.ActionModeHelperInterface;
import com.jelliroo.api.contextmenu.listeners.ActionModeListener;

import java.util.List;

/**
 * Created on 3/20/2017 by roger
 */

@SuppressWarnings({"unused", "WeakerAccess"})
public class ActionModeHelper {

    private GestureDetector gestureDetector;

    private RecyclerView recyclerView;

    private ActionMode actionMode;

    private Integer menuId;

    private String singularItemTitle = "", pluralItemTitle = "";

    private boolean multiSelectEnabled = true;

    private boolean singleSelectEnabled = true;

    /**
     *  multi sing
     *  true  true
     *  true  false
     *  false true
     *  false false
     */

    private Context context;

    private ActionModeListener actionModeListener;

    private ActionModeHelperInterface actionModeHelperInterface;

    public ActionModeHelper(Context context){
        this.context = context;
    }

    public void setActionModeListener(ActionModeListener actionModeListener) {
        this.actionModeListener = actionModeListener;
    }

    public void setActionModeHelperInterface(ActionModeHelperInterface actionModeHelperInterface) {
        this.actionModeHelperInterface = actionModeHelperInterface;
    }

    public final void init(){
        final SupportCallbackAdapter supportCallbackAdapter = new SupportCallbackAdapter() {
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                List<?> selectedItemPositions = actionModeHelperInterface.getAdapter().getSelectedItems();
                return actionModeListener.onContextMenuItemSelected(selectedItemPositions , item.getItemId());
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                if(menuId != null)
                    inflater.inflate(menuId, menu);
                else inflater.inflate(R.menu.menu, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                super.onDestroyActionMode(mode);
                if(actionMode != null){
                    actionModeHelperInterface.getAdapter().clearSelections();
                    actionMode = null;
                }
            }
        };

        OnGestureAdapter onGestureAdapter = new OnGestureAdapter() {




            @Override
            public void onLongPress(MotionEvent e) {

                if(multiSelectEnabled && singleSelectEnabled){
                    View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    int position = recyclerView.getChildAdapterPosition(view);
                    if(position == -1) return;
                    if (actionMode != null) {
                        return;
                    }
                    actionMode = ((AppCompatActivity) context).startSupportActionMode(supportCallbackAdapter);
                    toggleSelection(position);
                    actionModeListener.onContextMenuOpened();
                }


            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                int position = recyclerView.getChildAdapterPosition(view);
                if(multiSelectEnabled && singleSelectEnabled) {
                    if (actionMode == null) {
                        actionModeListener.onItemSelected(actionModeHelperInterface.getAdapter().getItemAt(position));
                    } else {
                        toggleSelection(position);
                    }

                } else if(!multiSelectEnabled && singleSelectEnabled){
                    actionModeListener.onItemSelected(actionModeHelperInterface.getAdapter().getItemAt(position));
                } else if(multiSelectEnabled && !singleSelectEnabled){
                    if(actionMode == null){
                        if(position == -1) return true;
                        if (actionMode != null) {
                            return true;
                        }
                        actionMode = ((AppCompatActivity) context).startSupportActionMode(supportCallbackAdapter);
                        toggleSelection(position);
                        actionModeListener.onContextMenuOpened();
                    } else {
                        toggleSelection(position);
                    }
                }
                return true;
            }
        };

        gestureDetector = new GestureDetector(context, onGestureAdapter);
    }

    public void toggleSelection(int pos) {
        if(pos == -1) return;

        actionModeHelperInterface.getAdapter().toggleSelection(pos);
        actionModeHelperInterface.getAdapter().notifyItemChanged(pos);
        updateActionModeTitle();
        if(actionModeHelperInterface.getAdapter().getSelectedItemCount() == 0){
            exitActionMode();
        }
    }

    public final void toggleSelectAll(){
        actionModeHelperInterface.getAdapter().toggleSelectAll();
        updateActionModeTitle();
    }

    public final void updateActionModeTitle() {
        if(actionModeHelperInterface.getAdapter().getSelectedItemCount() > 1){
            actionMode.setTitle(actionModeHelperInterface.getAdapter().getSelectedItemCount() + " " + pluralItemTitle);
        } else {
            actionMode.setTitle(actionModeHelperInterface.getAdapter().getSelectedItemCount() + " " + singularItemTitle);
        }
    }

    public final void setRecyclerView(final RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        OnItemTouchAdapter onItemTouchAdapter = new OnItemTouchAdapter() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                gestureDetector.onTouchEvent(e);
                return false;
            }
        };
        recyclerView.addOnItemTouchListener(onItemTouchAdapter);
    }

    public final void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public final void exitActionMode(){
        if(actionMode != null){
            actionModeHelperInterface.getAdapter().clearSelections();
            actionMode.finish();
            actionMode = null;
            actionModeListener.onContextMenuClosed();
        }
    }

    public final void setSingularItemTitle(String singularItemTitle) {
        this.singularItemTitle = singularItemTitle;
    }

    public final void setPluralItemTitle(String pluralItemTitle) {
        this.pluralItemTitle = pluralItemTitle;
    }

    public void setMultiSelectEnabled(boolean multiSelectEnabled) {
        this.multiSelectEnabled = multiSelectEnabled;
    }

    public void setSingleSelectEnabled(boolean singleSelectEnabled) {
        this.singleSelectEnabled = singleSelectEnabled;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

}
