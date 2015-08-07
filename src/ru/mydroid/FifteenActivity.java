package ru.mydroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FifteenActivity extends Activity implements SeekBar.OnSeekBarChangeListener{
    public static final int EMPTY_SPACE_NUMBER = -1;
    static int tilesAmount = 4;
    private SquareButton[][] buttons;
    private Point emptySpace = new Point();
    private List<Tile> tileList = new ArrayList<>();
    private int turn;
    private TextView turnsTextView;
    private TextView tilesTextView;
    private SeekBar tilesSeekBar;
    private static Random random = new Random();

    //TODO при повороте экрана изображение обретает артефакты
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        turnsTextView = (TextView) findViewById(R.id.turnsTextView);
        tilesTextView = (TextView) findViewById(R.id.tilesTextView);
        tilesTextView.setText("Поле: " + tilesAmount + "x" + tilesAmount);
        tilesSeekBar = (SeekBar) findViewById(R.id.tilesSeekBar);
        tilesSeekBar.setProgress(tilesAmount - 3);
        tilesSeekBar.setOnSeekBarChangeListener(this);

        prepareField();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }
//TODO подключить openFileDialog https://github.com/18446744073709551615/android-file-chooser-dialog
    private void prepareField(){
        sliceImage();
        initArray();
        mixTiles();
        OnClickListener listener = new OnClickListener() {
            public void onClick(View myView) {
                SquareButton clickedButton = (SquareButton) myView;
                Point clickedPoint = getClickedPoint(clickedButton);
                if (clickedPoint != null && canMove(clickedPoint)) {
                    SquareButton emptyButton = buttons[emptySpace.x][emptySpace.y];
                    SquareButton.swapTilesOnButtons(clickedButton, emptyButton);
                    emptySpace.x = clickedPoint.x;
                    emptySpace.y = clickedPoint.y;
                    if (win()) {
                        turnsTextView.setText("ПОБЕДА!");
                    } else {
                        turn++;
                        turnsTextView.setText("Ход номер " + turn);
                    }
                }
            }
        };


        for (int i = 0; i < tilesAmount; i++) {
            for (int j = 0; j < tilesAmount; j++) {
                Button button = buttons[i][j];
                button.setOnClickListener(listener);
            }
        }
    }

    private void initArray() {
        buttons = new SquareButton[tilesAmount][tilesAmount];
        TableLayout tableLayout = (TableLayout)findViewById(R.id.field);
        tableLayout.removeAllViews();
        for (int row = 0; row < tilesAmount; row++) {
            TableRow tableRow = new TableRow(this);
            for (int cellInRow = 0; cellInRow < tilesAmount; cellInRow++) {
                buttons[row][cellInRow] = new SquareButton(this, tilesAmount);
                tableRow.addView(buttons[row][cellInRow]);
            }
            tableLayout.addView(tableRow);
        }
    }

    private void sliceImage() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int displayWidth = displaymetrics.widthPixels;
        int displayHeight = displaymetrics.heightPixels;
        int sizeOfResizedImage = (displayWidth < displayHeight) ? displayWidth : displayHeight;
        int sizeOfTile = sizeOfResizedImage / tilesAmount;
        Bitmap resizedBMP = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(), R.drawable.puzzleimg), sizeOfResizedImage, sizeOfResizedImage, false);
        int tileNumber = 1;
        int maxTiles = tilesAmount * tilesAmount;
        tileList.clear();
        for (int i = 0; i < tilesAmount; i++) {
            for (int j = 0; j < tilesAmount; j++) {
                if (tileNumber == maxTiles) {
                    tileList.add(new Tile(null, EMPTY_SPACE_NUMBER));
                } else {
                    tileList.add(new Tile(Bitmap.createBitmap(resizedBMP, j * sizeOfTile, i * sizeOfTile, sizeOfTile, sizeOfTile), tileNumber++));
                }
            }
        }
    }

    private void mixTiles() {
        List<Tile> cloneOfTileList = new ArrayList<>(tileList);
        List<Tile> result = new ArrayList<>();
        int cloneListSize = cloneOfTileList.size();
        while (cloneListSize > 0) {
            int randomIndex = random.nextInt(cloneListSize);
            result.add(cloneOfTileList.get(randomIndex));
            cloneOfTileList.remove(randomIndex);
            cloneListSize--;
        }
        int indexOfTile = 0;
        for (int i = 0; i < tilesAmount; i++) {
            for (int j = 0; j < tilesAmount; j++) {
                SquareButton currentButton = buttons[i][j];
                currentButton.setTile(result.get(indexOfTile++));
                if (currentButton.getTile().getNumber() == EMPTY_SPACE_NUMBER) {
                    Point newEmptyPoint = getClickedPoint(currentButton);
                    emptySpace.x = newEmptyPoint.x;
                    emptySpace.y = newEmptyPoint.y;
                }
            }
        }
        turn = 1;
        turnsTextView.setText("Ход номер 1");
    }

    public void mixTilesButtonClick(View view) {
        mixTiles();
    }

    private void changeTilesCount(int newTileAmount){
        newTileAmount += 3;
        if(newTileAmount == tilesAmount)
            return;
        tilesAmount = newTileAmount;
        prepareField();
    }

    private boolean win() {
        int number = 1;
        int maxNumber = tilesAmount * tilesAmount;
        for (int i = 0; i < tilesAmount; i++) {
            for (int j = 0; j < tilesAmount; j++) {
                if (number >= maxNumber)
                    break;
                if (buttons[i][j].getTile().getNumber() == number)
                    number++;
                else
                    return false;
            }
        }
        return true;
    }

    private Point getClickedPoint(Button clickedButton) {
        for (int i = 0; i < tilesAmount; i++) {
            for (int j = 0; j < tilesAmount; j++) {
                if (clickedButton == buttons[i][j]) {
                    Point point = new Point();
                    point.x = i;
                    point.y = j;
                    return point;
                }
            }
        }
        return null;
    }

    private boolean canMove(Point clicked) {
        if (clicked.equals(emptySpace)) {
            return false;
        }

        if (clicked.x == emptySpace.x) {
            int diff = Math.abs(clicked.y - emptySpace.y);
            if (diff == 1) {
                return true;
            }
        } else if (clicked.y == emptySpace.y) {
            int diff = Math.abs(clicked.x - emptySpace.x);
            if (diff == 1) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void onProgressChanged(SeekBar seek, int value, boolean fromTouch) {
        int newTileAmount = value + 3;
        tilesTextView.setText("Поле: " + newTileAmount + "х" + newTileAmount);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        changeTilesCount(seekBar.getProgress());
    }
}