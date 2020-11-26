package com.example.dialog2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {
AlertDialog.Builder builder;
LinearLayout screen;
String[]colors={"Red","Green","Blue"};
int[] rgb={0,0,0};
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen=(LinearLayout)findViewById(R.id.screen);
    }

    /**
     * when the button clicked opens alert dialog with option to change screen color(rgb) or cancels
     * <p>
     * @param view the button that got clicked
     */
    public void switchc(View view) {
        builder=new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("choose rgb color");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rgb[0]=0;
                rgb[1]=0;
                rgb[2]=0;
                rgb[which]=255;
                screen.setBackgroundColor(Color.rgb(rgb[0],rgb[1],rgb[2]));
            }
        });
        builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog ad =builder.create();
        ad.show();
    }
    /**
     * when the button clicked open alert dialog with option to change screen color(rgb combination) or cancels
     * <p>
     * @param view the button that got clicked
     */
    public void cancel(View view) {
        builder=new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("choose color combination");
        builder.setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked)
                    rgb[which]=255;
                else if(rgb[which]==255)
                    rgb[which]=0;
            }
        });
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                screen.setBackgroundColor(Color.rgb(rgb[0],rgb[1],rgb[2]));
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog ad =builder.create();
        ad.show();
    }
    /**
     * when the button clicked sets screen color back to white
     * @param view the button that got clicked
     */
    public void reset(View view) {
        screen.setBackgroundColor(WHITE);
    }
    /**
     * when the button clicked open alert dialog with text view and makes toast out of the text
     * <p>
     * @param view the button that got clicked
     */
    public void textb(View view) {
        builder=new AlertDialog.Builder(this);
        builder.setCancelable(false);
        final EditText et=new EditText(this);
        et.setHint("your name");
        builder.setView(et);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name =et.getText().toString();
                Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog ad =builder.create();
        ad.show();
    }

    /**
     * creats the xml generl option menu
     * <p>
     * @param menu the xml general menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * when item go clicked in the option menu goes to the activity that was chosen
     * <p>
     * @param item the item in the menu that got clicked
     * @return true if was operated successfully
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String s=item.getTitle().toString();
        if(s.equals("credits screen")) {
            Intent si = new Intent(this,credits.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}