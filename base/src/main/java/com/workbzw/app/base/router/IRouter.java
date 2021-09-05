package com.workbzw.app.base.router;

import androidx.fragment.app.Fragment;

import com.workbzw.app.base.router.path.IPath;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/9/2 12:56 AM
 * @desc
 */
public interface IRouter {

    void nav(IPath path);

    Fragment fragment(IPath path);

}
