package in.ultraneo.seriousandroiddeveloper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseClass extends SQLiteOpenHelper{
	String tableNAME;

	public DatabaseClass(Context context,int version,String DBName,String tableNAME) {
		super(context,DBName,null,1);
		this.tableNAME = tableNAME;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table "+tableNAME+" " +
				"(Id integer primary key autoincrement, " +
				"txt1 TEXT, " +
                "txt2 TEXT, " +
                "txt3 TEXT, " +
                "txt4 TEXT, " +
                "txt5 TEXT, " +
                "txt6 TEXT, " +
                "txt7 TEXT);");
			
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}