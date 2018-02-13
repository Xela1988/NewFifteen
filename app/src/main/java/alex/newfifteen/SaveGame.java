package alex.newfifteen;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;

import java.util.HashMap;

/**
 * Created by Alex on 02/11/2017.
 */
public class SaveGame  extends SQLiteOpenHelper {

    public static String AUTHORITY = "alex.newfifteen.SaveGame";
    public static Uri CONTENT_URI_PUZZLES = Uri.parse("content://" + AUTHORITY + "/puzzlefifteen");
    private static final String TAG = "PuzzleNewFifteenProvider";

    /**
     * This class helps open, create, and upgrade the database file.
     */
    private static final String DATABASE_NAME = "newFifteenSave.db";
   // private static final int DATABASE_VERSION = 1; // 7.6 lessons
    private static final String TABLE_NAME = "listFifteen_data";

    private static HashMap<String, String> sGamesProjectionMap;
    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";
    /*  protected static final int TYPE_PUZZLE = 1;
      protected static final int TYPE_PRACTICE = 2;
      public static final String COL_ID = "_ID";
      public static final String COL_PGN = "PGN";
      public static final String COL_TYPE = "PUZZLE_TYPE";
      protected static UriMatcher sUriMatcher;
      */
    public static final String[] COLUMNS = {
            COL1,
            COL2,
    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                "("
                + COL1 + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + "ITEM1 TEXT);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean salvaGame(String item1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public SaveGame(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public Cursor caricaGame() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    /*
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                    + COL1 + "INTEGER PRIMARY KEY, "
                    + COL2 + "VARCHAR "
                    + ");" );

        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }*/
}



