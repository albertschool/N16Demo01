package com.example.n16demo01.recievers;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * @author		Levy Albert albert.school2015@gmail.com
 * @version     2.0
 * @since		10/6/2023
 * Network receiver to check if there is a Data connection
 */

public class NetworkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent ri) {
        if (ri.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                // There is an active data connection
                showMessage(context, true);
                Toast.makeText(context, "Data connection available", Toast.LENGTH_LONG).show();
            } else {
                // There is no active data connection
                showMessage(context, false);
                Toast.makeText(context, "No data connection available", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showMessage(Context context, boolean isConnected) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Connection status")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Perform any action when the "OK" button is clicked
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Perform any action when the "Cancel" button is clicked
                        dialog.dismiss();
                    }
                });
        if (isConnected){
            builder.setMessage("Data connection available");
        } else {
            builder.setMessage("No data connection available");
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

