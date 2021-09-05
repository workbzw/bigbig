package com.workbzw.app.base.image;

import android.widget.ImageView;

import com.workbzw.app.base.image.path.IPath;
import com.workbzw.app.base.image.path.PathFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/9/4 4:59 PM
 * @desc
 */
public class Imager {
    private static Map<String, ILoader> loaderMap;
    public static final String KEY_GLIDE = "glide";

    static {
        loaderMap = new HashMap<>();
        GlideLoaderImpl glide = GlideLoaderImpl.getInstance();
        loaderMap.put(KEY_GLIDE, glide);
    }

    private static void load(ILoader imageLoader, ImageView iv, IPath path) {
        if (imageLoader == null) {
            imageLoader = GlideLoaderImpl.getInstance();
        }
        imageLoader.load(iv, path);
    }

    private static ILoader getLoader(String key) {
        ILoader loader = loaderMap.get(key);
        return loader;
    }

    /**
     * ARouter 路由方案
     *
     * @param path 路径
     */
    public static void loadUrl(ImageView iv, String path) {
        IPath p = PathFactory.get(IPath.Type.URL, path);
        ILoader glide = getLoader(KEY_GLIDE);
        load(glide, iv, p);
    }

}
