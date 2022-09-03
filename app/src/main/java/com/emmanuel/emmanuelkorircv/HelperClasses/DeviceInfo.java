package com.emmanuel.emmanuelkorircv.HelperClasses;

import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class DeviceInfo {

    public static int readIntegerFile(String filePath) {

        try {
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath)), 1000);
            final String line = reader.readLine();
            reader.close();

            return Integer.parseInt(line);
        } catch (Exception e) {
            return 0;
        }
    }
    public static int takeMaxCpuFreq(int coreIndex)  {
        return readIntegerFile("/sys/devices/system/cpu/cpu" + coreIndex + "/cpufreq/cpuinfo_max_freq");
    }

    public static int takeCurrentCpuFreq(int coreIndex) {
        return readIntegerFile("/sys/devices/system/cpu/cpu" + coreIndex + "/cpufreq/scaling_cur_freq");
    }

    public static int calcCpuCoreCount() {
        int sLastCpuCoreCount;
        try {
            // Get directory containing CPU info
            final File dir = new File("/sys/devices/system/cpu/");
            // Filter to only list the devices we care about
            final File[] files = dir.listFiles(new FileFilter() {

                public boolean accept(File pathname) {
                    //Check if filename is "cpu", followed by a single digit number
                    return Pattern.matches("cpu[0-9]", pathname.getName());
                }
            });
            // Return the number of cores (virtual CPU devices)
            sLastCpuCoreCount = files.length;

        } catch(Exception e) {
            sLastCpuCoreCount = Runtime.getRuntime().availableProcessors();
        }
        return sLastCpuCoreCount;
    }



    public static int calThermalsCount() {
        int sLastCpuCoreCount;
        try {
            // Get directory containing CPU info
            final File dir = new File("sys/class/thermal/");
            // Filter to only list the devices we care about
            final File[] files = dir.listFiles(new FileFilter() {

                public boolean accept(File pathname) {
                    //Check if filename is "cpu", followed by a single digit number
                    return Pattern.matches("thermal_zone[0-100]", pathname.getName());
                }
            });
            // Return the number of cores (virtual CPU devices)
            sLastCpuCoreCount = files.length;

        } catch(Exception e) {
            sLastCpuCoreCount = Runtime.getRuntime().availableProcessors();
        }
        return sLastCpuCoreCount;
    }



    public static String thermalTemp(int i) {
        Process process;
        BufferedReader reader;
        String line;
        String t = null;
        float temp = 0;
        try {
            process = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone" + i + "/temp");
            process.waitFor();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            line = reader.readLine();
            if (line != null) {
                temp = Float.parseFloat(line);
            }
            reader.close();
            process.destroy();
            if (!((int) temp == 0)) {
                if ((int) temp > 10000) {
                    temp = temp / 1000;
                } else if ((int) temp > 1000) {
                    temp = temp / 100;
                } else if ((int) temp > 100) {
                    temp = temp / 10;
                }
                t= temp +" °C";
            } else
                t = "0.0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static float thermalTempfloat(int i) {
        Process process;
        BufferedReader reader;
        String line;
        float t = 0;
        float temp = 0;
        try {
            process = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone" + i + "/temp");
            process.waitFor();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            line = reader.readLine();
            if (line != null) {
                temp = Float.parseFloat(line);
            }
            reader.close();
            process.destroy();
            if (!((int) temp == 0)) {
                if ((int) temp > 10000) {
                    temp = temp / 1000;
                } else if ((int) temp > 1000) {
                    temp = temp / 100;
                } else if ((int) temp > 100) {
                    temp = temp / 10;
                }
                t= (temp);
            } else
                t = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static String thermalType(int i) {
        Process process;
        BufferedReader reader;
        String line, type = null;
        try {
            process = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone" + i + "/type");
            process.waitFor();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            line = reader.readLine();
            if (line != null) {
                type = line;
            }
            reader.close();
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }


    ///////////////////////////////////////-------------------------storage--------------------------------------////////////////////////////////////////
    public static boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        return availableBlocks * blockSize;
    }


    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        return totalBlocks * blockSize;
    }

    public static long getAvailableExternalMemorySize() {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSizeLong();
            long availableBlocks = stat.getAvailableBlocksLong();
            return availableBlocks * blockSize;

    }

    public static long getTotalExternalMemorySize() {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSizeLong();
            long totalBlocks = stat.getBlockCountLong();
            return totalBlocks * blockSize;

    }



    public static String formatSize(long size) {
        long kilobyte = 1024;
        long megabyte = kilobyte * 1024;
        long gigabyte = megabyte * 1024;
        long terabyte = gigabyte * 1024;

        if ((size >= 0) && (size < kilobyte)) {
            return size + " B";

        } else if ((size >= kilobyte) && (size < megabyte)) {
            return (float)(size / kilobyte) + " KB";

        } else if ((size >= megabyte) && (size < gigabyte)) {
            return (float)(size / megabyte) + " MB";

        } else if ((size >= gigabyte) && (size < terabyte)) {
            return (float)(size / gigabyte) + " GB";

        } else if (size >= terabyte) {
            return (float)(size / terabyte) + " TB";

        } else {
            return size + " size";
        }
    }



    public static String formatSizeRAM(long size) {
        String suffix = null;
        long kilobyte = 1024;
        long megabyte = kilobyte * 1024;
        long gigabyte = megabyte * 1024;
        long terabyte = gigabyte * 1024;

        if ((size >= 0) && (size < kilobyte)) {
            return size + " B";

        } else if ((size >= kilobyte) && (size < megabyte)) {
            return (float)(size / kilobyte) + " KB";

        } else if ((size >= megabyte) && (size < terabyte)) {
            return (float)(size / megabyte) + " MB";



        } else if (size >= terabyte) {
            return (float)(size / terabyte) + " TB";

        } else {
            return size + " size";
        }
    }
}
