package in.ultraneo.seriousandroiddeveloper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.CheckBox;
import android.widget.ImageView;



public class Orderofcustomdialogbox {
   
    private String orderName;
    private String orderStatus;
    private int bmp;
    private CheckBox checkBox;
    private Boolean checked=false;
    
    public boolean isChecked() {  
        return checked;  
      }  
      public void setChecked(boolean checked) {  
        this.checked = checked;  
      }  
    
    
    public String getOrderName() {
        return orderName;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
    
    public void setbitMAP(int bmp) {
       this.bmp = bmp;
        	
    }
    /*public CheckBox getCheckBox() {  
    	
        return checkBox;  
      }  
      public void setCheckBox(CheckBox checkBox) {  
        this.checkBox = checkBox;  
      } */
    
    public String getOrderStatus() {
        return orderStatus;
    }
    
    public int getbitMAP() {
        return bmp;
    }
    
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}