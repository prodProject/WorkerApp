package com.prod.app.Widget.OtpVerificationWidget;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.prod.app.Activity.VerifyPhoneByFirebase;
import com.prod.app.Interfaces.IContext;

import java.util.concurrent.TimeUnit;

public class OtpVerificationView implements IContext {

    private FirebaseAuth mAuth;
    private Context m_context;


    public OtpVerificationView() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void sendVerificationCode(String number, PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivityContext().getApplicationContext(),
                                    "LoginSuccessfull", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivityContext().getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void verifySignInCode(String code, String verificationId) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    @Override
    public Context getActivityContext() {
        return m_context;
    }

    @Override
    public void setActivityContext(Context context) {
        m_context = context;
    }
}
