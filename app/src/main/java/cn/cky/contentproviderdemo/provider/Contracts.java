package cn.cky.contentproviderdemo.provider;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by fengfancky
 */
public class Contracts {

    protected static final String CONTENT_AUTHORITY = "cky.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final class HistoryEntry implements BaseColumns {

        protected static final String TABLE_NAME = "history";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        protected static Uri buildUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static final String COLUMN_ID="contentID";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_PICURL="picUrl";


    }

    public static final class CollectEntry implements BaseColumns {
        protected static final String TABLE_NAME="collect";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        protected static Uri buildUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static final String COLUMN_ID="contentID";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_PICURL="picUrl";
        public static final String COLUMN_PACKAGENAME="packageName";

    }



}
