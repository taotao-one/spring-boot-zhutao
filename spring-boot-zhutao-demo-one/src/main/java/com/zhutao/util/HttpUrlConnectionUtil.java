package com.zhutao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUrlConnectionUtil {

    /**
     * 参数：url
     * 创建一个远程的链接对象   设置方法类型 GET
     * 设置相关参数   发送请求
     * 通过io接收数据后返回
     */

    public static String doGet(String urlStr) {
        HttpURLConnection conn = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder result = new StringBuilder();
        try {
            //创建远程url连接对象
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            //设置为GET请求
            conn.setRequestMethod("GET");
            //设置重要的参数 连接超时时间 和 读取超时时间
            //超时时间  更多被距离影响    读取时间   更多被数据量影响
            conn.setConnectTimeout(15000);

            conn.setReadTimeout(60000);
            //header参数设置  可以不设置
            conn.setRequestProperty("Accept", "application/json");
            //发送请求
            conn.connect();

            //状态码  200   302   404  500
            //如果比较时  可能出现空指针  把确定值放在前面 可以避免空指针
            if (200 == conn.getResponseCode()) {
                is = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                String line;
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
            } else {
                System.out.println("error responseCode:" + conn.getResponseCode());
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (br != null) {
                    br.close();
                }

                if (is != null) {
                    is.close();
                }
                return result.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            conn.disconnect();

        }

        return result.toString();
    }

    public static void main(String[] args) {
        String str = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
        String result = doGet(str);
        System.out.println(result);
    }

}
