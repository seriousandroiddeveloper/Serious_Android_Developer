package in.ultraneo.seriousandroiddeveloper;




import java.util.ArrayList;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Customdialogwithcustomlist extends SherlockActivity {
Button btn1;
ListView list;
private OrderAdapter m_adapter;
private ArrayList<Orderofcustomdialogbox> m_orders = null;
private  Boolean listalternater= false;
private Boolean[] checkboxstatus = new Boolean[9];

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
        setContentView(R.layout.customdialoglist);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        for(int i=0;i<checkboxstatus.length;i++)
        {
        	checkboxstatus[i]=false;
        }
        btn1 = (Button)findViewById(R.id.button1);
        
        
        btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				CustomizeDialog dialog = new CustomizeDialog(v.getContext(), R.layout.exampleofcustomdialogbox);
				/*Dialog dialog = new Dialog(v.getContext());
				dialog.setContentView(R.layout.activity_main);*/
				
	
				list = (ListView)dialog.findViewById(R.id.listView1);
				 m_orders = new ArrayList<Orderofcustomdialogbox>();
			        m_adapter = new OrderAdapter(v.getContext(), R.layout.customdialogrow, m_orders);
			        list.setAdapter(m_adapter);
			        m_orders = new ArrayList<Orderofcustomdialogbox>();
		              Orderofcustomdialogbox o1 = new Orderofcustomdialogbox();
		              o1.setOrderName("Bed Bugs");
		              o1.setbitMAP(android.R.drawable.ic_menu_compass);
		              m_orders.add(o1);
		              m_adapter.add(o1);
		              
		              Orderofcustomdialogbox o2 = new Orderofcustomdialogbox();
		              o2.setOrderName("Simple Bugs");
		              o2.setbitMAP(android.R.drawable.ic_menu_compass);
		              o2.setChecked(true);
		              m_orders.add(o2);
		              m_adapter.add(o2);
		              
		              Orderofcustomdialogbox o3 = new Orderofcustomdialogbox();
		              o3.setOrderName("Ants");
		              o3.setbitMAP(android.R.drawable.ic_menu_compass);
		              m_orders.add(o3);
		              m_adapter.add(o3);
		              
		              Orderofcustomdialogbox o4 = new Orderofcustomdialogbox();
		              o4.setOrderName("Bees");
		              o4.setbitMAP(android.R.drawable.ic_menu_compass);
		              m_orders.add(o4);
		              m_adapter.add(o4);
		              
		              Orderofcustomdialogbox o5 = new Orderofcustomdialogbox();
		              o5.setOrderName("Butterflies");
		              o5.setbitMAP(android.R.drawable.ic_menu_compass);
		              m_orders.add(o5);
		              m_adapter.add(o5);
		              
		              Orderofcustomdialogbox o6 = new Orderofcustomdialogbox();
		              o6.setOrderName("Beatles");
		              o6.setbitMAP(android.R.drawable.ic_menu_compass);
		              m_orders.add(o6);
		              m_adapter.add(o6);
		              
		              Orderofcustomdialogbox o7 = new Orderofcustomdialogbox();
		              o7.setOrderName("Flies");
		              o7.setbitMAP(android.R.drawable.ic_menu_compass);
		              m_orders.add(o7);
		              m_adapter.add(o7);
		              
		              Orderofcustomdialogbox o8 = new Orderofcustomdialogbox();
		              o8.setOrderName("Mites");
		              o8.setbitMAP(android.R.drawable.ic_menu_compass);
		              m_orders.add(o8);
		              m_adapter.add(o8);
		              
		              Orderofcustomdialogbox o9 = new Orderofcustomdialogbox();
		              o9.setOrderName("Mites");
		              o9.setbitMAP(android.R.drawable.ic_menu_compass);
		              m_orders.add(o9);
		              m_adapter.add(o9);
		              
		              
		              
		              m_adapter.notifyDataSetChanged();
			        
		              dialog.setTitle(null);   
				dialog.show();
				dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
					
					@Override
					public void onDismiss(DialogInterface dialog) {
						for(int i =0;i<checkboxstatus.length;i++){
						// when it get dismissed
							if(checkboxstatus[i])
							{
								 Toast.makeText(getApplicationContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();	
							}
					
							
						
						}
						
					}
				});
				
				
				
			}
		});
    }

    
    private class OrderAdapter extends ArrayAdapter<Orderofcustomdialogbox> {

        private ArrayList<Orderofcustomdialogbox> items;

        public OrderAdapter(Context context, int textViewResourceId, ArrayList<Orderofcustomdialogbox> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.customdialogrow, null);
                   
                }
                final Orderofcustomdialogbox o = items.get(position);
                if (o != null) {
                        TextView tt = (TextView) v.findViewById(R.id.textView1);
                       // TextView bt = (TextView) v.findViewById(R.id.textView2);
                        ImageView ii = (ImageView) v.findViewById(R.id.imageView1);
                        final CheckBox checkBox = (CheckBox)v.findViewById( R.id.checkBox1);
                        
                        if (tt != null) {
                              tt.setText(o.getOrderName());                            }
                      /*  if(bt != null){
                              bt.setText(o.getOrderStatus());
                        }*/
                        if(ii != null){
                        	Bitmap tep = BitmapFactory.decodeResource(getResources(),o.getbitMAP());
                            ii.setImageBitmap(tep);
                      }
                        
                        if(checkBox!= null)
                        {
                        	
                        	checkBox.setChecked(checkboxstatus[position]);
                        	
                        }
                        checkBox.setOnClickListener( new View.OnClickListener() {  
                            public void onClick(View v) {  
                            	if(checkBox.isChecked()){
                            		checkboxstatus[position]=true;
                            		
                             
                            	}else {
                            		//do false
                            		checkboxstatus[position]=false;
                            		
                            	}
                              
                            }  
                          });    
                        
                        
                        
                        

                        
                        
                }
                return v;
        }
        
         
        
}
    
}
