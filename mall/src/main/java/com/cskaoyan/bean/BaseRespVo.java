package com.cskaoyan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRespVo<T> {
    Integer errno;
    T data;
    String errmsg;
}
