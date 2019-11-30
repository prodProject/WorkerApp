package com.prod.app.ActivityFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.prod.app.Enums.ScreenStateUiEnum;
import com.prod.app.R;


/**
 * Created by yarolegovich on 25.03.2017.
 */

public class CenteredTextFragment extends Fragment {

    private static final String EXTRA_TEXT = "text";

    public static CenteredTextFragment createFor(ScreenStateUiEnum screenStateUiEnum) {
        CenteredTextFragment fragment = new CenteredTextFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TEXT, screenStateUiEnum.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch (ScreenStateUiEnum.valueOf(getArguments().getString(EXTRA_TEXT))) {
            case POS_HOME:
                return inflater.inflate(R.layout.home_widget, container, false);
            case POS_MESSAGES:
            case POS_CART:
            case POS_LOGOUT:
            case POS_ACCOUNT:
                return inflater.inflate(R.layout.fragment_text, container, false);
        }
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final String text = getArguments().getString(EXTRA_TEXT);
        Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT).show();
    }
}