package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private RadioGroup paymentMethod;
    private ProgressBar progressBar;
    private NumberPicker amountPicker;
    private Button donateButton;

    private int totalDonated = 0;
    final int MAX_DONATION_AMOUNT = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action",
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        this.donateButton = findViewById(R.id.donateButton);
        if (this.donateButton != null) {
            Log.v("Donate", "Donate button created");
        }

        this.paymentMethod = findViewById(R.id.paymentMethod);
        this.progressBar = findViewById(R.id.progressBar);
        this.progressBar.setMax(MAX_DONATION_AMOUNT);

        this.amountPicker = findViewById(R.id.amountPicker);
        this.amountPicker.setMinValue(0);
        this.amountPicker.setMaxValue(1000);
    }

    public void donateButtonClicked(View view) {
        int amount = this.amountPicker.getValue();
        this.totalDonated += amount;
        this.progressBar.setProgress(totalDonated);
        String method = this.paymentMethod.getCheckedRadioButtonId() == R.id.PayPal ? "using PayPal" : "directly";
        Log.v("Donate", "Donated $" + amount + " " + method);
        Log.v("Donate", "Current donation amount: $" + this.totalDonated);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuReport) {
            Toast toast = Toast.makeText(this, "Report Selected",
                    Toast.LENGTH_SHORT);
            toast.show();
            startActivity(new Intent(this, Report.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}