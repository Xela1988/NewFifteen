package redmcrosco.alex.puzzlefifteen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    Button buttonHomeNewGame;
    ImageView thanksgiving;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dichiaro un ImageView da cliccare la foto con la cake
        thanksgiving = (ImageView) findViewById(R.id.ringraziamenti);
        //gli imposto un Listener
        thanksgiving.setOnClickListener(new View.OnClickListener() {
            //invoco necesariamente un metodo on click
            @Override
            public void onClick(View v) {
                //creo l'ggetto Dialog
                final Dialog dialog = new Dialog(context);
                //questa row mi serve per non mostrare la toolbar in alto
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                //il setContentView restituisce il layout da far apparire
                dialog.setContentView(R.layout.dialog_layout);
                //creo un buttone per tornare indietro e gli imposto il suo metodoonClick
                Button dialogButton = (Button) dialog.findViewById(R.id.buttonIndietro);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        buttonHomeNewGame = (Button) findViewById(R.id.buttonHomeNewGame);

        buttonHomeNewGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Scelta.class);
                startActivity(intent);
            }
        });
    }
}

