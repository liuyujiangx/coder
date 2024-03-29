package com.yujl.coder.util;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Velocity工具类,根据模板内容生成文件
 * @author liuyj
 */
public class VelocityUtil {

    public static String generate(VelocityContext context, String template) {
        StringReader reader = new StringReader(template);
        StringWriter writer = new StringWriter();
        // 不用vm文件
        Velocity.evaluate(context, writer, "generator", reader);

        try {
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }
}
