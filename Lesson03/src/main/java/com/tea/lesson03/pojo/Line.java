package com.tea.lesson03.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/3/25 9:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Line {
    private String lineId;
    private String lineName;
    private String stationId;
    private String stationIds[];
    private List<Station> stations;
    private int stationNum;

    public void setStationIds(String stationId) {
        String tempStationId = stationId;

        this.stationIds = new String[this.getStationNum()];     //初始化

        int index = 0;
        while (tempStationId.indexOf(',') != -1) {
            stationIds[index] = tempStationId.substring(0, tempStationId.indexOf(','));
            tempStationId = tempStationId.substring(tempStationId.indexOf(',') + 1);
            index++;
        }

        stationIds[index] = tempStationId;
    }
}
