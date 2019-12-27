package com.qr.library.keep_alive;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class KeepAliveUtils {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean isIgnoringBatteryOptimizations(@NonNull Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (powerManager == null) {
            return false;
        }
        return powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void requestIgnoringBatteryOptimizations(@NonNull Activity activity) {
        try {
            Intent intent = new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
            intent.setData(Uri.parse("package:" + activity.getPackageName()));
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void requestIgnoringBatteryOptimizations(@NonNull Activity activity, int requestCode) {
        try {
            Intent intent = new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
            intent.setData(Uri.parse("package:" + activity.getPackageName()));
            activity.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showLaunchActivity(@NonNull Activity activity, @NonNull String packageName) {
        Intent intent = activity.getPackageManager().getLaunchIntentForPackage(packageName);
        activity.startActivity(intent);
    }

    public static void showPointAtActivity(@NonNull Activity activity, @NonNull String packageName, @NonNull String activityDir) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityDir));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public static boolean isHwawei() {
        if (Build.BRAND == null) {
            return false;
        } else {
            return Build.BRAND.toLowerCase().equals("hwawei") || Build.BRAND.toLowerCase().equals("honor");
        }
    }

    public static void goHwaweiSetting(Activity activity) {
        try {
            showPointAtActivity(activity, "com.huawei.systemmanager",
                    "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
        } catch (Exception e) {
            try {
                showPointAtActivity(activity, "com.huawei.systemmanager",
                        "com.huawei.systemmanager.optimize.bootstart.BootStartActivity");
            } catch (Exception er) {
                Log.d(KeepAliveUtils.class.getSimpleName(), "Hwawei 启动项设置页面打开失败!!!", er);
            }
        }
    }

    public static boolean isXiaomi() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("xiaomi");
    }

    public static void goXiaomiSetting(Activity activity) {
        try {
            showPointAtActivity(activity, "com.miui.securitycenter",
                    "com.miui.permcenter.autostart.AutoStartManagementActivity");
        } catch (Exception e) {
            Log.d(KeepAliveUtils.class.getSimpleName(), "Xiaomi 启动项设置页面打开失败!!! ", e);
        }
    }

    public static boolean isOPPO() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("oppo");
    }

    public static void goOPPOSetting(Activity activity) {
        try {
            showLaunchActivity(activity, "com.coloros.phonemanager");
        } catch (Exception e1) {
            try {
                showLaunchActivity(activity, "com.oppo.safe");
            } catch (Exception e2) {
                try {
                    showLaunchActivity(activity, "com.coloros.oppoguardelf");
                } catch (Exception e3) {
                    try {
                        showLaunchActivity(activity, "com.coloros.safecenter");
                    } catch (Exception e4) {
                        Log.d(KeepAliveUtils.class.getSimpleName(), "OPPO 启动项设置页面打开失败!!!", e4);
                    }
                }
            }
        }
    }

    public static boolean isVIVO() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("vivo");
    }

    public static void goVIVOSetting(Activity activity) {
        try {
            showLaunchActivity(activity, "com.iqoo.secure");
        } catch (Exception e) {
            Log.d(KeepAliveUtils.class.getSimpleName(), "VIVO 启动项设置页面打开失败!!!", e);
        }
    }

    public static boolean isMeizu() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("meizu");
    }

    public static void goMeizuSetting(Activity activity) {
        try {
            showLaunchActivity(activity, "com.meizu.safe");
        } catch (Exception e) {
            Log.d(KeepAliveUtils.class.getSimpleName(), "Meizu 启动项设置页面打开失败!!!", e);
        }
    }

    public static boolean isSamsung() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("samsung");
    }

    public static void goSamsungSetting(Activity activity) {
        try {
            showLaunchActivity(activity, "com.samsung.android.sm_cn");
        } catch (Exception e) {
            try {
                showLaunchActivity(activity, "com.samsung.android.sm");
            } catch (Exception e1) {
                Log.d(KeepAliveUtils.class.getSimpleName(), "Samsung 启动项设置页面打开失败!!!", e1);
            }
        }
    }

    public static boolean isLeTV() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("letv");
    }

    public static void goLetvSetting(Activity activity) {
        try {
            showPointAtActivity(activity, "com.letv.android.letvsafe",
                    "com.letv.android.letvsafe.AutobootManageActivity");
        } catch (Exception e) {
            Log.d(KeepAliveUtils.class.getSimpleName(), "Letv 启动项设置页面打开失败!!!", e);
        }
    }

    public static boolean isSmartisan() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("smartisan");
    }

    private void goSmartisanSetting(Activity activity) {
        try {
            showLaunchActivity(activity, "com.smartisanos.security");
        } catch (Exception e) {
            Log.d(KeepAliveUtils.class.getSimpleName(), "Smartisan 启动项设置页面打开失败!!!", e);
        }
    }
}
