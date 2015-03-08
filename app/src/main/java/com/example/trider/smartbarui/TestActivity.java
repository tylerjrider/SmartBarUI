package com.example.trider.smartbarui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class TestActivity extends Activity {
    Context context;
    CommStream PiComm;
    boolean isActive = true;
    String InMessage;

/*Background Communications*/
    Runnable mListenerTask = new Runnable() {
        @Override
        public void run() {
            InMessage = PiComm.readString();
            if(InMessage != null){
                Toast.makeText(getApplicationContext(),InMessage,Toast.LENGTH_SHORT).show();
            }
            //Waits for new input communication
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Restarts this thread only if active
            if(isActive) {
                new Thread(this).start();
            }
        }
    };
//    public void onStop(){
//        super.onStop();
//        PiComm.writeString("STOP");
//        isActive = false;
//
//    }
    public void onResume(){
        super.onResume();
        if(PiComm.isInitialized()){
            PiComm.writeString("Resume");
        }
        hideSystemUI();
        isActive = true;
    }





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        hideSystemUI();


        TextView textView = (TextView) findViewById(R.id.testView);
        PiComm = new CommStream();

        if (PiComm.isInitialized()) {
            //textView.setText(PiComm.readString());
            context = getApplicationContext();
            Toast toast = Toast.makeText(context,"PiComm is Initialized",Toast.LENGTH_LONG);
            toast.show();
            PiComm.writeString("Test Comms");
        }else{
            context = getApplicationContext();
            Toast toast = Toast.makeText(context,"No PiComm is Initialized",Toast.LENGTH_LONG);
            toast.show();
            textView.setText(PiComm.ReadStatus());
        }

        if(PiComm.isInitialized()){
            new Thread(mListenerTask).start();
        }
    }




    public void StartUIClicked(View view){
        Intent intent = new Intent(this, IdleMenu.class);
        startActivity(intent);
    }
    public void SystemStatusClicked(View view){
        startActivity(new Intent(this,SystemStatus.class));
    }




/**System level functions**/
    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        View mDecorView;
        mDecorView = getWindow().getDecorView();
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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
