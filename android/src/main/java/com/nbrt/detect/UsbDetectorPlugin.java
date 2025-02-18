package com.nbrt.detect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.util.HashMap;
import java.util.Map;

@CapacitorPlugin(name = "UsbDetector")
public class UsbDetectorPlugin extends Plugin {
    private static final String TAG = "UsbDetectorPlugin";
    private BroadcastReceiver usbReceiver;

    @Override
    public void load() {
        Log.d(TAG, "Plugin loading...");
        registerUsbReceiver();
        Log.d(TAG, "USB receiver registered");
    }

    private void registerUsbReceiver() {
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
            filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);

            usbReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

                    Log.i(TAG, "USB Event received: " + action);

                    if (device == null) {
                        Log.e(TAG, "USB Device is null");
                        return;
                    }

                    JSObject ret = new JSObject();
                    ret.put("deviceName", device.getDeviceName());
                    ret.put("deviceId", device.getDeviceId());
                    ret.put("vendorId", device.getVendorId());
                    ret.put("productId", device.getProductId());

                    if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                        Log.i(TAG, "USB Device attached: " + device.getDeviceName() + " (ID: " + device.getDeviceId() + ")");
                        notifyListeners("usbAttached", ret);
                    } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                        Log.i(TAG, "USB Device detached: " + device.getDeviceName() + " (ID: " + device.getDeviceId() + ")");
                        notifyListeners("usbDetached", ret);
                    }
                }
            };

            getContext().registerReceiver(usbReceiver, filter);
            Log.d(TAG, "USB receiver registered successfully");
        } catch (Exception e) {
            Log.e(TAG, "Error registering USB receiver: " + e.getMessage(), e);
        }
    }

    @Override
    protected void handleOnDestroy() {
        if (usbReceiver != null) {
            try {
                Log.d(TAG, "Unregistering USB receiver");
                getContext().unregisterReceiver(usbReceiver);
                Log.d(TAG, "USB receiver unregistered successfully");
            } catch (Exception e) {
                Log.e(TAG, "Error unregistering USB receiver: " + e.getMessage(), e);
            }
        }
    }
}