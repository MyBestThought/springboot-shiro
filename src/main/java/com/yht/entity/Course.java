package com.yht.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Integer id;
    private String courseName;
    private int courseHour;
    private int courseScore;
    private String courseDetail;
}
