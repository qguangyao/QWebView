package com.qt.qwebview.customview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class FullScreenHolder extends FrameLayout {
    public FullScreenHolder(Context context) {
        super(context);
        this.setBackgroundColor(ContextCompat.getColor(context,(android.R.color.black)));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}