package com.tea.lesson03.service;

import com.tea.lesson03.pojo.Line;
import com.tea.lesson03.pojo.Station;

import java.util.List;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/3/25 10:39
 */
public interface LineService {
    public List<Line> selectLineIdByStationId(String stationId);

    public Line selectLineByLineId(String lineId);

    public List<Line> selectAllLines();
}
