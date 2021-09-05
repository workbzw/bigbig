package com.workbzw.app.base;

import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;

import xyz.doikki.videoplayer.exo.ExoMediaPlayerFactory;
import xyz.doikki.videoplayer.player.AndroidMediaPlayerFactory;
import xyz.doikki.videoplayer.player.VideoViewConfig;
import xyz.doikki.videoplayer.player.VideoViewManager;


/**
 * @author bzw [workbzw@outlook.com]
 * @date 2021/8/31 12:33 AM
 * @desc
 */
public class AppApplication extends MultiDexApplication {
    private static AppApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        VideoViewManager.setConfig(VideoViewConfig.newBuilder()
                //使用使用IjkPlayer解码
//                .setPlayerFactory(IjkPlayerFactory.create())
                //使用ExoPlayer解码
                .setPlayerFactory(ExoMediaPlayerFactory.create())
                //使用MediaPlayer解码
                .setPlayerFactory(AndroidMediaPlayerFactory.create())
                .build());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }

    public static AppApplication getInstance() {
        return application;
    }
}
