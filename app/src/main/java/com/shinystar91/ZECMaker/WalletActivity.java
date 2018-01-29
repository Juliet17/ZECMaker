package com.shinystar91.ZECMaker;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WalletActivity extends AppCompatActivity {

    Button SaveBtn;
    TextView Crypt_Adr,Your_Money;
    EditText Wallet;
    MediaType JSON;
    String x,y,wallet="",prevWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        MobileAds.initialize(getApplicationContext(), getString(R.string.app));
        AdView myAdView=(AdView)findViewById(R.id.AdSettings);
        AdRequest adRequest=new AdRequest.Builder().build();
        myAdView.loadAd(adRequest);
        SaveBtn=(Button) findViewById(R.id.SaveBtn);
        Wallet=(EditText)findViewById(R.id.Address);
        Crypt_Adr=(TextView) findViewById(R.id.YourCryptAdress);
        Your_Money=(TextView) findViewById(R.id.SetMes);
        x=getIntent().getStringExtra("x");
        y=getIntent().getStringExtra("y");
        wallet=getIntent().getStringExtra("wallet");
        Wallet.setText(wallet);
    }


    public void Save(View view)
    {
        AsyncTaskForMining myTask = new AsyncTaskForMining();
        myTask.execute();
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


    private class AsyncTaskForMining extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String myurl = "http://ethonline.site/users/wallet";
            String an = "ZEC Maker";
            String w=Wallet.getText().toString();
            prevWallet=wallet;
            wallet=w;
            JSON = MediaType.parse("application/json; charset=utf-8");

            OkHttpClient client = new OkHttpClient();
            JSONObject postdata = new JSONObject();

            try {
                postdata.put("x", x);
                postdata.put("y", y);
                postdata.put("an", an);
                postdata.put("w", w);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(JSON, postdata.toString());
            Request request = new Request.Builder()
                    .url(myurl)
                    .post(body)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result)
        {
            if(result.contains("true")) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        R.string.Success, Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                Toast toast = Toast.makeText(getApplicationContext(),
                        R.string.Fail, Toast.LENGTH_SHORT);
                toast.show();
                wallet=prevWallet;
            }
            SaveBtn.setClickable(true);
        }


        @Override
        protected void onPreExecute() {
            SaveBtn.setClickable(false);
        }
    }
}
