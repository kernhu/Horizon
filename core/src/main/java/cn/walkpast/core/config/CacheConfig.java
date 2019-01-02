package cn.walkpast.core.config;

import android.content.Context;

import java.io.File;

/**
 * Author: Kern
 * Time: 2019/1/2 19:53
 * Description: This is..
 */

public class CacheConfig {

    public static String getCachePath(Context context) {

        StringBuffer mBuffer = new StringBuffer();
        String core_cache_path = mBuffer.append("h").append("o").append("r").append("i").append("z").append("o").append("n").append("_").append("c").append("o").append("r").append("e").append("-").append("c").append("a").append("c").append("h").append("e").toString();
        core_cache_path = File.separator + core_cache_path;

        return context.getCacheDir().getAbsolutePath() != null ? context.getCacheDir().getAbsolutePath() + core_cache_path : core_cache_path;
    }
}
