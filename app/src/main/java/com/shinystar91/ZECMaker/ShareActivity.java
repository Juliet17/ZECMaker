package com.shinystar91.ZECMaker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ShareActivity extends AppCompatActivity {

    TextView Promo, Activ, Mods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Promo=(TextView)findViewById(R.id.YourPromo);
        Activ=(TextView)findViewById(R.id.ActivatedApp);
        Mods=(TextView)findViewById(R.id.Coeff);
        MobileAds.initialize(getApplicationContext(), getString(R.string.app));
        AdView myAdView=(AdView)findViewById(R.id.AdShare);
        AdRequest adRequest=new AdRequest.Builder().build();
        myAdView.loadAd(adRequest);
        Promo.setText(R.string.YourPromo);
        String promoText= Promo.getText().toString()+getIntent().getStringExtra("Promo");
        Promo.setText(promoText);
        Activ.setText(R.string.Activated);
        String activText= Activ.getText().toString()+getIntent().getStringExtra("Ref");
        Activ.setText(activText);
        Mods.setText(R.string.Coeff);
        String modText= Mods.getText().toString()+getIntent().getStringExtra("Mod");
        Mods.setText(modText);
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


    public void SendMyPromo(View view)
    {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String textToSend=getResources().getString(R.string.Text_to_Share)+" "+getIntent().getStringExtra("Promo");
        intent.putExtra(Intent.EXTRA_TEXT, textToSend);
        try
        {
            startActivity(Intent.createChooser(intent, "Описание действия"));
        }
        catch (android.content.ActivityNotFoundException ex)
        {

        }
    }
}
