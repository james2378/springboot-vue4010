package com.project.demo.controller;

import com.project.demo.entity.OnlineRegistration;
import com.project.demo.service.OnlineRegistrationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *在线报名：(OnlineRegistration)表控制层
 *
 */
@RestController
@RequestMapping("/online_registration")
public class OnlineRegistrationController extends BaseController<OnlineRegistration,OnlineRegistrationService> {

    /**
     *在线报名对象
     */
    @Autowired
    public OnlineRegistrationController(OnlineRegistrationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
