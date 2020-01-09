package com.example.friends_4ever;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Arrays;

public class End10min extends AppCompatActivity {
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;
    TextView disptxt;
    final Handler handlerx=new Handler();
    SoundPool soundPool;
    int sound5;
    ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end10min);


        barChart = findViewById(R.id.BarChart);

        home = (ImageButton) findViewById(R.id.imageButton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                openHome1();

            }
        });

        ArrayList<Float> points=new ArrayList<>();
        ArrayList<String> xvals=new ArrayList<>();
        ArrayList<Float> dist=new ArrayList<>();
        ArrayList<BarEntry> yvals=new ArrayList<>();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes=new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool=new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else{
            soundPool=new SoundPool(1, AudioManager.STREAM_MUSIC,0 );
        }
        sound5=soundPool.load(this,R.raw.sound5,1);


        disptxt=(TextView)findViewById(R.id.textView5);

        Bundle extras=getIntent().getExtras();
        int finmarks=extras.getInt("finalmarks",0);
        float farhan=extras.getFloat("farhan",0);
        float virus=extras.getFloat("virus",0);
        float pia=extras.getFloat("pia",0);
        float others=extras.getFloat("other",0);
        float chatur=extras.getFloat("chatur",0);
        float raju=extras.getFloat("raju",0);
        float ryancho=extras.getFloat("ryancho",0);

        points.add(ryancho);
        points.add(raju);
        points.add(chatur);
        points.add(farhan);
        points.add(virus);
        points.add(pia);
        points.add(others);
        dist.add(0f);
        dist.add(1f);
        dist.add(2f);
        dist.add(3f);
        dist.add(4f);
        dist.add(5f);
        dist.add(6f);
        ArrayList<BarEntry>barEntries = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            BarEntry value = new BarEntry(Float.valueOf(dist.get(i)),points.get(i));
            barEntries.add(value);
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "description");
        BarData barData=new BarData(barDataSet);
        barChart.setData(barData);
        final ArrayList<String> xVals = new ArrayList<>();
        xVals.add("Ryancho(1)");
        xVals.add("Raju(1)");
        xVals.add("Chatur(2)");
        xVals.add("Farhan(1)");
        xVals.add("Veeru Sahastrabuddhe(1)");
        xVals.add("Pia(1)");
        xVals.add("Others(3)");
        XAxis xAxis=barChart.getXAxis();
        xAxis.setGranularity(1f);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        barChart.animateXY(2000,2000);
        barChart.invalidate();

        disptxt.setText(""+finmarks);
        disptxt.append("/10");
        if (finmarks>=8){
            boolean gotFocus = requestAudioFocusForMyApp(End10min.this);
            if(gotFocus) {

                soundPool.play(sound5,1,1,0,0,1);
                handlerx.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        releaseAudioFocusForMyApp(End10min.this);
                    }
                },4000);
            }
        }
    }

    public void openHome1(){
        Intent intent2=new Intent(this,MainActivity.class);
        startActivity(intent2);
    }
    private boolean requestAudioFocusForMyApp(final Context context) {
        AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);


        int result = am.requestAudioFocus(null,

                AudioManager.STREAM_MUSIC,

                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            Log.d("AudioFocus", "Audio focus received");
            return true;
        } else {
            Log.d("AudioFocus", "Audio focus NOT received");
            return false;
        }
    }
    void releaseAudioFocusForMyApp(final Context context) {
        AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        am.abandonAudioFocus(null);
    }
}
