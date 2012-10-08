package in.ultraneo.seriousandroiddeveloper;

import java.util.ArrayList;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class DataLayer {
	
	DatabaseClass dbhelper;
	String fileNAME,tableNAME;
	
	 public DataLayer(Context c,String fileNAME,String tableNAME){
		this.fileNAME = fileNAME;
		this.tableNAME = tableNAME;
		 dbhelper = new DatabaseClass(c,1,fileNAME,tableNAME); 
		 }
	 
	 public void Updatesentdata(int rowID,String smsdelivered)
	 {
		 String[] args={String.valueOf(rowID)};
		 ContentValues cv=new ContentValues();
		 cv.put("txt7", smsdelivered);
		 SQLiteDatabase updatesql = dbhelper.getWritableDatabase();
		 updatesql.update(tableNAME, cv,  "Id = ?", args);
	 }
	 public void Updaterest(int rowID,String lat,String lon,String date,String time,String permission){
		 String[] args={String.valueOf(rowID)};
		 ContentValues cv=new ContentValues();
		 cv.put("txt2", lat);
		 cv.put("txt3", lon);
		 cv.put("txt4", date);
		 cv.put("txt5", time);
		 cv.put("txt7", permission);
		 SQLiteDatabase updatesql = dbhelper.getWritableDatabase();
		 updatesql.update(tableNAME, cv,  "Id = ?", args);
		 
		 
	 }
	 
	 
	 
	 public void DataAdd(String Txt1,String Txt2,String Txt3,String Txt4,String Txt5,String Txt6,String Txt7)
	 {
		 SQLiteDatabase db = dbhelper.getWritableDatabase();
		
		
		 
	        try {
	            ContentValues values = new ContentValues();
	           // values.put("PlayerId", playerId);
	            values.put("txt1", Txt1);
	            values.put("txt2", Txt2);
	            values.put("txt3", Txt3);
	            values.put("txt4", Txt4);
	            values.put("txt5", Txt5);
	            values.put("txt6", Txt6);
	            values.put("txt7", Txt7);
	 
	            db.insert(tableNAME, "", values);
	        } finally {if (db != null) db.close();}
	 }
	 public ArrayList<GameResult>  SelectGames() {
		    SQLiteDatabase db = dbhelper.getReadableDatabase();
		    try {
		        ArrayList<GameResult> results = new ArrayList<GameResult>();
		        Cursor c = db.rawQuery("select * from "+tableNAME, null);
		        if (c.getCount() > 0) {
		            c.moveToFirst();
		            do {
		                results.add(new GameResult(
		                		c.getInt(c.getColumnIndex("Id")),
		                        c.getString(c.getColumnIndex("txt1")),
		                        c.getString(c.getColumnIndex("txt2")),
		                        c.getString(c.getColumnIndex("txt3")),
		                        c.getString(c.getColumnIndex("txt4")),
		                        c.getString(c.getColumnIndex("txt5")),
		                        c.getString(c.getColumnIndex("txt6")),
		                        

		                        c.getString(c.getColumnIndex("txt7"))));//c.getInt(c.getColumnIndex("Difficulty"))));
		            } while (c.moveToNext());
		        }
		        return results;
		    } finally {
		        if (db != null)
		            db.close();
		    }
		}
	 public void deletedata(int rowID)
	 {
		 SQLiteDatabase dbdel= dbhelper.getWritableDatabase();
		 String[] args={String.valueOf(rowID)};
	   	 dbdel.delete(tableNAME, "Id=?", args);
		 
	 }
	 
	 

}
