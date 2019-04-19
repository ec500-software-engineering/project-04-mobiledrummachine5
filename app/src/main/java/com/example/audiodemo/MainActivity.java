package com.example.audiodemo;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AudioRecordTest";
    private Button btnRecord;
    private String fileName;
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecord = findViewById(R.id.btnRecord);

        btnRecord.setOnClickListener(new View.OnClickListener() {
            boolean mStartRecording = true;
            @Override
            public void onClick(View v) {
                onRecord(mStartRecording);
                if(mStartRecording){
                    btnRecord.setText("Stop");
                }else{
                    btnRecord.setText("Record");
                }
                mStartRecording = !mStartRecording;
            }
        });

        fileName = getExternalCacheDir().getAbsolutePath();
        Log.i(TAG, fileName);
        fileName += "/audiorecordtest2.3gp";

    }

    @Override
    public void onStop() {
        super.onStop();
        if(mRecorder != null){
            mRecorder.release();
            mRecorder = null;
        }
    }

    private void onRecord(boolean start){
        if(start){
            startRecording();
        }else{
            stopRecording();
        }
    }

    private void startRecording(){
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(fileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try{
            mRecorder.prepare();
        }catch (IOException e){
            Log.e(TAG, "Recorder preparation failed!");
        }

        mRecorder.start();
    }

    private void stopRecording(){
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }


}


