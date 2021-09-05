package com.workbzw.app.base.router.path;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/9/2 1:05 AM
 * @desc
 */
public class BasePath implements IPath {
    private String path;

    public BasePath(String path) {
        this.path = path;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }
}
