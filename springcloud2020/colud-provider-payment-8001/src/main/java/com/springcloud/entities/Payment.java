package com.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data  //getter setter 等方法
@AllArgsConstructor  //所有参数构造方法
@NoArgsConstructor   //无参
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
