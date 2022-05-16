package com.cream.zhxy_springboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_clazz")
@ToString
public class Clazz {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer number;
    private String introducation;
    private String headmaster;
    private String email;
    private String telephone;
    private String grade_name;
}
