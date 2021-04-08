package com.lishuai.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author lishuai
 */
@Data
@TableName(value = "user")
public class User {

    @TableId
    private int id;
    private String username;
    private String password;
    private String salt;

    @TableField(exist = false)
    private List<Role>roles;
}
