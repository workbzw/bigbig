package com.workbzw.app.base.util;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/8/17 8:51 PM
 * @desc
 */

import android.os.SystemClock;
import android.view.View;

/**
 * @Author bzw [workbzw@outlook.com]
 * @CreateDate: 2021/5/6 4:09 PM
 * @Description: 防止连续点击
 */
public abstract class OnSingleClickListener implements View.OnClickListener {

    public OnSingleClickListener(long intervalTime) {
        this.intervalTime = intervalTime;
    }

    public OnSingleClickListener() {
    }

    /**
     * 限制时间间隔-毫秒
     */
    private long intervalTime = 800;
    /**
     * 上次click的时间
     */
    private long lastClickTime;

    /**
     * click响应函数
     *
     * @param v The view that was clicked.
     */
    public abstract void onSingleClick(View v);

    @Override
    public final void onClick(View v) {
        long currentTime = SystemClock.uptimeMillis();
        long elapsedTime = currentTime - lastClickTime;
        if (elapsedTime <= intervalTime)
            return;
        onSingleClick(v);
        lastClickTime = currentTime;
    }
}
