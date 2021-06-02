package com.zhutao.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@TableName("epidemic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataBean implements Serializable {
    private static final long serialVersionUID = 675250593574236273L;


    // Alt + 7 查看类的方法

    // 地区  累计确诊人数  现有确诊人数   死亡人数  治愈人数
    private String area;
    private int confirm;
    private int nowConfirm;
    private int dead;
    private int heal;
}
