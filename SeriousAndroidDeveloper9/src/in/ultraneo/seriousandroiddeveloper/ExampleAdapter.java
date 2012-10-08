package in.ultraneo.seriousandroiddeveloper;

import java.util.ArrayList;

import org.w3c.dom.Text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

class ExampleAdapter extends ArrayAdapter<TextArrangment> {

    private ArrayList<TextArrangment> items;
    private Context context;
    int textviewResourceId;
    public ExampleAdapter(Context context, int textViewResourceId, ArrayList<TextArrangment> items) {
            super(context, textViewResourceId, items);
            this.items = items;
            this.context = context;
            this.textviewResourceId = textViewResourceId;
    }		
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                
                
                v = vi.inflate(textviewResourceId,null);
                //v.setBackgroundResource(R.drawable.backgroung2);
            }
            TextArrangment o = items.get(position);
            if (o != null) {
                    TextView tt = (TextView) v.findViewById(R.id.toptext);
                    TextView bt = (TextView) v.findViewById(R.id.bottomtext);
                    ImageView ii = (ImageView) v.findViewById(R.id.icon);
                    if (tt != null) {
                          tt.setText(o.getOrderName());                            }
                    if(bt != null){
                          bt.setText(o.getOrderStatus());
                    }
                    if(ii != null){
                    	Bitmap tep = BitmapFactory.decodeResource(context.getResources(),o.getbitMAP());
                        ii.setImageBitmap(tep);
                        ii.setVisibility(View.GONE);
                  }
                    
            }
            return v;
    }
}