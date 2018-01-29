package com.shinystar91.ZECMaker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    TextView Int_Lang;
    String Lang="Русский";
    ImageView imageFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        MobileAds.initialize(getApplicationContext(),getString(R.string.app));
        AdView myAdView=(AdView)findViewById(R.id.AdSettings);
        AdRequest adRequest=new AdRequest.Builder().build();
        myAdView.loadAd(adRequest);
        final Spinner spinner = (Spinner) findViewById(R.id.Language);

        imageFlag = (ImageView) findViewById(R.id.imageFlag);

        Int_Lang=(TextView) findViewById(R.id.InterfaceLang);
        String language=getIntent().getStringExtra("Lang");
        /*x=getIntent().getStringExtra("x");
        y=getIntent().getStringExtra("y");*/
        if(language.equals("ru")) spinner.setSelection(0);
        else spinner.setSelection(1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId)
            {
                Lang=spinner.getSelectedItem().toString();
                if(Lang.equals("Русский"))
                {
                    Lang="ru";
                    imageFlag.setImageResource(R.drawable.russia);
                }else{
                    imageFlag.setImageResource(R.drawable.england);
                    Lang="en";
                }
                Locale myLocale = new Locale(Lang);
                Locale.setDefault(myLocale);
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#f98204"));
                ((TextView) parent.getChildAt(0)).setTextSize(22);
                ((TextView) parent.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
                android.content.res.Configuration config = new android.content.res.Configuration();
                config.locale = myLocale;
                getResources().updateConfiguration(config, getResources().getDisplayMetrics());
                Intent intent=new Intent();
                intent.putExtra("Lang", Lang);
                setResult(RESULT_OK, intent);
                Int_Lang.setText((getResources().getString(R.string.Choose_Int_Lang)));
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


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

}
