package cn.walkpast.core.config;

import android.app.Activity;

import cn.walkpast.core.constant.FilterType;
import cn.walkpast.core.constant.Strategy;
import cn.walkpast.core.constant.Theme;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/1 5:36 PM
 * describe: This is...
 */

public class CoreConfig {

    private Activity mActivity;

    private boolean mSavePassword = true;

    private boolean mPatternlessEnable = false;

    private boolean mWakeupEnable = true;

    private boolean mHardwareAccelerated = true;

    private boolean mGeolocationEnalbe = true;

    private boolean mThemeEnable = false;

    private int mFontSize = 16;

    private boolean mAdblockPlusEnable = false;

    private boolean mWebContentsDebuggingEnabled = false;

    private Strategy mStrategy = Strategy.CORE_BOTH_TEXT_IMAGE;

    private Theme mTheme = Theme.THEME_LIGHT;

    private FilterType mFilterType = FilterType.TYPE_MATCH_HOST;

    private String[] mFilterList;

    private String mFilterReplaceUrl;

    public CoreConfig(Activity activity) {
        mActivity = activity;
    }

    public static CoreConfig with(Activity activity) {

        return new CoreConfig(activity);
    }


    public Activity getActivity() {
        return mActivity;
    }

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

    public CoreConfig setFilterList(FilterType type, String replaceUrl, String... filterList) {
        mFilterType = type;
        mFilterList = filterList;
        mFilterReplaceUrl = replaceUrl;
        return this;
    }

    public FilterType getFilterType() {
        return mFilterType;
    }

    public String getFilterReplaceUrl() {
        return mFilterReplaceUrl;
    }

    public CoreConfig config() {

        return this;
    }
}
