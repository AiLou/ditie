package com.tea.lesson03.service;

import com.tea.lesson03.pojo.Line;
import com.tea.lesson03.pojo.Station;

import java.util.List;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/3/25 11:02
 */
public interface StationService {
    public Station selectStationByStationId(String stationId);

    public Station selectStationByStationName(String stationName);

    public List<Station> selectAllStation();
}
