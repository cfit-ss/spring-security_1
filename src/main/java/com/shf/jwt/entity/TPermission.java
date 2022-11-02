package com.shf.jwt.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 描述：
 *
 * @Author shengs
 * @Date 2022/10/31 10:54
 * @Version V1.0
 **/
@Data
@Entity
@Table(name = "t_permission")
public class TPermission {
    @Id
    private long id;

    private String name;

    private String permission;

    private String url;
}
