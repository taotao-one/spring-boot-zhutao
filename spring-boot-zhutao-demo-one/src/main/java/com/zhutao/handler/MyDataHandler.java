package com.zhutao.handler;


import com.google.gson.Gson;
import com.zhutao.bean.DataBean;
import com.zhutao.service.DataService;
import com.zhutao.util.HttpUrlConnectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class MyDataHandler {
    @Autowired
    private DataService dataService;
    //数据初始化

    // 在服务器加载Servlet 时运行 且  只运行一次
    @PostConstruct
    public void saveData() throws InterruptedException {
        List<DataBean> dataBeans = getData();
        //mybatis-plus 提供了可用的方法
        //删除全部数据  批量新增数据
        dataService.remove(null);
        dataService.saveBatch(dataBeans);
    }

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(cron = "0 1 * * * ? ")
    public void update(){
        System.out.println("数据更新了");
        System.out.println("当前时间"+dateFormat.format(new Date()));

        List<DataBean> dataBeans = getData();
        //mybatis-plus 提供了可用的方法
        //删除全部数据  批量新增数据
        dataService.remove(null);
        dataService.saveBatch(dataBeans);
    }


    public static String testStr = "{\"name\":\"duyi\"}";
    private static String str = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
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
//            FileReader fr = new FileReader("spring-boot-zhutao-demo-one/tmp.txt");
//            char[] cBuf = new char[1024];
//            int cRead = 0;
//
//            StringBuilder builder = new StringBuilder();
//            while ((cRead = fr.read(cBuf)) > 0) {
//                builder.append(new String(cBuf, 0, cRead));
//            }
//            fr.close();

            String results = HttpUrlConnectionUtil.doGet(str);

//          System.out.println(builder.toString());

            // 再通过gson 解析数据
            Gson gson = new Gson();
            Map strMap = gson.fromJson(results,Map.class);

            String subStr =(String) strMap.get("data");

            Map map = gson.fromJson(subStr.toString(), Map.class);
//          System.out.println(map);

            ArrayList areaList = (ArrayList) map.get("areaTree");

            Map dataMap = (Map) areaList.get(0);

            ArrayList childrenList = (ArrayList) dataMap.get("children");

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
