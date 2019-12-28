package com.prod.app.Widget.DownloadImageWidget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.prod.app.Interfaces.IView;
import com.prod.app.R;

public class DownloadImageWidget extends LinearLayout implements IView<DownloadImageView> {


    private Context m_context;
    private ImageView m_imageView;

    public DownloadImageWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_context=context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.download_image_layout, this);
        inflateLayout();
        if (!isInEditMode()) {
            initWidget();
        }
    }

    private void inflateLayout() {
        m_imageView = findViewById(R.id.imangeview);
        FragmentActivity activity = (FragmentActivity)getActivityContext();
    }

    private void initWidget() {

    }

    public void getImageFromUrl(String url){
        Glide.with(getActivityContext()).load(url).into(m_imageView);
    }


    @Override
    public DownloadImageView getView() {
        return null;
    }

    public Context getActivityContext() {
        return m_context;
    }
}
