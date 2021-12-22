package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.models.Donation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Donate extends Base {
    private RadioGroup paymentMethod;
    private ProgressBar progressBar;
    private NumberPicker amountPicker;
    private Button donateButton;

    private EditText amountText;
    private TextView amountTotal;

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
        this.amountText = (EditText) findViewById(R.id.paymentAmount);
        this.amountTotal = (TextView) findViewById(R.id.totalSoFar);
    }

    public void donateButtonClicked(View view) {
        int amount = this.amountPicker.getValue();
        String method = this.paymentMethod.getCheckedRadioButtonId() == R.id.PayPal ? "using PayPal" : "directly";

        if (amount == 0)
        {
            String text = this.amountText.getText().toString();
            if (!text.equals(""))
                amount = Integer.parseInt(text);
        }
        if (amount > 0)
        {
            newDonation(new Donation(amount, method));
            progressBar.setProgress(this.totalDonated);
            String totalDonatedStr = "$" + this.totalDonated;
            this.amountTotal.setText(totalDonatedStr);
        }
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