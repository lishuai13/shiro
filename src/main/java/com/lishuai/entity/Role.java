package com.lishuai.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色
 * @author lishuai
 */
@Data
@TableName("role")
public class Role {
    private int id;
    private String name;
}
