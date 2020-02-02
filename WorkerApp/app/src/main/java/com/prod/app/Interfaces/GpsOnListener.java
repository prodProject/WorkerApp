package com.prod.app.Interfaces;

import android.location.Location;

public interface GpsOnListener {

    public void gpsStatus(boolean _status);
    public void gpsPermissionDenied(int deviceGpsStatus);
    public void gpsLocationFetched(Location location);
}