package com.example.n16demo01.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyCustomReceiver extends BroadcastReceiver {
    private final String CUSTOM_ACTION = "com.example.n16demo01.receivers.CustomReceiver";
    @Override
    public void onReceive(Context context, Intent ri) {
        if (ri.getAction().equals(CUSTOM_ACTION)) {
            String msg = ri.getStringExtra("data");
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}