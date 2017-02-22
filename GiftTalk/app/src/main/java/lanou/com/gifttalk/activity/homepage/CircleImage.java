package lanou.com.gifttalk.activity.homepage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import lanou.com.gifttalk.R;

/**
 * Created by dllo on 17/2/21.
 */

public class CircleImage extends ImageView {

    private Paint paint;
    private boolean drawCircle;

    public CircleImage(Context context, AttributeSet attrs) {
        super(context,attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.CircleImage);
        drawCircle=typedArray.getBoolean(R.styleable.CircleImage_drawCircle,true);
    }

    public CircleImage(Context context) {
        super(context);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable= getDrawable();
        BitmapDrawable bd = (BitmapDrawable) drawable;

        if (drawCircle==true){
            if (drawable!=null) {

                Bitmap bitmap = bd.getBitmap();
                Bitmap bitmap1 = getCircleBitmap(bitmap);

                Paint paint1 = new Paint();
                Rect rect = new Rect(0, 0, bitmap1.getWidth(), bitmap1.getHeight());
                canvas.drawBitmap(bitmap1, rect, rect, paint1);
            }
        }else {
            super.onDraw(canvas);
        }

    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap emeptyBitmap=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(emeptyBitmap);
        paint=new Paint();
        paint.setColor(Color.CYAN);
        canvas.drawCircle(bitmap.getWidth()/2,bitmap.getHeight()/2,bitmap.getWidth()/2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Rect rect=new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        canvas.drawBitmap(bitmap,rect,rect,paint);

        return emeptyBitmap;
    }
}
