package in.ultraneo.seriousandroiddeveloper;



import java.util.ArrayList;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ExampleList extends SherlockActivity implements OnItemClickListener{
   
    private ProgressDialog m_ProgressDialog = null;
    private ArrayList<TextArrangment> m_orders = null;
    private ExampleAdapter m_adapter;
    private Runnable viewOrders;
    ListView listview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.example_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        m_orders = new ArrayList<TextArrangment>();
        this.m_adapter = new ExampleAdapter(this, R.layout.example_listrow, m_orders);
        listview = (ListView)findViewById(R.id.list);
        listview.setAdapter(this.m_adapter);
        listview.setOnItemClickListener(this);
       
        viewOrders = new Runnable(){
            @Override
            public void run() {
                getOrders();
            }
        };
        Thread thread =  new Thread(null, viewOrders, "MagentoBackground");
        thread.start();
        m_ProgressDialog = ProgressDialog.show(ExampleList.this,    
              "Please wait...", "Retrieving data ...", true);
    }
    
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
    private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if(m_orders != null && m_orders.size() > 0){
                m_adapter.notifyDataSetChanged();
                for(int i=0;i<m_orders.size();i++)
                m_adapter.add(m_orders.get(i));
            }
            m_ProgressDialog.dismiss();
            m_adapter.notifyDataSetChanged();
        }
    };
    private void getOrders(){
          try{
              m_orders = new ArrayList<TextArrangment>();
              TextArrangment o1 = new TextArrangment();
              o1.setOrderName("Camera By Intent");
              o1.setOrderStatus("Example of Camera Android Intent, Easy to learn source code avaiable here open this to view and download the source.");
             
              
              TextArrangment o2 = new TextArrangment();
              o2.setOrderName("Camera API");
              o2.setOrderStatus("Capture Image directly from your application without using default camera application. Easy to learn Source code is avaiable insde open this to view and download the source.");
             
              
              TextArrangment o3 = new TextArrangment();
              o3.setOrderName("Ultra Custom List");
              o3.setOrderStatus("New Ultra Custom Listview, Open this to view and download the source code.");
             
              
              TextArrangment o4 = new TextArrangment();
              o4.setOrderName("Custom List inside Custom Dialog box");
              o4.setOrderStatus("Open this to view and download the source code.");
             
              
              
              TextArrangment o5 = new TextArrangment();
              o5.setOrderName("Share your Source");
              
              o5.setOrderStatus(getResources().getString(R.string.sharesoucecodetous));
              //o5.setbitMAP(android.R.drawable.ic_menu_info_details);
              
              m_orders.add(o1);
              m_orders.add(o2);
              m_orders.add(o3);
              m_orders.add(o4);
              m_orders.add(o5);
           
              Log.i("ARRAY", ""+ m_orders.size());
            } catch (Exception e) {
              Log.e("BACKGROUND_PROC", e.getMessage());
            }
            runOnUiThread(returnRes);
        }
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		
		if(position==0)
		{
			 Bundle bundle = new Bundle();
	      	 bundle.putInt("id", position);
	      	bundle.putInt("pic",R.drawable.narayanan);
	      	 bundle.putString("title","Camera By Intent");
	      	 bundle.putString("name"," Chandran Narayanan");
	      	 bundle.putString("email"," cnarayan12ster@gmail.com");
	      	bundle.putString("info",getResources().getString(R.string.longinfocameraintent));
	     	 bundle.putString("update","Oct 7,2012");
	     	 bundle.putString("link","http://github.com/seriousandroiddeveloper/Camera_Intent");
	     	bundle.putString("permission",getResources().getString(R.string.camerintentpermission)+"\n\n"+getResources().getString(R.string.SDcardpermission)+"\n");
	     	 //permission
	      	Intent i = new Intent(v.getContext(),Openexample.class);
			 i.putExtras(bundle);
			startActivity(i);
			
			
		}else if(position==1)
		{
			Bundle bundle = new Bundle();
	      	 bundle.putInt("id", position);
	      	bundle.putInt("pic",R.drawable.narayanan);
	      	 bundle.putString("title","Camera API");
	      	 bundle.putString("name"," Chandran Narayanan");
	      	 bundle.putString("email"," cnarayan12ster@gmail.com");
	      	bundle.putString("info",getResources().getString(R.string.cameraapiinfo));
	     	 bundle.putString("update","Oct 8,2012");
	     	 bundle.putString("link","http://github.com/seriousandroiddeveloper/Camera_API");
	     	bundle.putString("permission",getResources().getString(R.string.camerintentpermission)+"\n\n"+getResources().getString(R.string.camerAPI)+"\n\n"+getResources().getString(R.string.SDcardpermission)+"\n");
	      	Intent i = new Intent(v.getContext(),Openexample.class);
			 i.putExtras(bundle);
			startActivity(i);
			
			
		}else if(position==2){
			Bundle bundle = new Bundle();
	      	 bundle.putInt("id", position);
	      	bundle.putInt("pic",R.drawable.narayanan);
	      	 bundle.putString("title","Ultra Custom List");
	      	 bundle.putString("name"," Chandran Narayanan");
	      	 bundle.putString("email"," cnarayan12ster@gmail.com");
	      	bundle.putString("info","Its features \n "+"* Custom listview with Image and Text. \n * With special background drawable. \n * drawable made up of Shape xml.\n");
	     	 bundle.putString("update","Oct 10,2012");
	     	 bundle.putString("link","http://github.com/seriousandroiddeveloper/CustomListAdapter");
	     	bundle.putString("permission","No special permission required");
	      	Intent i = new Intent(v.getContext(),Openexample.class);
			 i.putExtras(bundle);
			startActivity(i);
			
		}else if(position==3){
			Bundle bundle = new Bundle();
	      	 bundle.putInt("id", position);
	      	bundle.putInt("pic",R.drawable.narayanan);
	      	 bundle.putString("title","Custom List inside Dialog box");
	      	 bundle.putString("name"," Chandran Narayanan");
	      	 bundle.putString("email"," cnarayan12ster@gmail.com");
	      	bundle.putString("info","Its features \n "+"* Custom listview with Image and Text. \n * Custom ListView inside Custom dialog box"+"\n");
	     	 bundle.putString("update","Oct 11,2012");
	     	 bundle.putString("link","http://github.com/seriousandroiddeveloper/Customlist_inside_Custom_DialogBox");
	     	bundle.putString("permission","No special permission required");
	      	Intent i = new Intent(v.getContext(),Openexample.class);
			 i.putExtras(bundle);
			startActivity(i);
			
		}
		
		
		
	}
    
    
}