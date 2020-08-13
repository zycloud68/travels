package com.cs.travels.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.regex.Pattern;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Place {
    private String id;
    private String name;
    private String picpath;
    // 在进行日期的添加的时候，应当注意格式
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hottime; //旺季时间
    private Double hotticket; // 旺季门票
    private Double dimticket;  //淡季门票
    private String placedes;    // 景点介绍
    private String provinceid;   //省份id

}
