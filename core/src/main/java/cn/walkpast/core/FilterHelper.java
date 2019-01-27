package cn.walkpast.core;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.walkpast.core.constant.FilterType;
import cn.walkpast.utils.LogUtils;

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
            case TYPE_CONTAINS_URL:

                for (String filter : filterList) {
                    if (targetUrl.contains(filter)) {
                        return true;
                    }
                }

                break;
        }
        return false;
    }


    /**
     * @param targetUrl
     * @return
     */
    public static String getUrlToString(String targetUrl) {

        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10 * 1000);
            connection.setConnectTimeout(40 * 1000);
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            LogUtils.e(FilterHelper.class.toString(), "MalformedURLException:" + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e(FilterHelper.class.toString(), "IOException:" + e.toString());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     *
     */
    public static String DEFAULT_REPLACE_URL = "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>非法链接，不予打开</title>\n" +
            "\n" +
            "    <meta content=\"width=device-width, initial-scale=0.4, maximum-scale=0.4, user-scalable=0;\"\n" +
            "          name=\"viewport\"/>\n" +
            "\n" +
            "    <style type=\"text/css\">\n" +
            "        body, html {\n" +
            "            height: 100%;\n" +
            "            width: 100%;\n" +
            "        }\n" +
            "    </style>\n" +
            "\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<div style=\"height: 100px; background-color: #e6b500; text-align: center; line-height: 100px\">\n" +
            "    已拦截该非法页面\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
}
