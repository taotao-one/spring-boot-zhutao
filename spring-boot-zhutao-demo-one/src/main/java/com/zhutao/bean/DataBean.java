package com.zhutao.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataBean {

    // Alt + 7 查看类的方法

    // 地区  累计确诊人数  现有确诊人数   死亡人数  治愈人数
    private String area;
    private int confirm;
    private int nowConfirm;
    private int dead;
    private int heal;
}
