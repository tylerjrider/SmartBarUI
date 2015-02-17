package com.example.trider.smartbarui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;




public class PickUpFinger extends Activity {

    CommStream PiComm;
    boolean toggle = false;
    ImageView fingImg;

    TimerTask FlashFinger =  new TimerTask() {
        public void run() {
            PickUpFinger.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(toggle){
                        fingImg.setVisibility(View.INVISIBLE);
                    }else{
                        fingImg.setVisibility(View.VISIBLE);
                    }
                    toggle = !toggle;
                }
            });
        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_finger);


        ImageView usbConn = (ImageView) findViewById(R.id.usbCon1);
        fingImg= (ImageView) findViewById(R.id.fingerImg);


        PiComm = new CommStream();
        if(!PiComm.isInitialized()){
            usbConn.setVisibility(View.INVISIBLE);
        }

        new Timer().schedule(FlashFinger,1000,1000);


    }

    public void SkipFingerPrint(View view){
        startActivity(new Intent(this,CheckBAC.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick_up_finger, menu);
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
