package in.ultraneo.seriousandroiddeveloper;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;


 
public class ReadXMLFile {
	private ArrayList<UpdateItem> update = null;
   public  ArrayList<UpdateItem> ReadXML(String endU, String startU) {
	   update = new ArrayList<UpdateItem>();
    try {
 
	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();
 
	DefaultHandler handler = new DefaultHandler() {
 
	boolean serious = false;
	boolean android = false;
	boolean POSTID = false;
	boolean SUB = false;
	boolean MSG = false;
	boolean PIC = false;
	boolean VDOL = false;
	boolean RLINK = false;
	UpdateItem item;
 
	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {
 
	
 
		if (qName.equalsIgnoreCase("serious")) {
			serious = true;
		}
 
		if (qName.equalsIgnoreCase("android")) {
			 item = new UpdateItem();
			android = true;
		}
 
		if (qName.equalsIgnoreCase("POSTID")) {
			POSTID = true;
		}
 
		if (qName.equalsIgnoreCase("SUB")) {
			SUB = true;
		}
		if (qName.equalsIgnoreCase("MSG")) {
			MSG = true;
		}
		if (qName.equalsIgnoreCase("PIC")) {
			PIC = true;
		}
		if (qName.equalsIgnoreCase("VDOL")) {
			VDOL = true;
		}
		if (qName.equalsIgnoreCase("RLINK")) {
			RLINK = true;
		}
		
	}
 
	public void endElement(String uri, String localName,
		String qName) throws SAXException {
		
		
		
		if (serious) {
		
				serious = false;
			}
	 
			if (android) {
			
				update.add(item);
				android = false;
			}
			if (POSTID) {
			
				POSTID = false;
			}
	 
			if (SUB) {
			
				SUB = false;
			}
	 
			if (MSG) {
			
				MSG = false;
			}
			if (PIC) {
			
				PIC = false;
			}
			if (VDOL) {
			
				VDOL = false;
			}
			if (RLINK) {
			
				RLINK = false;
			}
 
	}
 
	public void characters(char ch[], int start, int length) throws SAXException {
		
		
		
			if(POSTID)
			{
				item.setPOSTID(new String(ch, start, length));
		
			}
			if(SUB)
			{
				item.setsubject(new String(ch, start, length));
		
			}
			if(MSG)
			{
				item.setmessage(new String(ch, start, length));
		
			}
			if(PIC)
			{
				item.setpicture(new String(ch, start, length));
				
			}
			if(VDOL)
			{
				item.setvideo(new String(ch, start, length));
				
			}
			if(RLINK)
			{
				item.setreferencelink(new String(ch, start, length));
				
			}
			
			
 
	}
 
     };
 
     
InputStream is = getConnection("http://www.ultraneo.comuv.com/post.xml");
     
     SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		parser.parse(is, handler);
 
     } catch (Exception e) {
       Log.i("uo", "exception1");
     }

   return update;  
     
     
   }
   public static  InputStream getConnection(String url) {
		InputStream is = null;
		try {
			URLConnection conn = new URL(url).openConnection();
			conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
			is = conn.getInputStream();
		} catch (MalformedURLException e) {
			Log.i("uo", "exception2");
		} catch (IOException e) {
			Log.i("uo", "exception3");
		}
		return is;
	}
}