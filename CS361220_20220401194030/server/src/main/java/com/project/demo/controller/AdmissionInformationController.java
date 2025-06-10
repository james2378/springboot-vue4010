package com.project.demo.controller;

import com.project.demo.entity.AdmissionInformation;
import com.project.demo.service.AdmissionInformationService;
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
 *准考证信息：(AdmissionInformation)表控制层
 *
 */
@RestController
@RequestMapping("/admission_information")
public class AdmissionInformationController extends BaseController<AdmissionInformation,AdmissionInformationService> {

    /**
     *准考证信息对象
     */
    @Autowired
    public AdmissionInformationController(AdmissionInformationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapexaminee_number_ = new HashMap<>();
        mapexaminee_number_.put("examinee_number_",String.valueOf(paramMap.get("examinee_number_")));
        List listexaminee_number_ = service.select(mapexaminee_number_, new HashMap<>()).getResultList();
        if (listexaminee_number_.size()>0){
            return error(30000, "字段考生考号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
