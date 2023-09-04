package com.tea.lesson03.controller;

import com.tea.lesson03.pojo.Line;
import com.tea.lesson03.pojo.Station;
import com.tea.lesson03.service.LineServiceImpl;
import com.tea.lesson03.service.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/3/25 15:02
 */
@Controller
//@RestController
public class MainController {
    private final LineServiceImpl lineService;
    private final StationServiceImpl stationService;

    public MainController(LineServiceImpl lineService, StationServiceImpl stationService) {
        this.lineService = lineService;
        this.stationService = stationService;
    }

    //展示所有的地铁线路
    @RequestMapping("/getAllLines")
    @ResponseBody
    public String getAllLines(HttpSession httpSession, Model model) {
        List<Line> lines = lineService.selectAllLines();

        for (Line line : lines) {
            List<Station> stations = new ArrayList<>();
            for (int i = 0; i < line.getStationNum(); i++) {
                Station station = stationService.selectStationByStationId(line.getStationIds()[i]);
                stations.add(i, station);        //把车站加入到线路对象中
            }
            line.setStations(stations);
        }

        httpSession.setAttribute("allLines", lines);

        return String.valueOf(lines);
//        return "./getAllLinesRes";
    }

    @RequestMapping("toInputLineIdPage")
    public String toInputLineIdPage() {
        return "/inputLineIdPage";
    }

    //根据具体地铁线号展示地铁线路
    @RequestMapping("/getLineByLineId")
    @ResponseBody
    public String getLineByLineId(HttpServletRequest request,
                                  Model model,
                                  HttpSession httpSession) {
        List<Line> lines = (List<Line>) httpSession.getAttribute("allLines");

        model.addAttribute("line", lines.get(Integer.parseInt(request.getParameter("lineId")) - 1));

        return String.valueOf(lines.get(Integer.parseInt(request.getParameter("lineId")) - 1));
//        return "/getLineByLineIdRes";
    }

    @RequestMapping("/toInputStationNamePage")
    public String toInputStationNamePage() {
        return "/inputStationNamePage";
    }

    //根据站点名字展示经过的地铁线路
    @RequestMapping("/getLinesByStationName")
    @ResponseBody
    public String getLinesByStationId(HttpServletRequest request,
                                      HttpSession httpSession,
                                      Model model) {
        Station station = stationService.selectStationByStationName(request.getParameter("stationName"));       //首先根据地铁站名查出地铁站信息

        List<Line> lines = new ArrayList<>();
        List<Line> allLines = (List<Line>) httpSession.getAttribute("allLines");
        for (int i = 0; i < station.getLineNum(); i++) {
            lines.add(i, allLines.get(Integer.parseInt(station.getLinesIds()[i]) - 1));     //再去session中把经过这个地铁站的各条线路查询出来
        }

        model.addAttribute("lines", lines);

        return String.valueOf(lines);
//        return "/getLinesByStationId";
    }

    @RequestMapping("/toInputShortestWay")
    public String in() {
        return "/inputShortestWay";
    }

    //显示最短地铁线路
    @RequestMapping("/getShortestWay")
    @ResponseBody
    public String getShortestWay(HttpServletRequest request,
                                 HttpSession httpSession,
                                 Model model) {
        String startStationName = request.getParameter("startStationName");
        String endStationName = request.getParameter("endStationName");

        String path = "C:\\CS\\Java\\IdeaProjects\\Lesson03\\src\\main\\java\\com\\tea\\lesson03\\function\\test.txt";
        try {
            Files.writeString(Paths.get(path), startStationName + '\n' + endStationName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String tempStr = "";
        try {
            String[] str = new String[]{"python",
                    "C:\\CS\\Java\\IdeaProjects\\Lesson03\\src\\main\\java\\com\\tea\\lesson03\\function\\count.py"};
            Process proc = Runtime.getRuntime().exec(str);      // 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            tempStr = in.readLine();
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tempStr);

        String[] str = tempStr.split("-");      //用于存放站名和其线的id及途径几站
        List<Station> stationList = new ArrayList<>();      //记录经过的站

        for (int i = 0; i < str.length - 1; i += 2) {
            String lineId = str[i + 1].substring(0, str[i + 1].indexOf('('));       //获得当前站的线号
            int tempNum = Integer.parseInt(str[i + 1].substring(str[i + 1].indexOf('(') + 1, str[i + 1].indexOf(')')));        //读取间隔站数

            Station station = stationService.selectStationByStationName(str[i]);       //首先根据地铁站名查出起始/中转地铁站信息
            //得到整条线路
            Line line = ((List<Line>) httpSession.getAttribute("allLines"))
                    .get(Integer.parseInt(str[i + 1].substring(0, str[i + 1].indexOf('('))) - 1);

            int startStationId = 0;
            for (int j = 0; j < station.getLineNum(); j++) {
                if (station.getLinesIds()[j].equals(line.getLineId())) {
                    //获取起始/中转站在此线路上的id号
                    //获取最后两位尾号即可
                    startStationId = Integer.parseInt(station.getStationIds()[j].substring(station.getStationIds()[j].length() - 2));
                }
            }

            for (int j = startStationId; j < startStationId + tempNum; j++) {
                stationList.add(line.getStations().get(j - 1));
            }
        }
        Station station = stationService.selectStationByStationName(str[str.length - 1]);   //查询出最后一站然后添加进这个List
        stationList.add(station);

        model.addAttribute("stations", stationList);

//        String ending = String.join(",",stationList);
        try {
            FileWriter fileOut = new FileWriter("C:\\CS\\Java\\IdeaProjects\\Lesson03\\src\\main\\java\\com\\tea\\lesson03\\function\\test.txt");

            fileOut.write("");

            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(stationList);
    }
}
