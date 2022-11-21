package com.login.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.login.service.CoreService;
import common.domain.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/22 12:13
 */


@Controller
public class ResourcesController {


    @Autowired
    CoreService coreService;


    //获取课件资源
    @RequestMapping("/documentResources")
    public String documentResources(@RequestParam(value = "pn",defaultValue ="1") Integer pn,
                                    String searchWhat,
                                    Model model,HttpSession session){

        //搜索查询分页
        if (searchWhat!=null){
            Page page = coreService.searchResources(searchWhat, pn);
            model.addAttribute("page",page);
        }
        //全部查询分页
        else {
            Page page = coreService.resources(pn);
            model.addAttribute("page", page);
        }

        //我的课件查询(老师才有)
        String teacherName = session.getAttribute("teacherName")+"";
        String accountType = session.getAttribute("accountType") + "";
        if (accountType.equals("教师账户")){
            List<Resources> resources = coreService.selectResourcesByName(teacherName);
            model.addAttribute("myResources",resources);
        }


        return "documentResources";
    }



    //下载指定资源
    @RequestMapping("/downResources/{id}")
    public ResponseEntity<byte[]> downResources(@PathVariable("id") Integer id){


        ResponseEntity<byte[]> responseEntity = coreService.downResources(id);


        return responseEntity;
    }



    //上传资源
    @RequestMapping("/uploadResources")
    public String uploadResources(MultipartFile resources, HttpSession session,Model model){


        String teacherName = session.getAttribute("teacherName")+"";
        String userName = session.getAttribute("userName")+"";
        String substring = userName.substring(userName.length() - 2, userName.length());


        if (substring.equals("老师"))
           coreService.uploadResources(resources, teacherName);
       else
          model.addAttribute("msg","您没有权限！") ;


        return "forward:/documentResources";
    }



    //删除资源
    @RequestMapping("/deleteMyResources/{id}")
    public String deleteMyResources(@PathVariable("id") Integer id, HttpSession session){


        Boolean aBoolean = coreService.deleteMyResources(id);

        return "redirect:/documentResources";
    }


}
