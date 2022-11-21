package com.core.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/22 9:57
 */

@RestController
public class UserMangeController {




    @RequestMapping("/updatePssWord/{newPassWord}/{confirmPassWord}")
    public Boolean updatePssWord(@PathVariable("newPassWord") String newPassWord,
                                 @PathVariable("confirmPassWord") String confirmPassWord){

        return false;

    }


}
