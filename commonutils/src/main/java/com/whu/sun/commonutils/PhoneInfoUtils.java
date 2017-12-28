package com.whu.sun.commonutils;

/**
 * Created by Administrator on 2017/12/28.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * desc ：读取手机必要信息(需要READ_PHONE_STATE权限)
 *
 * author : Marc SUN
 * email : 710641245@qq.com
 * creation date: 2017/12/28 15:45
 */
public class PhoneInfoUtils {

    // 获取电话号码
    /*
    * 手机号码不是所有的都能获取,只是有一部分可以拿到;
    * 这个是由于移动运营商没有把手机号码的数据写入到sim卡中;
    * SIM卡只有唯一的编号，供网络与设备识别那就是IMSI号码
    * */
    @SuppressLint("MissingPermission")
    public static String getNativePhoneNumber(Context context) {
        //访问与手机通讯相关的信息的管理器
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getLine1Number();
    }

    // 获取手机服务商信息
    @SuppressLint("MissingPermission")
    public static String getProviderName(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String providerName = "N/A";

        /*
        *  国际移动用户识别码（IMSI: International Mobile SubscriberIdentification Number，存储在SIM卡中）
        *  IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信
        * */
        String IMSI = telephonyManager.getSubscriberId();

        if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
            providerName = "中国移动";
        } else if (IMSI.startsWith("46001")) {
            providerName = "中国移动";
        } else if (IMSI.startsWith("46003")) {
            providerName = "中国移动";
        }

        return providerName;
    }

    //获取手机信息
    @SuppressLint("MissingPermission")
    public static String getPhoneInfo(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        StringBuilder sb = new StringBuilder();

        sb.append("\nDeviceId(IMEI) = " + tm.getDeviceId());                       // IMEI: International Mobile Equipment Identity,国际移动设备身份码
        sb.append("\nDeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion());
        sb.append("\nLine1Number = " + tm.getLine1Number());
        sb.append("\nNetworkCountryIso = " + tm.getNetworkCountryIso());
        sb.append("\nNetworkOperator = " + tm.getNetworkOperator());              // 移动运营商编号
        sb.append("\nNetworkOperatorName = " + tm.getNetworkOperatorName());      // 移动运营商名称
        sb.append("\nNetworkType = " + tm.getNetworkType());
        sb.append("\nPhoneType = " + tm.getPhoneType());
        sb.append("\nSimCountryIso = " + tm.getSimCountryIso());
        sb.append("\nSimOperator = " + tm.getSimOperator());
        sb.append("\nSimOperatorName = " + tm.getSimOperatorName());
        sb.append("\nSimSerialNumber = " + tm.getSimSerialNumber());
        sb.append("\nSimState = " + tm.getSimState());
        sb.append("\nSubscriberId(IMSI) = " + tm.getSubscriberId());
        sb.append("\nVoiceMailNumber = " + tm.getVoiceMailNumber());

        return sb.toString();
    }
}
