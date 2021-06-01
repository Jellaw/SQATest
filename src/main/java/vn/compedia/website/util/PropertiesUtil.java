package vn.compedia.website.util;

import java.util.Properties;

public class PropertiesUtil {
    private static Properties props;

    public static String getProperty(String name) {
        try {
            if (props == null) {
                props = new Properties();
                props.load(PropertiesUtil.class.getResourceAsStream("/application.properties"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props.getProperty(name);
    }

}
