package com.example.contacts;


import android.telephony.PhoneNumberUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;

public class ContactBook implements Serializable {

    private HashMap<String, String> contacts = new HashMap<>();
    private HashMap<String, String> addresses = new HashMap<>();

    public void add(String name, String number) {

        contacts.put(name, number);

    }

    public void addAddress(String name, String address) {

        if (address.isEmpty()) return;

        addresses.put(name, address);

    }

    public ArrayList<String> getNames() {


        ArrayList<String> names = new ArrayList<>(contacts.keySet());
        Collections.sort(names);

        return names;

    }

    public String getNumber(String name) {

        return PhoneNumberUtils.formatNumber(this.contacts.get(name), Locale.getDefault().getCountry());


    }

    public String getAddress(String name) {

        return this.addresses.get(name);

    }


    public void delete(String name) {
        contacts.remove(name);
        addresses.remove(name);

    }


}
