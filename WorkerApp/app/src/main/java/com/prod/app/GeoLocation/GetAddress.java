package com.prod.app.GeoLocation;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.prod.app.protobuff.Address.AddressPb;

import java.util.List;
import java.util.Locale;

public class GetAddress {

    Activity activity;
    private AddressPb.Builder m_addressPb;

    public GetAddress(Activity context) {
        this.activity = context;
        m_addressPb = AddressPb.newBuilder();
    }

    public AddressPb fetchCurrentAddressPb(Location location) {
        try {

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(activity, Locale.getDefault());
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address[] = addresses.get(0).getAddressLine(0).split(",");
            m_addressPb.getLatlongBuilder().setLatitude(location.getLatitude());
            m_addressPb.getLatlongBuilder().setLongitude(location.getLongitude());
            m_addressPb.setStreetOrRoad(address[0]);
            m_addressPb.setArea(address[1]);
            m_addressPb.setLandmark(address[2]);
            m_addressPb.setCity(addresses.get(0).getLocality());
            m_addressPb.setState(addresses.get(0).getAdminArea());
            m_addressPb.setPincode(addresses.get(0).getPostalCode());
            m_addressPb.setCountry(addresses.get(0).getCountryName());
        } catch (Exception e) {

        }

        return m_addressPb.build();
    }
}