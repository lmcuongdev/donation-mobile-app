package com.example.main;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.models.Donation;

import java.util.ArrayList;
import java.util.List;

public class DonationApp extends Application
{
    public final int target = 10000;
    public int totalDonated = 0;
    public List<Donation> donations = new ArrayList<Donation>();
    public void newDonation(Donation donation)
    {
        boolean targetAchieved = totalDonated > target;
        if (!targetAchieved)
        {
            donations.add(donation);
            totalDonated += donation.amount;
        }
        else
        {
            Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("Donate", "Donation App Started");
    }
}