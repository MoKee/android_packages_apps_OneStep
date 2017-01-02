package com.smartisanos.sidebar.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.Window;
import android.view.onestep.OneStepManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toolbar;

import com.smartisanos.sidebar.R;

public class SettingActivity extends Activity {

    private static final int BIT_SIDEBAR_IN_LEFT_TOP_MODE = 1 << 0;
    private static final int BIT_SIDEBAR_IN_RIGHT_TOP_MODE = 1 << 1;

    private static final long SIDEBAR_ENTER_DELAY = 500;

    private OneStepManager mOneStepManager;
    private Handler mMainThreadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        mOneStepManager = (OneStepManager) getSystemService(Context.ONE_STEP_SERVICE);

        mMainThreadHandler = new Handler(Looper.getMainLooper());

        final Window mWindow = getWindow();
        final Resources mResources = getResources();
        mWindow.setStatusBarColor(mResources.getColor(R.color.theme_primary_dark));
        mWindow.setNavigationBarColor(mResources.getColor(R.color.theme_primary));

        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.settings);
        mToolbar.getMenu().findItem(R.id.introduction_link)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        final Uri uri = Uri.parse("http://www.smartisan.com/pr/#/video/onestep-Introduction");
                        final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        return true;
                    }
                });

        final Switch mSidebarSwitch = (Switch) findViewById(R.id.onestep_enabled);
        mSidebarSwitch.setChecked(isSidebarEnabled());
        mSidebarSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                setSidebarEnabled(isChecked);
                if (isChecked) {
                    enterSidebarMode();
                }
            }
        });

        tryEnterSidebarMode();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        tryEnterSidebarMode();
    }

    private void tryEnterSidebarMode() {
        if (isSidebarEnabled() && !mOneStepManager.isInOneStepMode()) {
            mMainThreadHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enterSidebarMode();
                }
            }, SIDEBAR_ENTER_DELAY);// waiting for window-animation finished !
        }
    }

    private void enterSidebarMode() {
        mOneStepManager.requestEnterOneStepMode(BIT_SIDEBAR_IN_RIGHT_TOP_MODE);
    }

    private boolean isSidebarEnabled() {
        return Settings.Global.getInt(getContentResolver(), Settings.Global.SIDE_BAR_MODE, 1) == 1;
    }

    private void setSidebarEnabled(boolean enabled) {
        Settings.Global.putInt(getContentResolver(), Settings.Global.SIDE_BAR_MODE, enabled ? 1 : 0);
    }

}
