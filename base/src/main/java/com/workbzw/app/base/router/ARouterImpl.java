package com.workbzw.app.base.router;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.workbzw.app.base.router.path.IPath;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/9/2 12:58 AM
 * @desc
 */
public class ARouterImpl implements IRouter {
    private static ARouterImpl impl;

    static {
        impl = new ARouterImpl();
    }

    public static ARouterImpl getInstance() {
        return impl;
    }

    @Override
    public void nav(IPath path) {
        ARouter.getInstance().build(path.path()).navigation();
    }

    @Override
    public Fragment fragment(IPath path) {
        return (Fragment) ARouter.getInstance().build(path.path()).navigation();
    }
}
