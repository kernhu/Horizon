package cn.walkpast.core.config;

import cn.walkpast.core.constant.Strategy;
import cn.walkpast.core.constant.Theme;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 5:36 PM
 * describe: This is...
 */

public class CoreConfig {

    private CoreConfig mCoreConfig;

    private boolean mSavePassword = true;

    private boolean mPatternlessEnable = false;

    private boolean mWakeupEnable = true;

    private boolean mHardwareAccelerated = true;

    private boolean mGeolocationEnalbe = true;

    private boolean mThemeEnable;

    private int mFontSize;

    private boolean mAdblockPlusEnable;

    private boolean mWebContentsDebuggingEnabled;

    private Strategy mStrategy;

    private Theme mTheme;

    private String[] mFilterList;

    private String mErrorPage;

//    public static CoreConfig getInstance() {
//        if (mCoreConfig == null) {
//            mCoreConfig = new CoreConfig();
//        }
//        return mCoreConfig;
//    }


    public boolean isSavePassword() {
        return mSavePassword;
    }

    public CoreConfig setSavePassword(boolean savePassword) {
        mSavePassword = savePassword;
        return this;
    }

    public boolean isPatternlessEnable() {
        return mPatternlessEnable;
    }

    public CoreConfig setPatternlessEnable(boolean patternless) {
        mPatternlessEnable = patternless;
        return this;
    }

    public boolean isWakeupEnable() {
        return mWakeupEnable;
    }

    public CoreConfig setWakeupEnable(boolean wakeupEnable) {
        mWakeupEnable = wakeupEnable;
        return this;
    }

    public boolean isHardwareAccelerated() {
        return mHardwareAccelerated;
    }

    public CoreConfig setHardwareAccelerated(boolean hardwareAccelerated) {
        mHardwareAccelerated = hardwareAccelerated;
        return this;
    }

    public boolean isGeolocationEnalbe() {
        return mGeolocationEnalbe;
    }

    public CoreConfig setGeolocationEnalbe(boolean localizable) {
        mGeolocationEnalbe = localizable;
        return this;
    }

    public boolean isThemeEnable() {
        return mThemeEnable;
    }

    public CoreConfig setThemeEnable(boolean themeEnable) {
        mThemeEnable = themeEnable;
        return this;
    }

    public int getFontSize() {
        return mFontSize;
    }

    public CoreConfig setFontSize(int fontSize) {
        mFontSize = fontSize;
        return this;
    }

    public Strategy getStrategy() {
        return mStrategy;
    }

    public CoreConfig setStrategy(Strategy strategy) {
        mStrategy = strategy;
        return this;
    }

    public boolean isAdblockPlusEnable() {
        return mAdblockPlusEnable;
    }

    public CoreConfig setAdblockPlusEnable(boolean adblockPlusEnable) {
        mAdblockPlusEnable = adblockPlusEnable;
        return this;
    }

    public boolean isWebContentsDebuggingEnabled() {
        return mWebContentsDebuggingEnabled;
    }

    public CoreConfig setWebContentsDebuggingEnabled(boolean webContentsDebuggingEnabled) {
        mWebContentsDebuggingEnabled = webContentsDebuggingEnabled;
        return this;
    }

    public Theme getTheme() {
        return mTheme;
    }

    public CoreConfig setTheme(Theme theme) {
        mTheme = theme;
        return this;
    }

    public String[] getFilterList() {
        return mFilterList;
    }

    public CoreConfig setFilterList(String... filterList) {
        mFilterList = filterList;
        return this;
    }

    public String getErrorPage() {
        return mErrorPage;
    }

    public CoreConfig setErrorPage(String errorPage) {
        mErrorPage = errorPage;
        return this;
    }

    public CoreConfig build() {

        return this;
    }
}
