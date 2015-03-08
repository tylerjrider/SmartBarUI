package com.example.trider.smartbarui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Container_Screen extends Activity {

    Inventory INV = new Inventory();

    String ContainerString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container__screen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_container__screen, menu);
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



    public void onContainerClick(View view){


         Button button = (Button) view;


        switch(view.getId()){
            case R.id.con1_button:
                ContainerString = INV.getContainer(1).PrintContainer();
                //button.setBackground();
                break;
            case R.id.con2_button:
                ContainerString = INV.getContainer(2).PrintContainer();
                break;
            case R.id.con3_button:
                ContainerString = INV.getContainer(3).PrintContainer();
                break;
            case R.id.con4_button:
                ContainerString = INV.getContainer(4).PrintContainer();
                break;
            case R.id.con5_button:
                ContainerString = INV.getContainer(5).PrintContainer();
                break;
            case R.id.con6_button:
                ContainerString = INV.getContainer(6).PrintContainer();
                break;
            case R.id.con7_button:
                ContainerString = INV.getContainer(7).PrintContainer();
                break;
            case R.id.con8_button:
                ContainerString = INV.getContainer(8).PrintContainer();
                break;
            case R.id.con9_button:
                ContainerString = INV.getContainer(9).PrintContainer();
                break;
            case R.id.con10_button:
                ContainerString = INV.getContainer(10).PrintContainer();
                break;
            case R.id.con11_button:
                ContainerString = INV.getContainer(11).PrintContainer();
                break;
            case R.id.con12_button:
                ContainerString = INV.getContainer(12).PrintContainer();
                break;
            case R.id.con13_button:
                ContainerString = INV.getContainer(13).PrintContainer();
                break;
            case R.id.con14_button:
                ContainerString = INV.getContainer(14).PrintContainer();
                break;
            case R.id.con15_button:
                ContainerString = INV.getContainer(15).PrintContainer();
                break;
            case R.id.con16_button:
                ContainerString = INV.getContainer(16).PrintContainer();
                break;
            case R.id.con17_button:
                ContainerString = INV.getContainer(17).PrintContainer();
                break;
            case R.id.con18_button:
                ContainerString = INV.getContainer(18).PrintContainer();
                break;
        }
        Toast.makeText(getApplicationContext(),ContainerString,Toast.LENGTH_LONG).show();




        TextView tView = (TextView) findViewById(R.id.cont_tview);
        tView.setText(ContainerString);
//
//
// //The inflater is the actual box that pops up
//        LayoutInflater inflater= LayoutInflater.from(this);
//
//        //The view being uploaded is formatted in the fragment_change_liquor_inv_inv.xml file is in the text
//        //box area
//        View v = inflater.inflate(R.layout.fragment_container_info, null);
//
//        AlertDialog.Builder ad =new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.SmartUIDialog))
//                .setTitle("Current Liquor Inventory")
//                .setMessage("Scroll for current Liquor Inventory")
//                .setView(v)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // continue with delete
//                    }
//                })
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do nothing
//                    }
//                });
//
//                        //.setIcon(android.R.drawable.ic_dialog_alert)
//
//                ad.show();


    }
}
