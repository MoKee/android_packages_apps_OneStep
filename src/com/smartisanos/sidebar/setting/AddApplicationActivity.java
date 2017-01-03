package com.smartisanos.sidebar.setting;

import android.os.Bundle;
import android.widget.ListView;

import com.smartisanos.sidebar.R;

public class AddApplicationActivity extends BaseActivity {

    private AddApplicationAdapter mAdapter;

    @Override
    protected int provideContentView() {
        return R.layout.add_app_activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new AddApplicationAdapter(this.getApplicationContext());

        final ListView mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.onStop();
    }

}
