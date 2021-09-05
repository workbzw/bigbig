package com.workbzw.app.base.image;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.workbzw.app.base.R;
import com.workbzw.app.base.image.path.IPath;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/9/4 5:01 PM
 * @desc
 */
public class GlideLoaderImpl implements ILoader {
    private static GlideLoaderImpl g;

    static {
        g = new GlideLoaderImpl();
    }

    public static GlideLoaderImpl getInstance() {
        return g;
    }

    public void loadUrl(ImageView iv, String url) {
        Glide.with(iv.getContext()).load(url).centerCrop().placeholder(R.mipmap.base_placeholder).into(iv);
    }

    @Override
    public void load(ImageView iv, IPath path) {
        if (path.type() == IPath.Type.URL) {
            loadUrl(iv, path.path());
        }
    }
}
