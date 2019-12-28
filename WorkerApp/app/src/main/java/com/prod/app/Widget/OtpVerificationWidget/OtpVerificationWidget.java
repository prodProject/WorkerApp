package com.prod.app.Widget.OtpVerificationWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.prod.app.Activity.VerifyPhoneByFirebase;
import com.prod.app.Interfaces.IView;
import com.prod.app.R;
import com.prod.app.Utility.AndroidUtility;

import org.greenrobot.greendao.annotation.Id;

import java.util.concurrent.TimeUnit;

public class OtpVerificationWidget extends LinearLayout implements IView<OtpVerificationView> {

    private Button m_getverificationcode, m_BUTTON1;
    private EditText m_editTextPhone, m_editTextCode;
    private LinearLayout m_sendOtpLayout, m_verifyOtpLayout;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String verificationId;
    private String number;



    private OtpVerificationView m_view;


    public OtpVerificationWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_view = new OtpVerificationView();
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.activity_verify_phone_by_firebase, this);
        getView().setActivityContext(context);
        inflateLayout();
        if (!isInEditMode()) {
            initWidget();
        }
    }

    private void inflateLayout() {

        /*m_sendOtpLayout = (LinearLayout) findViewById(R.id.numberPanel);
        m_verifyOtpLayout = (LinearLayout) findViewById(R.id.verify);*/
        m_editTextCode = (EditText) findViewById(R.id.editTextCode);
        m_editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        m_BUTTON1 = (Button) findViewById(R.id.BUTTON1);
        m_getverificationcode = (Button) findViewById(R.id.getverificationcode);

    }

    private void initWidget() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                String code = phoneAuthCredential.getSmsCode();
                if (code != null) {
                    m_editTextCode.setText(code);
                    getView().verifySignInCode(code,verificationId);
                }
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getView().getActivityContext().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId = s;
            }
        };

        m_getverificationcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().sendVerificationCode(AndroidUtility.getTextFromEditText(m_editTextPhone), mCallbacks);
            }
        });
    }

    @Override
    public OtpVerificationView getView() {
        return m_view;
    }
}
