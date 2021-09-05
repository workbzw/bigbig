package com.workbzw.app.base.router;

import androidx.fragment.app.Fragment;

import com.workbzw.app.base.router.path.IPath;
import com.workbzw.app.base.router.path.PathFactory;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/9/2 1:01 AM
 * @desc 路由
 */
public class Navigator {

    private static void nav(IRouter router, IPath path) {
        if (router == null) {
            router = ARouterImpl.getInstance();
        }
        router.nav(path);
    }

    private static Fragment fragment(IRouter router, IPath path) {
        if (router == null) {
            router = ARouterImpl.getInstance();
        }
        return router.fragment(path);
    }

    /**
     * ARouter 路由方案
     *
     * @param path 路径
     */
    public static void nav(String path) {
        IPath p = PathFactory.get(path);
        ARouterImpl aRouter = ARouterImpl.getInstance();
        nav(aRouter, p);
    }

    public static Fragment fragment(String path) {
        IPath p = PathFactory.get(path);
        ARouterImpl aRouter = ARouterImpl.getInstance();
        return fragment(aRouter, p);
    }
}
