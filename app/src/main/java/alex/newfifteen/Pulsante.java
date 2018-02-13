package alex.newfifteen;

import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alex on 12/02/2018.
 */
public class Pulsante {

    private static final int n = 3; // размерность поля (campo dimensione): n*n
    private Button[] pulsanti; //массив ячеек (array di celle)
    private int indexEmpty; //индекс пустой ячейки (indice cella vuota)
    private int indexes[]; //хранит индексы ячеек (negozi indici cella)
    private int countPressBtn = 0; //хранит кол-во ходов (memorizza i numero di colpi)
    private TextView textView; //выводит кол-во ходов (esso mostra il numero di colpi)


    public Pulsante(int indexEmpty, int [] indexes, int countPressBtn, TextView textView) {
        this.indexEmpty = indexEmpty;
        this.indexes = indexes;
        this.countPressBtn = countPressBtn;
        this.textView = textView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public static int getN() {
        return n;
    }

    public int getIndexEmpty() {
        return indexEmpty;
    }

    public void setIndexEmpty(int indexEmpty) {
        this.indexEmpty = indexEmpty;
    }

    public int[] getIndexes() {
        return indexes;
    }

    public void setIndexes(int[] indexes) {
        this.indexes = indexes;
    }

    public int getCountPressBtn() {
        return countPressBtn;
    }

    public void setCountPressBtn(int countPressBtn) {
        this.countPressBtn = countPressBtn;
    }
}
