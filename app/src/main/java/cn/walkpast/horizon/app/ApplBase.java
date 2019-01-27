package cn.walkpast.horizon.app;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cn.walkpast.core.config.HorizonConfig;
import cn.walkpast.horizon.BuildConfig;
import cn.walkpast.horizon.R;
import cn.walkpast.horizon.constants.Bugly;
import cn.walkpast.horizon.constants.Umeng;

/**
 * Author: Kern
 * Time: 2019/1/4 20:32
 * Description: This is..
 */

public class ApplBase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initBugly();
        initUmeng();
        initHorizonConfig();

    }

    /*********************************************************************************************/

    /**
     * init UMENG
     */
    private void initUmeng() {

        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(BuildConfig.DEBUG);

        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:【友盟+】 AppKey
         * 参数3:【友盟+】 Channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        UMConfigure.init(this, Umeng.APP_KEY, BuildConfig.FLAVOR, UMConfigure.DEVICE_TYPE_PHONE, null);

    }

    /*********************************************************************************************/

    /**
     * init bugly
     */
    private void initBugly() {

        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, Bugly.APP_ID, BuildConfig.DEBUG, strategy);
        // 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
        // CrashReport.initCrashReport(context, strategy);
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /*********************************************************************************************/

    /**
     * init horizon config
     */
    private void initHorizonConfig() {

        HorizonConfig.with(this)
                .setToastBgc(R.color.ToastBgc)
                .setToastMsgColor(R.color.ToastTextColor)
                .setLogSwitch(BuildConfig.DEBUG)
                .setMaxCount(20)
                .config();
    }

    /*********************************************************************************************/

}
