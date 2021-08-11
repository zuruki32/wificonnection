package com.example.connection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class connection extends AppCompatActivity {
    private WifiManager wifiManager;
    private ListView listView;
    private Button discoverybtn, wifibtn, homebtn;
    private List<ScanResult> results;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter adapter;
    private int size = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        Button homebtn = (Button) findViewById(R.id.homebtn);
        final Button wifibtn = (Button) findViewById(R.id.wifibtn);
        Button Discovery = (Button) findViewById(R.id.discoverybtn);
        discoverybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanwifi();
            }
        });
        listView = (ListView) findViewById(R.id.mylistview);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        exqlistener();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        scanwifi();
        gohome();
    }

    private void gohome() {
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(connection.this, MainActivity.class));

            }
        });
    }

    private void exqlistener() {
        wifibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wifiManager.isWifiEnabled()) {
                    wifibtn.setText("turn_wifi_off");
                    wifiManager.setWifiEnabled(false);
                } else if (!wifiManager.isWifiEnabled()) {
                    wifibtn.setText("turn_wifi_on");
                    wifiManager.setWifiEnabled(true);
                }
            }
        });
    }

    private void scanwifi() {
        arrayList.clear();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(this, "scanning wifi", Toast.LENGTH_SHORT).show();
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifiManager.getScanResults();
            unregisterReceiver(this);
            for (ScanResult ScanResult : results) {
                arrayList.add(ScanResult.SSID + "..." + ScanResult.capabilities);
                adapter.notifyDataSetChanged();

            }
        }

    };
}

