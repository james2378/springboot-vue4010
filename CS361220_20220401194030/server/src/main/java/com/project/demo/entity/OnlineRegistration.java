package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *在线报名：(OnlineRegistration)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "OnlineRegistration")
public class OnlineRegistration implements Serializable {

    //OnlineRegistration编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "online_registration_id")
    private Integer online_registration_id;
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
    // 上传照片
    @Basic
    private String upload_photos;
    // 报名状态
    @Basic
    private String registration_status;
    // 回复信息
    @Basic
    private String reply_message;
    // 支付状态
    @Basic
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @Basic
    private String pay_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
