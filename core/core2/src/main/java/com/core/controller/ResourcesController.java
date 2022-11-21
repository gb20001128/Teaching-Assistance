package com.core.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.Service.ResourcesService;
import com.core.utils.FileUtils;
import com.core.utils.TimeUtils;
import common.domain.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/22 15:11
 */

@RestController
public class ResourcesController {

    @Autowired
    ResourcesService resourcesService;


    @RequestMapping("/resources")
    public  Page  resources(@RequestParam(value = "pn",defaultValue ="1") Integer pn) {

        //分页查询数据
        Page<Resources> resourcesPage=new Page<>(pn,5);//规定当前的页码和记录数大小

        QueryWrapper<Resources> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        //分页查询的结果
        Page<Resources> page=resourcesService.page(resourcesPage,queryWrapper);
        //请求页的页码大于总页数,就设置当前页数为总页数
        if(pn>page.getPages()){
            resourcesPage=new Page<>(page.getPages(),5);
            page=resourcesService.page(resourcesPage,queryWrapper);
        }


        return page;
    }


    @RequestMapping("/downResources/{id}")
    public ResponseEntity<byte[]> downResources(@PathVariable("id") Integer id)throws Exception{


        Resources reso = resourcesService.getById(id);

        String resourcesName = reso.getResourcesName();

        ResponseEntity<byte[]> responseEntity = FileUtils.down(resourcesName);

        return responseEntity;

    }


    //微服务之间调用,参数是MultipartFile,应该这样写,consumes和@RequestPart要注意
    @PostMapping(value = "/uploadResources",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Boolean uploadResources(@RequestPart("resources") MultipartFile resources,@RequestParam("teacherName") String teacherName){



        String fileName = FileUtils.upload(resources);
        Resources reso=new Resources();
        reso.setResourcesName(fileName);
        reso.setTeacherName(teacherName);

        String time = TimeUtils.getTimeNow();
        reso.setTime(time);

        boolean save = resourcesService.save(reso);

        return save;
    }


    @RequestMapping("/selectResourcesByName/{teacherName}")
    public List<Resources> selectResourcesByName(@PathVariable("teacherName") String teacherName){


        QueryWrapper<Resources> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_name", teacherName).orderByDesc("id");
        List<Resources> list = resourcesService.list(queryWrapper);

        return list;
    }


    @RequestMapping("/deleteMyResources/{id}")
    public Boolean deleteMyResources(@PathVariable("id") Integer id){

        Resources res = resourcesService.getById(id);
        String resourcesName = res.getResourcesName();
        FileUtils.delete1(resourcesName);

        boolean b = resourcesService.removeById(id);

        return b;
    }


    @RequestMapping("/search/{searchWhat}/{pn}")
    public Page searchResources(@PathVariable("searchWhat") String searchWhat,
                                           @PathVariable("pn") Integer pn){

        QueryWrapper<Resources> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .like("teacher_name", searchWhat)
                .or()
                .like("resources_name",searchWhat)
                .orderByDesc("id");

        //分页查询数据
        Page<Resources> resourcesPage=new Page<>(pn,5);//规定当前的页码和记录数大小


        //分页查询的结果
        Page<Resources> page=resourcesService.page(resourcesPage,queryWrapper);
        //请求页的页码大于总页数,就设置当前页数为总页数
        if(pn>page.getPages()){
            resourcesPage=new Page<>(page.getPages(),5);
            page=resourcesService.page(resourcesPage,queryWrapper);
        }


        return page;
    }


}

