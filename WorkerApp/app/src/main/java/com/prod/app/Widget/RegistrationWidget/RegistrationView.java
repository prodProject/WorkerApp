package com.prod.app.Widget.RegistrationWidget;

import android.content.Context;
import android.content.SharedPreferences;

import com.prod.app.ControlFlows.RegistrationControlFlow;
import com.prod.app.Helper.RegistrationHelper;
import com.prod.app.Interfaces.IContext;
import com.prod.app.protobuff.Gender;
import com.prod.app.protobuff.Registration;
import com.prod.app.protobuff.Time;

public class RegistrationView implements IContext {

    private Context m_context;
    private RegistrationHelper m_helper;
    private RegistrationControlFlow m_registrationControlFlow;
    private Registration.RegistrationRequestPb.Builder m_registrationRequestPb;

    public RegistrationView(Context context) {
        m_context = context;
        m_helper = new RegistrationHelper();
        m_registrationControlFlow = new RegistrationControlFlow(getActivityContext());
        m_registrationRequestPb = Registration.RegistrationRequestPb.newBuilder();
    }

    public void performRegistration(String firstName, String lastName, String email, String mobileNumber, String password) {
        m_registrationRequestPb = m_helper.getRegistrationRequestPb(firstName, lastName, email, mobileNumber, password);
        getFirebasePushNotification();
        m_registrationControlFlow.startRegistration(m_registrationRequestPb.build());
    }

    public void setDateOfBirthToRegistartionRequestPb(Time.TimePb dob) {
        m_registrationRequestPb.getWorkerBuilder().setDob(dob);
    }

    @Override
    public Context getActivityContext() {
        return m_context;
    }

    @Override
    public void setActivityContext(Context context) {
        m_context = context;
    }

    public void setGenderToRegistrationRequestPb(Gender.GenderEnum gender) {
        m_registrationRequestPb.getWorkerBuilder().getGenderBuilder().setGender(gender);
    }

    public void getFirebasePushNotification() {
        SharedPreferences prefd = getActivityContext().getSharedPreferences("firebaetokenId", Context.MODE_PRIVATE);
        m_registrationRequestPb.setPushNotificationToken(String.valueOf(prefd.getString("firebaetokenId", null)));
    }
}
