package com.zhutao.handler;

import com.google.gson.Gson;
import com.zhutao.bean.DataBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHandler {


    //    public  String testStr = "{\"confirm\":110247,\"heal\":98840,\"dead\":4924,\"nowConfirm\":6483,\"suspect\":3,\"nowSevere\":4,\"importedCase\":6019,\"noInfect\":405,\"showLocalConfirm\":1,\"showlocalinfeciton\":1,\"localConfirm\":24,\"noInfectH5\":32,\"localConfirmH5\":25}";
    public static ArrayList<DataBean> dataBeans = new ArrayList<>();

    public static void main(String[] args) {
//        Gson gson = new Gson();
//        Map map = gson.fromJson(testStr, Map.class);
//        System.out.println(map);
        getData();
//        System.out.println(ClassLoader.getSystemResource(""));
//        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
//        System.out.println(System.getProperty("user.dir"));
    }

    //从文件中读取json数据  然后使用gson转换成需要的格式

    public static List<DataBean> getData() {
        char[] chars = new char[1];
        try {
            FileReader fileReader = new FileReader("spring-boot-zhutao-one/tmp.txt");
            char[] cBuf = new char[1024];
            int cRead = 0;
            StringBuilder builder = new StringBuilder();

            while ((cRead = fileReader.read(cBuf)) > 0) {
                builder.append(new String(cBuf, 0, cRead));
            }

            Gson gson = new Gson();

            Map map = gson.fromJson(builder.toString(),Map.class);

            System.out.println(map);

            ArrayList arrayList = (ArrayList)map.get("areaTree");

            Map dataMap =(Map) arrayList.get(0);

            ArrayList childrenList = (ArrayList) dataMap.get("children");

            for (int i = 0; i < childrenList.size(); i++) {

                Map tmp = (Map)childrenList.get(i);

                String name = (String)tmp.get("name");

                Map total = (Map)tmp.get("total");

                double confirm = (double)total.get("confirm");

                double nowConfirm = (double)total.get("nowConfirm");

                double dead = (double)total.get("dead");

                double heal = (double)total.get("heal");

                dataBeans.add(new DataBean(name,(int)confirm,(int)nowConfirm,(int)dead,(int)heal));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return dataBeans;
    }

}
