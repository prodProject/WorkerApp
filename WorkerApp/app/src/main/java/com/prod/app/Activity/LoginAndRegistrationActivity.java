package com.prod.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.prod.app.R;
import com.prod.app.Widget.LoginWidget.LoginWidget;
import com.prod.app.Widget.RegistrationWidget.RegistrationWidget;

public class LoginAndRegistrationActivity extends AppCompatActivity {

    private Button m_toggleButton;
    private RegistrationWidget m_registration;
    private LoginWidget m_login;
    private boolean m_showWidget = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_registration);
        getButtonName();
        m_toggleButton = findViewById(R.id.toggleButton);
        m_registration = findViewById(R.id.registration);
        m_login = findViewById(R.id.login);
        m_toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getButtonName();
            }
        });
        getButtonName();
    }

    private void getButtonName() {
        if (m_showWidget) {
            m_toggleButton.setText("Registration");
            m_login.setVisibility(View.GONE);
            m_registration.setVisibility(View.VISIBLE);
            m_showWidget=true;
        } else {
            m_toggleButton.setText("Login");
            m_login.setVisibility(View.VISIBLE);
            m_registration.setVisibility(View.GONE);
            m_showWidget=false;
        }
    }

}
