package com.example.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.apache.commons.lang3.StringUtils;



/**
 * @author lil
 *
 */
public class MoneyFormatUtil
{

	/**
     * @Title: formatMoney
     * @Description: 将价格为分转换为元，保留两位小数
     * @param moneyStr
     * @return
     * @return String 返回类型
     * @throws
     */
    public static String formatMoney(String money)
    {

        if (StringUtils.isBlank(money))
        {
            return "0.00";
        }
        else
        {
            String moneyF = "";
            BigDecimal moneyD = new BigDecimal(money).divide(new BigDecimal(100));
            // double moneyD = Double.parseDouble(money) / 100;
            money = moneyD + "";
            StringBuffer sb = new StringBuffer();
            if (StringUtils.isBlank(money))
            {
                return "0.00";
            }
            int index = money.indexOf(".");
            if (index == -1)
            {
                return money + ".00";
            }
            else
            {
                String s0 = money.substring(0, index);// 整数部分
                String s1 = money.substring(index + 1);// 小数部分
                if (s1.length() == 1)
                {// 小数点后一位
                    s1 = s1 + "0";
                }
                else if (s1.length() > 2)
                {// 如果超过3位小数，截取2位就可以了
                    s1 = s1.substring(0, 2);
                }
                sb.append(s0);
                sb.append(".");
                sb.append(s1);
                moneyF = sb.toString();
                return moneyF;
            }
        }
    }

    /**
     * @Title: formatMoney
     * @Description: 元，保留两位小数
     * @param moneyStr
     * @return
     * @return String 返回类型
     * @throws
     */
    public static String formatMoneyY(String money)
    {

        if (StringUtils.isBlank(money))
        {
            return "0.00";
        }
        else
        {
            String moneyF = "";

            StringBuffer sb = new StringBuffer();
            if (StringUtils.isBlank(money))
            {
                return "0.00";
            }
            int index = money.indexOf(".");
            if (index == -1)
            {
                return money + ".00";
            }
            else
            {
                String s0 = money.substring(0, index);// 整数部分
                String s1 = money.substring(index + 1);// 小数部分
                if (s1.length() == 1)
                {// 小数点后一位
                    s1 = s1 + "0";
                }
                else if (s1.length() > 2)
                {// 如果超过3位小数，截取2位就可以了
                    s1 = s1.substring(0, 2);
                }
                sb.append(s0);
                sb.append(".");
                sb.append(s1);
                moneyF = sb.toString();
                return moneyF;
            }
        }
    }

    /**
     * @Title: fromYuanToFen
     * @Description: 元转换为分
     * @param yuan
     * @return
     * @return String 返回类型
     * @throws
     */
    public static String fromYuanToFen(final String yuan)
    {
        String fen = "";

        if (StringUtils.isBlank(yuan))
        {
            return "0";
        }
        NumberFormat format = NumberFormat.getInstance();
        BigDecimal temp = new BigDecimal(yuan).multiply(new BigDecimal(100));
        // double temp = number.doubleValue() * 100.0;
        // 默认情况下GroupingUsed属性为true 不设置为false时,输出结果为2,012
        format.setGroupingUsed(false);
        // 设置返回数的小数部分所允许的最大位数
        format.setMaximumFractionDigits(0);
        fen = format.format(temp);

        return fen;
    }
    
}
