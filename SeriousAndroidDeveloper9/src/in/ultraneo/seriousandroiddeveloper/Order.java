package in.ultraneo.seriousandroiddeveloper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;



public class Order {
   
    private String orderName;
    private String orderStatus;
    private String bmp;
    private String POST;
    private String video;
    private String Rlink;
    
    public String getPOST() {
        return POST;
    }
    public void setPOST(String POST) {
        this.POST = POST;
    }
    
    
    public String getvideo() {
        return video;
    }
    public void setvideo(String video) {
        this.video = video;
    }
    
    public String getRlink() {
        return Rlink;
    }
    public void setRlink(String video) {
        this.Rlink = Rlink;
    }
    
    
    
    
    
    public String getOrderName() {
        return orderName;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
    
    public void setbitMAP(String bmp) {
       this.bmp = bmp;
        	
    }
    
    
    public String getOrderStatus() {
        return orderStatus;
    }
    
    public String getbitMAP() {
        return bmp;
    }
    
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}