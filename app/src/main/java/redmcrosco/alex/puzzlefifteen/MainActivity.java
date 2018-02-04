package redmcrosco.alex.puzzlefifteen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button buttonHomeNewGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonHomeNewGame= (Button)findViewById(R.id.buttonHomeNewGame);

        buttonHomeNewGame.setOnClickListener( new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                // Intent code for open new activity through intent.

                Intent intent = new Intent(MainActivity.this, Scelta.class);
                //    Intent intent1= new Intent(MainActivity.this, Case5.class);
                startActivity(intent);
                //   startActivity(intent1);

            }});
    }}

