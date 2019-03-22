package com.example.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;


public class DateTimeUtil
{

    // 格式：2007年06月07日 12时12分12秒234毫秒
    private final static String[] FORMAT_CHINA      = {"年", "月", "日", "时", "分", "秒", "毫秒"};
    // 格式：2007年06月07日
    private final static String[] FORMAT_DATE_CHINA = {"年", "月", "日"};
    // 格式：2007-06-07 12:12:12 234
    private final static String[] FORMAT_NORMAL     = {"-", "-", "", ":", ":", "", ""};
    // 格式：2007/06/07 12:12:12 234
    private final static String[] FORMAT_DATATIME   = {"/", "/", "", ":", ":", "", ""};
    // 格式：中文星期
    private final static String[] FORMAT_WEEK       = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * @Title: getLastMonth
     * @Description: 获取上月信息
     * @Param: @param formart 给定转换格式
     * @Param: @return
     * @Return: String
     * @Comment:
     * @Author: Administrator
     * @CreateDate: Mar 29, 2012 8:54:59 PM
     */
    public static String getLastMonth(String strFormart)
    {
        SimpleDateFormat df = new SimpleDateFormat(strFormart);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        return df.format(calendar.getTime()).toString();
    }

    /**
     * 
     * @Title: getTodayYear
     * @Description: 获取今日年份
     * @return
     * @return String 返回类型
     */
    public static String getTodayYear()
    {
        return DateFormatUtils.format(new Date(), "yyyy");
    }

    /**
     * 
     * @Title: getTodayMonth @Description: 获取今日月份 @return MM @return String
     *         返回类型 @throws
     */
    public static String getTodayMonth()
    {
        return DateFormatUtils.format(new Date(), "MM");
    }

    /**
     * 
     * 
     * @Title: getLastMonthChar6
     * @Description: 获取上月信息 以yyyymm格式返回
     * @Param: @return
     * @Return: String
     * @Comment:
     * @Author: Administrator
     * @CreateDate: 2012-6-9 下午03:37:12
     */
    public static String getLastMonthChar6()
    {
        GregorianCalendar gerCal = new GregorianCalendar();
        gerCal.setTime(new Date());
        gerCal.add(GregorianCalendar.MONTH, -1);// 在日期上减一个月
        SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMM");
        return lastDf.format(gerCal.getTime()).toString();
    }

    /**
     * 
     * @Title: getLastMonthChar6ByMonth @Description:
     *         根据穿入的月份获取相应的加减月份 @return @return String 返回类型 @throws
     */
    public static String getLastMonthChar6ByMonth(int month)
    {
        GregorianCalendar gerCal = new GregorianCalendar();
        gerCal.setTime(new Date());
        gerCal.add(GregorianCalendar.MONTH, month);// 在日期上减一个月
        SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMM");
        return lastDf.format(gerCal.getTime()).toString();
    }

    public static String getLastMonthChar8ByMonth(int month)
    {
        GregorianCalendar gerCal = new GregorianCalendar();
        gerCal.setTime(new Date());
        gerCal.add(GregorianCalendar.MONTH, month);// 在日期上减一个月
        SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMdd");
        return lastDf.format(gerCal.getTime()).toString();
    }

    /**
     * 
     * @Title: getDateChar8 @Description: 根据穿入的年月日获取日期 @param year @param
     *         month @param day @return @return String 返回类型 @throws
     */
    public static String getDateChar8(int year, int month, int day)
    {
        GregorianCalendar gerCal = new GregorianCalendar();
        gerCal.setTime(new Date());
        gerCal.add(GregorianCalendar.YEAR, year);// 在日期上减一个月
        gerCal.add(GregorianCalendar.MONTH, month);// 在日期上减一个月
        gerCal.add(GregorianCalendar.DATE, day);// 在日期上减1天
        SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMdd");
        return lastDf.format(gerCal.getTime()).toString();
    }

    /**
     * 获取今日日期
     * 
     * @return dd
     */
    public static String getTodayDay()
    {
        return DateFormatUtils.format(new Date(), "dd");
    }

    /**
     * 获取短日月
     * 
     * @return MMdd
     */
    public static String getTodayChar4()
    {
        return DateFormatUtils.format(new Date(), "MMdd");
    }

    /**
     * 获取短日月
     * 
     * @return MMdd
     */
    public static String getTodayChar4En()
    {
        return DateFormatUtils.format(new Date(), "MM-dd");
    }

    /**
     * 返回年月
     * 
     * @return yyyyMM
     */
    public static String getTodayChar6()
    {
        return DateFormatUtils.format(new Date(), "yyyyMM");
    }

    /**
     * 返回年月
     * 
     * @return yyyyMM
     */
    public static String getTodayChar6En()
    {
        return DateFormatUtils.format(new Date(), "yyyy-MM");
    }

    /**
     * 返回年月日
     * 
     * @return yyyyMMdd
     */
    public static String getTodayChar8()
    {
        return DateFormatUtils.format(new Date(), "yyyyMMdd");
    }

    public static String getTodayCharYYMMDD()
    {
        return DateFormatUtils.format(new Date(), "yyMMdd");
    }

    /**
     * 返回年月日
     * 
     * @return yyyyMMdd
     */
    public static String getTodayChar8En()
    {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd");
    }

    /**
     * 
     * @Title: getLastDayCharAdd7En @Description: 获取7天后日期 格式
     *         yyyy-MM-dd @return @return String 返回类型 @throws
     */
    public static String getLastDayCharAdd7En()
    {
        GregorianCalendar gerCal = new GregorianCalendar();
        gerCal.setTime(new Date());
        gerCal.add(GregorianCalendar.DATE, +7);// 在日期上+7天
        SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMddHHmm");
        return lastDf.format(gerCal.getTime());
    }

    /**
     * 
     * @Title: getLastDayChar8En
     * @Description: 获取昨天日期 格式 yyyy-MM-dd
     * @Param: @return
     * @Return: String
     * @Comment:
     * @Author: Administrator
     * @CreateDate: 2012-5-29 上午11:20:37
     */
    public static String getLastDayChar8En()
    {
        GregorianCalendar gerCal = new GregorianCalendar();
        gerCal.setTime(new Date());
        gerCal.add(GregorianCalendar.DATE, -1);// 在日期上减1天
        SimpleDateFormat lastDf = new SimpleDateFormat("yyyy-MM-dd");
        return lastDf.format(gerCal.getTime());
    }

    /**
     * @Title: getLastDayChar8 @Description: 获取昨天日期 格式 yyyyMMdd @return @return
     *         String 返回类型 @throws
     */
    public static String getLastDayChar8()
    {
        GregorianCalendar gerCal = new GregorianCalendar();
        gerCal.setTime(new Date());
        gerCal.add(GregorianCalendar.DATE, -1);// 在日期上减1天
        SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMdd");
        return lastDf.format(gerCal.getTime());
    }

    /**
     * 
     * @Title: getTodayChar12
     * @Description: 返回 年月日小时分
     * @return yyyyMMddHHmm
     * @return String 返回类型
     */
    public static String getTodayChar12()
    {
        return DateFormatUtils.format(new Date(), "yyyyMMddHHmm");
    }

    /**
     * 
     * @Title: getTodayChar12En
     * @Description: 获取当前年月日小时分
     * @return 年月日小时分
     * @return String 返回类型
     */
    public static String getTodayChar12En()
    {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm");
    }

    /**
     * 
     * @Title: getTodayChar14
     * @Description: 获取当前年月日小时分秒
     * @return 年月日小时分秒
     * @return String 返回类型
     */

    public static String getTodayChar14()
    {
        return DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 
     * @Title: getTodayChar8
     * @Description: 获取当前月日小时分
     * @return 年月日小时分秒
     * @return String 返回类型
     */

    public static String getTodayChar7()
    {
        return DateFormatUtils.format(new Date(), "M-d HH:mm");
    }

    /**
     * 
     * @Title: getDayChar14ByDays
     * @Description: 根据天数加减当前日期
     * @param days
     * @return
     * @return String 返回类型
     */
    public static String getDayChar14ByDays(int days)
    {
        GregorianCalendar gerCal = new GregorianCalendar();
        gerCal.setTime(new Date());
        gerCal.add(GregorianCalendar.DATE, days);// 在日期上加减天数
        SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMddHHmmss");
        return lastDf.format(gerCal.getTime());
    }

    /**
     * @Title: getDayChar17ByMinutes @Description: 根据分钟数加减当前日期 @param
     *         minute @return @return String 返回类型 @throws
     */
    public static String getDayChar17ByMinutes(int minute)
    {
        GregorianCalendar gerCal = new GregorianCalendar();
        gerCal.setTime(new Date());
        gerCal.add(GregorianCalendar.MINUTE, minute);// 在日期上加减分钟
        SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMddHHmmss");
        return lastDf.format(gerCal.getTime());
    }

    /**
     * 
     * @Title: getDayChar14ByMunites
     * @Description: 根据分钟加减当前日期
     * @param days
     * @return
     * @return String 返回类型
     */
    public static String getDayChar14ByMunites(int mni)
    {
        GregorianCalendar gerCal = new GregorianCalendar();
        gerCal.setTime(new Date());
        gerCal.add(GregorianCalendar.MINUTE, mni);// 在分钟上加减天数
        SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMddHHmmss");
        return lastDf.format(gerCal.getTime());
    }

    /**
     * 
     * @Title: getTodayChar14En @Description: 获取年月日小时分秒 @return 年月日小时分秒 @return
     *         String 返回类型 @throws
     */
    public static String getTodayChar14En()
    {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 
     * @Title: getTodayChar17 @Description: 获取当前的年月日小时分秒 毫秒 @return 年月日小时分秒
     *         毫秒 @return String 返回类型 @throws
     */
    public static String getTodayChar17()
    {
        String dateString = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssS");
        int length = dateString.length();

        if (length < 17)
        {
            String endStr = dateString.substring(14, length);
            int len = endStr.length();
            for (int i = 0; i < 3 - len; i++)
            {
                endStr = "0" + endStr;
            }
            dateString = dateString.substring(0, 14) + endStr;
        }
        return dateString;
    }

    /**
     * 
     * @Title: getTodayChar17En
     * @Description:当前 年月日小时分秒 毫秒
     * @return 年月日小时分秒 毫秒
     * @return String 返回类型
     */
    public static String getTodayChar17En()
    {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 
     * @Title: getToDateCn
     * @Description: 获取年月日小时分秒 毫秒
     * @return 年月日小时分秒 毫秒
     * @return String 返回类型
     */
    public static String getToDateCn()
    {
        return DateFormatUtils.format(new Date(), "yyyy年MM月dd日");
    }

    /**
     * 
     * @Title: getToDateEn @Description: 获取当前的年-月-日 @return 年-月-日 @return String
     *         返回类型 @throws
     */
    public static String getToDateEn()
    {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd");
    }

    /**
     * 
     * @Title: getFormatChina
     * @Description: 获取中文日期格式 支持4、6、8、12、14、17位转换
     * @param charDateTime
     *            长整型 CHAR
     * @return 2007年12月12日 12时12分12秒 234毫秒 中文日期格式
     * @return String 返回类型
     */
    public static String getFormatChina(String charDateTime)
    {
        return getFormatDateTime(charDateTime, "FORMAT_CHINA");
    }

    /**
     * 
     * @Title: getFormatNormal
     * @Description: 获取标准日期格式 支持4、6、8、12、14、17位转换
     * @param charDateTime
     *            长整型 CHAR
     * @return 2007-12-12 12:12:12 234
     * @return String 返回类型
     */
    public static String getFormatNormal(String charDateTime)
    {
        return getFormatDateTime(charDateTime, "FORMAT_NORMAL");
    }

    /**
     * 
     * @Title: getFormatDateTime
     * @Description: 标准日期格式 支持4、6、8、12、14、17位转换
     * @param charDateTime
     *            长整型 CHAR
     * @return 2007/12/12 12:12:12 234
     * @return String 返回类型
     */
    public static String getFormatDateTime(String charDateTime)
    {
        return getFormatDateTime(charDateTime, "FORMAT_DATATIME");
    }

    /**
     * 
     * @Title: getDateTimeToChar @Description:把日期格式转换为长整型格式 @param
     *         inputDateTime @return @return String 返回类型 @throws
     */
    public static String getDateTimeToChar(String inputDateTime)
    {
        String strResult = "";
        if (null == inputDateTime)
        {
            return strResult = "";
        }

        if (("".equals(inputDateTime.trim())))
        {
            return strResult = "";
        }

        // 替换
        strResult = inputDateTime.replaceAll("年", "");
        strResult = strResult.replaceAll("月", "");
        strResult = strResult.replaceAll("日", "");
        strResult = strResult.replaceAll("时", "");
        strResult = strResult.replaceAll("分", "");
        strResult = strResult.replaceAll("秒", "");
        strResult = strResult.replaceAll("毫", "");
        strResult = strResult.replaceAll(" ", "");
        strResult = strResult.replaceAll("-", "");
        strResult = strResult.replaceAll("/", "");
        strResult = strResult.replaceAll(":", "");
        return strResult;

    }

    /**
     * 
     * @Title: getFormatDateTime
     * @Description: 对日期进行转换
     * @Param: @param charDateTime
     * @Param: @param formatType FORMAT_CHINA 中文 FORMAT_NORMAL以‘-’先分隔
     *         FORMAT_DATATIME 以‘/’分隔
     * @Param: @return
     * @Return: String
     * @Comment:
     * @Author: Administrator
     * @CreateDate: Mar 29, 2012 8:35:29 PM
     */
    private static String getFormatDateTime(String charDateTime, String formatType)
    {
        String strResult = "";
        if (null == charDateTime)
        {
            return strResult = "";
        }

        if (("".equals(charDateTime.trim())))
        {
            return strResult = "";
        }

        String[] FORMAT_CHAR = null;
        if (formatType.equals("FORMAT_CHINA"))
        {
            FORMAT_CHAR = FORMAT_CHINA;
        }
        else if (formatType.equals("FORMAT_NORMAL"))
        {
            FORMAT_CHAR = FORMAT_NORMAL;
        }
        else if (formatType.equals("FORMAT_DATATIME"))
        {
            FORMAT_CHAR = FORMAT_DATATIME;
        }
        else
        {
            return strResult = charDateTime;
        }

        // 去掉空格
        charDateTime = charDateTime.trim();

        if (charDateTime.length() == 4)
        {
            // MMdd 转换 MM月dd日
            strResult = charDateTime.substring(0, 2) + FORMAT_CHAR[1] + charDateTime.substring(2, 4) + FORMAT_CHAR[2];
        }
        else if (charDateTime.length() == 6)
        {
            // yyyyMM 转换 yyyy年MM月
            strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0] + charDateTime.substring(4, 6) + FORMAT_CHAR[1];
        }
        else if (charDateTime.length() == 8)
        {
            // yyyyMMdd
            strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0] + charDateTime.substring(4, 6) + FORMAT_CHAR[1] + charDateTime.substring(6, 8)
                    + FORMAT_CHAR[2];
        }
        else if (charDateTime.length() == 12)
        {
            // yyyyMMddHHmm
            strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0] + charDateTime.substring(4, 6) + FORMAT_CHAR[1] + charDateTime.substring(6, 8)
                    + FORMAT_CHAR[2] + " " + charDateTime.substring(8, 10) + FORMAT_CHAR[3] + charDateTime.substring(10, 12) + FORMAT_CHAR[4];
        }
        else if (charDateTime.length() == 14)
        {
            // yyyyMMddHHmmss
            strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0] + charDateTime.substring(4, 6) + FORMAT_CHAR[1] + charDateTime.substring(6, 8)
                    + FORMAT_CHAR[2] + " " + charDateTime.substring(8, 10) + FORMAT_CHAR[3] + charDateTime.substring(10, 12) + FORMAT_CHAR[4]
                    + charDateTime.substring(12, 14) + FORMAT_CHAR[5];
        }
        else if (charDateTime.length() == 17)
        {
            // yyyyMMddHHmmssS
            strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0] + charDateTime.substring(4, 6) + FORMAT_CHAR[1] + charDateTime.substring(6, 8)
                    + FORMAT_CHAR[2] + " " + charDateTime.substring(8, 10) + FORMAT_CHAR[3] + charDateTime.substring(10, 12) + FORMAT_CHAR[4]
                    + charDateTime.substring(12, 14) + FORMAT_CHAR[5] + " " + charDateTime.substring(14, 17) + FORMAT_CHAR[6];
        }
        else
        {
            strResult = charDateTime;
        }

        return strResult;
    }

    /**
     * @Title: getOracleDate
     * @Description: 将指定Date类型参数转换为指定的Oracle日期时间格式字符串
     * @param inputDate
     *            传入Date类型参数
     * @return String
     */
    public static String getOracleDate(Date inputDateTime) throws NullPointerException
    {
        if (null == inputDateTime)
        {
            return StringUtils.EMPTY;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(inputDateTime);
    }

    /**
     * 
     * @Title: getDistanceDT
     * @Description: 比对两个时间间隔
     * @param startDateTime
     *            开始时间
     * @param endDateTime
     *            结束时间
     * @param distanceType
     *            计算间隔类型 天d 小时h 分钟m 秒s
     * @return
     */
    public static String getDistanceDT(String startDateTime, String endDateTime, String distanceType)
    {
        String strResult = StringUtils.EMPTY;
        long lngDistancVal = 0;
        try
        {
            SimpleDateFormat tempDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date startDate = tempDateFormat.parse(startDateTime);
            Date endDate = tempDateFormat.parse(endDateTime);
            if (distanceType.equals("d"))
            {
                lngDistancVal = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
            }
            else if (distanceType.equals("h"))
            {
                lngDistancVal = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60);
            }
            else if (distanceType.equals("m"))
            {
                lngDistancVal = (endDate.getTime() - startDate.getTime()) / (1000 * 60);
            }
            else if (distanceType.equals("s"))
            {
                lngDistancVal = (endDate.getTime() - startDate.getTime()) / (1000);
            }
            strResult = String.valueOf(lngDistancVal);
        }
        catch (Exception e)
        {
            strResult = "0";
        }
        return strResult;
    }

    /**
     * 
     * @Title: getIncreaseDT
     * @Description:日期差计算 例如在某个日期增加几天后的日期 返回几天后日期
     * @param startDate
     * @param addDate
     * @return
     */
    public static String getIncreaseDT(String startDate, int addDate)
    {
        String strResult = "0000-00-00";

        try
        {
            Calendar localDate = new GregorianCalendar();
            // 把字符串型日期转换为日期
            Date tempDate = new Date();
            if (!"".equals(startDate))
            {
                SimpleDateFormat tempDateFormat = new SimpleDateFormat("yyyyMMdd");
                tempDate = tempDateFormat.parse(startDate);
            }
            localDate.setTime(tempDate);
            localDate.add(Calendar.DATE, addDate);
            String curyear = "" + localDate.get(Calendar.YEAR);
            int intmonth = localDate.get(Calendar.MONTH) + 1;
            String curmonth = "" + intmonth;
            String curday = "" + localDate.get(Calendar.DAY_OF_MONTH);

            if (curmonth.length() == 1)
            {
                curmonth = "0" + curmonth;
            }
            if (curday.length() == 1)
            {
                curday = "0" + curday;
            }
            strResult = curyear + "" + curmonth + "" + curday;
        }
        catch (Exception e)
        {
            strResult = "";
        }
        return strResult;
    }

    /**
     * 获取本周的开始日期 （星期天的日期）20070201
     * 
     * @return
     */
    public static String getWeekStartDate()
    {
        String strResult = "19000101";
        try
        {
            Calendar calendar = Calendar.getInstance();
            int intWeekNum = calendar.get(Calendar.DAY_OF_WEEK);
            intWeekNum = intWeekNum - 1;
            strResult = getIncreaseDT(getTodayChar8(), -intWeekNum);
        }
        catch (Exception ex)
        {
            strResult = "19000101";
        }
        return strResult;
    }

    /**
     * 
     * @Title: getWeekChina
     * @Description: 获取今天星期几 中文
     * @return
     * @return String 返回类型
     */
    public static String getWeekChina()
    {
        String strResult = " ";
        try
        {
            Calendar calendar = Calendar.getInstance();
            int intWeekNum = calendar.get(Calendar.DAY_OF_WEEK);
            intWeekNum = intWeekNum - 1;
            strResult = FORMAT_WEEK[intWeekNum];
        }
        catch (Exception ex)
        {
            strResult = " ";
        }
        return strResult;
    }

    private static SimpleDateFormat dateFormat    = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat dateFormat_1  = new SimpleDateFormat("yyyyMM");
    private static SimpleDateFormat dateFormat_2  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat dateFormat_3  = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat dateFormat_4  = new SimpleDateFormat("yyyy年MM月");
    private static SimpleDateFormat dateFormat_5  = new SimpleDateFormat("yyyy年MM月dd日");
    private static SimpleDateFormat dateFormat_6  = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat dateFormat_7  = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat dateFormat_8  = new SimpleDateFormat("yyyyMMddHHmm");
    private static SimpleDateFormat dateFormat_9  = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat dateFormat_10 = new SimpleDateFormat("yyyy-MM");
    private static SimpleDateFormat dateFormat_11 = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat dateFormat_12 = new SimpleDateFormat("HHmmss");
    private static SimpleDateFormat dateFormat_13 = new SimpleDateFormat("yyyy年MM月dd日HH:mm");
    private static SimpleDateFormat dateFormat_14 = new SimpleDateFormat("yyyyMMddHHmmssSss");

    public static String formatDateStrToOtherStr20(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat_10.format(dateFormat_1.parse(time));
        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    public static String formatDateStrToOtherStr(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat_2.format(dateFormat.parse(time));

        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    public static String formatDateStrToOtherStr7(String time)
    {
        String reTime = "";
        try
        {
            if (time.length() == 14)
            {
                reTime = dateFormat_2.format(dateFormat.parse(time));

            }
            else if (time.length() == 12)
            {
                reTime = dateFormat_9.format(dateFormat_8.parse(time));
            }
        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    public static String formatDateStrToOtherStr3(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat_3.format(dateFormat.parse(time));
        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    public static String formatDateStr2ToOtherStr(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat.format(dateFormat_2.parse(time));
        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    public static String formatDateStr2ToOtherStr17(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat_14.format(dateFormat_2.parse(time));
        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    public static String formatDateStrToOtherStr4(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat_4.format(dateFormat_1.parse(time));
        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    public static String formatDateStrToOtherStr5(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat_5.format(dateFormat_6.parse(time));
        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    public static String formatDateStrToOtherStr6(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat_5.format(dateFormat_7.parse(time));
        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    public static String formatDateStrToOtherStr8(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat_6.format(dateFormat_6.parse(time));
        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    public static String formatDateStrToOtherStr12(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat_11.format(dateFormat_12.parse(time));
        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    /**
     * 
     * @Title: formatDateStrToOtherStr9
     * @Description: 将 "2012-5-1" 转换成 "20120501"
     * @Param: @param time
     * @Param: @return
     * @Return: String
     * @Comment:
     * @Author: Administrator
     * @CreateDate: Apr 24, 2012 10:09:38 AM
     */
    public static String formatDateStrToOtherStr9(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat_6.format(dateFormat_3.parse(time));
        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    public static String formatDateStrToOtherStr14(String time)
    {

        String reTime = "";
        try
        {
            reTime = dateFormat_13.format(dateFormat_7.parse(time));
        }
        catch (ParseException e)
        {
        }
        return reTime;

    }

    /**
     * 
     * 
     * @Title: formatDateStrToOtherStr10
     * @Description: 将 "20120501" 转换成 "2012-5-1"
     * @Param: @param time
     * @Param: @return
     * @Return: String
     * @Comment:
     * @Author: Administrator
     * @CreateDate: Apr 29, 2012 3:45:54 PM
     */
    public static String formatDateStrToOtherStr10(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat_3.format(dateFormat_6.parse(time));
        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    /**
     * 
     * 
     * @Title: formatDateStrToOtherStr11
     * @Description: 将 yyyy-MM-dd HH:mm:ss 转换成 yyyy-MM-dd
     * @Param: @param time
     * @Param: @return
     * @Return: String
     * @Comment:
     * @Author: Administrator
     * @CreateDate: May 4, 2012 10:52:59 AM
     */
    public static String formatDateStrToOtherStr11(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat_3.format(dateFormat_2.parse(time));
        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    /**
     * 
     * @Title: formatDateStrToStr8 @Description: 将 yyyyMMddHHmmss 转换成
     *         yyyyMMdd @param time @return @return String 返回类型 @throws
     */
    public static String formatDateStrToStr8(String time)
    {
        String reTime = "";
        try
        {
            reTime = dateFormat_6.format(dateFormat.parse(time));

        }
        catch (ParseException e)
        {
        }
        return reTime;
    }

    /**
     * 
     * @Title: getDayChar8EnByDays
     * @Description: 根据传入的天数获取日期
     * @param days
     * @return
     * @return String 返回类型
     */
    public static String getDayChar8EnByDays(int days)
    {
        GregorianCalendar gerCal = new GregorianCalendar();
        gerCal.setTime(new Date());
        gerCal.add(GregorianCalendar.DATE, days);// 在日期上减1天
        SimpleDateFormat lastDf = new SimpleDateFormat("yyyy-MM-dd");
        return lastDf.format(gerCal.getTime());
    }

    /**
     * 
     * @Title: compareTime
     * @Description: 比较时间与当前时间先后
     * @Param: @param startTime
     * @Param: @return
     * @Return: 1 晚于当前时间 2 与当前时间相等 3 早于当前时间
     * @Comment:
     * @Author: Administrator
     * @CreateDate: Mar 29, 2012 8:34:11 PM
     */
    public static String compareTime(String startTime)
    {
        startTime = startTime.substring(0, 4) + "-" + startTime.substring(4, 6) + "-" + startTime.substring(6, 8);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try
        {
            c1.setTime(df.parse(getTodayChar8En()));
            c2.setTime(df.parse(startTime));
        }
        catch (java.text.ParseException e)
        {
            e.printStackTrace();
        }
        int result = c1.compareTo(c2);
        if (result == 0)
        {
            return "2";
        }
        else if (result < 0)
        {
            return "1";
        }
        else
        {
            return "3";
        }
    }

    /**
     * 
     * @Title: getFirstDay
     * @Description:当月第一天
     * @return 当月第一天
     * @return String 返回类型
     */
    public static String getFirstDay()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first);
        return str.toString();

    }

    /**
     * 
     * @Title: getLastDay
     * @Description: 当月最后一天
     * @return 当月最后一天
     * @return String 返回类型
     */
    public static String getLastDay()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s);
        return str.toString();

    }

    /**
     * @Title: getEndTime
     * @Description: 根据传入单位和数量，确定时间
     * @return
     * @return String 返回类型
     */
    public static String getEndTime(String unit, int scope, String beginTime)
    {
        String endTime = StringUtils.EMPTY;
        try
        {
            if ("MM".equals(unit))
            {
                GregorianCalendar gerCal = new GregorianCalendar();
                gerCal.setTime(dateFormat.parse(beginTime));
                gerCal.add(GregorianCalendar.MONTH, -scope);// 在日期上减1天
                SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMddHHmmss");
                endTime = lastDf.format(gerCal.getTime());
            }
            else if ("dd".equals(unit))
            {
                GregorianCalendar gerCal = new GregorianCalendar();
                gerCal.setTime(dateFormat.parse(beginTime));
                gerCal.add(GregorianCalendar.DATE, -scope);// 在日期上减1天
                SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMddHHmmss");
                endTime = lastDf.format(gerCal.getTime());
            }
            else if ("HH".equals(unit))
            {
                GregorianCalendar gerCal = new GregorianCalendar();
                gerCal.setTime(dateFormat.parse(beginTime));
                gerCal.add(GregorianCalendar.HOUR, -scope);// 在日期上减1天
                SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMddHHmmss");
                endTime = lastDf.format(gerCal.getTime());
            }
            else if ("mm".equals(unit))
            {
                GregorianCalendar gerCal = new GregorianCalendar();
                gerCal.setTime(dateFormat.parse(beginTime));
                gerCal.add(GregorianCalendar.MINUTE, -scope);// 在日期上减1天
                SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMddHHmmss");
                endTime = lastDf.format(gerCal.getTime());
            }
            else if ("ss".equals(unit))
            {
                GregorianCalendar gerCal = new GregorianCalendar();
                gerCal.setTime(dateFormat.parse(beginTime));
                gerCal.add(GregorianCalendar.MILLISECOND, -scope);// 在日期上减1天
                SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMddHHmmss");
                endTime = lastDf.format(gerCal.getTime());
            }
        }
        catch (Exception e)
        {
        }

        return endTime;
    }

    /**
     * @Title: getEndTimeByYMD @Description: 根据传入单位和数量，确定时间，格式为年月日 @param
     *         unit @param scope @param beginTime @return @return String
     *         返回类型 @throws
     */
    public static String getEndTimeByYMD(String unit, int scope, String beginTime)
    {
        String endTime = StringUtils.EMPTY;

        try
        {
            if ("MM".equals(unit))
            {
                GregorianCalendar gerCal = new GregorianCalendar();
                gerCal.setTime(dateFormat_6.parse(beginTime));
                gerCal.add(GregorianCalendar.MONTH, -scope);// 在日期上减1天
                SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMdd");
                endTime = lastDf.format(gerCal.getTime());
            }
            else if ("dd".equals(unit))
            {
                GregorianCalendar gerCal = new GregorianCalendar();
                gerCal.setTime(dateFormat_6.parse(beginTime));
                gerCal.add(GregorianCalendar.DATE, -scope);// 在日期上减1天
                SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMdd");
                endTime = lastDf.format(gerCal.getTime());
            }
        }
        catch (Exception e)
        {
        }

        return endTime;
    }

    /**
     * 
     * @Title: getWeekNumber
     * @Description:获取今天是本周的第几天（数字）
     * @return
     * @return int 返回类型
     */
    public static int getWeekNumber()
    {
        int strResult = 0;
        try
        {
            Calendar calendar = Calendar.getInstance();
            int intWeekNum = calendar.get(Calendar.DAY_OF_WEEK);
            intWeekNum = intWeekNum - 1;
            if (intWeekNum == 0)
            {
                intWeekNum = 7;
            }
            strResult = intWeekNum;
        }
        catch (Exception ex)
        {
            strResult = 0;
        }
        return strResult;
    }

    /**
     * 
     * @Title: getTodayHours
     * @Description:获取当前小时（0~23）
     * @return
     * @return int 返回类型
     */
    public static int getTodayHours()
    {
        int result = 0;
        Calendar calendar = Calendar.getInstance();
        result = calendar.get(Calendar.HOUR_OF_DAY);
        return result;
    }

    /**
     * 
     * @Title: getStartTimeAndEndTimeBetweenTime
     * @Description: 获取两个日期之间的所有日期
     * @param startTime
     * @param endTime
     * @return
     * @return List<String> 返回类型
     */
    public static List<String> getStartTimeAndEndTimeBetweenDays(String startTime, String endTime, String format)
    {
        SimpleDateFormat formatTime = new SimpleDateFormat(format);
        List<String> list = new ArrayList<String>();
        if (startTime.equals(endTime))// 开始时间和结束时间相等 就返回开始时间
            list.add(startTime);
        else
        {
            try
            {
                list.add(startTime);
                String tmp = formatTime.format(formatTime.parse(startTime).getTime() + 3600 * 24 * 1000);
                while (tmp.compareTo(endTime) <= 0)
                {
                    list.add(tmp);
                    tmp = formatTime.format(formatTime.parse(tmp).getTime() + 3600 * 24 * 1000);
                }
                // list.add(endTime);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 
     * @Title: getStartTimeAndEndTimeBetweenMonths
     * @Description: 获取俩个时间之间所有的月份
     * @param startTime
     * @param endTime
     * @param format
     * @return
     * @return List<String> 返回类型
     */
    public static List<String> getStartTimeAndEndTimeBetweenMonths(String startTime, String endTime, String format)
    {
        SimpleDateFormat formatTime = new SimpleDateFormat(format);
        List<String> list = new ArrayList<String>();
        try
        {
            Date startDate = formatTime.parse(startTime);
            Date endDate = formatTime.parse(endTime); // 结束日期
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);
            String result = null;
            while (startCalendar.compareTo(endCalendar) <= 0)
            {
                startDate = startCalendar.getTime();
                result = new SimpleDateFormat(format).format(startDate);
                // result = result.substring(0, result.length());
                list.add(result);
                // 开始日期加一个月直到等于结束日期为止
                startCalendar.add(Calendar.MONTH, 1);
            }

        }
        catch (ParseException e)
        {
            e.printStackTrace();
        } // 开始日期

        return list;
    }

    /**
     * 
     * @Title: getStartTimeAndEndTimeNumbers @Description:
     *         获取两个时间段内月份只差 @return @return int 返回类型 @throws
     */
    public static int getStartTimeAndEndTimeNumbers(String startTime, String endTime, String format)
    {
        SimpleDateFormat formatTime = new SimpleDateFormat(format);
        try
        {
            Date startDate = formatTime.parse(startTime);
            Date endDate = formatTime.parse(endTime); // 结束日期
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);
            return endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 
     * @Title: getDayChar8ByDays
     * @Description: 根据天数加减当前日期并返回yyyyMMdd的日期格式
     * @param days
     * @return
     * @return String 返回类型
     */
    public static String getDayChar8ByDays(int days)
    {
        GregorianCalendar gerCal = new GregorianCalendar();
        gerCal.setTime(new Date());
        gerCal.add(GregorianCalendar.DATE, days);// 在日期上加减天数
        SimpleDateFormat lastDf = new SimpleDateFormat("yyyyMMdd");
        return lastDf.format(gerCal.getTime());
    }

    /**
     * 
     * @Title: getDayChar8ByDays
     * @Description: 根据天数加减当前日期并返回yyyyMMdd的日期格式
     * @param days
     * @return
     * @return String 返回类型
     */
    public static String getDayChar11ByDays(int days)
    {
        GregorianCalendar gerCal = new GregorianCalendar();
        gerCal.setTime(new Date());
        gerCal.add(GregorianCalendar.DATE, days);// 在日期上加减天数
        SimpleDateFormat lastDf = new SimpleDateFormat("yyyy-MM-dd");
        return lastDf.format(gerCal.getTime());
    }

    /**
     * 
     * @Title: daysBetween @Description: 字符串的日期格式的计算 @param startDate @param
     *         endDate @return @throws ParseException @return int 返回类型 @throws
     */
    public static int daysBetween(String startDate, String endDate) throws ParseException
    {
        // 日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();

        // 设置开始日期
        cal.setTime(sdf.parse(startDate));
        long startTime = cal.getTimeInMillis();

        // 设置结束日期
        cal.setTime(sdf.parse(endDate));
        long endTime = cal.getTimeInMillis();
        long between_days = (endTime - startTime) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days)) + 1;
    }

    /**
     * 
     * @Title: isNowBetweenDays @Description: 当前日期是否在指定时间内 @param
     *         startDate @param endDate @return @throws ParseException @return
     *         boolean 返回类型 @throws
     */
    public static boolean isNowBetweenDays(String startDate, String endDate) throws ParseException
    {
        String today = getTodayChar8();
        System.out.println(startDate.compareTo(today));
        System.out.println(endDate.compareTo(today));
        if (startDate.compareTo(today) == 0 || endDate.compareTo(today) == 0)
        {
            return true;
        }

        if (startDate.compareTo(today) < 0 && endDate.compareTo(today) > 0)
        {
            return true;
        }

        return false;
    }

    /**
     * 
     * @Title: getDistanceDT
     * @Description: 计算年龄
     * @param startDateTime
     *            开始时间
     * @param distanceType
     *            计算间隔类型 年y 年月ym
     * @return
     */
    public static String getDistanceDate(String startDateTime, String distanceType)
    {
        String strResult = StringUtils.EMPTY;
        long lngDistancVal = 0;

        try
        {
            SimpleDateFormat tempDateFormat = new SimpleDateFormat("yyyyMMdd");
            Date startDate = tempDateFormat.parse(startDateTime);
            Date endDate = new Date();

            lngDistancVal = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24) + 1;

            // 开始时间大于当前时间，直接返回0
            if (lngDistancVal < 0)
            {
                if (distanceType.equals("y"))
                {
                    return "0";
                }
                else if (distanceType.equals("ym"))
                {
                    return "0个月";
                }
            }

            String year = new DecimalFormat("#.00").format(lngDistancVal / 365f);
            String age[] = year.split("\\.");

            if (distanceType.equals("y"))
            {
                strResult = age[0].length() < 1 ? "0" : age[0];
            }
            else if (distanceType.equals("ym"))
            {
                strResult = age[0];

                String month = new DecimalFormat("#.00").format(((Long.parseLong(age[1]) / 100f) * 365f) / 30);
                String months[] = month.split("\\.");

                if (age[0].length() > 0)
                {
                    strResult += "年" + (months[0].length() < 1 ? "0" : months[0]) + "个月";
                }
                else
                {
                    strResult = (months[0].length() < 1 ? "不满1" : months[0]) + "个月";
                }
            }

        }
        catch (Exception e)
        {
            strResult = "0";
        }
        return strResult;
    }

    /**
     * @Title: getBetweenMonths @Description: 查询输入时间与当前时间直接的月份 @param
     *         startTime @param format @return @return int 返回类型 @throws
     */
    public static int getBetweenMonths(String startTime, String format)
    {
        SimpleDateFormat formatTime = new SimpleDateFormat(format);
        List<String> list = new ArrayList<String>();
        try
        {
            Date startDate = formatTime.parse(startTime);

            Date endDate = new Date(); // 结束日期
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);
            String result = null;
            while (startCalendar.compareTo(endCalendar) <= 0)
            {
                startDate = startCalendar.getTime();
                result = new SimpleDateFormat(format).format(startDate);
                // result = result.substring(0, result.length());
                list.add(result);
                // 开始日期加一个月直到等于结束日期为止
                startCalendar.add(Calendar.MONTH, 1);
            }

        }
        catch (ParseException e)
        {
            e.printStackTrace();
        } // 开始日期

        return list.size() - 1;
    }

    /**
     * @Title: oneYearsTime @Description: 获取去年的今日 @param fmt @return @return
     *         String 返回类型 @throws
     */
    public static String oneYearsTime(String fmt, String startDate)
    {
        Calendar cal = Calendar.getInstance();

        // 日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);

        // 设置开始日期
        try
        {
            cal.setTime(sdf.parse(startDate));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        int year = cal.get(Calendar.YEAR) - 1;// yy

        int month = cal.get(Calendar.MONTH) + 1;// MM
        int day = cal.get(Calendar.DATE);// dd

        if (fmt.indexOf("yyyy") != -1)
        {
            fmt = fmt.replaceAll("yyyy", String.valueOf(year));
        }
        if (fmt.indexOf("MM") != -1)
        {
            fmt = fmt.replaceAll("MM", month < 10 ? "0" + String.valueOf(month) : String.valueOf(month));
        }
        if (fmt.indexOf("dd") != -1)
        {
            fmt = fmt.replaceAll("dd", day < 10 ? "0" + String.valueOf(day) : String.valueOf(day));
        }
        return fmt;
    }

    /**
     * @Title: getLastMonth @Description: 查询传入日期11个月之前的月份 @param fmt @param
     *         startDate @return @return String 返回类型 @throws
     */
    public static String getLastMonth(String fmt, String startDate)
    {
        Calendar cal = Calendar.getInstance();

        // 日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

        // 设置开始日期
        try
        {
            cal.setTime(sdf.parse(startDate));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        int year = cal.get(Calendar.YEAR) - 1;// yy

        int month = cal.get(Calendar.MONTH) + 2;// MM
        int day = cal.get(Calendar.DATE);// dd

        if (fmt.indexOf("yyyy") != -1)
        {
            fmt = fmt.replaceAll("yyyy", String.valueOf(year));
        }
        if (fmt.indexOf("MM") != -1)
        {
            fmt = fmt.replaceAll("MM", month < 10 ? "0" + String.valueOf(month) : String.valueOf(month));
        }
        if (fmt.indexOf("dd") != -1)
        {
            fmt = fmt.replaceAll("dd", day < 10 ? "0" + String.valueOf(day) : String.valueOf(day));
        }
        return fmt;
    }

    /**
     * 
     * @Title: getFirstDayByMonthChar8 @Description: 获取某月的第一天 @param year @param
     *         month @return @return String 返回类型 @throws
     */
    public static String getFirstDayByMonthChar8(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        // 就是取当月的第一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);

        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = cal.getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String s = df.format(firstDate);
        StringBuffer str = new StringBuffer().append(s);
        return str.toString();
    }

    /**
     * 
     * @Title: getSecondDayByMonthChar8 @Description: 获取某月的第二天 @param
     *         year @param month @return @return String 返回类型 @throws
     */
    public static String getSecondDayByMonthChar8(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        // 就是取当月的第一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);

        cal.set(Calendar.DAY_OF_MONTH, 2);
        Date firstDate = cal.getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String s = df.format(firstDate);
        StringBuffer str = new StringBuffer().append(s);
        return str.toString();
    }

    /**
     * 
     * @Title: getLastDayPlusOneByMonthChar8 @Description: 获取某月的最后一天再加一天 @param
     *         year @param month @return @return String 返回类型 @throws
     */
    public static String getLastDayPlusOneByMonthChar8(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        // 就是取当月的最后一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date lastDate = cal.getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String s = df.format(lastDate);
        StringBuffer str = new StringBuffer().append(s);
        return str.toString();
    }

    /**
     * 
     * @Title: getLastDayByMonth @Description: 获取某月的最后一天 @param year @param
     *         month @return @return String 返回类型 @throws
     */
    public static String getLastDayByMonthChar8(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        // 就是取当月的最后一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String s = df.format(lastDate);
        StringBuffer str = new StringBuffer().append(s);
        return str.toString();
    }

    /**
     * 
     * @Title: getTimeByMonthChar8 @Description:
     *         获取其它月份下与当前时间相同的时间[20150909010101----》month is
     *         -3----》20150609010101] @param month @return @return String
     *         返回类型 @throws
     */
    public static String getTimeByMonthChar8(int month)
    {
        Calendar cal = Calendar.getInstance();
        // 就是取当月的最后一天
        // cal.set(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        Date lastDate = cal.getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = df.format(lastDate);
        StringBuffer str = new StringBuffer().append(s);
        return str.toString();
    }

    /**
     * 
     * @Title: compareTime
     * @Description: 比较时间
     * @Param: @param startTime
     * @Param: @return
     * @Return: 0:相等 1:小于 2:大于
     * @Comment:
     * @Author: Administrator
     * @CreateDate: Mar 29, 2012 8:34:11 PM
     */
    public static int compareTime(String startTime, String endTime)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try
        {
            c1.setTime(dateFormat.parse(startTime));
            c2.setTime(dateFormat.parse(endTime));
        }
        catch (java.text.ParseException e)
        {
            e.printStackTrace();
        }

        return c1.compareTo(c2);
    }

    /**
     * 
     * @Title: compareDay
     * @Description: 比较时间
     * @Param: @param startTime
     * @Param: @return
     * @Return: 0:相等 1:小于 2:大于
     * @Comment:
     * @Author: Administrator
     * @CreateDate: Mar 29, 2012 8:34:11 PM
     */
    public static int compareDay(String startTime, String endTime)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try
        {
            c1.setTime(dateFormat.parse(startTime));
            c2.setTime(dateFormat.parse(endTime));
        }
        catch (java.text.ParseException e)
        {
            e.printStackTrace();
        }

        return c1.compareTo(c2);
    }

    /**
     * 
     * @Title: getLastMonthChar6ByTime @Description: 获取某月的第二天 @param year @param
     *         month @return @return String 返回类型 @throws
     */
    public static String getNextMonthChar6ByTime(int year, int month)
    {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.add(Calendar.MONTH, +1);
        return format.format(calendar.getTime()).toString();
    }

    /**
     * 
     * @Description: 每日不重复的微信订单号
     * @Author: WUXX
     * @CreateDate:20160604
     */
    public static String getWxMch_billno()
    {
        String wxMch_billno = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String cuTime = dateFormat.format(new Date());
        Random random = new Random();
        String randomresult = "";

        for (int i = 0; i < 8; i++)
        {
            randomresult += random.nextInt(10);
        }
        wxMch_billno = cuTime + randomresult;
        return wxMch_billno;
    }

    /**
     * 获得指定日期的前一天
     * 
     * @param specifiedDay
     *            格式 2017-01-01
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay)
    {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try
        {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     * 
     * @param specifiedDay
     *            格式 2017-01-01
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay)
    {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try
        {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    /**
     * 指定日期 减去指定天数 获取新的日期
     * 
     * @param days
     *            减少的天数
     * @param dataTime
     *            指定的日期 格式 2017-01-01
     * @return
     */
    public static String getsubtractDayDate(String days, String dataTime)
    {
        String remindTime = null;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dataTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(calendar.DATE, calendar.get(calendar.DATE) - Integer.parseInt(days));
            // 返回减去后的最终时间
            remindTime = sdf.format(calendar.getTime());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return remindTime;
    }

    /**
     * 指定日期 加上指定天数 获取新的日期
     * 
     * @param days
     *            加上的天数
     * @param dataTime
     *            指定的日期 格式 20170101
     * @return
     */
    public static String getAddDayDate8(int days, String dataTime)
    {
        String remindTime = null;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = sdf.parse(dataTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + days);
            // 返回减去后的最终时间
            remindTime = sdf.format(calendar.getTime());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return remindTime;
    }

    /**
     * 
     * @Title: getSixDayDate @Description: 获取1月1号到传入时间的7个日期节点 @param days @param
     *         dataTime @return @return List<String> 返回类型 @throws
     */
    public static List<String> getSevenDayDate(String dataTime)
    {
        List<String> timeList = new ArrayList<String>();
        String time = null;
        try
        {
            String beginTime = dataTime.substring(0, 4).concat("0101");
            timeList.add(beginTime);
            time = dataTime.substring(0, 4).concat("-01-01");
            time = getSpecifiedDayBefore(time).replace("-", "");
            // 计算1月1号到传入日期的天数
            int daysCount = daysBetween(beginTime, dataTime);
            // 余数
            int remainder = daysCount % 6;
            // 均数
            int dayAverage = daysCount / 6;
            for (int i = 0; i < 6; i++)
            {
                if (remainder > 0)
                {
                    time = getAddDayDate8(dayAverage + 1, time);
                }
                else
                {
                    time = getAddDayDate8(dayAverage, time);
                }
                remainder = remainder - 1;
                timeList.add(time);
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return timeList;
    }

    /**
     * 获取本周一的日期
     * 
     * @return
     */
    public static String getWeekStartDate1()
    {
        String strResult = null;
        try
        {
            strResult = getSpecifiedDayAfter(dateFormat_3.format(dateFormat_6.parse(getWeekStartDate()))).replace("-", "");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return strResult;
    }

    /**
     * @Description: 获取N年后的时间 @return String @throws
     */
    public static String getFewYearLater(int year)
    {
        String remindTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, year);
        Date y = calendar.getTime();
        remindTime = sdf.format(y);
        return remindTime;
    }

    public static void main(String[] args) throws ParseException
    {
        System.out.println(getTodayChar17En().length());
        System.out.println(getFewYearLater(20));
        System.out.println(formatDateStr2ToOtherStr17("2018-10-18 17:25:27"));

    }
}
