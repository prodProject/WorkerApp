package com.prod.app.Widget.MapWidget;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.prod.app.R;

public class MapWidget extends Fragment {

    private MapView m_view;
    private Context m_context;

    public MapWidget() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.map_layout, container, false);
    }


    private void init(Context context, AttributeSet attrs) {


    }

    private void inflateLayout() {
    }

    private void initWidget() {

    }

    public MapView getWidgetView() {
        return m_view;
    }
}
