package com.example.downloadimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button downloadImageButton;
    ImageView downloadImageView;

    public void downloadImage(View view){

        ImageDownloader task = new ImageDownloader();

        Bitmap myImage = null;

        try {

            myImage = task.execute("https://i.ebayimg.com/images/g/iEwAAOSwnC1cfC82/s-l300.png").get();

        } catch (Exception e) {

            e.printStackTrace();
        }

        downloadImageView.setImageBitmap(myImage);

        Log.i("Download Button","Pressed.");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadImageButton = findViewById(R.id.downloadImageButton);
        downloadImageView = findViewById(R.id.downloadImageView);


    }
    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream in = connection.getInputStream();

                Bitmap myBitMap = BitmapFactory.decodeStream(in);

                return myBitMap;

            } catch (Exception e) {

                e.printStackTrace();

                return null;
            }
        }
    }
}
