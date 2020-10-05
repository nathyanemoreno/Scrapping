package com.example.scrapping;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scrapping.AndroidAppium;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scrapping.adapters.EventListAdapter;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    String urlEven3 = "https://www.even3.com.br/eventos/?language:Java";
    String urlSympla = "https://www.sympla.com.br/eventos/aprender/?language:Java";
    private LinkedList<String> myDataset = new LinkedList<>();
    private RemoteWebDriver webDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_event_view);
        setUp();

        Button btnScrap = (Button) findViewById(R.id.btnScrap);
        btnScrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Scrap().execute(urlEven3);
            }
        });

    }

    public  void setUp(){
        for (int i= 0 ; i<=10 ; i++){
            myDataset.addLast("");
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EventListAdapter(this, myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        new Scrap().execute(urlEven3);
        Log.d("Dataset:", myDataset.get(0));
    }

    @SuppressLint("StaticFieldLeak")
    private class Scrap extends AsyncTask<String, Void, LinkedList<String> >implements com.example.scrapping.Scrap {

        private static final String TAG = "Title";
        private LinkedList<String> data = new LinkedList<>();

        @Override
        protected LinkedList<String> doInBackground(String... params) {
            String current = "oi";

            try {
//                new AndroidAppium().setUp();
                DesiredCapabilities cap = new DesiredCapabilities();
                webDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4723"), cap);
                webDriver.get(urlEven3);
                WebElement element = (WebElement) webDriver.findElement(By.id("logo"));
                System.out.println("Page title is: " + webDriver.getTitle());
                for (String s : params){
//                    Document doc = Jsoup.connect(s)
//                            .userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:73.0) Gecko/20100101 Firefox/73.0")
//                            .header("cookie","AWSALB=AT3uE3+d+BraPIPqynlacnUZP9cARdxgUxiqj1RHG2Fy0Fkvso87Qm3Xptb6Fvbp3iQTyzDjJBE28ZO4Y9cEpczrc5QKiKncEMcxMd3c3ITKLoRsjTXdpOmTMiKV; AWSALBCORS=AT3uE3+d+BraPIPqynlacnUZP9cARdxgUxiqj1RHG2Fy0Fkvso87Qm3Xptb6Fvbp3iQTyzDjJBE28ZO4Y9cEpczrc5QKiKncEMcxMd3c3ITKLoRsjTXdpOmTMiKV; sympla_tck=%7B%22id%22%3A%225e7051c5c3da62.39876905bbe011ce03b4094e535f4557244680d6%22%2C%22created_at%22%3A1584419269%7D; visid_incap_2014001=WKxtLrywQeuIaNcNpS62GMVRcF4AAAAAQUIPAAAAAADhOVU9lnMsHo4vgMD082JJ; optimizelyEndUserId=oeu1584419271060r0.7227624198954922; optimizelySegments=%7B%224225866243%22%3A%22ff%22%2C%224230684143%22%3A%22false%22%2C%224232464299%22%3A%22direct%22%7D; optimizelyBuckets=%7B%7D; _pxvid=aa705cab-6807-11ea-bcdc-0242ac12000c; _gcl_au=1.1.750740058.1584419273; _ga=GA1.3.1031451469.1584419273; cto_bundle=EHge5F9tbCUyQlRlWTdvbTVxUFpWRzdETlM3a2xIN25RU2clMkJRcVRSRmZXTXU2V0lWWFNBVmZwTWVyOG5HUUhOODlUMCUyQjlHYkZWZXRDZjMyWHpFSGd6TEFIeFlsJTJGTGIlMkJZJTJCYWFtVG1tUUpSSUdCbUFIaEhtdU5WMm8wNE8lMkJiNTBmdE1VNWF4WTFxaEo3N2k4eE9INVBDV1BBa1M5dyUzRCUzRA; __zlcmid=xGizSSLbclMtnE; sympla=8e6bm1h86p1rdbptjbqg4iai55; nlbi_2014001=CVBXFitZRT0XSlFDIXcouAAAAAAJtkrikZ/WtuKePBFHIZ1+; incap_ses_1239_2014001=OwTFJB87tVQ5O0LwXNAxEcZRcF4AAAAAOBFXRv7VNyCXslqx7EyZSg==; incap_ses_684_2014001=PSqVRcHwfl6B6IDcuA9+Cd1+dl4AAAAAaNyqmkeuQBpjPrunfSK3Zg==; sympla_explore=%7B%22city_slug%22%3A%22%22%2C%22city_name%22%3A%22%22%2C%22city_state%22%3A%22%22%7D; _px3=77f4da9fe88235a8af1d748c54d958a9c482f801f716b386432110d062467299:QxsOdNC3Kj2OA0rJN4LtC2NY2q4OSHDFmTqm+LbRK0MRuIaFKBKMSYyf8vKCs9l4Y1Vo97381IdraXXLmlNLww==:1000:Vs1fmv2mGXju/I7WswPBLuSZwfDdOzpjmEs1lnQq7nLGwoA3EzqNNRBGQexTxD8EqCUOtAnuIp1hgnSI8P1hbkRHF/6rrj29UC0Kj2U7FtOFpeZXrk26/K93rL5DlV8le9WD/vhAdBr/ZyybL8J+/jYtXbvtY6KRj7qlwUKUA1o=; _gid=GA1.3.773135932.1584824039")
//                            .timeout(0)
//                            .get();
                    Document doc = Jsoup.connect(s)
                            .userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:73.0) Gecko/20100101 Firefox/73.0")
                            .header("cookie","_ga=GA1.3.611131465.1584419303; ac_enable_tracking=1; __hstc=36596095.e7b89ee4d018e8e6a4e8ce35b5f52623.1584419303816.1584653808764.1584828205067.3; hubspotutk=e7b89ee4d018e8e6a4e8ce35b5f52623; __APPLICATION_LANGUAGE=pt; ARRAffinity=f32f0ecb4f040b875bbe5941651c7a7e54e436879969f1bffa3acad9b560eac4; hsfirstvisit=; __hssrc=1; _gid=GA1.3.931936834.1584828204")
                            .timeout(5000)
                            .get();
                    Elements list = doc.select("div[ng-controller]>div.container>div>div.row").last().getAllElements();
                    Log.d("Size", "List size :" + list.size());
                    int i = list.size();
                    for (Element li : list){
                        Log.d("DIV", "Div contains :" + li.select("div.col-md-4 div.panel-body a h1").attr("title"));
//                        current = li.select("li").select("a").attr("data-name");
                        data.addLast(current);
                        i++;
                    }
                }
            } catch (IOException e) {
                Log.d("Size", "None");
                e.printStackTrace();
            }
//            if (data != null) {
//                Log.d("Data", "Data size : (%s)" + data.length);
//            }
            return data;
        }

        @Override
        protected void onPostExecute(LinkedList<String> resultData) {
            super.onPostExecute(data);
            myDataset = data;
            for (String s : data) {
                Log.d(TAG, "onPostExecute() returned: " + s);
//            TextView titleTextView = (TextView) findViewById(R.id.title);
//            titleTextView.setText(title);
            }
        }
    }
}
