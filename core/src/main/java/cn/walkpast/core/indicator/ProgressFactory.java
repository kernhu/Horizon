package cn.walkpast.core.indicator;

/**
 * Author: Kern
 * Time: 2019/1/11 20:48
 * Description: This is..
 */

public interface ProgressFactory {

    void createProgress();

    void setProgress(int progress);

    void setVisible();

    void setInvisible();
}
