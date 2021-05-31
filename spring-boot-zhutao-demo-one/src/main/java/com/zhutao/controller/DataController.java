package com.zhutao.controller;

import com.zhutao.bean.DataBean;
import com.zhutao.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/")
    public String list(Model model) {
        List<DataBean> beanList = dataService.list();
        model.addAttribute("beanList", beanList);
        return "list";
    }
}
