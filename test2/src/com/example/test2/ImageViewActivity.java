package com.example.test2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        // TODO Auto-generated method stub  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.common_image_view);  
        findView();  
    }  
    ImageView imgControl;  
    private void findView() {  
        imgControl = (ImageView) findViewById(R.id.common_imageview_imageControl1);  
    }  
  
}  