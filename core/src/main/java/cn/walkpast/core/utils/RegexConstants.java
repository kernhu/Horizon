package cn.walkpast.core.utils;

/**
 * Author: Kern
 * Time: 2019/1/2 10:52
 * Description: This is..
 */

public class RegexConstants {

    /**
     * 正则：URL
     */
    public static final String REGEX_URL = "[a-zA-z]+://[^\\s]*";

    /**
     * 正则：是否是纯净URL
     */
    public static final String REGEX_PURE_URL = "(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_#\\./-~-]*)?";

    /**
     * 正则：是否是纯净URL(带请求头)
     */
    public static final String REGEX_PURE_HEAD_URL = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_#\\./-~-]*)?";

}
