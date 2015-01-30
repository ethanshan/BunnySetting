package net.codingpark.bunnysetting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.softwinner.SystemMix;

import java.io.DataOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private static final String TAG         = MainActivity.class.getSimpleName();

    public static final String PREFERENCE_DATA                  = "preference_data";
    public static final String AUTO_ENABLE_EIGHT_CORE_PREF_KEY  = "auto_enable_eight_core_pref";

    private static final String SVC_START_CMD               = "ctl.start";
    private static final String CPU_UP_SVC                  = "cpu_up";

    private Button enable_eight_cores_bt    = null;
    private CheckBox auto_enable_ck         = null;

    private SharedPreferences mPref         = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Obtain SharedPreferences object
        mPref = this.getSharedPreferences(
                MainActivity.PREFERENCE_DATA, Context.MODE_PRIVATE);

        initUI();
        initHandler();
    }

    private void initUI() {
        enable_eight_cores_bt = (Button)findViewById(R.id.enable_eight_cores_bt);
        auto_enable_ck = (CheckBox)findViewById(R.id.auto_enable_ck);
        auto_enable_ck.setChecked(mPref.getBoolean(AUTO_ENABLE_EIGHT_CORE_PREF_KEY, true));
    }

    private void initHandler() {
        enable_eight_cores_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Enable 8 cores!");
                SystemMix.setProperty(SVC_START_CMD, CPU_UP_SVC);
            }
        });

        auto_enable_ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPref.edit().putBoolean(AUTO_ENABLE_EIGHT_CORE_PREF_KEY, isChecked).apply();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
