package com.prod.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.prod.app.R;
import com.prod.app.SessionsManger.WorkerSession;

public class RegistrationActivity extends AppCompatActivity {

    private WorkerSession m_session;
    private Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        click = (Button) findViewById(R.id.b2);
        m_session = new WorkerSession();
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("regvalue",m_session.getSession().getType().getPersonType().name());
            }
        });
    }
}
