package com.workbzw.app.base.image.path;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/9/2 1:05 AM
 * @desc
 */
public class BasePath implements IPath {
    private String path;
    private Type type;

    public BasePath(String path) {
        this.path = path;
    }

    @Override
    public String path() {
        return this.path;
    }

    @Override
    public Type type() {
        return this.type;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }
}
