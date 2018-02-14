package alex.newfifteen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * Created by Alex on 22/10/2017.
 */
public class Case3 extends AppCompatActivity implements View.OnClickListener {
    private static final int n = 3; // размерность поля (campo dimensione): n*n
    private Button[] buttons; //массив ячеек (array di celle)
    private int indexEmpty; //индекс пустой ячейки (indice cella vuota)
    protected int indexes[]; //хранит индексы ячеек (negozi indici cella)
    private int countPressBtn = 0; //хранит кол-во ходов (memorizza i numero di colpi)
    private TextView textView; //выводит кол-во ходов (esso mostra il numero di colpi)
    public String franco[];
    // queste due rows mi servono per salvare
    protected Button buttonSave;
    // richiamo la classe e la nomino mSaveGame
    SaveGame mSaveGame;
    private Button buttonLoad;
    private ListView mListView;
    int[] arrayb = new int[]{1, 2, 3, 4};
    private Button button;
    String puls = String.valueOf(button);
    private GridLayout mGridLayout;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

    private ListView listView;

    private SimpleCursorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case3);
        // modifiche per creazione button Save Load
        // buttonSave = (Button) findViewById(R.id.buttonSave);
        //  buttonLoad = (Button) findViewById(R.id.buttonLoad);
        //prova per caricare eventuali schemi
        mGridLayout = (GridLayout) findViewById(R.id.gridLayout3);
        buttons = new Button[n * n];
        indexes = new int[n * n];
        int[] numbers = RandomOrder.getShuffleArray(0, n * n, n); //массив с числами в случайном порядке (una serie di numeri casuali)
        mSaveGame = new SaveGame(this);
        String parseFran = String.valueOf(numbers);
        final String franco = parseFran;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout3);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonLoad = (Button) findViewById(R.id.buttonLoad);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);


        //ho impostato due Listener il primo per salavare utilizzando il method salvaGame
        // e il secondo per caricare usa il metodo caricaGame
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvaGame(indexes);
                //   Intent intent = new Intent(Case3.this, Case3.class );
            }
        });
        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caricaGame();
                Intent intent = new Intent(Case3.this, Case3.class);
            }
        });

        Button btn;
        for (int i = 0; i < n * n; i++) {
            btn = (Button) gridLayout.getChildAt(i);
            if (numbers[i] == 0) {
                btn.setVisibility(View.INVISIBLE);
                indexEmpty = i;
            }
            indexes[numbers[i]] = i;
            buttons[i] = btn;
            btn.setText(String.valueOf(numbers[i]));
            btn.setOnClickListener(this);

        }


        textView = (TextView) findViewById(R.id.textView);
    }


    @Override
    public void onClick(View v) {

        // String newEntry = editText.getText().toString();


        //ячейка, которая была нажата (cella che è stato cliccato)
        int number = Integer.parseInt(((Button) v).getText().toString());

        //индекс ячейки (indice delle celle)
        int idxPressBtn = indexes[number];

        if (((idxPressBtn == indexEmpty - 1) & ((idxPressBtn + 1) % n != 0)) ||
                ((idxPressBtn == indexEmpty + 1) & (idxPressBtn % (n) != 0)) ||
                (idxPressBtn == indexEmpty - n) || (idxPressBtn == indexEmpty + n)) {

            swapButton(buttons[idxPressBtn], buttons[indexEmpty]);

            indexes[number] = indexEmpty;
            indexEmpty = idxPressBtn;

            switch (v.getId()) {
                case R.id.buttonSave:
                    salvaGame(indexes);
                    break;
                case R.id.buttonLoad:
                    caricaGame();
            }
            if (isSolve()) {
                Toast.makeText(this, "You solved it! CONGRATULATION", Toast.LENGTH_LONG).show();
            } else
                textView.setText("Number of moves: " + (++countPressBtn));
        }

    }

    private void swapButton(Button pressButton, Button emptyButton) {
        emptyButton.setText(pressButton.getText());
        emptyButton.setVisibility(View.VISIBLE);
        pressButton.setVisibility(View.INVISIBLE);
    }


    @SuppressLint("SetTextI18n")
    private void startNewGame() {
        int[] numbers = RandomOrder.getShuffleArray(0, n * n, n);
        buttons[indexEmpty].setVisibility(View.VISIBLE);
        for (int i = 0; i < n * n; i++) {
            indexes[numbers[i]] = i;
            if (numbers[i] != 0)
                buttons[i].setText(String.valueOf(numbers[i]));
            else {
                buttons[i].setVisibility(View.INVISIBLE);
                indexEmpty = i;
            }
        }
        textView.setText("Number of moves: " + (countPressBtn = 0)); //обнулить счетчик (azzera il  contatore)
    }
    //messaggio customizzato

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //creata prova per salvare effettuo il parse di n in String
    @SuppressLint("SetTextI18n")
    private void salvaGame(int[] indexes) {

        //si potrebbe inserire un onClickListener
        String parsataIntN = String.valueOf(this.indexes);
        boolean insertData = mSaveGame.salvaGame(parsataIntN);
        if (insertData == true) {
            toastMessage("Salvataggio completato");
        } else {
            toastMessage("Error Save not succesfully");
        }
    }


    //creata prova per caricare
    @SuppressLint("SetTextI18n")
    private void caricaGame() {
        Cursor data = mSaveGame.caricaGame();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {

            //prova questa istruzione cerca nella COL2 dei buttons
            listData.add(data.getString(1));
            toastMessage("Sto in Caricamento completato");
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.activity_list_item, listData);
        mListView.setAdapter(adapter);
        toastMessage("Sto nell'Adapter");
    }

    // la soluzione

    private boolean isSolve() {
        for (int i = 1; i < indexes.length - 1; i++)
            if (indexes[i] != i - 1) return false;

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        startNewGame();
        salvaGame(this.indexes);
        caricaGame();


        // le opzioni sulla scrollbar in alto orizzontale
        //   salvaGame(n);
        //  caricaGame();
        return super.onOptionsItemSelected(item);
    }
}

