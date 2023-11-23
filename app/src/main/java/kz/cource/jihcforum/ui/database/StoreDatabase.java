package kz.cource.jihcforum.ui.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class StoreDatabase extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "quiz.db";
    static final int DATABASE_VERSION = 7;

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL= "email";
    public static final String COLUMN_PASSWORD = "password";
    Context context;
    public StoreDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_NAME+ " TEXT, " +
                        COLUMN_EMAIL+ " TEXT, " +
                        COLUMN_PASSWORD+ " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
