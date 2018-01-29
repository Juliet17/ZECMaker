package com.shinystar91.ZECMaker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Date;

public class CheckBalanceActivity extends AppCompatActivity {

    TextView Balance;
    String Rate, YourBalance;
    Button KnowBtn;
    //Date OldDate, NewDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_balance);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        KnowBtn=findViewById(R.id.KnowBtn);
        Balance = (TextView)findViewById(R.id.TextBalance);
        Rate=getIntent().getStringExtra("json");
        YourBalance=getIntent().getStringExtra("balance")+" ZEC";
        Balance.setText(YourBalance);
        String time = getIntent().getStringExtra("Date");
        //OldDate = new Date(Long.parseLong(time));
        MobileAds.initialize(getApplicationContext(),getString(R.string.app));
        AdView myAdView=(AdView)findViewById(R.id.AdBalance);
        AdRequest adRequest=new AdRequest.Builder().build();
        myAdView.loadAd(adRequest);
    }


   //это для стрелки "назад"
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void TodayRate(View view)
    {
        Intent intent = new Intent(CheckBalanceActivity.this,YourRate.class);
        intent.putExtra("json",Rate);
        intent.putExtra("balance",YourBalance);
        startActivity(intent);

        //Log.d("ERROR!!!", Rate);
    }



}
