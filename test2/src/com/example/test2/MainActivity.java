package com.example.test2;

import java.util.ArrayList;
import java.util.TreeSet;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private String LOG_TAG = "±Í«©…Ë÷√";
	private MainActivity instance = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		_listViewItem();
		TextView click = (TextView) findViewById(R.id.click);
		click.setOnClickListener( new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Dialog dialog = new Dialog(instance,R.style.dialog_login);
				dialog.setContentView(R.layout.item_pic_dialog);
				dialog.setCancelable(true);
				dialog.setCanceledOnTouchOutside(true);
				dialog.show();
			}
		});
	}
	@Override 
    protected void onStart() { 
        Log.e(LOG_TAG , "onStart"); 
        super.onStart(); 
    } 
    @Override 
    protected void onResume() { 
        Log.e(LOG_TAG, "onResume"); 
        super.onResume(); 
    } 
    @Override 
    protected void onPause() { 
        Log.e(LOG_TAG, "onPause"); 
        super.onPause(); 
    } 
    @Override 
    protected void onStop() { 
        Log.e(LOG_TAG, "onStop"); 
        super.onStop(); 
    } 
    @Override 
    protected void onDestroy() { 
        Log.e(LOG_TAG, "onDestroy "); 
        super.onDestroy(); 
    } 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void _listViewItem(){
		ListView list = (ListView) findViewById(R.id.list);
		MyCustomAdapter mAdapter = new MyCustomAdapter();
        for (int i = 1; i < 50; i++) {
            mAdapter.addItem("item " + i);
            if (i % 4 == 0) {
                mAdapter.addSeparatorItem("separator " + i);
            }
        }
        list.setAdapter(mAdapter);
	}
	
	 private class MyCustomAdapter extends BaseAdapter {
		  
	        private static final int TYPE_ITEM = 0;
	        private static final int TYPE_SEPARATOR = 1;
	        private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
	  
	        private ArrayList mData = new ArrayList();
	        private LayoutInflater mInflater;
	  
	        private TreeSet mSeparatorsSet = new TreeSet();
	  
	        public MyCustomAdapter() {
	            mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        }
	  
	        public void addItem(final String item) {
	            mData.add(item);
	            notifyDataSetChanged();
	        }
	  
	        public void addSeparatorItem(final String item) {
	            mData.add(item);
	            // save separator position
	            mSeparatorsSet.add(mData.size() - 1);
	            notifyDataSetChanged();
	        }
	  
	        @Override
	        public int getItemViewType(int position) {
	            return mSeparatorsSet.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
	        }
	  
	        @Override
	        public int getViewTypeCount() {
	            return TYPE_MAX_COUNT;
	        }
	  
	        @Override
	        public int getCount() {
	            return mData.size();
	        }
	  
	        @Override
	        public String getItem(int positionl) {
	            return (String) mData.get(positionl);
	        }
	  
	        @Override
	        public long getItemId(int position) {
	            return position;
	        }
	  
	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	        	TextView textView = null;
	            int type = getItemViewType(position);
                switch (type) {
                    case TYPE_ITEM:
                        convertView = mInflater.inflate(R.layout.item1, null);
                        textView = (TextView) convertView.findViewById(R.id.text);
                        break;
                    case TYPE_SEPARATOR:
                        convertView = mInflater.inflate(R.layout.item2, null);
                        textView = (TextView)convertView.findViewById(R.id.textSeparator);
                        break;
                }
	            textView.setText(""+mData.get(position) );
	            textView.setOnClickListener( new OnClickListener(){
					@Override
					public void onClick(View arg0) {
						
						startActivity( new Intent(MainActivity.this,H.class) );
						
					}
	            	
	            });
	            return convertView;
	        }

	  
	    }
}
