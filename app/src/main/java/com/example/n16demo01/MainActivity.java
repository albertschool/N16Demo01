package com.example.n16demo01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.n16demo01.recievers.BatteryLevelReceiver;
import com.example.n16demo01.recievers.MyCustomReceiver;
import com.example.n16demo01.recievers.NetworkStateReceiver;
import com.example.n16demo01.recievers.SMSReceiver;

/**
 * @author		Levy Albert albert.school2015@gmail.com
 * @version     2.0
 * @since		10/6/2023
 * A basic demo application to show the use of four different Broadcast receivers:
 * 1. Static SMS receiver
 * 2. Dynamic Battery Level receiver
 * 3. Dynamic Data connection receiver
 * 3. Dynamic Custom receiver
 */

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private BatteryLevelReceiver batteryLevelReceiver;
    private NetworkStateReceiver networkStateReceiver;
    private MyCustomReceiver myCustomReceiver;
//      ! Not needed when receiver is static !
//    private SMSReceiver smsReceiver;
    private boolean checkData = false;
    private final String CUSTOM_ACTION = "com.example.n16demo01.recievers.CustomReceiver";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        batteryLevelReceiver = new BatteryLevelReceiver();

        myCustomReceiver = new MyCustomReceiver();
//      ! Not needed when receiver is static !
//        smsReceiver = new SMSReceiver();
//        IntentFilter smsFilter = new IntentFilter();
//        smsFilter.setPriority(100);
//        smsFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
//        registerReceiver(smsReceiver,smsFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, batteryFilter);

        IntentFilter customFilter = new IntentFilter(CUSTOM_ACTION);
        registerReceiver(myCustomReceiver, customFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (checkData) {
            unregisterReceiver(networkStateReceiver);
        }
        unregisterReceiver(batteryLevelReceiver);
        unregisterReceiver(myCustomReceiver);
//      ! Not needed when receiver is static !
//        unregisterReceiver(smsReceiver);
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
        Intent intent = new Intent(CUSTOM_ACTION);
        intent.putExtra("data","Data receiver status changed!");
        sendBroadcast(intent);
    }
}