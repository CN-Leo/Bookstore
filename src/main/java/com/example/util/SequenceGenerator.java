package com.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class SequenceGenerator
{
    private String              prefix    = "";

    private long                min       = 100000000L;

    private long                max       = 999999999L;

    private long                counter   = 100000000L;

    private SimpleDateFormat    format14  = new SimpleDateFormat("yyyyMMddHHmmss");

    private SimpleDateFormat    format8   = new SimpleDateFormat("yyyyMMdd");

    private static final String DATE_EXP  = "%{date}";

    private static final String DATE_EXP8 = "%{date8}";

    public SequenceGenerator()
    {
        this(DATE_EXP, 100000000L, 999999999L);
    }

    public SequenceGenerator(String prefix, long min, long max)
    {
        this.prefix = prefix;
        this.min = min;
        this.max = max;
        this.counter = min;
    }

    // 获取序列号
    public synchronized String next()
    {
        String pre = this.prefix;
        if (this.prefix.contains(DATE_EXP))
        {
            String dateStr = this.format14.format(new Date());
            pre = pre.replaceFirst("\\%\\{date\\}", dateStr);
        }
        else if (this.prefix.contains(DATE_EXP8))
        {
            String dateStr = this.format8.format(new Date());
            pre = pre.replaceFirst("\\%\\{date8\\}", dateStr);
        }

        if (counter > max)
        {
            counter = min;
        }
        return pre + (counter++);
    }

}
