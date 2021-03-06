package pl.hackyeah.positivedevs.escapeit.Bluetooth;

/**
 * Created by Krzysiek on 2017-10-29.
 */

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

public class ConnectThread extends Thread{
    private BluetoothSocket bTSocket;

    public BluetoothSocket connect(BluetoothDevice bTDevice, UUID mUUID) {
        BluetoothSocket temp = null;
        try {
            temp = bTDevice.createRfcommSocketToServiceRecord(mUUID);
        } catch (IOException e) {
            Log.d("CONNECTTHREAD","Could not create RFCOMM socket:" + e.toString());
            return null;
        }
        try {
            temp.connect();
            //ManageConnectThread manage = new ManageConnectThread();
            //int otrzymana = 0;
            //otrzymana = manage.receiveData(temp);
            //Log.i("??",Integer.toString(otrzymana));
        } catch(IOException e) {
            Log.d("CONNECTTHREAD","Could not connect: " + e.toString());
            /*try {
                //temp.close();
            } catch(IOException close) {
                Log.d("CONNECTTHREAD", "Could not close connection:" + e.toString());
                return null;
            }*/
            return null;
        }
        return temp;
    }

    public boolean cancel() {
        try {
            bTSocket.close();
        } catch(IOException e) {
            Log.d("CONNECTTHREAD","Could not close connection:" + e.toString());
            return false;
        }
        return true;
    }

}