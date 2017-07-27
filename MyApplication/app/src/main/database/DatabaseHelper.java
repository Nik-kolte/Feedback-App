package database;

import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ABHISHEK(LEO) on 7/26/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "";

    private static String DB_NAME = "fed_app.db";  //check extensions too

    private SQLiteDatabase myDb;

    private Context myContext;
    public  static final int DATABASE_VERSION = 3; //CHECK

    //constructor
    public DatabaseHelper(Context context, String name,CursorFactory factory,int version){
            super(context,name,factory,version);
            this.myContext = context;
        DB_PATH = myContext.getDatabasePath(DB_NAME).toString();
    }
    //Creating empty database on SYSTEM
    public void createDatabase() throws IOException
    {
        boolean dbExist = checkDatabase();
        if(dbExist)
        {
            Log.v("DB exists" , "db exists");
        }

        boolean dbExist1 =  checkDatabase();
        if(!dbExist1)                 //***********************
        {
            this.getReadableDatabase();
            try
            {
                this.close();
                copyDatabase();
            }
            catch (IOException e)
            {
                throw new Error("ERROR copying Database");
            }
        }
    }

    private boolean checkDatabase()
    {
        boolean checkdb = false;
        try
        {
            String path = DB_PATH;
            File dbfile =  new File(path);
            checkdb = dbfile.exists();
        }
        catch (
                SQLException e
                )
        {

        }
        return checkdb;

    }

    private void copyDatabase() throws IOException
    {
        String outfile = DB_PATH;
        OutputStream output = new FileOutputStream(outfile);
        InputStream input = myContext.getAssets().open(DB_NAME);
        byte[] buffer = new byte[1024];
        int len;
        while((len = input.read(buffer))>0)
        {
            output.write(buffer,0,len);
        }
        input.close();
        output.flush();
        output.close();
    }

    //opening the database
    public void openDatabase() throws SQLException
    {
            String path = DB_PATH;
            myDb = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);  //read ONLY
    }

    public synchronized void closeDatabase() throws SQLException
    {
        if(myDb!=null)
        {
            myDb.close();
             //++
        }
        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //DO WE NEED IT?
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        if(i > i1)
        {
            Log.v("","");         //CHECK WHAT TO DO
        }

    }

    //PLEASE CHECK QUERY and ATTRIBUTE PASSWORD AND USERNAME AGAIN
    public boolean getPassword(String userpassword, String TABLE)   //TABLE (teachers and students will use the same variable TABLE )
    {
        Cursor cur;
        cur = myDb.rawQuery("SELECT * FROM "+ TABLE + "WHERE password = ' " + userpassword + "'",null);
        boolean exist = (cur.getCount()>0);
        cur.close();
        myDb.close();
        return exist;
    }
    public boolean getUsername(String username, String TABLE)   //TABLE (teachers and students will use the same variable TABLE )
    {
        Cursor cur;
        cur = myDb.rawQuery("SELECT * FROM "+ TABLE + "WHERE username = ' " + username + "'",null);
        boolean exist = (cur.getCount()>0);
        cur.close();
        myDb.close();
        return exist;
    }


}
