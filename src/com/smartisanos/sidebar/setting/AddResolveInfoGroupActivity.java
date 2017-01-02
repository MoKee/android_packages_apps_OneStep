package com.smartisanos.sidebar.setting;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.smartisanos.sidebar.R;
import com.smartisanos.sidebar.SidebarController;
import com.smartisanos.sidebar.SidebarStatus;
import com.smartisanos.sidebar.util.Utils;

public class AddResolveInfoGroupActivity extends AppCompatActivity {

    private static final int MAX_TIMES = 5;
    private static final String KEY_TIMES = "hint_sort_by_long_press_times";

    private View mContainer;
    private AddResolveInfoGroupAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_resolve_layout);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContainer = findViewById(R.id.container);

        mAdapter = new AddResolveInfoGroupAdapter(getApplicationContext());

        final ListView mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(mAdapter);

        showHintToast();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showHintToast() {
        int times = Utils.Config.getIntValue(getApplicationContext(), KEY_TIMES);
        if (times < MAX_TIMES) {
            Snackbar.make(mContainer, R.string.hint_sort_by_long_press, Snackbar.LENGTH_SHORT).show();
            Utils.Config.setIntValue(getApplicationContext(), KEY_TIMES, times + 1);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.onStart();
        SidebarController.getInstance(getApplicationContext()).requestStatus(
                SidebarStatus.UNNAME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.onStop();
        SidebarController.getInstance(getApplicationContext()).requestStatus(
                SidebarStatus.NORMAL);
    }

}
