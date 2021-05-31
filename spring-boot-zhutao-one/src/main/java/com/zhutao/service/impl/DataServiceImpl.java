package com.zhutao.service.impl;

import com.zhutao.bean.DataBean;
import com.zhutao.handler.DataHandler;
import com.zhutao.service.DataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {
    @Override
    public List<DataBean> list() {
        return DataHandler.getData();
    }
}
