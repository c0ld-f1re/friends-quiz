package com.example.friends_4ever;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class fiveminact extends AppCompatActivity {

    SoundPool soundPool;
    int sound1,sound2,sound3,sound4;
    TextView txt;
    TextView sno;
    Animation shaking;
    TextView textViewResult;
    CountDownTimer timer;
    long left=60000;
    long left1=15000;
    int queno;
    boolean flag=false;
    RadioButton rdbql1;
    RadioButton rdbql2;
    RadioButton rdbql3;
    RadioButton rdbql4;
    RadioButton rdbql5;
    RadioButton ansbtn1;
    RadioButton ansbtn2;
    RadioButton ansbtn3;
    RadioButton ansbtn4;
    RadioGroup grp;
    ImageView imageView;
    ImageButton home;
    ImageButton share;

    final Handler handler3 = new Handler();
    final Handler handlerx=new Handler();
    Button submit;
    Button save;
    long num=600;
    float others=0f;
    float pia=0f;
    float farhan=0f;
    float virus=0f;

    ArrayList<String> que = new ArrayList<>();
    ArrayList<Integer> idl=new ArrayList<>();
    ArrayList<Integer> ansl=new ArrayList<>();
    ArrayList<String> opt1=new ArrayList<>();
    ArrayList<String> opt2=new ArrayList<>();
    ArrayList<String> opt3=new ArrayList<>();
    ArrayList<String> opt4=new ArrayList<>();
    ArrayList<String> imgl=new ArrayList<>();
    ArrayList<String> character=new ArrayList<>();
    int marks=0;
    int answer=4;
    int qno=2;
    int choice;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiveminact);


        grp = (RadioGroup) findViewById(R.id.radioGroup3);
        shaking = AnimationUtils.loadAnimation(this, R.anim.shaking);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }
        sound1 = soundPool.load(this, R.raw.sound1, 1);

        sound2 = soundPool.load(this, R.raw.sound2, 1);
        sound3 = soundPool.load(this, R.raw.sound3, 1);
        sound4 = soundPool.load(this, R.raw.sound4, 1);
        submit = (Button) findViewById(R.id.button6);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnd5();
            }
        });
        home = (ImageButton) findViewById(R.id.imageButton);
        share = (ImageButton) findViewById(R.id.imageButton2);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler3.removeCallbacksAndMessages(null);

                openHome1();
                timer.cancel();
            }
        });
        imageView = (ImageView) findViewById(R.id.ImageView1);
        textViewResult = findViewById(R.id.textView3);
        sno = (TextView) findViewById(R.id.textView);
        txt = findViewById(R.id.textView2);
        save = (Button) findViewById(R.id.button5);
        rdbql1 = (RadioButton) findViewById(R.id.radioButton);
        rdbql2 = (RadioButton) findViewById(R.id.radioButton2);
        rdbql3 = (RadioButton) findViewById(R.id.radioButton3);
        rdbql4 = (RadioButton) findViewById(R.id.radioButton4);
        rdbql5 = (RadioButton) findViewById(R.id.radioButton5);
        ansbtn1 = (RadioButton) findViewById(R.id.answerButton1);
        ansbtn2 = (RadioButton) findViewById(R.id.answerButton2);
        ansbtn3 = (RadioButton) findViewById(R.id.answerButton3);
        ansbtn4 = (RadioButton) findViewById(R.id.answerButton4);
        ansbtn1.setClickable(false);
        ansbtn1.setTextColor(Color.GRAY);
        ansbtn2.setClickable(false);
        ansbtn2.setTextColor(Color.GRAY);
        ansbtn3.setClickable(false);
        ansbtn3.setTextColor(Color.GRAY);
        ansbtn4.setClickable(false);
        ansbtn4.setTextColor(Color.GRAY);
        save.setClickable(false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/c0ld-f1re/424427792fb4266c6e69521024684574/raw/52a109e050bd4a92c902a662782771772408fe00/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code:" + response.code());
                    return;
                }
                List<Post> posts = response.body();
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
                for (Post post : posts) {
                    que.add(post.getQuestion());
                    idl.add(post.getId());
                    ansl.add(post.getAns());
                    opt1.add(post.getOption1());
                    opt2.add(post.getOption2());
                    opt3.add(post.getOption3());
                    opt4.add(post.getOption4());
                    imgl.add(post.getImage());
                    character.add(post.getCharacter());
                    rdbql1.performClick();
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler3.removeCallbacksAndMessages(null);
                timer.cancel();
                if (rdbql1.isChecked()) queno = 0;
                else if (rdbql2.isChecked()) queno = 1;
                else if (rdbql3.isChecked()) queno = 2;
                else if (rdbql4.isChecked()) queno = 3;
                else if (rdbql5.isChecked()) queno = 4;
                textViewResult.append("" + queno);
                String msg = "";

                msg += "Wassup!!! I came across this awesome 3 idiots quiz. Would you like to attempt a question as a challenge from my side ;)" + "\n\n";
                msg += que.get(queno) + "\n";
                msg += "1)" + opt1.get(queno) + "\n";
                msg += "2)" + opt2.get(queno) + "\n";
                msg += "3)" + opt3.get(queno) + "\n";
                msg += "4)" + opt4.get(queno) + "\n";
                if (imgl.get(queno) != null) {
                    msg += "The image url is " + imgl.get(queno);
                }

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });
        save.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler3.removeCallbacksAndMessages(null);
                save.startAnimation(shaking);
                timer.cancel();
                ansbtn1.setClickable(false);
                ansbtn2.setClickable(false);
                ansbtn3.setClickable(false);
                ansbtn4.setClickable(false);
                if (ansbtn1.isChecked()) {
                    choice = 1;
                } else if (ansbtn2.isChecked()) {
                    choice = 2;
                } else if (ansbtn3.isChecked()) {
                    choice = 3;
                } else if (ansbtn4.isChecked()) {
                    choice = 4;
                }

                if (answer == choice) {
                    marks += 1;
                    if ((character.get(id)).equalsIgnoreCase("others")) {
                        others += 1f;
                        nextclick();
                    } else if (character.get(id).equalsIgnoreCase("virus")) {
                        virus += 1f;
                        nextclick();
                    } else if (character.get(id).equalsIgnoreCase("farhan")) {
                        farhan += 1f;
                        nextclick();
                    } else if (character.get(id).equalsIgnoreCase("pia")) {
                        pia += 1f;
                        nextclick();
                    }

                } else if (answer != choice && choice != 0) {
                    boolean gotFocus = requestAudioFocusForMyApp(fiveminact.this);
                    if(gotFocus) {
                        //play audio.
                        soundPool.play(sound4,1,1,0,0,1);
                        handlerx.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                releaseAudioFocusForMyApp(fiveminact.this);
                            }
                        },4000);
                    }

                    final Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            nextclick();

                        }
                    }, 4000);
                } else {
                    nextclick();
                }
                choice = 0;
            }
        }));
        rdbql1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 0;
                qset();

            }
        });
        rdbql2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 1;
                qset();
            }
        });
        rdbql3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 2;
                qset();

            }
        });

        rdbql4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 3;
                qset();

            }
        });

        rdbql5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 4;
                qset();

            }
        });
    }
    public void openEnd5(){
        Intent intent1=new Intent(this,End5min.class);
        Bundle extras=new Bundle();
        extras.putInt("finalmarks",marks);
        extras.putFloat("farhan",farhan);
        extras.putFloat("other",others);
        extras.putFloat("virus",virus);
        extras.putFloat("pia",pia);
       intent1.putExtras(extras);
        startActivity(intent1);

    }
    public void openHome1(){
        Intent intent2=new Intent(this,MainActivity.class);
        startActivity(intent2);
    }

    public void startTimer(){
        left=60000;
    timer=new CountDownTimer(left,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            left=millisUntilFinished;
            updateTimer();
        }

        @Override
        public void onFinish() {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    save.performClick();
                }
            }, 5000);
            boolean gotFocus = requestAudioFocusForMyApp(fiveminact.this);
            if(gotFocus) {
                //play audio.
                soundPool.play(sound3,1,1,0,0,1);
                handlerx.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        releaseAudioFocusForMyApp(fiveminact.this);
                    }
                },4000);
            }
            ansbtn1.setClickable(false);
            ansbtn1.setTextColor(Color.GRAY);
            ansbtn2.setClickable(false);
            ansbtn2.setTextColor(Color.GRAY);
            ansbtn3.setClickable(false);
            ansbtn3.setTextColor(Color.GRAY);
            ansbtn4.setClickable(false);
            ansbtn4.setTextColor(Color.GRAY);
            save.setClickable(false);
        }
    }.start();
    }
    public void updateTimer(){
        int min=(int) left/60000;
        int sec=(int) left%60000/1000;
        String tl;
        tl=""+min;
        tl+=":";
        if(sec<10) tl+="0";
        tl+=sec;
        txt.setText(tl);

    }
    public void qset(){

        boolean gotFocus = requestAudioFocusForMyApp(fiveminact.this);
        if(gotFocus) {
            //play audio.
            soundPool.play(sound1,1,1,0,0,1);
            handlerx.postDelayed(new Runnable() {
                @Override
                public void run() {
                    releaseAudioFocusForMyApp(fiveminact.this);
                }
            },4000);
        }
        handler3.removeCallbacksAndMessages(null);
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {

                boolean gotFocus = requestAudioFocusForMyApp(fiveminact.this);
                if(gotFocus) {
                    //play audio.
                    soundPool.play(sound2,1,1,0,0,1);
                    handlerx.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            releaseAudioFocusForMyApp(fiveminact.this);
                        }
                    },4000);
                }

          //      soundPool.play(sound2,1,1,0,0,1);

            }
        }, 15000);
        grp.clearCheck();
        ansbtn1.setTextColor(Color.BLACK);
        ansbtn2.setTextColor(Color.BLACK);
        ansbtn3.setTextColor(Color.BLACK);
        ansbtn4.setTextColor(Color.BLACK);

        textViewResult.setTextColor(Color.BLACK);
        //id=0;
        if (imgl.get(id)!=null){
            Picasso.get().load(imgl.get(id)).into(imageView);
        }
        else{
            imageView.setImageResource(android.R.color.transparent);
        }
        save.setClickable(true);
        ansbtn1.setClickable(true);
        ansbtn2.setClickable(true);
        ansbtn3.setClickable(true);
        ansbtn4.setClickable(true);
        if (flag) {
            timer.cancel();
        }
        rdbql1.setClickable(false);
        rdbql1.setTextColor(Color.RED);
        startTimer();

        flag=true;
        sno.setText("");
        int h=id+1;
        String k="";
        k+=h+"/5";
        sno.setText(k);
        String content="";
        content+=que.get(id);
        textViewResult.setText(content);
        ansbtn1.setText(opt1.get(id));
        ansbtn2.setText(opt2.get(id));
        ansbtn3.setText(opt3.get(id));
        ansbtn4.setText(opt4.get(id));
        answer=ansl.get(id);
        qno=idl.get(id);

    }
    public void nextclick(){
        if (rdbql1.isChecked()){
            rdbql2.performClick();
        }
        else if (rdbql2.isChecked()){
            rdbql3.performClick();
        }
        else if (rdbql3.isChecked()){
            rdbql4.performClick();
        }
        else if (rdbql4.isChecked()){
            rdbql5.performClick();
        }
        else if (rdbql5.isChecked()){
            submit.performClick();
        }
    }
    private boolean requestAudioFocusForMyApp(final Context context) {
        AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        // Request audio focus for playback
        int result = am.requestAudioFocus(null,
                // Use the music stream.
                AudioManager.STREAM_MUSIC,
                // Request permanent focus.
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
