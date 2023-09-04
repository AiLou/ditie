package com.tea.lesson03.service;

import com.tea.lesson03.mapper.LineMapper;
import com.tea.lesson03.pojo.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/3/25 10:39
 */
@Service
public class LineServiceImpl implements LineService {
    private final LineMapper lineMapper;

    public LineServiceImpl(LineMapper lineMapper) {
        this.lineMapper = lineMapper;
    }

    @Override
    public List<Line> selectLineIdByStationId(String stationId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("stationId", stationId);
        List<Line> lines = this.lineMapper.select(hashMap);

        for (Line line : lines) {
            line.setStationIds(line.getStationId());
        }

        return lines;
    }

    @Override
    public Line selectLineByLineId(String lineId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lineId", lineId);

        Line line = this.lineMapper.select(hashMap).get(0);
        if (line != null) {
            line.setStationIds(line.getStationId());
        }

        return line;
    }

    @Override
    public List<Line> selectAllLines() {
        List<Line> lines = lineMapper.select(new HashMap<>());

        for (Line line : lines) {
            line.setStationIds(line.getStationId());
        }
        return lines;
    }
}
