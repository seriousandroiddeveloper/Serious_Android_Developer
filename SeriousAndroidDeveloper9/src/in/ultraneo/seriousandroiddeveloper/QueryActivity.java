package in.ultraneo.seriousandroiddeveloper;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QueryActivity extends SherlockActivity {
TextView txt1,txt3,txt4s;
Button btn1,btn2;
EditText etxt1;
ImageView img1 ;
ProgressDialog dialogc;
Facebook facebook = new Facebook("370646329680036");
private SharedPreferences mPrefs;
private  String access_token;
private Boolean Cancelled = false;

	CustomizeDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postquery);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        txt1 = (TextView)findViewById(R.id.textView1);
        txt3 = (TextView)findViewById(R.id.textView3);
        btn2 = (Button)findViewById(R.id.button2);
        img1 = (ImageView)findViewById(R.id.imageView1);
        txt4s = (TextView)findViewById(R.id.textView5);
        etxt1 = (EditText)findViewById(R.id.editText1);
        //btn1.setTypeface(Typeface.createFromAsset(getAssets(), "type2.ttf"));
        btn2.setTypeface(Typeface.createFromAsset(getAssets(), "type2.ttf"));
         txt1.setTypeface(Typeface.createFromAsset(getAssets(), "type.ttf"));
         txt3.setTypeface(Typeface.createFromAsset(getAssets(), "social.ttf"));
         
         mPrefs = getPreferences(MODE_PRIVATE);
         access_token = mPrefs.getString("access_token", null);
         long expires = mPrefs.getLong("access_expires", 0);
         if(new UltraFileaccess().get_Data("GAuth", this).equals("1"))
         {
        	 img1.setImageResource(android.R.drawable.presence_online);
        	 txt4s.setText(new UltraFileaccess().get_Data("PERSONAL_INFO", this));
         }else
         {
        	 img1.setImageResource(android.R.drawable.presence_offline);
        	 txt4s.setText("not connected");
         }
         
         if(access_token != null) {
             facebook.setAccessToken(access_token);
             //Toast.makeText(this,"Access token"+ access_token,Toast.LENGTH_SHORT).show();
             
         }
         if(expires != 0) {
             facebook.setAccessExpires(expires);
             //Toast.makeText(this,"Access Expires"+expires,Toast.LENGTH_SHORT).show();
         }
       
         btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					
					//Toast.makeText(v.getContext(), "NO Internet connection" , Toast.LENGTH_LONG).show();
					
				
					if(isOnline()){
					 if(new UltraFileaccess().get_Data("GAuth", v.getContext()).equals("1"))
					 {
						 if(etxt1.getText().toString().length()>=10){
							 new sendquery().execute();
							}else {
								Toast.makeText(v.getContext(), "Type In atleast 10 alphabet" , Toast.LENGTH_LONG).show();
							}
						 
						
						
					
					
					 }else{
						 final CustomizeDialog dialog2 = new CustomizeDialog(QueryActivity.this, R.layout.facebookdialog);
							TextView txt1d = (TextView)dialog2.findViewById(R.id.Authentication);
							txt1d.setText("Authentication Required");
							TextView txt2d = (TextView)dialog2.findViewById(R.id.textView1);
							Button btn1 = (Button)dialog2.findViewById(R.id.button1);
							Button btn2 = (Button)dialog2.findViewById(R.id.button2);
							btn1.setText("Authenticate Me");
							btn2.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									String url = "http://www.facebook.com/groups/c2dmdeveloper/";
									Intent i = new Intent(Intent.ACTION_VIEW);
									i.setData(Uri.parse(url));
									startActivity(i);
									
								}
							});
							
							btn1.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									dialog2.dismiss();
									if(isOnline())
						        	{
						        facebookconnection();
						        	}else
						        	{
						        		Toast.makeText(v.getContext(), "NO Internet connection" , Toast.LENGTH_LONG).show(); 		
						        	}
									
									
								}
							});
							
							
							txt2d.setText("Only member of Serious Android developer,Facebook Group, can sent post! If u are member of Serious android developer Please Click on <Authenticate Me> to authenticate urself! If u are not a member Click on Join Group to join the Group ! Its free");
							dialog2.show();
							
					 }
					}else{
						Toast.makeText(v.getContext(), "NO Internet connection" , Toast.LENGTH_LONG).show();	
					}
					
					
					
					
				
				}catch(Exception e)
				{
					 Toast.makeText(v.getContext(), "exception" , Toast.LENGTH_LONG).show();
				}
				//
				
				
				
			}
		});
         
         
         
         
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
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getSupportMenuInflater();//getMenuInflater();
    	 inflater.inflate(R.menu.facebookmenu, menu);
 	    return true;
        
    }
    
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        
	    case android.R.id.home:
        {
        	finish();
        	
        	break;
        }
	    
	    
	        case R.id.menu_settings:
	        {
	        	if(isOnline())
	        	{
	        facebookconnection();
	        	}else
	        	{
	        		Toast.makeText(this, "NO Internet connection" , Toast.LENGTH_LONG).show(); 		
	        	}
	        	break;
	        }
	        
	         
	        default:
	            return super.onOptionsItemSelected(item);
	    }
		return false;
	}


	private void facebookconnection() {
		
		 dialog = new CustomizeDialog(QueryActivity.this, R.layout.facebookdialog);
		final Button btnd1 = (Button)dialog.findViewById(R.id.button2);
		Button btnd2 = (Button)dialog.findViewById(R.id.button1);
		btnd2.setVisibility(View.INVISIBLE);
		btnd1.setText("    Proceed    ");
		dialog.show();
		btnd1.setOnClickListener(new View.OnClickListener() {
			Boolean authT = false;
			@Override
			public void onClick(View v) {
				btnd1.setVisibility(View.INVISIBLE);
				facebook.authorize(QueryActivity.this,new String[] { "user_groups","publish_stream","friends_groups" }, new DialogListener() {
		            @Override
		            public void onComplete(Bundle values) {
		            	
		            	
		            	 SharedPreferences.Editor editor = mPrefs.edit();
		                    editor.putString("access_token", facebook.getAccessToken());
		                    editor.putLong("access_expires", facebook.getAccessExpires());
		                    editor.commit();
		                //   Toast.makeText(QueryActivity.this,"new access_token"+facebook.getAccessToken()+"Expirese"+facebook.getAccessExpires(),Toast.LENGTH_LONG).show();
		                 
		                    
		                    /////
		                    AsyncFacebookRunner syncinfo = new AsyncFacebookRunner(facebook);
		                	
		                	syncinfo.request("me",new AsyncFacebookRunner.RequestListener() {
		                		
		                		@Override
		                		public void onMalformedURLException(MalformedURLException e, Object state) {
		                			Log.i("uo","malformation");
		                			
		                		}
		                		
		                		@Override
		                		public void onIOException(IOException e, Object state) {
		                			Log.i("uo","io exception");
		                			
		                		}
		                		
		                		@Override
		                		public void onFileNotFoundException(FileNotFoundException e, Object state) {
		                			Log.i("uo","filenotfounderror");
		                			
		                		}
		                		
		                		@Override
		                		public void onFacebookError(FacebookError e, Object state) {
		                			Log.i("uo",e.getErrorType());
		                			
		                		}
		                		
		                		@Override
		                		public void onComplete(String response, Object state) {
		                			Log.i("uo", response);
		                			
		                			try {
		                				
		                				
		                				JSONObject json = new JSONObject(response);
		                				String id = json.getString("id");
		                				String  name = json.getString("name");
		                				new UltraFileaccess().Load_Data("PERSONAL_INFOID", id, QueryActivity.this);
		                				new UltraFileaccess().Load_Data("PERSONAL_INFO",name, QueryActivity.this);
		                				Log.i("uo",name+" "+id);	
		                				
		                			/*	for (int i = 0; i < jsongroup.length(); i++) {
		                					JSONObject jsonObject = jsongroup.getJSONObject(i);
		                			        String groupid = jsonObject.getString("id");*/
		                			        
		                			        
		                			        
		                			        
		                				
		                				
		                				
		                			} catch (JSONException e) {
		                				// TODO Auto-generated catch block
		                				
		                				Log.i("uo","error in fetching json response");
		                				
		                				
		                				
		                			}
		                			
		                		}

		                		
		                	});
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    /////

		    				
		                	AsyncFacebookRunner syncinfo2 = new AsyncFacebookRunner(facebook);
		                	
		                	syncinfo2.request("me/groups",new AsyncFacebookRunner.RequestListener() {
		                		
		                		@Override
		                		public void onMalformedURLException(MalformedURLException e, Object state) {
		                			// TODO Auto-generated method stub
		                			
		                		}
		                		
		                		@Override
		                		public void onIOException(IOException e, Object state) {
		                			// TODO Auto-generated method stub
		                			
		                		}
		                		
		                		@Override
		                		public void onFileNotFoundException(FileNotFoundException e, Object state) {
		                			Log.i("uo","filenotfounderror");
		                			
		                		}
		                		
		                		@Override
		                		public void onFacebookError(FacebookError e, Object state) {
		                			Log.i("uo",e.getErrorType());
		                			
		                		}
		                		
		                		@Override
		                		public void onComplete(String response, Object state) {
		                			Log.i("uo", response);
		                			
		                			try {
		                				
		                				
		                				JSONObject json = new JSONObject(response);
		                				
		                				JSONArray jsongroup = json.getJSONArray("data");
		                				
		                				for (int i = 0; i < jsongroup.length(); i++) {
		                			        JSONObject jsonObject = jsongroup.getJSONObject(i);
		                			        String groupid = jsonObject.getString("id");
		                			        
		                			        if(groupid.equals("213065595425025"))
		                			        {
		                			        	Log.i("uo", groupid);
		                			        	authT = true;
		                			        	new UltraFileaccess().Load_Data("GAuth", "1", QueryActivity.this);
		                			        	
		                			        	
		                			        	
		                			        	
		                			        	dialog.dismiss();
		                			        }else
		                			        {
		                			        	dialog.dismiss();
		                			        }
		                			        
		                			        
		                				}
		                				
		                				
		                			} catch (JSONException e) {
		                				// TODO Auto-generated catch block
		                				dialog.dismiss();
		                				Log.i("uo","error in fetching json response");
		                				
		                				
		                				
		                			}
		                			
		                		}

		                		
		                	});
		                	
		                    /////
		            }

		            @Override
		            public void onFacebookError(FacebookError error) {
		            	Log.i("uo",error.getErrorType());
		            	
		            }

		            @Override
		            public void onError(DialogError e) {
		            	Log.i("uo",e.getMessage());
		            	
		            }

		            @Override
		            public void onCancel() {
		            	Log.i("uo","Canceled");
		            	Cancelled = true;
		            	dialog.dismiss();
		            	
		            }

					
		            
		        });
				
				dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
					
					@Override
					public void onDismiss(DialogInterface dialog) {
						if(authT&&(!Cancelled)){
							
							final CustomizeDialog dialog2 = new CustomizeDialog(QueryActivity.this, R.layout.facebookdialog);
							TextView txt1d = (TextView)dialog2.findViewById(R.id.Authentication);
							TextView txt2d = (TextView)dialog2.findViewById(R.id.textView1);
							Button btn1 = (Button)dialog2.findViewById(R.id.button1);
							Button btn2 = (Button)dialog2.findViewById(R.id.button2);
							btn2.setText("    OK    ");
							btn2.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									dialog2.dismiss();
									
								}
							});
							btn1.setText("Tell ur Friends");
							btn1.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(final View v) {
									Bundle params = new Bundle();
								    params.putString("message", "Use this awesome application!");
								    facebook.dialog(v.getContext(), "apprequests", params,new Facebook.DialogListener() {
										
										@Override
										public void onFacebookError(FacebookError e) {
											// TODO Auto-generated method stub
											
										}
										
										@Override
										public void onError(DialogError e) {
											// TODO Auto-generated method stub
											
										}
										
										@Override
										public void onComplete(Bundle values) {
											Toast.makeText(v.getContext(), "Thanks!",Toast.LENGTH_SHORT).show();
											dialog2.dismiss();
										}
										
										@Override
										public void onCancel() {
											// TODO Auto-generated method stub
											
											
										}
									});
									
								}
							});
							txt1d.setText(new UltraFileaccess().get_Data("PERSONAL_INFO",QueryActivity.this));
							txt2d.setText("Authorisation success as member of Serious Android Developer facebook group, Now you can send query");
							dialog2.show();
							/*txt4s.setText("Now Connected");*/
							txt4s.setText(new UltraFileaccess().get_Data("PERSONAL_INFO", QueryActivity.this));
							img1.setImageResource(android.R.drawable.presence_online);
						
						}else {
							if(!Cancelled){
								
							txt4s.setText("not connected");
							img1.setImageResource(android.R.drawable.presence_offline);
							final CustomizeDialog dialog2 = new CustomizeDialog(QueryActivity.this, R.layout.facebookdialog);
							TextView txt1d = (TextView)dialog2.findViewById(R.id.Authentication);
							TextView txt2d = (TextView)dialog2.findViewById(R.id.textView1);
							Button btn1 = (Button)dialog2.findViewById(R.id.button1);
							Button btn2 = (Button)dialog2.findViewById(R.id.button2);
							btn1.setText("    Later!    ");
							btn2.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									String url = "http://www.facebook.com/groups/c2dmdeveloper/";
									Intent i = new Intent(Intent.ACTION_VIEW);
									i.setData(Uri.parse(url));
									startActivity(i);
									
								}
							});
							
							btn1.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									dialog2.dismiss();
									
								}
							});
							
							txt1d.setText(new UltraFileaccess().get_Data("PERSONAL_INFO",QueryActivity.this));
							txt2d.setText("Your facebook password is correct! but your are not a member of Serious Android Developer facebook group.To Join, click on Join Group and its free!");
							dialog2.show();
							
							}else{
								Toast.makeText(QueryActivity.this, "You cancelled Authentication!",Toast.LENGTH_SHORT).show();
							}
							
						}
					}
					
				});
				
			}
		});
		
	}

	class sendquery extends AsyncTask<Void, Void, String>{
String lin2;
		@Override
		protected String doInBackground(Void... arg0) {
			
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost("https://graph.facebook.com/213065595425025/feed");
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("access_token",access_token));
			nameValuePairs.add(new BasicNameValuePair("message",etxt1.getText().toString()));
			nameValuePairs.add(new BasicNameValuePair("picture","http://ultraimager.webs.com/facebookpic.jpg"));
			nameValuePairs.add(new BasicNameValuePair("link","http://www.facebook.com/groups/c2dmdeveloper/"));
			nameValuePairs.add(new BasicNameValuePair("name","Query posted using Android app"));
			nameValuePairs.add(new BasicNameValuePair("caption","click here to download application"));
			/*nameValuePairs.add(new BasicNameValuePair("description",""));*/
			
			/*http://www.ultraneo.comuv.com/androidapp.jpg*/
			try {
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			} catch (UnsupportedEncodingException e) {
				
			}
			HttpResponse response = null;
			try {
				response = client.execute(post);
			} catch (ClientProtocolException e) {
				
			} catch (IOException e) {
				
			}
			BufferedReader rd = null;
			try {
				rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));
			} catch (IllegalStateException e) {
				
			} catch (IOException e) {
				
			}
			String line = "";
			try {
				while ((line = rd.readLine()) != null) {
					Log.e("HttpResponse", line);
				lin2 = line;
					//Toast.makeText(QueryActivity.this, line, Toast.LENGTH_LONG).show();	
				}
			} catch (IOException e) {
				
			}
			
			
			return null;
			
			
			
		}

		@Override
		protected void onPreExecute() {
			dialogc = ProgressDialog.show(QueryActivity.this,null,"Please wait...", true);
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			if(dialogc.isShowing()){
			dialogc.dismiss(); 
			
			Toast.makeText(QueryActivity.this, lin2, Toast.LENGTH_LONG).show();
			}
			super.onPostExecute(result);
		}
		
		
		
	}
	
    
    
}
