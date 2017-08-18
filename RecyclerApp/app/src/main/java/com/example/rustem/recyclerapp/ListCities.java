package com.example.rustem.recyclerapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ListCities {
    public static List<CapitalModel> getListCities() {
        List<CapitalModel> capitalModels = new ArrayList<>();
        String[] isoCountryCodes = Locale.getISOCountries();
        for (String countryCode : isoCountryCodes) {
            Locale locale = new Locale("", countryCode);
            String code = locale.getCountry();
            String countryName = locale.getDisplayCountry();
            capitalModels.add(new CapitalModel(countryName, "", code));
        }

        Collections.sort(capitalModels, new Comparator<CapitalModel>() {
            @Override
            public int compare(CapitalModel o1, CapitalModel o2) {
                return o1.getCountryName().compareTo(o2.getCountryName());
            }
        });
        return capitalModels;
    }
}
