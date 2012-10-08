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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Customlist extends SherlockActivity{
   
    private ProgressDialog m_ProgressDialog = null;
    private ArrayList<customlistOrder> m_orders = null;
    private CustomlistOrderAdapter m_adapter;
    private Runnable viewOrders;
    ListView list;
    
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.customlistexample);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        m_orders = new ArrayList<customlistOrder>();
        list = (ListView)findViewById(R.id.list);
        this.m_adapter = new CustomlistOrderAdapter(getApplicationContext(), R.layout.examplerowxinside, m_orders);
       list.setAdapter(this.m_adapter);
       
        viewOrders = new Runnable(){
            @Override
            public void run() {
                getOrders();
            }
        };
        Thread thread =  new Thread(null, viewOrders, "MagentoBackground");
        thread.start();
        m_ProgressDialog = ProgressDialog.show(Customlist.this,    
              "Please wait...", "Retrieving data ...", true);
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
              m_orders = new ArrayList<customlistOrder>();
              customlistOrder o1 = new customlistOrder();
              o1.setOrderName("Title");
              o1.setOrderStatus(getResources().getString(R.string.longtext));
              o1.setbitMAP(android.R.drawable.ic_menu_compass);
              
              customlistOrder o2 = new customlistOrder();
              o2.setOrderName("Title");
              o2.setOrderStatus(getResources().getString(R.string.longtext));
              o2.setbitMAP(android.R.drawable.ic_menu_mapmode);
              
              customlistOrder o3 = new customlistOrder();
              o3.setOrderName("Title");
              o3.setOrderStatus(getResources().getString(R.string.longtext));
              o3.setbitMAP(android.R.drawable.ic_menu_myplaces);
              
              customlistOrder o4 = new customlistOrder();
              o4.setOrderName("Title");
              o4.setOrderStatus(getResources().getString(R.string.longtext));
              o4.setbitMAP(android.R.drawable.ic_menu_mapmode);
              
              
              customlistOrder o5 = new customlistOrder();
              o5.setOrderName("Title");
              o5.setOrderStatus(getResources().getString(R.string.longtext));
              o5.setbitMAP(android.R.drawable.ic_menu_info_details);
              
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
    
}