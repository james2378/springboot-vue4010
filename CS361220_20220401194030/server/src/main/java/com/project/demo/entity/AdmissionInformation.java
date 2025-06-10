package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *准考证信息：(AdmissionInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "AdmissionInformation")
public class AdmissionInformation implements Serializable {

    //AdmissionInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admission_information_id")
    private Integer admission_information_id;
    // 考生信息
    @Basic
    private Integer candidate_information;
    // 姓名
    @Basic
    private String full_name;
    // 性别
    @Basic
    private String gender;
    // 学号
    @Basic
    private String student_number;
    // 院系
    @Basic
    private String faculty;
    // 报考等级
    @Basic
    private String registration_level;
    // 考生考号
    @Basic
    private String examinee_number_;
    // 考场名称
    @Basic
    private String centre_name_;
    // 考试时间
    @Basic
    private Timestamp examination_time;
    // 备注信息
    @Basic
    private String remark_information;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
