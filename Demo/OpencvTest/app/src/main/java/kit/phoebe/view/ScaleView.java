package kit.phoebe.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ScaleView extends View {
	private String LOG_TAG = "ScaleView";
	static final int comW = 360;
	static final int comH = 50;
	
	private Context mContext;
	
	private Paint paint;
	private Rect edgeRect;
	private Rect scaleLenthRect;
	
	private void ComponentInit()
	{
		paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.WHITE);
		
		edgeRect = new Rect(10, 10, 2380, 90);
	}
	
	public ScaleView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Log.d(LOG_TAG,"Scale View Constructor");
		this.mContext = context;
		
		ComponentInit();
		
	}
	
	public ScaleView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		int x = getWidth();
		int y = getHeight();
		
		Log.d(LOG_TAG,"Scale View onDraw");
		canvas.drawPaint(paint);
		// Use Color.parseColor to define HTML colors
		paint.setColor(Color.parseColor("#CD5C5C"));
		//canvas.drawCircle(x / 2, y / 2, radius, paint);
		canvas.drawRect(edgeRect, paint);
	}
	
	public void setProgress(boolean showText) {
		invalidate();
		requestLayout();
	}
}
