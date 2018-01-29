package com.shinystar91.ZECMaker;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ExRate extends AppCompatActivity {

    TextView ExText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_rate);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        ExText=(TextView)findViewById(R.id.ExtText);
        String json=getIntent().getStringExtra("json");
        MobileAds.initialize(getApplicationContext(), getString(R.string.app));
        AdView myAdView=(AdView)findViewById(R.id.AdExRate);
        AdRequest adRequest=new AdRequest.Builder().build();
        myAdView.loadAd(adRequest);
        ExText.setText(json);
    }


    //кнопка "назад"
    @Override
    public boolean onOptionsItemSelected (MenuItem item)
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


    /*private String  ParseJson(String json)
    {
        String USD = "\"USD\":",End=",\"",EUR="\"EUR\":",GBP="\"GBP\":",CHF="\"CHF\":",CNY="\"CNY\":";
        String JPY="\"JPY\":", EndJpy="}";
        int startUSD=json.indexOf(USD)+USD.length();
        int endUSD=json.indexOf(End,startUSD);
        int startEUR=json.indexOf(EUR)+EUR.length();
        int endEUR=json.indexOf(End,startEUR);
        int startGBP=json.indexOf(GBP)+GBP.length();
        int endGBP=json.indexOf(End,startGBP);
        int startCHF=json.indexOf(CHF)+USD.length();
        int endCHF=json.indexOf(End,startCHF);
        int startCNY=json.indexOf(CNY)+USD.length();
        int endCNY=json.indexOf(End,startCNY);
        int startJPY=json.indexOf(JPY)+USD.length();
        int endJPY=json.indexOf(EndJpy,startJPY);
        String result="";
        result+="USD: "+json.substring(startUSD,endUSD)+"\n";
        result+="EUR: "+json.substring(startEUR,endEUR)+"\n";
        result+="GBP: "+json.substring(startGBP,endGBP)+"\n";
        result+="CHF: "+json.substring(startCHF,endCHF)+"\n";
        result+="CNY: "+json.substring(startCNY,endCNY)+"\n";
        result+="JPY: "+json.substring(startJPY,endJPY)+"\n";
        return result;
    }*/
}
