package com.prod.app.DeviceInfo;

import android.os.Build;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class DeviceInfo {

    public static String getDeviceName() {
        return Build.MODEL;
    }

    public static String getId() {
        return Build.ID;
    }

    public static String getDevice() {
        return Build.DEVICE;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getUser() {
        return Build.USER;
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    //res1.append(Integer.toHexString(b & 0xFF) + ":");
                    res1.append(String.format("%02X:", b));
                }
                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }
}
