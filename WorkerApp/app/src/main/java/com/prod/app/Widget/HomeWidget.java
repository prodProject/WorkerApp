package com.prod.app.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.prod.app.R;

public class HomeWidget extends LinearLayout {
    public HomeWidget(Context context) {
        super(context, null);
    }

    public HomeWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public HomeWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout();
        if (!isInEditMode()) {
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.home_widget_layout, this);

    }

    private void initWidget() {

    }
}

