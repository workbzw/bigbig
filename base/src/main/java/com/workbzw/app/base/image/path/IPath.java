package com.workbzw.app.base.image.path;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/9/2 1:01 PM
 * @desc 路由路径
 */
public interface IPath {

    String path();

    Type type();

    void setPath(String path);

    void setType(Type type);

    enum Type {
        FILE,URL,URI,RES_ID,GIF;
    }
}
