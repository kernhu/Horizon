package cn.walkpast.core.utils;

import java.util.regex.Pattern;

/**
 * Author: Kern
 * Time: 2019/1/2 10:52
 * Description: This is..
 */

public class RegexUtils {

    /**
     * 验证URL
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isURL(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_URL, input);
    }

    /**
     * 是否是合法url
     *
     * @param input
     * @return
     */
    public static boolean isLegalURL(final CharSequence input) {

        return isMatch(RegexConstants.REGEX_PURE_URL, input);
    }

    /**
     * 是否是纯净的URL
     *
     * @param input
     * @return
     */
    public static boolean isPureURL(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_PURE_URL, input) || isMatch(RegexConstants.REGEX_PURE_HEAD_URL, input);
    }


    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(final String regex, final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

}
