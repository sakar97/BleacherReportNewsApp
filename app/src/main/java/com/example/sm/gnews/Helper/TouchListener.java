package com.example.sm.gnews.Helper;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.sm.gnews.RecyclerViewAdapter;

public class TouchListener extends ItemTouchHelper.SimpleCallback {

    private TouchListenerListener touch;
    public TouchListener(int dragDirs, int swipeDirs,TouchListenerListener touch) {
        super(dragDirs, swipeDirs);
        this.touch=touch;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        if(touch!=null){
            touch.onSwiped(viewHolder,direction,viewHolder.getAdapterPosition());
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        View foregroundView=((RecyclerViewAdapter.MyViewHolder)viewHolder).itemView;
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            View fore = ((RecyclerViewAdapter.MyViewHolder) viewHolder).itemView;
            getDefaultUIUtil().onSelected(fore);
        }
    }

        @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View fore=((RecyclerViewAdapter.MyViewHolder)viewHolder).itemView;
        getDefaultUIUtil().onDraw(c,recyclerView,fore,dX,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View fore=((RecyclerViewAdapter.MyViewHolder)viewHolder).itemView;
        getDefaultUIUtil().onDrawOver(c,recyclerView,fore,dX,dY,actionState,isCurrentlyActive);
    }
}
