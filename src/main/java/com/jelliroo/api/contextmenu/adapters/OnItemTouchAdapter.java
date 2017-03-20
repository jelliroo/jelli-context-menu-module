package com.jelliroo.api.contextmenu.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created on 3/5/2017 by roger
 * Convenience adapter
 */

public abstract class OnItemTouchAdapter implements RecyclerView.OnItemTouchListener {
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
