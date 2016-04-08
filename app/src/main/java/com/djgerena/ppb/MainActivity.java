package com.djgerena.ppb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class MainActivity extends AppCompatActivity {

    private String apiKey = "fa1c923f-b672-487b-94a5-b0d1a527bb9e";
    private String customerKey = "0122234";
    private Button b1, b2;
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final ImageView imageh = (ImageView) findViewById(R.id.home);
        imageh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageh.setVisibility(View.INVISIBLE);
            }
        });

        b1 = (Button) findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageh = (ImageView) findViewById(R.id.home);

                imageh.setVisibility(View.INVISIBLE);
                if(page<3)
                    page++;
                else if (page == 3)
                    page=1;
                String pageStr = "s" + page;
                if (page == 1) {
                    ImageView image = (ImageView) findViewById(R.id.s1);
                    image.setScaleType(ImageView.ScaleType.FIT_XY);
                    ImageView image2 = (ImageView) findViewById(R.id.s2);
                    ImageView image3 = (ImageView) findViewById(R.id.s3);
                    image.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.INVISIBLE);
                    image3.setVisibility(View.INVISIBLE);
                } else if (page == 2) {
                    ImageView image = (ImageView) findViewById(R.id.s2);
                    ImageView image1 = (ImageView) findViewById(R.id.s1);
                    ImageView image3 = (ImageView) findViewById(R.id.s3);
                    image.setVisibility(View.VISIBLE);
                    image1.setVisibility(View.INVISIBLE);
                    image3.setVisibility(View.INVISIBLE);
                    image.setScaleType(ImageView.ScaleType.FIT_XY);
                } else if (page == 3) {
                    ImageView image = (ImageView) findViewById(R.id.s3);
                    image.setVisibility(View.VISIBLE);
                    image.setScaleType(ImageView.ScaleType.FIT_XY);
                    ImageView image2 = (ImageView) findViewById(R.id.s2);
                    ImageView image1 = (ImageView) findViewById(R.id.s1);
                    image1.setVisibility(View.INVISIBLE);
                    image2.setVisibility(View.INVISIBLE);
                }
            }
        });
        b2 = (Button) findViewById(R.id.button3);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageh = (ImageView) findViewById(R.id.home);
                imageh.setVisibility(View.INVISIBLE);
                if(page>1)
                    page--;
                else if(page == 1)
                    page = 3;
                String pageStr = "s" + page;
                if(page == 3) {
                    ImageView image = (ImageView) findViewById(R.id.s3);
                    image.setVisibility(View.VISIBLE);
                    image.setScaleType(ImageView.ScaleType.FIT_XY);
                    ImageView image2 = (ImageView) findViewById(R.id.s2);
                    ImageView image1 = (ImageView) findViewById(R.id.s1);
                    image1.setVisibility(View.INVISIBLE);
                    image2.setVisibility(View.INVISIBLE);
                }
                else if(page == 2){
                    ImageView image = (ImageView) findViewById(R.id.s2);
                    ImageView image1 = (ImageView) findViewById(R.id.s1);
                    ImageView image3 = (ImageView) findViewById(R.id.s3);
                    image.setVisibility(View.VISIBLE);
                    image1.setVisibility(View.INVISIBLE);
                    image3.setVisibility(View.INVISIBLE);
                    image.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                else if(page == 1){
                    ImageView image = (ImageView) findViewById(R.id.s1);
                    image.setScaleType(ImageView.ScaleType.FIT_XY);
                    ImageView image2 = (ImageView) findViewById(R.id.s2);
                    ImageView image3 = (ImageView) findViewById(R.id.s3);
                    image.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.INVISIBLE);
                    image3.setVisibility(View.INVISIBLE);
                }
            }
        });


        ImageView image = (ImageView) findViewById(R.id.s1);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
      //  RewardSummary rs = new RewardSummary(customerKey);
        TextView t;
        t = new TextView(this);
        t = (TextView) findViewById(R.id.textView2);

      new RewardSummary(customerKey).execute();


    }

    private class RewardSummary extends AsyncTask<Void, Void, String> {
        String custNum;

        RewardSummary(String inString) {
            custNum = inString;
        }

        Response response = null;

        @Override
        protected String doInBackground(Void... urls) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://apilink-qa.pnc.com/hackathon/qa/retail/rewards/v1/rewardsSummary?customerKey="+customerKey)
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("x-ibm-client-id", apiKey)
                    .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            TextView t;
            String s = "$100";
          //  t = (TextView) findViewById(R.id.summary);
            try {
                s = response.body().toString();
            } catch (NullPointerException e1) {
                e1.printStackTrace();
            }

            t = (TextView) findViewById(R.id.textView2);
            t.setText(s);
        }


    }

//
//
//    public String getRewardSummary(String customerNumber) {
//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/octet-stream");
//        RequestBody body = RequestBody.create(mediaType, "{  \"customerKey\": \"apiKey\"}");
//        Request request = new Request.Builder()
//                .url("https://apilink-qa.pnc.com/hackathon/qa/retail/rewards/v1/getRewardsSummary")
//                .post(body)
//                .addHeader("accept", "application/json")
//                .addHeader("x-ibm-client-id", apiKey)
//                .addHeader("content-type", "application/json")
//                .build();
//
//        Response response = null;
//
//        try {
//            response = client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        //response.toString();
//        return response.toString();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
