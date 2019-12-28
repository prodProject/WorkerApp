package com.prod.app.Widget.LoginWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.prod.app.Interfaces.IView;
import com.prod.app.R;

public class LoginWidget extends LinearLayout implements IView<LoginView> {

    private EditText m_emailOrPhone;
    private EditText m_password;
    private Button m_login;

    private LoginView m_view;

    public LoginWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        getView().setActivityContext(context);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.login_widget, this);
        inflateLayout();
        if (!isInEditMode()) {
            initWidget();
        }
    }

    private void inflateLayout() {
        m_emailOrPhone = findViewById(R.id.emailOrMobile);
        m_password = findViewById(R.id.password);
        m_login = findViewById(R.id.loginButton);
    }

    private void initWidget() {

    }


    @Override
    public LoginView getView() {
        return m_view;
    }
}
