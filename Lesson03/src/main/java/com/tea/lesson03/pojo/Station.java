package com.tea.lesson03.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/3/25 9:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    private String stationIds[];
    private String stationId;
    private String stationName;
    private String lineId;
    private int lineNum;
    private String linesIds[];

    public void setStationIds(String stationId, int lineNum) {
        String tempStationId = stationId;

        this.stationIds = new String[lineNum];

        int index = 0;      //标记
        while (tempStationId.indexOf(',') != -1) {
            this.stationIds[index] = tempStationId.substring(0, tempStationId.indexOf(','));
            tempStationId = tempStationId.substring(tempStationId.indexOf(',') + 1);
            index++;
        }
        this.stationIds[index] = tempStationId.substring(0);
    }

    public void setLines(String lineId) {
        String tempLine = lineId;

        this.linesIds = new String[this.getLineNum()];

        int index = 0;
        while (tempLine.indexOf(',') != -1) {
            linesIds[index] = tempLine.substring(0, tempLine.indexOf(','));
            tempLine = tempLine.substring(tempLine.indexOf(',') + 1);
            index++;
        }

        linesIds[index] = tempLine;
    }
}
