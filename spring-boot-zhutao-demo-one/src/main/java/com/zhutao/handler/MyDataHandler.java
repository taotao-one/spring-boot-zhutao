package com.zhutao.handler;


import com.google.gson.Gson;
import com.zhutao.bean.DataBean;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyDataHandler {

    public static String testStr = "{\"name\":\"duyi\"}";

    public static void main(String[] args) {
//        Gson gson = new Gson();
//        Map map = gson.fromJson(testStr, Map.class);
//        System.out.println(map);

        // 类加载器所在的路径
//        System.out.println(ClassLoader.getSystemResource(""));
//        System.out.println(Thread.currentThread()
//                .getContextClassLoader().getResource(""));
        // 项目所在路径
//        System.out.println(System.getProperty("user.dir"));

        try {
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // 从文件中读取json数据  使用gson转化成需要的格式

    public static List<DataBean> getData() {

        List<DataBean> result = new ArrayList<>();

        try {
            // 先通过io 读取数据
            FileReader fr = new FileReader("spring-boot-zhutao-demo-one/tmp.txt");
            char[] cBuf = new char[1024];
            int cRead = 0;

            StringBuilder builder = new StringBuilder();
            while ((cRead = fr.read(cBuf)) > 0) {
                builder.append(new String(cBuf, 0, cRead));
            }
            fr.close();

//          System.out.println(builder.toString());

            // 再通过gson 解析数据
            Gson gson = new Gson();
            Map map = gson.fromJson(builder.toString(), Map.class);
//          System.out.println(map);

            ArrayList areaList = (ArrayList) map.get("areaTree");

            Map dataMap = (Map) areaList.get(0);

            ArrayList childrenList = (ArrayList) dataMap.get("children");

            System.out.println(childrenList);


            for (int i = 0; i < childrenList.size(); i++) {
                Map tmp = (Map) childrenList.get(i);
                String name = (String) tmp.get("name");

                Map totalMap = (Map) tmp.get("total");
                double confirm = (Double) totalMap.get("confirm");
                double nowConfirm = (Double) totalMap.get("nowConfirm");
                double dead = (Double) totalMap.get("dead");
                double heal = (Double) totalMap.get("heal");
//              int heal = (int) totalMap.get("heal");
                // 使用错误的类型接收时  会报错如下
                // java.lang.Double cannot be cast to java.lang.Integer

                DataBean dataBean = new DataBean(
                        name, (int) confirm, (int) nowConfirm, (int) dead, (int) heal);
                result.add(dataBean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println(result);
        return result;
    }

}
