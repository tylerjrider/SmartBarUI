package com.example.trider.smartbarui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class RegisterFingerPrint extends ActionBarActivity {

CommStream PiComm = new CommStream();

static int trans = 1;

    //States for fingerprints
public enum FingerState {
     IDLE,
     FIRST_FINGER,
     SECOND_FINGER,
     THIRD_FINGER,
     REGISTERED,
     WARNING
}
FingerState currentState = FingerState.IDLE;
FingerState nextState = FingerState.IDLE;




    TimerTask ChangeFinger = new TimerTask() {
        public void run() {
            RegisterFingerPrint.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   ImageView finger = (ImageView) findViewById(R.id.newFingerImg);
                    //finger.setColorFilter(Color.argb(trans, 0, 0, 255));
                    Log.d("Color", "Trans is " + trans);
                    trans = trans*2;
                    if(trans > 255){
                        trans =1;
                    }
                }
            });
        }
    };


    /**
     * @title: mListenerTask
     * @description: The background thread that receives serial communication from the raspberry pi,
     *
     */
    Runnable mListenerTask = new Runnable() {
        @Override
        public void run() {
            byte[] buffer = new byte[128];
            //ret is the size of the size of the incoming buffer
            int ret;
            try {
                ret = PiComm.getIStream().read(buffer);


                if (ret < 128) {
                    String msg = new String(buffer);
                    RunPrintStateMachine(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Waits for new input communication
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(this).start();
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_finger_print);

        ImageView finger = (ImageView) findViewById(R.id.newFingerImg);

        PiComm.writeString("$FP.First");

        new Thread(mListenerTask).start();


        new Timer().schedule(ChangeFinger,1000,1000);



    }



    public void RunPrintStateMachine(String s){

        final String T = s;
        s= s.trim();
        RegisterFingerPrint.this.runOnUiThread(new Runnable(){
            @Override
            public void run(){
                Toast.makeText(getApplicationContext(),"Incoming Message: "+ T,Toast.LENGTH_SHORT).show();
            }
        });

            switch(currentState){
                case IDLE:
                    switch(s){
                        case "$FP.Ready":
                            nextState = FingerState.FIRST_FINGER;
                            break;
                        default:
                            break;
                    }
                    break;
                case FIRST_FINGER:
                    switch(s){
                        case "$FP.1.Success":
                            nextState = FingerState.SECOND_FINGER;
                            break;
                        case "FP.1.Failure":
                            nextState = FingerState.WARNING;
                            break;
                        default:
                            break;
                    }
                    break;
                case SECOND_FINGER:
                    switch(s){
                        case "$FP.2.Success":
                            nextState = FingerState.THIRD_FINGER;
                            break;
                        case "$FP.2.Failure":
                            nextState = FingerState.WARNING;
                            break;
                    }
                    break;
                case THIRD_FINGER:
                    switch(s){
                        case "$FP.3.Success":
                            nextState = FingerState.REGISTERED;
                            break;
                        case "FP.3.Failure":
                            nextState = FingerState.WARNING;
                            break;
                    }
                    break;
                case WARNING:
                    break;
            }


        /*
        RegisterFingerPrint.this.runOnUiThread(new Runnable(){
            @Override
            public void run(){
                Toast.makeText(getApplicationContext(),"Incoming Message: "+ T,Toast.LENGTH_SHORT).show();
            }
        });
        */
    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_finger_print, menu);
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
