package cn.walkpast.core.except;

/**
 * Author: Kern
 * Time: 2019/1/3 15:10
 * Description: This is..
 */

public class HorizonException extends Exception {


    public HorizonException(String message) {
        super(message);
    }

    public HorizonException(String message, Throwable cause) {
        super(message, cause);
    }
}
