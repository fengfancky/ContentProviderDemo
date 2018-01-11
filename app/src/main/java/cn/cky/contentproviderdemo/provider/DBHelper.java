package cn.cky.contentproviderdemo.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "cky_test.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_HISTORY_TABLE = "CREATE TABLE "
                + Contracts.HistoryEntry.TABLE_NAME + "( "
                + Contracts.HistoryEntry.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + Contracts.HistoryEntry.COLUMN_NAME + "  CHAR(50), "
                + Contracts.HistoryEntry.COLUMN_PICURL + " CHAR(50));";

        final String SQL_CREATE_COLLECT_TABLE = "CREATE TABLE "
                + Contracts.CollectEntry.TABLE_NAME + "( "
                + Contracts.CollectEntry.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + Contracts.CollectEntry.COLUMN_NAME + "  CHAR(50), "
                + Contracts.CollectEntry.COLUMN_PICURL + " CHAR(50), "
                + Contracts.CollectEntry.COLUMN_PACKAGENAME + " CHAR(50) );";

        db.execSQL(SQL_CREATE_HISTORY_TABLE);
        db.execSQL(SQL_CREATE_COLLECT_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contracts.HistoryEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Contracts.CollectEntry.TABLE_NAME);
        onCreate(db);
    }
}
