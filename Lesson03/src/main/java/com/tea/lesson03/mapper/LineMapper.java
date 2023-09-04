package com.tea.lesson03.mapper;

import com.tea.lesson03.pojo.Line;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/3/25 10:38
 */
@Mapper
@Repository
public interface LineMapper {
    public List<Line> select(Map map);
}
