package com.workbzw.app.base.router.path;

import com.workbzw.app.base.util.StringUtils;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/9/2 12:59 PM
 * @desc
 */
public class PathFactory {
    private static IPath p;

    static {
        p = new BasePath(StringUtils.EMPTY_STR);
    }

    /**
     * 不支持多线程
     *
     * @param path 路由路径
     * @return 路由路径
     */
    public static IPath get(String path) {
        p.setPath(path);
        return p;
    }
}
