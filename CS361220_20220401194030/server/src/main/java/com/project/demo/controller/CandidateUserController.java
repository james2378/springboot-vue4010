package com.project.demo.controller;

import com.project.demo.entity.CandidateUser;
import com.project.demo.service.CandidateUserService;
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
 *考生用户：(CandidateUser)表控制层
 *
 */
@RestController
@RequestMapping("/candidate_user")
public class CandidateUserController extends BaseController<CandidateUser,CandidateUserService> {

    /**
     *考生用户对象
     */
    @Autowired
    public CandidateUserController(CandidateUserService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapstudent_number = new HashMap<>();
        mapstudent_number.put("student_number",String.valueOf(paramMap.get("student_number")));
        List liststudent_number = service.select(mapstudent_number, new HashMap<>()).getResultList();
        if (liststudent_number.size()>0){
            return error(30000, "字段学号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
