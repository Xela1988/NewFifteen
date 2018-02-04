package redmcrosco.alex.puzzlefifteen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by Alex on 26/10/2017.
 */
public class Scelta extends Activity implements OnClickListener {
    protected Button buttonScelta3;
    protected Button buttonScelta4;
    protected Button buttonScelta5;
    protected Button buttonScelta6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scelta);

        Button buttonScelta3 = (Button) findViewById(R.id.buttonScelta3);
        buttonScelta3.setOnClickListener(this); // calling onClick() method
        Button buttonScelta4 = (Button) findViewById(R.id.buttonScelta4);
        buttonScelta4.setOnClickListener(this);
        Button buttonScelta5 = (Button) findViewById(R.id.buttonScelta5);
        buttonScelta5.setOnClickListener(this);
        Button buttonScelta6 = (Button) findViewById(R.id.buttonScelta6);
        buttonScelta6.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.buttonScelta3:
                // code for button when user clicks buttonScelta.
                Intent intent = new Intent(Scelta.this, Case3.class);
                startActivity(intent);
                break;

            case R.id.buttonScelta4:
                // do your code
                Intent intent1 = new Intent(Scelta.this, Case4.class);
                startActivity(intent1);
                break;
            case R.id.buttonScelta5:
                // do your code
                Intent intent2 = new Intent(Scelta.this, Case5.class);
                startActivity(intent2);
                break;
            case R.id.buttonScelta6:
                Intent intent3 = new Intent(Scelta.this, Case6.class);
                startActivity(intent3);
                break;

            default:
                break;
        }
    }
}