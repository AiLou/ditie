package com.tea.lesson03.service;

import com.tea.lesson03.mapper.StationMapper;
import com.tea.lesson03.pojo.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/3/25 11:05
 */
@Service
public class StationServiceImpl implements StationService{
    private final StationMapper stationMapper;

    public StationServiceImpl(StationMapper stationMapper) {
        this.stationMapper = stationMapper;
    }

    @Override
    public Station selectStationByStationId(String stationId) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("stationId",stationId);
        Station station = this.stationMapper.select(hashMap).get(0);

        if (station != null){
            station.setLines(station.getLineId());
            station.setStationIds(station.getStationId(),station.getLinesIds().length);
        }
        return station;
    }

    @Override
    public Station selectStationByStationName(String stationName) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("stationName",stationName+"站");
        Station station = this.stationMapper.select(hashMap).get(0);

        if (station != null){
            station.setLines(station.getLineId());
            station.setStationIds(station.getStationId(),station.getLinesIds().length);
        }
        return station;
    }

    @Override
    public List<Station> selectAllStation() {
        List<Station> stations = stationMapper.select(new HashMap());

        for (Station station : stations) {
            station.setLines(station.getLineId());
            station.setStationIds(station.getStationId(),station.getLinesIds().length);
        }
        return stations;
    }
}
