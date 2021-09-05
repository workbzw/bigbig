package com.workbzw.app.base.image;

import android.widget.ImageView;

import com.workbzw.app.base.image.path.IPath;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/9/4 5:01 PM
 * @desc
 */
public interface ILoader {
    void load(ImageView iv, IPath path);
}
