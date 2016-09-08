package com.andpack.annotation.statusbar;

import android.support.annotation.ColorRes;
import android.support.annotation.FloatRange;

/**
 * 指定任务透明
 * Created by SCWANG on 2016/9/8.
 */
public @interface StatusBarTranslucent {
    @FloatRange(from = 0.0, to = 1.0) float value() default 0f;//alpha
    @ColorRes int color() default android.R.color.black ;
}
