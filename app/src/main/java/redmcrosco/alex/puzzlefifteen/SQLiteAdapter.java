package redmcrosco.alex.puzzlefifteen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SQLiteAdapter {

    private static final String DATABASE_NAME = "db_newfifteen";
    private static final int DATABASE_VERSION = 1;

    /* database tables */
    private static final String TABLE_CONF = "tbl_conf";
    private static final String TABLE_TRANSACTIONS = "tbl_transactions";

	/* TABLE_USER_LIBRARY table fields */

    private static final String KEY_ID = "id";
    private static final String KEY_BUTTONS = "buttons";
    private static final String KEY_N = "n";


    /* TABLE_TRANSACTIONS table fields */

    private static final String KEY_NAME_TRANS = "name_trans";
    private static final String KEY_BUTTONS_TRANS = "buttons_trans";
    private static final String KEY_N_TRANS = "n_trans";



    /* create table tbl_trans_details script */

    private static final String SCRIPT_CREATE_TABLE_TRANSACTIONS = "create table if not exists " + TABLE_TRANSACTIONS + " (" + KEY_NAME_TRANS + " text, " + KEY_BUTTONS_TRANS + " buttons, " + KEY_N_TRANS + " n );";


    /* create table tbl_user_books_status script */
    private static final String SCRIPT_CREATE_TABLE_USER = "create table if not exists " + TABLE_CONF
            + " (" + KEY_ID + " integer primary key autoincrement, " + KEY_BUTTONS + " buttons, " + KEY_N
            + " n);";

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    private Context context;

    public SQLiteAdapter(Context context) {
        this.context = context;
    }

    /**
     * Open database with read only permission.
     *
     * @return
     * @throws android.database.SQLException
     */
    private SQLiteAdapter openToRead() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        Log.d(this.getClass().getName(), "openToRead");
        return this;
    }

    /**
     * Open database with read/write permission
     *
     * @return
     * @throws android.database.SQLException
     */
    private SQLiteAdapter openToWrite() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        Log.d(this.getClass().getName(), "openToWrite");
        return this;
    }

    /**
     * Close database
     */
    private void close() {
        sqLiteHelper.close();
        Log.d(this.getClass().getName(), "close");
    }

    /**
     * @return number of rows deleted
     */
    public int deleteUserTable() {
        openToWrite();
        int result = sqLiteDatabase.delete(TABLE_CONF, null, null);
        close();
        Log.d(this.getClass().getName(), "All log files deleted successfully.");
        return result;
    }
/*

    public long insertOrUpdateProduct(Button [] buttons, Integer n) {
        if (null == buttons || null == n) {
            return -1;
        }

        boolean isRowExists = false;
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_BUTTONS, toString() ) ;
        contentValues.put(KEY_N, n);



        openToRead();
        // String Query = "SELECT * FROM " + TABLE_PRODUCT + " WHERE " + KEY_CODE + "=? AND " + KEY_DESCRITION + "=? AND " + KEY_NAME + "=? AND " + KEY_PRICE + "=? AND " + KEY_QUANTITY + "=?";
        String Query = "SELECT * FROM " + TABLE_CONF + " WHERE " + KEY_N+ "=?";
        Cursor cursor = sqLiteDatabase.rawQuery(Query, new String[]{name});

        if (cursor.moveToFirst()) {
            isRowExists = true;
        } else {
            isRowExists = false;
        }
        close();

        openToWrite();
        long result;
        if (!isRowExists)
            result = sqLiteDatabase.insert(TABLE_CONF, null, contentValues);
        else
            result = sqLiteDatabase.update(TABLE_CONF, contentValues, KEY_NAME + "=? ",
                    new String[]{name});
        close();
        return result;
    }


    public long insertTransaction(String name, String price, String quantity) {
        if (null == name || null == price || null == quantity) {
            return -1;
        }

        int totalPrice = Integer.parseInt(quantity) * Integer.parseInt(price);


        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME_TRANS, name);
        contentValues.put(KEY_PRICE_TRANS, price);
        contentValues.put(KEY_QUANTITY_TRANS, quantity);
        contentValues.put(KEY_TOTAL_PRICE, totalPrice);


        openToWrite();
        long result;

        result = sqLiteDatabase.insert(TABLE_TRANSACTIONS, null, contentValues);

        close();
        return result;
    }

    public List<TransactionModel> getAllTransaction() {
        List<TransactionModel> lst = new ArrayList<>();


        openToRead();

        String Query = "SELECT * FROM " + TABLE_TRANSACTIONS;
        Cursor cursor = sqLiteDatabase.rawQuery(Query, null);

        if (cursor.moveToFirst())

        {
            do {


                int index_NAME = cursor.getColumnIndex(KEY_NAME_TRANS);
                int index_PRICE = cursor.getColumnIndex(KEY_PRICE_TRANS);
                int index_QUANTITY = cursor.getColumnIndex(KEY_QUANTITY_TRANS);
                int index_TOTAL_PRICE = cursor.getColumnIndex(KEY_TOTAL_PRICE);

                String name = cursor.getString(index_NAME);
                String price = cursor.getString(index_PRICE);
                String quantity = cursor.getString(index_QUANTITY);
                String totalPrice = cursor.getString(index_TOTAL_PRICE);

                TransactionModel p = new TransactionModel(name, quantity, price, totalPrice);
                lst.add(p);
            }

            while (cursor.moveToNext());
        }

        close();


        return lst;
    }

    public Boolean isItemNameExist(String name) {
        if (null == name) {
            return false;
        }

        Boolean nameExist = false;
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);


        openToRead();
        String Query = "SELECT * FROM " + TABLE_CONF + " WHERE " + KEY_NAME + "=? ";
        Cursor cursor = sqLiteDatabase.rawQuery(Query, new String[]{name});

        if (cursor.moveToFirst()) {
            nameExist = true;
        } else {
            nameExist = false;
        }
        close();

        return nameExist;
    }

    public List<ProductModel> getAllProduct() {
        List<ProductModel> lst = new ArrayList<>();


        openToRead();

        String Query = "SELECT * FROM " + TABLE_CONF;
        Cursor cursor = sqLiteDatabase.rawQuery(Query, null);

        if (cursor.moveToFirst())

        {
            do {


                int index_NAME = cursor.getColumnIndex(KEY_NAME);
                int index_CODE = cursor.getColumnIndex(KEY_CODE);
                int index_DESCRITION = cursor.getColumnIndex(KEY_DESCRITION);
                int index_PRICE = cursor.getColumnIndex(KEY_PRICE);
                int index_QUANTITY = cursor.getColumnIndex(KEY_QUANTITY);

                String name = cursor.getString(index_NAME);
                String code = cursor.getString(index_CODE);
                String description = cursor.getString(index_DESCRITION);
                String price = cursor.getString(index_PRICE);
                String quantity = cursor.getString(index_QUANTITY);

                ProductModel p = new ProductModel(buttons, n);
                lst.add(p);
            }

            while (cursor.moveToNext());
        }

        close();


        return lst;
    }

    /**
     * Provides functionality for data definition
     */
    public class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(Context context, String name, CursorFactory factory, int version) {
            //   super(context, Environment.getExternalStorageDirectory() + "/com.homestudy.sqliteinventoryproject/" + name, null, version);
            super(context, name, null, version);
        }

        /**
         * Handle on database create
         */
        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(SCRIPT_CREATE_TABLE_USER);
            db.execSQL(SCRIPT_CREATE_TABLE_TRANSACTIONS);


        }


        /**
         * Handle on database update
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        }
    }

   /* public String getPasswordForEmail(String email) {
        if (null == email) {
            return "";
        }

        String password = "";
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_EMAIL_ID, email);

        openToRead();
        String Query = "SELECT * FROM " + TABLE_PRODUCT + " WHERE " + KEY_EMAIL_ID + "=?";
        Cursor cursor = sqLiteDatabase.rawQuery(Query, new String[]{email});

        if (cursor.moveToFirst()) {
            int index_KEY_PASSWORD = cursor.getColumnIndex(KEY_PASSWORD);
            password = cursor.getString(index_KEY_PASSWORD);
        } else {
            password = "";
        }
        close();

        return password;*/
}

