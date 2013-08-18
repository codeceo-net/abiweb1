package com.example.test2;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

/**
 *菜图放大
 */
public class ImageViewMot implements OnTouchListener {
	
	 private static final String TAG="Touch";  //标志
	   private static final int NONE= 0 ;  
	   private static final int DRAG = 1;  
	   private static final int ZOOM =4;  
	   int clickCount = 0;
	   long before=0;
		long after=0;
	   int mode  = NONE;  
	   Matrix matrix = new Matrix();  
	   Matrix savedMatrix =  new Matrix();  
	     
	   PointF start = new PointF();  
	   PointF mid = new PointF();  
	   float oldDist = 1f;  
	   private Handler handler;
	   
	   
	   
	public ImageViewMot(Handler handler) {
		super();
		this.handler = handler;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
	       ImageView view = (ImageView) v;  
	       switch(event.getAction()&MotionEvent.ACTION_MASK)  
	       {  
	       case MotionEvent.ACTION_DOWN:  
	           savedMatrix.set(matrix);  
	              //設置初始點位置  
	           start.set(event.getX(),event.getY());  
	           Log.d(TAG,"mode=DRAG");  
	           mode  =DRAG;  
	           break;  
	       case MotionEvent.ACTION_POINTER_1_DOWN:  
	           oldDist= spacing(event);  
	           Log.d(TAG,"lodDist="+oldDist);  
	           if(oldDist>10f){  
	               savedMatrix.set(matrix);  
	               midPoint(mid,event);  
	               mode = ZOOM;  
	               Log.d(TAG,"mode=ZOOM");  
	                 
	           }  
	           break;  
	       case MotionEvent.ACTION_UP:  
	    	   if(MotionEvent.ACTION_UP==event.getAction()){
					clickCount++;
					if(clickCount==1){
						before = System.currentTimeMillis(); 
					}else if(clickCount==2){
						after = System.currentTimeMillis();
						if((after-before)<500){
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("shuangJi", "shuangJi");
							msg.setData(data);
							msg.setTarget(handler);
							msg.sendToTarget();
							clickCount = 0;
							before=0;
							after=0;
						}else{
							before = after;
							clickCount = 1;
						}
					}
				}
	    	   
	       case MotionEvent.ACTION_POINTER_1_UP:  
	           mode  =NONE;  
	           Log.d(TAG,"mode=NONE");  
	           break;  
	       case MotionEvent.ACTION_MOVE:  
	           if(mode==DRAG){  
	               matrix.set(savedMatrix);  
	               matrix.postTranslate(event.getX()-start.x, event.getY()-start.y);  
	           }  
	           else if(mode == ZOOM){  
	                float newDist = spacing(event);  
	                   Log.d(TAG, "newDist=" + newDist);  
	                   if (newDist > 10f) {  
	                      matrix.set(savedMatrix);  
	                      float scale = newDist / oldDist;  
	                      matrix.postScale(scale, scale, mid.x, mid.y);  
	                   }  
	                }  
	           break;  
	       }  
	       view.setImageMatrix(matrix);  
	       return true;  
	}
    /** Determine the space between the first two fingers */  
    private float spacing(MotionEvent event){  
        float x = event.getX(0)-event.getY(1);  
        float y = event.getY(0)-event.getY(1);  
        return (float)Math.sqrt(x*x+y*y);  
    }  
    /** Calculate the mid point of the first two fingers */  
       private void midPoint(PointF point, MotionEvent event) {
          float x = event.getX(0) + event.getX(1);  
          float y = event.getY(0) + event.getY(1);  
          point.set(x / 2, y / 2);  
       }  
       
       

}
