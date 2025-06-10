package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *考生用户：(CandidateUser)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CandidateUser")
public class CandidateUser implements Serializable {

    //CandidateUser编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_user_id")
    private Integer candidate_user_id;
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
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
