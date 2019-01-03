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

    private boolean mSavePassword;

    private boolean mPatternlessEnable;

    private boolean mWakeupEnable;

    private boolean mHardwareAccelerated;

    private boolean mLocalizable;

    private boolean mThemeEnable;

    private int mFontSize;

    private boolean mAdblockPlusEnable;

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

    public boolean isLocalizable() {
        return mLocalizable;
    }

    public CoreConfig setLocalizable(boolean localizable) {
        mLocalizable = localizable;
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

    public CoreConfig setFilterList(String[] filterList) {
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
