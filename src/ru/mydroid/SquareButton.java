package ru.mydroid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.Button;

public class SquareButton extends Button {
	private Tile tile;
	public SquareButton(Context context, int tileAmount) {
		super(context);
		changeFontSizeByTileAmount(tileAmount);
		setTextColor(Color.WHITE);
	}

	public SquareButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void changeFontSizeByTileAmount(int tileAmount){
		float textSize = (float) (35 - tileAmount * 1.8 );
		setTextSize(textSize);
	}
	// This is used to make square buttons.
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
		refreshBackground();
	}

	static public void swapTilesOnButtons(SquareButton firstSquareButton, SquareButton secondSquareButton){
		Tile firstSquareButtonTile = firstSquareButton.getTile();
		firstSquareButton.setTile(secondSquareButton.getTile());
		secondSquareButton.setTile(firstSquareButtonTile);
	}

	public void refreshBackground(){
		this.setBackground(new BitmapDrawable(tile.getImage()));
		this.setText(String.valueOf(tile.getNumber()));
		if(getTile().getNumber() == -1){
			this.setVisibility(INVISIBLE);
		}
		else {
			this.setVisibility(VISIBLE);
		}
	}
}