package com.prod.app.Helper;

import com.prod.app.CommonCode.ProtoJsonUtil;
import com.prod.app.CommonCode.Strings;
import com.prod.app.DeviceInfo.DeviceInfo;
import com.prod.app.Enums.OSTypeEnum;
import com.prod.app.LocalDatabase.LoginEntity;
import com.prod.app.protobuff.Gender;
import com.prod.app.protobuff.Login;
import com.prod.app.protobuff.Mobile;
import com.prod.app.protobuff.Persontypeenum;
import com.prod.app.protobuff.Registration;
import com.prod.app.protobuff.Time;
import com.prod.app.protobuff.Worker;

import java.io.IOException;

public class RegistrationHelper {


    public Registration.RegistrationRequestPb.Builder getRegistrationRequestPb(String firstName, String lastName, String email, String mobileNumber, String password) {
        Registration.RegistrationRequestPb.Builder builder = Registration.RegistrationRequestPb.newBuilder();
        Worker.WorkerPb.Builder workerBuilder = builder.getWorkerBuilder();
        workerBuilder.getNameBuilder().setFirstName(firstName);
        workerBuilder.getNameBuilder().setLastName(lastName);
        workerBuilder.getContactDetailsBuilder().setEmail(Strings.getEmailIdPbFromStringId(email));
        workerBuilder.getContactDetailsBuilder().getPrimaryMobileBuilder().setCode(Mobile.CountryCodeEnum.ISD_91).setNumber(mobileNumber);
        workerBuilder.getTypeBuilder().setPersonType(Persontypeenum.PersonTypeEnum.WORKER);
        workerBuilder.getDeviceBuilder().setOsType(OSTypeEnum.ANDROID.name()).setModel(DeviceInfo.getDeviceName()).setDeviceName(DeviceInfo.getBrand()).setMacId(DeviceInfo.getMacAddr());
        workerBuilder.getGenderBuilder().setGender(Gender.GenderEnum.FEMALE);
        workerBuilder.getDobBuilder().setDate("21").setMonth("04").setTimezone(Time.TimeZoneEnum.IST).setYear("1997");
        workerBuilder.build();
        builder.setPassword(password);
        return builder;
    }

    public Registration.RegistrationRequestPb getRegistrationRequestPb(Registration.RegistrationRequestPb registrationRequestPb) {
        Registration.RegistrationRequestPb.Builder builder = registrationRequestPb.toBuilder();
        Worker.WorkerPb.Builder workerBuilder = builder.getWorkerBuilder();
        workerBuilder.getTypeBuilder().setPersonType(Persontypeenum.PersonTypeEnum.WORKER);
        workerBuilder.getDeviceBuilder().setOsType(OSTypeEnum.ANDROID.name()).setModel(DeviceInfo.getDeviceName()).setDeviceName(DeviceInfo.getBrand()).setMacId(DeviceInfo.getMacAddr());
        return builder.build();
    }

    public LoginEntity getLoginEntityFromLoginPb(Login.LoginPb pb) {
        LoginEntity entity = new LoginEntity();
        entity.setId(1l);
        entity.setEid(pb.getDbInfo().getId());
        try {
            entity.setData(ProtoJsonUtil.toJson(pb));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public Login.LoginPb getLoginPb(Login.LoginPb login, String textFromEditText) {
        Login.LoginPb.Builder loginPb = login.toBuilder();
        loginPb.setPassword(textFromEditText);
        return loginPb.build();
    }
}
