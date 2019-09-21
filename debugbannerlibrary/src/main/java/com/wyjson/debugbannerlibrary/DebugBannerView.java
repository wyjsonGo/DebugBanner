package com.wyjson.debugbannerlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.wyjson.debugbannerlibrary.utils.ScreenUtils;

/**
 * debug show checked mode banner label
 *
 * @author Wyjson
 * @version 1
 * @date 2019-09-21 23:50
 */
public final class DebugBannerView extends FrameLayout {

    private Paint paint;
    private TextView textView;
    private float bannerHeight;
    private BannerGravity bannerGravity;


    public DebugBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        bannerHeight = context.getResources().getDimension(R.dimen.banner_default_height);

        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);

        this.bannerGravity = BannerGravity.START;

        textView = new TextView(getContext());
        textView.setTextColor(Color.BLACK);
        textView.setIncludeFontPadding(false);
        textView.setRotation(-45.0F);
        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textView.setTextSize(12.0F);
        textView.setSingleLine();
        textView.setEllipsize(TextUtils.TruncateAt.END);

        this.addView(textView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        this.setBackgroundColor(Color.TRANSPARENT);

        this.setClickable(false);

        ViewCompat.setElevation(this, 30.0F);
    }

    protected void onDraw(Canvas canvas) {
        if (canvas != null) {
            Path path = new Path();
            path.moveTo(this.getWidth() - bannerHeight, 0.0F);
            path.lineTo(this.getWidth(), 0.0F);
            path.lineTo(0.0F, this.getHeight());
            path.lineTo(0.0F, this.getHeight() - bannerHeight);
            path.close();
            canvas.drawPath(path, this.paint);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (textView.getX() == 0.0F) {
            textView.setX(-bannerHeight / 4);
            textView.setY(-bannerHeight / 4);
            if (this.bannerGravity == BannerGravity.END) {
                this.setRotation(90.0F);
                this.setTranslationX(ScreenUtils.getScreenWidth(getContext()) - this.getMeasuredHeight());
            }
        }

    }

    public final void setBannerGravity(BannerGravity bannerGravity) {
        this.bannerGravity = bannerGravity;
    }

    public final void updateText(String text, int textColor) {
        textView.setText(text);
        textView.setTextColor(ContextCompat.getColor(this.getContext(), textColor));
    }

    public final void updateBannerColor(int bannerColor) {
        this.paint.setColor(ContextCompat.getColor(this.getContext(), bannerColor));
    }

}