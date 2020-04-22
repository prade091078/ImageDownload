package com.example.imagedownload;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView downloadedImage;

    public void downloadImage(View view)
    {
        //https://images.freeimages.com/images/large-previews/277/doggy-1382866.jpg

        ImageDownloder task = new ImageDownloder();
        Bitmap myImage = null;
       try {
            myImage = task.execute("https://images.freeimages.com/images/large-previews/277/doggy-1382866.jpg").get();
           Log.i("Interaction" ," button has been tapped");
       }
       catch(Exception e)
       {
         e.printStackTrace();
           Log.i("Download Failed" ," No image");
       }

        downloadedImage.setImageBitmap(myImage);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         downloadedImage = (ImageView) findViewById(R.id.imageView);
    }

    public class ImageDownloder extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap myBitmap = null;
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();
                InputStream inputStream = connection.getInputStream();

                 myBitmap = BitmapFactory.decodeStream(inputStream);

                return myBitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }
}
