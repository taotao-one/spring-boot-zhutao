package com.zhutao.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataBean {
    private String area;
    private int confirm;
    private int nowConfirm;
    private int dead;
    private int heal;
}