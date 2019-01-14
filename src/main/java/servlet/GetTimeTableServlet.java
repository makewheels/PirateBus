package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.timetable.client.ClientTimeTable;
import bean.timetable.my.DepartTime;
import bean.timetable.my.TimeTable;
import util.FileUtil;

/**
 * 获取发车时刻表
 * 
 * @author Administrator
 *
 */
public class GetTimeTableServlet extends HttpServlet {
	private static final long serialVersionUID = 910341781110464124L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		String busName = request.getParameter("busName");
		String direction = request.getParameter("direction");
		// 读发车时间
		String filename = "";
		if (direction.equals("1")) {
			filename = "f" + busName + ".json";
		} else {
			filename = busName + ".json";
		}
		ClientTimeTable clientTimeTable = new ClientTimeTable();
		TimeTable timeTable = null;
		List<LocalTime> localTimeList = new ArrayList<>();
		List<DepartTime> departTimeList;
		try {
			// 解析时刻表
			timeTable = JSON.parseObject(
					FileUtil.readTextFile(GetTimeTableServlet.class.getResource("/timetable/my/").getPath() + filename),
					TimeTable.class);
			// 拿到发车时间list结合
			departTimeList = timeTable.getDepartTimeList();
		} catch (Exception e) {
			// 如果找不到文件
			clientTimeTable.setMsg("error");
			writer.write(JSON.toJSONString(clientTimeTable));
			return;
		}
		for (DepartTime departTime : departTimeList) {
			localTimeList.add(departTime.getLocalTime());
		}
		// 目的：从现在的时间开始，往后的发车时间，优先显示。所以要调整顺序
		List<String> stringList = new ArrayList<>();
		int index = 0;
		for (int i = 0; i < localTimeList.size(); i++) {
			// 找到第一个比现在晚的
			if (localTimeList.get(i).isAfter(LocalTime.now(ZoneId.of("UTC+8")))) {
				index = i;
				break;
			}
		}
		// 现在到最后
		for (int i = index; i < localTimeList.size(); i++) {
			stringList.add(localTimeList.get(i).toString());
		}
		// 今天和明天发车时间的分隔行
		stringList.add("----------");
		// 开头到现在
		for (int i = 0; i < index - 1; i++) {
			stringList.add(localTimeList.get(i).toString());
		}
		// 回写给客户端
		clientTimeTable.setMsg("ok");
		clientTimeTable.setTopic(timeTable.getOriginalTopic());
		clientTimeTable.setTimeList(stringList);
		writer.write(JSON.toJSONString(clientTimeTable));
	}

}
