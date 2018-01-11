package cn.cky.contentproviderdemo.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Provider
 */
public class UserInfoProvider extends ContentProvider {
    private DBHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = new DBHelper(getContext());
        return true;
    }


    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        Cursor cursor = null;
        switch ( buildUriMatcher().match(uri)) {
            case HISTORY:
                cursor = db.query(Contracts.HistoryEntry.TABLE_NAME, projection, selection, selectionArgs, sortOrder, null, null);
                break;
            case COLLECT:
                cursor = db.query(Contracts.CollectEntry.TABLE_NAME, projection, selection, selectionArgs, sortOrder, null, null);
                break;
        }

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri;
        long _id;
        switch ( buildUriMatcher().match(uri)) {
            case HISTORY:
                _id = db.insert(Contracts.HistoryEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = Contracts.HistoryEntry.buildUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;

            case COLLECT:
                _id = db.insert(Contracts.CollectEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = Contracts.CollectEntry.buildUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;

            default:
                throw new android.database.SQLException("Unknown uri: " + uri);
        }
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        int num=0;
        switch ( buildUriMatcher().match(uri)) {
            case HISTORY:
                num=db.delete(Contracts.HistoryEntry.TABLE_NAME,selection,selectionArgs);
                break;
            case COLLECT:
                num=db.delete(Contracts.CollectEntry.TABLE_NAME,selection,selectionArgs);
                break;
        }
        return num;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        int num=0;
        switch ( buildUriMatcher().match(uri)) {
            case HISTORY:
                num=db.update(Contracts.HistoryEntry.TABLE_NAME,values,selection,null);
                break;
            case COLLECT:
                num=db.update(Contracts.CollectEntry.TABLE_NAME,values,selection,null);
                break;
        }
        return num;
    }

    private final static int HISTORY = 100;
    private final static int COLLECT = 200;

    static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = Contracts.CONTENT_AUTHORITY;

        matcher.addURI(authority, Contracts.HistoryEntry.TABLE_NAME, HISTORY);
        matcher.addURI(authority, Contracts.CollectEntry.TABLE_NAME, COLLECT);

        return matcher;
    }
}
