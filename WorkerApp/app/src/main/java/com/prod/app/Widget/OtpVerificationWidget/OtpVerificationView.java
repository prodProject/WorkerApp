package com.prod.app.Widget.OtpVerificationWidget;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerificationView {

    private FirebaseAuth mAuth;
    private Context m_context;

    public OtpVerificationView(Context context) {
        m_context=context;
        mAuth = FirebaseAuth.getInstance();
    }

    public void sendVerificationCode(String mobile, PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    public void verifyVerificationCode(String phoneNumber, String code) {
        Log.e("otp","code");
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(phoneNumber, code);
        signInWithPhoneAuthCredential(credential);
    }

    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e("otp","sucessfull");

                        } else {
                            Log.e("otp","unsucessfull");
                        }
                    }
                });
    }
}
