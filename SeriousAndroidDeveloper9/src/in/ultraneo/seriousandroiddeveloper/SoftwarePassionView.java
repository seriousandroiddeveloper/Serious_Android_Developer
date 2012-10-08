package in.ultraneo.seriousandroiddeveloper;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;



import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SoftwarePassionView extends SherlockActivity implements OnItemClickListener{
   
    private ProgressDialog m_ProgressDialog = null;
    private ArrayList<Order> m_orders = null;
    private OrderAdapter m_adapter;
    private Runnable viewOrders;
    private String POST_ID = "POSTID";
    ProgressDialog dialogc;
    ArrayList<GameResult> table;
    ListView list;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        list = (ListView)findViewById(R.id.list);
        
        m_orders = new ArrayList<Order>();
        this.m_adapter = new OrderAdapter(this, R.layout.rowx, m_orders);
       list.setAdapter(this.m_adapter);
       
       list.setOnItemClickListener(this);
        
       
        viewOrders = new Runnable(){
            @Override
            public void run() {
            	
            	
                getOrders();
            }
        };
        
        m_ProgressDialog = ProgressDialog.show(SoftwarePassionView.this,    
                "Please wait...", "Retrieving data ...", true);
       
        if(isOnline()){
        	ReadXMLFile read = new ReadXMLFile();
			ArrayList<UpdateItem> update = read.ReadXML("", "");
			int j2=Integer.parseInt(update.get(0).POSTID);
			int inphone = Integer.parseInt(new UltraFileaccess().get_Data(POST_ID,SoftwarePassionView.this));
        if(inphone<j2)
		  {
        	 
        	
        	
			int j=Integer.parseInt(update.get(0).POSTID);
			int i;
			
			for(i =0;i<j;i++)
			{
			DataLayer data = new DataLayer(SoftwarePassionView.this, "update.db", "SAVE");
			UpdateItem item = update.get(i);
			
			data.DataAdd(item.POSTID,item.subject,item.message,item.picture,item.video, item.referencelink,"1");
				
				
			}
			 // Toast.makeText(SoftwarePassionView.this, String.valueOf(j)+","+String.valueOf(i), Toast.LENGTH_SHORT).show();
			  new UltraFileaccess().Load_Data(POST_ID,String.valueOf(j),SoftwarePassionView.this);
			  
			  
		  }
		  else
		  {
			  Toast.makeText(SoftwarePassionView.this, "No new Updates", Toast.LENGTH_SHORT).show();
		  }
        }else
		  {
        	Toast.makeText(SoftwarePassionView.this, "No internet connection avaialable", Toast.LENGTH_SHORT).show();
		  }
        
                
        Thread thread =  new Thread(null, viewOrders, "MagentoBackground");
        thread.start();
       
       
    }
   
   
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	 MenuInflater inflater = getSupportMenuInflater();//getMenuInflater();
 	    inflater.inflate(R.menu.activity_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        
	        
	        
	        
	        case R.id.item4:
	        {
	        	startActivity(new Intent(SoftwarePassionView.this, About.class));
	        	break;
	        	
	        }
	        
	        case R.id.item3:
	        {
	        	
	        	startActivity(new Intent(SoftwarePassionView.this, QueryActivity.class));
	        	break;
	        	
	        }
	        case R.id.item5:
	        {
	        	
	        	startActivity(new Intent(SoftwarePassionView.this, ExampleList.class));
	        	break;
	        	
	        }
	        
	        
	         
	        default:
	            return super.onOptionsItemSelected(item);
	    }
		return false;
	}


	class startupdata extends AsyncTask<Void, Void, String>{

    	
		@Override
		protected void onPostExecute(String result) {
			if(dialogc.isShowing()){
				dialogc.dismiss(); 
				
				
				}
			
			
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			dialogc = ProgressDialog.show(SoftwarePassionView.this,null,"Please wait...", true);
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(Void... arg0) {

			
        	ReadXMLFile read = new ReadXMLFile();
			ArrayList<UpdateItem> update = read.ReadXML("", "");
			int j=Integer.parseInt(update.get(0).POSTID);
			int i;
			int up=0;
			for(i =j;i>j-5;i--)
			{
			DataLayer data = new DataLayer(SoftwarePassionView.this, "update.db", "SAVE");
			UpdateItem item = update.get(up);
			data.DataAdd(item.POSTID,item.subject,item.message,item.picture,item.video, item.referencelink,"1");
				
				up++;
			}
			  Toast.makeText(SoftwarePassionView.this, String.valueOf(j)+","+String.valueOf(i), Toast.LENGTH_SHORT).show();
			  new UltraFileaccess().Load_Data(POST_ID,String.valueOf(j),SoftwarePassionView.this);
			  
			  
			  
			return null;
		}
    	
    }
    
    protected boolean isOnline() {
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnected()) {
	        return true;
	    } else {
	        return false;
	    }
	}
    
    private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if(m_orders != null && m_orders.size() > 0){
                m_adapter.notifyDataSetChanged();
                for(int i=0;i<m_orders.size();i++)
                m_adapter.add(m_orders.get(i));
            }
           // m_ProgressDialog.dismiss();
            
            m_adapter.notifyDataSetChanged();
            
        }
    };
    
    private void getOrders(){
          try{
        	  
			  m_orders = new ArrayList<Order>();
			  
			  
			  DataLayer gettable = new DataLayer(SoftwarePassionView.this, "update.db", "SAVE");
			  table = gettable.SelectGames();
			  int highestpostvalue = Integer.parseInt(new UltraFileaccess().get_Data(POST_ID, SoftwarePassionView.this));
			  int i;
			  for( i=highestpostvalue;i>highestpostvalue-3;i--)
			  {
				  if(i==0)
				  {
					  break;
				  }
				  int j;
				  GameResult item = null ;
				  for(j =0;j<table.size();j++)
				  {
					   item = table.get(j);
					 if( Integer.parseInt(item.txt1)==i)
					 {
						break; 
					 }
				  }
				 
				  Order o1 = new Order();
	              o1.setOrderName(item.txt2);
	              o1.setOrderStatus(item.txt3);
	              o1.setbitMAP(item.txt4);
	              o1.setPOST(item.txt1);
	              o1.setvideo(item.txt5);
	              
	              o1.setRlink(item.txt6);
	              m_orders.add(o1);
				  
			  }
			  
			  if(i>0){
			  Order o2 = new Order();
			  
              o2.setOrderName("more");
              o2.setOrderStatus("");
              o2.setbitMAP(null);
              
              o2.setPOST(String.valueOf(highestpostvalue-3));
              o2.setvideo("");
              o2.setRlink("");
              
              m_orders.add(o2);
			  }
  			
           m_ProgressDialog.dismiss();
              Log.i("ARRAY", ""+ m_orders.size());
            } catch (Exception e) {
            	m_ProgressDialog.dismiss();
              Log.e("BACKGROUND_PROC", e.getMessage());
            }
            runOnUiThread(returnRes);
        }
    

    private class OrderAdapter extends ArrayAdapter<Order> {

        private ArrayList<Order> items;
        public ImageLoader imageLoader;
        public OrderAdapter(Context context, int textViewResourceId, ArrayList<Order> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        	imageLoader=new ImageLoader(SoftwarePassionView.this);
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.rowx, null);
                }
                Order o = items.get(position);
                if (o != null) {
                        TextView tt = (TextView) v.findViewById(R.id.toptext);
                        
                        TextView bt = (TextView) v.findViewById(R.id.textView1);
                        
                        ImageView ii = (ImageView) v.findViewById(R.id.icon);
                      
                        
                        if (tt != null) {
                        	if(o.getOrderName().equals("more"))
                        	{
                        		tt.setText("  More  ");
                        		
                        	}else{
                        		
                        		if(o.getOrderName().equals("previ")){
                        			tt.setText("  Pervious post  ");
                        		}else{
                        		
                        		tt.setText(o.getOrderName());                            }}
                        	}
                              
                        if(bt != null){
                        	
                        	if(o.getOrderStatus().length()<=100)
                        	{
                        		bt.setText(o.getOrderStatus());
                        	}else
                        	{
                        		
                        		bt.setText(o.getOrderStatus().substring(0, 90)+"....");
                        	}
                        	
                              
                        }
                        if(ii != null){
                        	//Bitmap tep = BitmapFactory.decodeResource(getResources(),o.getbitMAP());
                        	if(o.getbitMAP()==null)
                        	{
                        		ii.setVisibility(View.GONE);
                        		bt.setVisibility(View.GONE);
                        		
                        		
                        	}else{
                        		ii.setVisibility(View.VISIBLE);
                        		bt.setVisibility(View.VISIBLE);
                        	imageLoader.DisplayImage(o.getbitMAP(), ii);
                        	
                        	}
                            
                      }
                        
                }
                
                return v;
        }
}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
		
		Order item = m_adapter.getItem(arg2);
		
		
		
	if(item.getOrderName().equals("more"))
	{
		int postvalue = Integer.parseInt(item.getPOST());
		m_adapter.clear();
		//m_orders = new ArrayList<Order>();
		//m_adapter = new OrderAdapter(this, R.layout.rowx, m_orders);
		
		Order o1 = new Order();
		o1.setOrderStatus("");
		o1.setOrderName("previ");
		o1.setPOST(String.valueOf(postvalue+1));
		Log.i("uo", String.valueOf(postvalue+1));
		o1.setbitMAP(null);
		o1.setvideo("");
		o1.setRlink("");
		m_adapter.add(o1);
		
		 int highestpostvalue =postvalue;
		 
		 int i;
		  for( i=highestpostvalue;i>highestpostvalue-3;i--)
		  {
			  if(i==0)
			  {
				  break;
			  }
			  int j;
			  GameResult itemv = null ;
			 
			  for(j =0;j<table.size();j++)
			  {
				   itemv = table.get(j);
				 if( Integer.parseInt(itemv.txt1)==i)
				 {
					break; 
				 }
			  }
			 
			  Order o1v = new Order();
             o1v.setOrderName(itemv.txt2);
             
             o1v.setOrderStatus(itemv.txt3);
             o1v.setbitMAP(itemv.txt4);
             o1v.setPOST(itemv.txt1);
             o1v.setvideo(itemv.txt5);
             o1v.setRlink(itemv.txt6);
             
             m_orders.add(o1v);
             m_adapter.add(o1v);
             
			  
		  }
		  //dont cal
		  if(i>0){
		  Order o2 = new Order();
		  
         o2.setOrderName("more");
         o2.setOrderStatus("");
         o2.setbitMAP(null);
         
         o2.setPOST(String.valueOf(highestpostvalue-3));
         o2.setvideo("");
         o2.setRlink("");
         
         m_orders.add(o2);
         m_adapter.add(o2);
         
         
         m_adapter.notifyDataSetChanged();
         list.smoothScrollToPosition(0);
		  }
		
		//  runOnUiThread(returnRes);
		
		
		
		
	}
	else
	
	if(item.getOrderName().equals("previ"))
	{
		int value =Integer.parseInt(item.getPOST());
		int max = Integer.parseInt(new UltraFileaccess().get_Data(POST_ID, SoftwarePassionView.this));
		m_adapter.clear();
		Order[] O = new Order[5];
		int array =0;
		
		if(value>1) {
		 Order o2 = new Order();
         o2.setOrderName("more");
         o2.setOrderStatus("");
         o2.setbitMAP(null);
         
         o2.setPOST(String.valueOf(value-1));
         o2.setvideo("");
         o2.setRlink("");
         O[0]=o2;
         
         m_orders.add(o2);
		}
		else{
			array=-1;
		}
         
         
         int j;
         for(j=value;j<value+3;j++)
         {
        	
        	 if(j==max+1)
        	 {
        		 break;
        	 }
        	 
        	 GameResult itemv = null ;
			  for(int g =0;g<table.size();g++)
			  {
				   itemv = table.get(g);
				 if( Integer.parseInt(itemv.txt1)==j)
				 {
					break; 
				 }
			  }
        	 
			  Order o1v = new Order();
	             o1v.setOrderName(itemv.txt2 );
	             
	             o1v.setOrderStatus(itemv.txt3);
	             o1v.setbitMAP(itemv.txt4);
	             o1v.setPOST(itemv.txt1);
	             o1v.setvideo(itemv.txt5);
	             o1v.setRlink(itemv.txt6);
	             array=array+1;
	             O[array]= o1v;
			  
         }
         if(j<10)
         {
        	 
        	 Order o1v = new Order();
             o1v.setOrderName("previ");
             
             o1v.setOrderStatus("");
             o1v.setbitMAP(null);
             o1v.setPOST(String.valueOf(j));
             o1v.setvideo("");
             o1v.setRlink("");
        	 m_adapter.add(o1v);
        	 
        	 
         }
         
         
        for(int i = array;i>=0;i--)
        {
        m_adapter.add(O[i]);	
        }
		
        list.smoothScrollToPosition(value);	
		
	}else {
		
		GameResult itemv = null ;
		  for(int g =0;g<table.size();g++)
		  {
			   itemv = table.get(g);
			 if( Integer.parseInt(itemv.txt1)==Integer.parseInt(item.getPOST()))
			 {
				break; 
			 }
		  }
		 Bundle bundle = new Bundle();
      	 bundle.putString("head1",itemv.txt2);
      	 bundle.putString("head2",itemv.txt3);
      	 bundle.putString("head3",itemv.txt4);
      	bundle.putString("head4",itemv.txt5);
     	 bundle.putString("head5", itemv.txt6);
     	
      	Intent i = new Intent(v.getContext(),ActivityUpdateItem.class);
		 i.putExtras(bundle);
		startActivity(i);
	}
		
		
	}
	
	
	
}