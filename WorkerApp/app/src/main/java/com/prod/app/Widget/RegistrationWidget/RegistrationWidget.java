package com.prod.app.Widget.RegistrationWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentActivity;

import com.prod.app.Interfaces.IView;
import com.prod.app.R;
import com.prod.app.Utility.AndroidUtility;
import com.prod.app.protobuff.Gender;
import com.prod.app.protobuff.Time;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class RegistrationWidget extends LinearLayout implements IView<RegistrationView>, DatePickerDialog.OnDateSetListener {
    private EditText m_firstName;
    private EditText m_lastName;
    private EditText m_email;
    private EditText m_phone;
    private EditText m_password;
    private EditText m_dateOfBirth;
    private RadioGroup m_genderRadioGroup;
    private RadioButton m_genderRadioButton;
    private Button m_registration;

    private RegistrationView m_view;

    public RegistrationWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_view = new RegistrationView(context);
        getView().setActivityContext(context);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.registration_widget, this);
        inflateLayout();
        if (!isInEditMode()) {
            initWidget();
        }
    }

    private void inflateLayout() {
        m_firstName = findViewById(R.id.firstname);
        m_lastName = findViewById(R.id.lastName);
        m_email = findViewById(R.id.email);
        m_phone = findViewById(R.id.mobileNumber);
        m_password = findViewById(R.id.password);
        m_dateOfBirth = findViewById(R.id.dateOfbirth);
        m_registration = findViewById(R.id.signUp);
        m_genderRadioGroup = findViewById(R.id.genderGroup);
    }

    private void initWidget() {
        m_dateOfBirth.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        RegistrationWidget.this,
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
                FragmentActivity activity = (FragmentActivity) getView().getActivityContext();
                dpd.show(activity.getSupportFragmentManager(), "dateDailogBox");
                return false;
            }
        });
        m_registration.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                m_genderRadioButton = (RadioButton) findViewById(m_genderRadioGroup.getCheckedRadioButtonId());
                getView().setGenderToRegistrationRequestPb(Gender.GenderEnum.valueOf(m_genderRadioButton.getText().toString().toUpperCase()));
                getView().performRegistration(AndroidUtility.getTextFromEditText(m_firstName),
                        AndroidUtility.getTextFromEditText(m_lastName),
                        AndroidUtility.getTextFromEditText(m_email),
                        AndroidUtility.getTextFromEditText(m_phone),
                        AndroidUtility.getTextFromEditText(m_password));
            }
        });

    }


    @Override
    public RegistrationView getView() {
        return m_view;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        m_dateOfBirth.setText("" + dayOfMonth + "/" + monthOfYear + "/" + year);
        Time.TimePb.Builder dob = Time.TimePb.newBuilder();
        dob.setYear(String.valueOf(year));
        dob.setMonth(String.valueOf(monthOfYear));
        dob.setDate(String.valueOf(dayOfMonth));
        dob.setTimezone(Time.TimeZoneEnum.IST);
        getView().setDateOfBirthToRegistartionRequestPb(dob.build());
    }
}
//use this widget paste below code in activity.
/***
 *  SharedPreferences sharedPrefrences =  getApplicationContext().getSharedPreferences("firebaetokenId",MODE_PRIVATE);
 *                         SharedPreferences.Editor editor = sharedPrefrences.edit();
 *                         editor.putString("firebaetokenId", token);
 *                         editor.commit();
 ***/