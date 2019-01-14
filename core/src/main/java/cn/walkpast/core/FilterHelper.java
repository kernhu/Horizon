package cn.walkpast.core;

import android.net.Uri;

import cn.walkpast.core.constant.FilterType;

/**
 * author: Kern Hu
 * email: sky580@126.com
 * data_time: 2019/1/7 12:14 AM
 * describe: This is...
 */

public class FilterHelper {


    public static boolean isNeedFilter(FilterType type, String[] filterList, String targetUrl) {

        switch (type) {

            case TYPE_MATCH_HOST:

                Uri uri = Uri.parse(targetUrl);
                for (String filter : filterList) {
                    if (uri.getHost().equals(filter)) {
                        return true;
                    }
                }

                return false;
            case TYPE_MATCH_FULL_HOST:

                for (String filter : filterList) {
                    if (targetUrl.startsWith(filter)) {
                        return true;
                    }
                }

                return false;
            case TYPE_MATCH_URL:

                for (String filter : filterList) {
                    if (targetUrl.equals(filter)) {
                        return true;
                    }
                }

                return false;
        }

        return false;
    }
}
