package com.example.sm.gnews.Helper;

import android.support.v7.widget.RecyclerView;

public interface TouchListenerListener {

void onSwiped(RecyclerView.ViewHolder viewHolder,int direction,int position);

}
