package com.workbzw.app.base.router;

/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/8/30 9:17 AM
 * @desc
 */
public interface RouterPath {
    interface Main {
        String MAIN = "/app/main";
    }

    interface Video {
        String LIST = "/video/list";
        String TIKTOK = "/video/tiktok";
        String TIKTOK3 = "/video/tiktok3";
    }

    interface Account {
        String ACCOUNT = "/account/account";
    }

    interface Ad {
        String LIST = "/ad/list";
    }
}
