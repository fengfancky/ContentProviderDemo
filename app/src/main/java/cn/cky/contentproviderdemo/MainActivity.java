package cn.cky.contentproviderdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.cky.contentproviderdemo.provider.Contracts;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button update= (Button) findViewById(R.id.but);
        Button insert= (Button) findViewById(R.id.insert);
        Button delete= (Button) findViewById(R.id.delete);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    insertData();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,e.toString()+"",Toast.LENGTH_LONG).show();
                }

                Toast.makeText(MainActivity.this, queryData()+"", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    updateData();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,e.toString()+"",Toast.LENGTH_LONG).show();
                }

                Toast.makeText(MainActivity.this, queryData()+"", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    deleteData();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,e.toString()+"",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void insertData(){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Contracts.HistoryEntry.COLUMN_ID,"12233332");
        contentValues.put(Contracts.HistoryEntry.COLUMN_NAME,"hello");
        contentValues.put(Contracts.HistoryEntry.COLUMN_PICURL,"http://xx.xxx.xx");
        getContentResolver().insert(Contracts.HistoryEntry.CONTENT_URI,contentValues);

    }

    private void deleteData(){
        int id=getContentResolver().delete(Contracts.HistoryEntry.CONTENT_URI,Contracts.HistoryEntry.COLUMN_ID+"=12233332",null);
        Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();
    }

    private void updateData(){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Contracts.HistoryEntry.COLUMN_NAME,"hi");
        getContentResolver().update(Contracts.HistoryEntry.CONTENT_URI,contentValues,Contracts.HistoryEntry.COLUMN_ID+"=12233332",null);
    }

    private String queryData(){
        Cursor cursor=getContentResolver().query(Contracts.HistoryEntry.CONTENT_URI,null,null,null,null,null);
        String result="";
        if (cursor!=null){
            while (cursor.moveToNext()){
                String contentID=cursor.getString(cursor.getColumnIndex(Contracts.HistoryEntry.COLUMN_ID));
                String name=cursor.getString(cursor.getColumnIndex(Contracts.HistoryEntry.COLUMN_NAME));
                String pic_url=cursor.getString(cursor.getColumnIndex(Contracts.HistoryEntry.COLUMN_PICURL));
                result=contentID+"\n"+name+"\n"+pic_url;
            }
        }
        return result;
    }

}
