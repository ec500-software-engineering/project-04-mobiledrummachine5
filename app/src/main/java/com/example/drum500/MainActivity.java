package com.example.drum500;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    String padID;
    MediaPlayer drumPlayer;
    boolean nr = false;
    boolean fl = false;

    // UI elements
    Button switchMode;
    Button record;
    SeekBar bpmBar;
    SeekBar repeatBar;
    ToggleButton nodeRepeat;
    ToggleButton fullLevel;
    Button pad1;
    Button pad2;
    Button pad3;
    Button pad4;
    Button pad5;
    Button pad6;
    Button pad7;
    Button pad8;
    Button pad9;
    Button pad10;
    Button pad11;
    Button pad12;

    // Set onTouch
    View.OnTouchListener repeatPlaying = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            return false;
        }
    };

    public void playSample(View view) {

        int id = view.getId();

        padID = view.getResources().getResourceEntryName(id);
        int resourceID = getResources().getIdentifier(padID, "raw", getPackageName());

        drumPlayer = MediaPlayer.create(this, resourceID);
        drumPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }
        });
        if (drumPlayer.isPlaying()) {
            drumPlayer.reset();
            drumPlayer.release();
            drumPlayer.start();
        } else {
            drumPlayer.start();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View main = findViewById(R.id.freeView);
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        main.setSystemUiVisibility(uiOptions);

        // Set UI elements
        switchMode = (Button) findViewById(R.id.modeButton);
        record = (Button) findViewById(R.id.recordButton);
        nodeRepeat = (ToggleButton) findViewById(R.id.repeatButton);
        fullLevel = (ToggleButton) findViewById(R.id.fullButton);
        pad1 = (Button) findViewById(R.id.congal);
        pad2 = (Button) findViewById(R.id.congam);
        pad3 = (Button) findViewById(R.id.congah);
        pad4 = (Button) findViewById(R.id.clap);
        pad5 = (Button) findViewById(R.id.openhihat);
        pad6 = (Button) findViewById(R.id.tom);
        pad7 = (Button) findViewById(R.id.snare);
        pad8 = (Button) findViewById(R.id.closedhihat);
        pad9 = (Button) findViewById(R.id.kick);
        pad10 = (Button) findViewById(R.id.cowbell);
        pad11 = (Button) findViewById(R.id.clave);
        pad12 = (Button) findViewById(R.id.maraca);
        bpmBar = (SeekBar) findViewById(R.id.bpmBar);
        repeatBar = (SeekBar) findViewById(R.id.repeatTimes);


        // Set bpm
        repeatBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        // Set Node Repeat
        pad1.setOnTouchListener(repeatPlaying);
        pad2.setOnTouchListener(repeatPlaying);
        pad3.setOnTouchListener(repeatPlaying);
        pad4.setOnTouchListener(repeatPlaying);
        pad5.setOnTouchListener(repeatPlaying);
        pad6.setOnTouchListener(repeatPlaying);
        pad7.setOnTouchListener(repeatPlaying);
        pad8.setOnTouchListener(repeatPlaying);
        pad9.setOnTouchListener(repeatPlaying);
        pad10.setOnTouchListener(repeatPlaying);
        pad11.setOnTouchListener(repeatPlaying);
        pad12.setOnTouchListener(repeatPlaying);

        nodeRepeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    nr = true;
                } else {
                    nr = false;
                }
            }
        });

        // Set Full Level
        fullLevel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fl = true;
                } else {
                    fl = false;
                }
            }
        });

    }
}