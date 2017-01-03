package com.smartisanos.sidebar.setting;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.smartisanos.sidebar.R;

public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.onestep);
    }

}
