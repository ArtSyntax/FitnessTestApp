package com.artsyntax.fitnesstest.utils;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by ArtSyntax on 18/4/2559.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    private NetworkStateReceiverListener listener = null;
    private boolean connected;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);

//        Toast.makeText(context,
//                status,
//                Toast.LENGTH_SHORT)
//                .show();

        if(this.listener != null && status != null) {
            if (status.equals("Wifi enabled") || status.equals("Cellular enabled"))
                this.listener.networkAvailable();
            else
                this.listener.networkUnavailable();
        }
    }

    public void setListener(NetworkStateReceiverListener listener) {
        this.listener = listener;
    }

    public interface NetworkStateReceiverListener {
        public void networkAvailable();
        public void networkUnavailable();
    }
}