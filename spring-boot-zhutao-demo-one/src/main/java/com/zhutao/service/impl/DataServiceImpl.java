package com.zhutao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhutao.bean.DataBean;
import com.zhutao.handler.MyDataHandler;
import com.zhutao.mapper.DataMapper;
import com.zhutao.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl extends ServiceImpl<DataMapper,DataBean> implements DataService {


}
