package com.example.n16demo01.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

/**
 * @author		Levy Albert albert.school2015@gmail.com
 * @version     2.0
 * @since		10/6/2023
 * Battery level receiver to check & inform the battery level status
 */

public class BatteryLevelReceiver extends BroadcastReceiver {
    private static final int MIN_BATTERY_LEVEL = 30;
    private static final int CRITICAL_BATTERY_LEVEL = 10;
    private boolean firstMsg = true;
    @Override
    public void onReceive(Context context, Intent ri) {
        int batteryPercentage = ri.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int batteryStatus = ri.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

        if (batteryStatus != BatteryManager.BATTERY_STATUS_CHARGING) {
            if (batteryPercentage < MIN_BATTERY_LEVEL) {
                if (firstMsg) {
                    Toast.makeText(context, "Battery level is below "+MIN_BATTERY_LEVEL+"%", Toast.LENGTH_LONG).show();
                    firstMsg = false;
                }
            } else if (batteryPercentage > MIN_BATTERY_LEVEL) {
                firstMsg = true;
            }
        } else if (batteryPercentage < CRITICAL_BATTERY_LEVEL) {
            Toast.makeText(context, "Battery level is critical !!!", Toast.LENGTH_LONG).show();
        } else {
            firstMsg = true;
        }
    }
}

