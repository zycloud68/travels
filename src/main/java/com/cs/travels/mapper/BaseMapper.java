package com.cs.travels.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<T, K> {
    // 添加身份信息
    void add(T t);

    // 更新省份信息
    void updateProvinceInfo(T t);

    // 删除省份信息
    void deleteProvinceInfo(K k);

    // 查询一条省份信息
    T findOne(K k);

    // 查询所有省份信息
    List<T> findAll();

    // 通过页面查询所有信息
    List<T> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    // 查询省份信息的总条数
    Integer findTotals();


}
