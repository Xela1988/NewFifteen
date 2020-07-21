package com.example.alex.newfifteen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Alex on 31/01/2018.
 */
public class Case6 extends AppCompatActivity implements View.OnClickListener {
    private static final Integer n = 6; // размерность поля (campo dimensione): n*n
    private Button[] buttons; //массив ячеек (array di celle)
    private Integer indexEmpty; //индекс пустой ячейки (indice cella vuota)
    private Integer indexes[]; //хранит индексы ячеек (negozi indici cella)
    private Integer countPressBtn = 0; //хранит кол-во ходов (memorizza i numero di colpi)
    private TextView textView; //выводит кол-во ходов (esso mostra il numero di colpi)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case6);

        buttons = new Button[n * n];
        indexes = new Integer[n * n];
        Integer[] numbers = RandomOrder.getShuffleArray(0, n * n, n); //массив с числами в случайном порядке (una serie di numeri casuali)

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout6);

        Button btn;
        for (Integer i = 0; i < n * n; i++) {
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
        //ячейка, которая была нажата (cella che è stato cliccato)
        Integer number = Integer.parseInt(((Button) v).getText().toString());

        //индекс ячейки (indice delle celle)
        Integer idxPressBtn = indexes[number];

        if (((idxPressBtn == indexEmpty - 1) & ((idxPressBtn + 1) % n != 0)) ||
                ((idxPressBtn == indexEmpty + 1) & (idxPressBtn % (n) != 0)) ||
                (idxPressBtn == indexEmpty - n) || (idxPressBtn == indexEmpty + n)) {

            swapButton(buttons[idxPressBtn], buttons[indexEmpty]);

            indexes[number] = indexEmpty;
            indexEmpty = idxPressBtn;

            if (isSolve()) {
                Toast.makeText(this, "You solved it! YOU ARE REALLY VERY STRONG", Toast.LENGTH_LONG).show();
            } else
                textView.setText("Number of moves: "  + (++countPressBtn));
        }
    }

    private void swapButton(Button pressButton, Button emptyButton) {
        emptyButton.setText(pressButton.getText());
        emptyButton.setVisibility(View.VISIBLE);

        pressButton.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void startNewGame() {
        Integer[] numbers = RandomOrder.getShuffleArray(0, n * n, n);
        buttons[indexEmpty].setVisibility(View.VISIBLE);
        for (Integer i = 0; i < n * n; i++) {
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

    private boolean isSolve() {
        for (Integer i = 1; i < indexes.length - 1; i++)
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
        return super.onOptionsItemSelected(item);
    }
}
