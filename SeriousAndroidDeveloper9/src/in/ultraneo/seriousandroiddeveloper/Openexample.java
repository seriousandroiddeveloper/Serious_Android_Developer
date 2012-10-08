package in.ultraneo.seriousandroiddeveloper;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Openexample extends SherlockActivity {
LinearLayout linear,downloadsource;
TextView name,email,info,latestupdate,exampletitle,hyperlink,permission;
int id;
ImageView profilepic;
String link;

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	 switch (item.getItemId()) {
        
	    case android.R.id.home:
        {
        	finish();
        	
        	break;
        }      
	        default:
	            return super.onOptionsItemSelected(item);
	    }
		return false;
}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.openexample2);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
		linear = (LinearLayout)findViewById(R.id.linearlayout1);
		downloadsource = (LinearLayout)findViewById(R.id.downloadsource);
		name = (TextView)findViewById(R.id.authorname);
		email = (TextView)findViewById(R.id.mailid);
		info = (TextView)findViewById(R.id.information);
		latestupdate = (TextView)findViewById(R.id.lastupdate);
		exampletitle = (TextView)findViewById(R.id.exampletitle);
		profilepic = (ImageView)findViewById(R.id.profilepic);
		hyperlink = (TextView)findViewById(R.id.link);
		permission = (TextView)findViewById(R.id.permission);
		permission.setSingleLine(false);
		
		Bundle bundle = getIntent().getExtras();
		 id = bundle.getInt("id");
		String title = bundle.getString("title");
		int pic =bundle.getInt("pic");
        String authorname = bundle.getString("name");
        String emailid = bundle.getString("email");
        String info1 = bundle.getString("info");
         String update = bundle.getString("update");
         link = bundle.getString("link");
         String permissiontxt =  bundle.getString("permission");
         
         setTitle(title);
         exampletitle.setText("Open "+title+" Example");
         profilepic.setImageResource(pic);
         name.setText(authorname);
         
         SpannableString contentUnderline2 = new SpannableString(
     			emailid);
     	contentUnderline2.setSpan(new UnderlineSpan(), 0,
     			contentUnderline2.length(), 0);
         email.setText(contentUnderline2);
         info.setText(info1);
         latestupdate.setText(update);
         
         SpannableString contentUnderline = new SpannableString(
			link);
	contentUnderline.setSpan(new UnderlineSpan(), 0,
			contentUnderline.length(), 0);
	
         hyperlink.setText(contentUnderline);
         permission.setText(permissiontxt);
         
		linear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(id==0)
		         {
		        	 
		        	startActivity(new Intent(v.getContext(), CamerabyIntent.class));
		         }else if(id==1)
		         {
		        	 startActivity(new Intent(v.getContext(), CameraAPI.class));	 
		         }else if(id==2)
		         {
		        	 startActivity(new Intent(v.getContext(), Customlist.class));	 
		         }else if(id==3)
		         {
		        	 startActivity(new Intent(v.getContext(), Customdialogwithcustomlist.class));	 
		         }
				
				
			}
		});
		
		downloadsource.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(link));
				startActivity(i);
				
			}
		});
		
		
	}

	
}
