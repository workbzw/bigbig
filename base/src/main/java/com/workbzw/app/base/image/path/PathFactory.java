package com.workbzw.app.base.image.path;

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
     * @param type path类型
     * @param path 路由路径
     * @return 路由路径
     * @see IPath.Type
     */
    public static IPath get(IPath.Type type, String path) {
        p.setPath(path);
        p.setType(type);
        return p;
    }
}
