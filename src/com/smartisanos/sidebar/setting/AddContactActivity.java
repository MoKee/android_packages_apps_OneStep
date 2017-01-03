package com.smartisanos.sidebar.setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.smartisanos.sidebar.R;
import com.smartisanos.sidebar.util.AddContactManager;
import com.smartisanos.sidebar.util.AddContactManager.AddContactItem;

import java.util.List;

public class AddContactActivity extends AppCompatActivity {

    private final int[] mAddContactId = new int[]{
            R.id.add_contact_1,
            R.id.add_contact_2,
            R.id.add_contact_3,
            R.id.add_contact_4
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAddContactGroup();
    }

    private void updateAddContactGroup() {
        List<AddContactItem> list = AddContactManager.getInstance(this).getList();
        for (int i = 0; i < mAddContactId.length; ++i) {
            TextView itemText = (TextView) findViewById(mAddContactId[i]);
            if (i < list.size()) {
                itemText.setVisibility(View.VISIBLE);
                itemText.setText(list.get(i).labelId);
                itemText.setCompoundDrawablesWithIntrinsicBounds(list.get(i).iconId, 0, 0, 0);
                itemText.setOnClickListener(list.get(i).mListener);
            } else {
                itemText.setVisibility(View.GONE);
            }
        }
    }

}
