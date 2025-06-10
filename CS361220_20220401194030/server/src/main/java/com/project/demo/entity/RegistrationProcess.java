package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *报名流程：(RegistrationProcess)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "RegistrationProcess")
public class RegistrationProcess implements Serializable {

    //RegistrationProcess编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_process_id")
    private Integer registration_process_id;
    // 标题名称
    @Basic
    private String title_name;
    // 发布日期
    @Basic
    private Timestamp release_date;
    // 封面图片
    @Basic
    private String cover_photo_;
    // 报名准则
    @Basic
    private String registration_criteria;
    // 详细流程
    @Basic
    private String detailed_process;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
