package com.itheima.reggie.utils;
import java.io.FileWriter;
import java.io.IOException;
public class AppUtil {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000122682752";
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCs+oFcEto+27pYMk/Tf1Hyc2sKnBllP044/KJInI8y9f//XS5FSMO30p4Y67yIGgSI2AiYvoNZMYxbbKrJ1T1FCTcqTKKbU/IJKu68w+q6yNYs9NLvoyKh1WJLVDVcYsfzcDtIWSLgBrRVW/XCVfPEjS4dXTBCVJWN1u2crJK32cyANz0aYnzgWwWagE1dUOlz25M59q1PcBI5l8XkTsF4oTtKu0vle3kOLcCJ0cz/zGVg9iEiI/Fcwnzk5ygPk3PFh/xH7Ls09sdJIC8CUxtIs1TYPniU1c/x/5LF7Gx8y+TgoCwNtj/S3IkTLZK0QT3tNF2/hwbRKvVPKjgVdfRzAgMBAAECggEAbHljaeQArO6J+wkB+jWbpBkxBSRC4LVEToRPGI3lVyXhdbhvbc9lvP79Wc9rGUaHlXE0YuUF5Ycm56ZLq+pnU8WlrIcGabXI0Gr177ZEmwGbHfIh3VlGPIpd67NRCzB/B3BYHNSZBDfwGp9ijI3uIlIOmyd3PgFhD+O6ekfWcifAjtiYg9mL/LIdS8kxkhIzqXHmns9lYcb6CYMf1tYQ5LEY+VfIWVq4WO//O0pVNYxMN+KzjoUZr3G4U89IO2PR8Bwten1kQZ3nmX2Lc63B1cpMg/1qvbgVTtIu2ENa2PkMyNw+COi+A9V6Mr9BE1umltZwa/iKZZ9BKfGHnYeQMQKBgQDg1KOeFYUoS5YdBsP0rroZs+v95Up8wuFMBZdz8Q2P16WM+w1A87smNavNFtnfHArFeycyRQGCCw0DYne3aeHkQlw9TESf5vMYnPY9cxl0qx1ph0QjDbhjhd5YiHipjwvzjBTxuaztm9e0g3GAMSv3u7bIjG4kjmL8z1goTN6MNQKBgQDE9ZuGBoI2oWnXIvANHb21B4weNxJ+Z4e8ke1zQ2KiBTuhSjTA2XBh30uLs/1Ya0Pv4IhpYYJpAxPYeuAvVowkD/VU+TjQgB6b5uvVnvbw7jaQRXExCyo2JksTevf76OEbg4EU2OvdV7MOPaCQ63oSig+LsQq3fc83ZHVaDT2DBwKBgQCfwll1VqNlC7jibVFHB/WTmAcERi8U0kHXjE2fhFV3teucbzRuyAJfV1n6BNfSMxHwW99xWBmHN6UKl6ir+yAZ25HBuPU2jpyFC9vptGUNQx5GQ2kGY5R3m8NwM6FSEySR7iNf1OltegTf71rylX5BrfBnN2p4rXjnXcqt8FlfVQKBgE68fLLEcVOTjXMilWI47HitDreZfnwnuRyKHamBW7G3c9ITjyk1A3csw8+SYYnyP5NqaD3wbCi8wTc+T9a8u4NDGekhRDQds57RXp/kT4WbRRbBm/FmLDa/ci3Ub8Q1oZ/VH0oNOaUfhQYBXunwKLm2lWZbnRQjaC4Vq9AXEOsLAoGBANcDlTyPlKL3uCH+WV+GbgVX6m8tjeCwNaD8TVDSbQ4EpBDVa7QVFYrKX75am65hp6LvDOx50HNLxLRgY9MDxQSKqP+6X0wWHKJPxrEn2kCVcxceZ4gS3rr+4c3t+C9Vnf9vY4CQO+drcV6CvofyELPfPipk284TmIRQNeng4znM";
    // 支付宝公钥
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlypvqW6oUTor6AXRahMuKkNIKFjIRgN4E60CuBq186vSesNrnyuqoRW0WsnZYxkCo4TMPv9t1X9penmMoWf6R69mFTVc+gRRta4r+MnxXhQUpyXX4UaTse/1yqeLUObQx1/jqgtZQwcFXwCnPKe1UNpAfQCeADzyaYh3K/uzPXJleuGhIyNsJojdwdtulPXyweCxH7/cMhMr5XMOWHJXjhdArW0Nj2VuP5PIsUrS2SWk01stwf3AsxBOHb7ZryB7CvcjzPjhKDlv+yfbm8YH0KSzZi1FGQlDIyLxFDuYmbRmu4jZGi4jkW3ghL3xw5+wjrCjF5OvXiYZbo/1QKQ8FQIDAQAB";
    // 服务器异步通知页面路径
    //需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "https://d737556j88.goho.co/getnotify";
    // 页面跳转同步通知页面路径
    //需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "https://d737556j88.goho.co/front/page/pay-success.html";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关,注意这些使用的是沙箱的支付宝网关，与正常网关的区别是多了dev
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // 支付宝网关
    public static String log_path = "D:\\";
    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" +
                    System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}