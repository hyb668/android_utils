package dou.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by mac on 16/8/18.
 */
public class DeviceUtil {

    private static final String TAG = DeviceUtil.class.getSimpleName();
    private static final String CPU_INFO_PATH = "/proc/cpuinfo";
    private static final String CPU_FREQ_NULL = "N/A";
    private static final String CMD_CAT = "/system/bin/cat";
    private static final String CPU_FREQ_CUR_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq";
    private static final String CPU_FREQ_MAX_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
    private static final String CPU_FREQ_MIN_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";

    private static String CPU_NAME;
    private static int CPU_CORES = 0;
    private static long CPU_MAX_FREQENCY = 0;
    private static long CPU_MIN_FREQENCY = 0;


    /**
     * Print cpu info.
     */
    public static String printCpuInfo() {
        String info = FileUtil.getFileOutputString(CPU_INFO_PATH);
        DLog.i(TAG, "_______  CPU :   \n" + info);
        return info;
    }

    /**
     * Get available processors.
     */
    public static int getProcessorsCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * Gets the number of cores available in this device, across all processors.
     * Requires: Ability to peruse the filesystem at "/sys/devices/system/cpu"
     *
     * @return The number of cores, or available processors if failed to get result
     */
    public static int getCoresNumbers() {
        if (CPU_CORES != 0) {
            return CPU_CORES;
        }
        //Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                //Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]+", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }

        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            //Return the number of cores (virtual CPU devices)
            CPU_CORES = files.length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (CPU_CORES < 1) {
            CPU_CORES = Runtime.getRuntime().availableProcessors();
        }
        if (CPU_CORES < 1) {
            CPU_CORES = 1;
        }
        return CPU_CORES;
    }

    /**
     * Get CPU name.
     */
    public static String getCpuName() {
        if (!CheckUtil.isEmpty(CPU_NAME)) {
            return CPU_NAME;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(CPU_INFO_PATH), 8192);
            String line = bufferedReader.readLine();
            bufferedReader.close();
            String[] array = line.split(":\\s+", 2);
            if (array.length > 1) {
                DLog.i(TAG, array[1]);
                CPU_NAME = array[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CPU_NAME;
    }

    /**
     * Get current CPU freqency.
     */
    public static long getCurrentFreqency() {
        try {
            return Long.parseLong(FileUtil.getFileOutputString(CPU_FREQ_CUR_PATH).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get maximum CPU freqency
     */
    public static long getMaxFreqency() {
        if (CPU_MAX_FREQENCY > 0) {
            return CPU_MAX_FREQENCY;
        }
        try {
            CPU_MAX_FREQENCY = Long.parseLong(getCMDOutputString(new String[]{CMD_CAT, CPU_FREQ_MAX_PATH}).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CPU_MAX_FREQENCY;
    }

    /**
     * Get minimum frenqency.
     */
    public static long getMinFreqency() {
        if (CPU_MIN_FREQENCY > 0) {
            return CPU_MIN_FREQENCY;
        }
        try {
            CPU_MIN_FREQENCY = Long.parseLong(getCMDOutputString(new String[]{CMD_CAT, CPU_FREQ_MIN_PATH}).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CPU_MIN_FREQENCY;
    }

    /**
     * Get command output string.
     */
    public static String getCMDOutputString(String[] args) {
        try {
            ProcessBuilder cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] re = new byte[64];
            int len;
            while ((len = in.read(re)) != -1) {
                sb.append(new String(re, 0, len));
            }
            in.close();
            process.destroy();
            DLog.i(TAG, "CMD: " + sb.toString());
            return sb.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * 获取设备MAC地址
     * 需添加权限 android.permission.ACCESS_WIFI_STATE
     *
     * @param context 上下文
     * @return MAC地址
     */
    public static String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String macAddress = info.getMacAddress().replace(":", "");
        return macAddress == null ? "" : macAddress;
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 android.permission.ACCESS_WIFI_STATE</p>
     *
     * @return MAC地址
     */
    public static String getMacAddress() {
        String macAddress = null;
        LineNumberReader reader = null;
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            reader = new LineNumberReader(ir);
            macAddress = reader.readLine().replace(":", "");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return macAddress == null ? "" : macAddress;
    }

    /**
     * 获取设备厂商，如Xiaomi
     *
     * @return 设备厂商
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取设备型号，如MI2SC
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    public static String getBootTimeString() {
        long ut = SystemClock.elapsedRealtime() / 1000;
        int h = (int) ((ut / 3600));
        int m = (int) ((ut / 60) % 60);
        return h + ":" + m;
    }

    public static String getAndroidId(Context mContext) {
        return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getSERIAL() {
        return android.os.Build.SERIAL;
    }

    public static String getAllSystemInfo() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        StringBuilder sb = new StringBuilder();
        sb.append("_______  系统信息  ").append(time).append(" ______________");
        sb.append("\nID                 :").append(Build.ID);
        sb.append("\nBRAND              :").append(Build.BRAND);
        sb.append("\nMODEL              :").append(Build.MODEL);
        sb.append("\nRELEASE            :").append(Build.VERSION.RELEASE);
        sb.append("\nSDK                :").append(Build.VERSION.SDK);

        sb.append("\n_______ OTHER _______");
        sb.append("\nBOARD              :").append(Build.BOARD);
        sb.append("\nPRODUCT            :").append(Build.PRODUCT);
        sb.append("\nDEVICE             :").append(Build.DEVICE);
        sb.append("\nFINGERPRINT        :").append(Build.FINGERPRINT);
        sb.append("\nHOST               :").append(Build.HOST);
        sb.append("\nTAGS               :").append(Build.TAGS);
        sb.append("\nTYPE               :").append(Build.TYPE);
        sb.append("\nTIME               :").append(Build.TIME);
        sb.append("\nINCREMENTAL        :").append(Build.VERSION.INCREMENTAL);

        sb.append("\n_______ CUPCAKE-3 _______");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            sb.append("\nDISPLAY            :").append(Build.DISPLAY);
        }

        sb.append("\n_______ DONUT-4 _______");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            sb.append("\nSDK_INT            :").append(Build.VERSION.SDK_INT);
            sb.append("\nMANUFACTURER       :").append(Build.MANUFACTURER);
            sb.append("\nBOOTLOADER         :").append(Build.BOOTLOADER);
            sb.append("\nCPU_ABI            :").append(Build.CPU_ABI);
            sb.append("\nCPU_ABI2           :").append(Build.CPU_ABI2);
            sb.append("\nHARDWARE           :").append(Build.HARDWARE);
            sb.append("\nUNKNOWN            :").append(Build.UNKNOWN);
            sb.append("\nCODENAME           :").append(Build.VERSION.CODENAME);
        }

        sb.append("\n_______ GINGERBREAD-9 _______");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            sb.append("\nSERIAL             :").append(Build.SERIAL);
        }
        return sb.toString();
    }
}
