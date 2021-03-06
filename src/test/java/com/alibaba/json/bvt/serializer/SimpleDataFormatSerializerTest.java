package com.alibaba.json.bvt.serializer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Assert;
import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

public class SimpleDataFormatSerializerTest extends TestCase {

    private static SerializeConfig mapping = new SerializeConfig();
    static {
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
    }
    
    protected void setUp() throws Exception {
        JSON.defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
        JSON.defaultLocale = Locale.CHINA;
    }
    
    public void test_0() throws Exception {
        Date date = new Date();
        String text = JSON.toJSONString(date, mapping);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", JSON.defaultLocale);
        format.setTimeZone(JSON.defaultTimeZone);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", JSON.defaultLocale);
        format2.setTimeZone(JSON.defaultTimeZone);
        Assert.assertEquals(JSON.toJSONString(format.format(date)), text);
        Assert.assertEquals(JSON.toJSONString(format2.format(date)), text);
    }

}
