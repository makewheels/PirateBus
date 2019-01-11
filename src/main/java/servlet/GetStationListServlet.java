package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.stationinfo.my.BusStop;
import bean.stationinfo.my.MyStationInfo;
import util.FileUtil;

/**
 * 站点列表
 * 
 * @author Administrator
 *
 */
public class GetStationListServlet extends HttpServlet {
	private static final long serialVersionUID = 3249228653410736282L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String busName = request.getParameter("busName");
		String direction = request.getParameter("direction");
		// 读公交线路
		List<BusStop> busStopList = JSON.parseObject(
				FileUtil.readTextFile(
						GetStationListServlet.class.getResource("/stationinfo/my/" + busName + ".json").getPath()),
				MyStationInfo.class).getBusLineList().get(Integer.parseInt(direction)).getBusStopList();
		List<String> clientBusStopList = new ArrayList<>();
		for (BusStop busStop : busStopList) {
			clientBusStopList.add(busStop.getName());
		}
		// 回写站点列表
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(JSON.toJSONString(clientBusStopList));
	}

}
