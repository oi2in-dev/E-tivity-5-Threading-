package com.example.e_tivity_5_threading_ED5042;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ImageView;

import com.example.e_tivity_5_threading_ED5042.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {
    private ProgressBar bar;
    ImageView imageView;
    Handler handler = new Handler();
    ProgressDialog progressDialog;

    ActivityMainBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.editText1.setText("");
                binding.imageView.setImageBitmap(null);

            }
        });

        binding.Fetchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String url = binding.editText1.getText().toString();
            new FetchImage(url).start();

            }
        });

        bar = findViewById(R.id.progressBar1);


    }

    class FetchImage extends Thread{

        String URL;
        Bitmap bitmap;

        FetchImage(String URL){

            this.URL = URL;

        }

        @Override
        public void run() {

        handler.post(new Runnable() {
            @Override
            public void run() {

                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Fetching.....");
                progressDialog.show();

            }
        });

            InputStream inputStream = null;
            try {
                inputStream = new URL(URL).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                    binding.imageView.setImageBitmap(bitmap);

                }
            });

        }
    }


    public void startProgress(View view) {

        bar.setProgress(0);
        new Thread(new Task()).start();
    }

    class Task implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bar.setProgress(i);

            }
        }

    }

}