/*
 * Copyright (c) 2015 Prat Tanapaisankit
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.prat.gregeek.view;

import com.prat.gregeek.R;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by prt2121 on 1/10/15.
 */
public class FlashcardTextView extends TextView {

    private static int mFifteen;

    private static int mNine;

    public FlashcardTextView(Context context) {
        super(context);
        init();
    }

    public FlashcardTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlashcardTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.textview_border);
        setGravity(Gravity.CENTER);
        mFifteen = convertToPx(15);
        mNine = convertToPx(9);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(mFifteen, mNine, mFifteen, mNine);
        setLayoutParams(layoutParams);
        setPadding(mNine, mNine, mNine, mNine);
    }

    private int convertToPx(int dp) {
        Resources r = mContext.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
    }
}
