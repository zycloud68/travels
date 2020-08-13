package com.cs.travels.service;


import com.cs.travels.entity.Province;

import java.util.List;

public interface ProvinceService {
      // 参数1：当前页面     参数2 每页显示的记录数
    List <Province> findByPage(Integer page,Integer rows);

    // 查询总条数
    Integer findTotals();

    // 删除省份信息
    void delete(String id);

    //查询一个省份详细信息
    Province findOne(String id);

    // 保存省份信息
    void add(Province province);

    // 修改省份信息
    void update(Province province);
}
