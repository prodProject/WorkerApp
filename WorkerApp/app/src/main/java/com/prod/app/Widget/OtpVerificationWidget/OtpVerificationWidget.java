package com.prod.app.Widget.OtpVerificationWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.prod.app.Activity.VerifyPhoneByFirebase;
import com.prod.app.Interfaces.IView;
import com.prod.app.R;
import com.prod.app.Utility.AndroidUtility;

import org.greenrobot.greendao.annotation.Id;

public class OtpVerificationWidget extends LinearLayout implements IView<OtpVerificationView> {

    private Button m_sendCodeButton, m_verifyButton;
    private EditText m_phoneNumber, m_otpcode;
    private LinearLayout m_sendOtpLayout, m_verifyOtpLayout;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String verificationId;


    private OtpVerificationView m_view;


    public OtpVerificationWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.activity_verify_phone_by_firebase, this);
        inflateLayout();
        if (!isInEditMode()) {
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.activity_verify_phone_by_firebase, this);
        /*m_sendOtpLayout = (LinearLayout) findViewById(R.id.numberPanel);
        m_verifyOtpLayout = (LinearLayout) findViewById(R.id.verify);*/
        m_otpcode = (EditText) findViewById(R.id.editTextCode);
        m_phoneNumber = (EditText) findViewById(R.id.editTextPhone);
        m_verifyButton = (Button) findViewById(R.id.BUTTON1);
        m_sendCodeButton = (Button) findViewById(R.id.getverificationcode);
        m_view = new OtpVerificationView(getContext());
        m_verifyOtpLayout.setVisibility(GONE);
        m_sendOtpLayout.setVisibility(VISIBLE);
    }

    private void initWidget() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                String code = phoneAuthCredential.getSmsCode();
                if (code != null) {
                    editTextCode.setText(code);
                    verifySignInCode(code);
                }
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(VerifyPhoneByFirebase.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId = s;
            }
        };


        m_sendCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().sendVerificationCode(AndroidUtility.getTextFromEditText(m_phoneNumber), mCallbacks);
                switchLayout();
            }
        });
    }

    private void switchLayout() {
        m_sendOtpLayout.setVisibility(GONE);
        m_verifyOtpLayout.setVisibility(VISIBLE);
    }

    @Override
    public OtpVerificationView getView() {
        return m_view;
    }
}
