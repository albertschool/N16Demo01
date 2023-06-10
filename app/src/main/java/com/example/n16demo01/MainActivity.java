package com.example.n16demo01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.n16demo01.recievers.BatteryLevelReceiver;
import com.example.n16demo01.recievers.NetworkStateReceiver;

/**
 * @author		Levy Albert albert.school2015@gmail.com
 * @version     2.0
 * @since		10/6/2023
 * A basic demo application to show the use of two different Broadcast receivers:
 * Battery Level receiver & Data connection receiver
 */

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private BatteryLevelReceiver batteryLevelReceiver;
    private NetworkStateReceiver networkStateReceiver;
    private boolean checkData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        batteryLevelReceiver = new BatteryLevelReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
        unregisterReceiver(batteryLevelReceiver);
    }

    private void initViews() {
        btn = findViewById(R.id.btn);
    }

    public void onOff(View view) {
        if (!checkData) {
            networkStateReceiver = new NetworkStateReceiver();

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

            registerReceiver(networkStateReceiver, intentFilter);
            checkData = true;
            btn.setText("Turn off Data Receiver");
        } else {
            unregisterReceiver(networkStateReceiver);
            checkData = false;
            btn.setText("Turn on Data Receiver");
        }
    }
}