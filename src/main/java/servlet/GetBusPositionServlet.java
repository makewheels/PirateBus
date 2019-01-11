package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.allbusposition.AllBusPosition;
import bean.allbusposition.BusList;
import util.WebUtil;

/**
 * 公交位置
 * 
 * @author Administrator
 *
 */
public class GetBusPositionServlet extends HttpServlet {
	private static final long serialVersionUID = -226638341223606409L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String busName = request.getParameter("busName");
		String direction = request.getParameter("direction");
		// 做转换：这个调用公交秘书服务器，查所有车的接口，和原app的direction是相反的
		if (direction.equals("0")) {
			direction = "1";
		} else if (direction.equals("1")) {
			direction = "0";
		}
		// 请求公交秘书服务器
		String url = "http://busmishu.com:8080/BusComeServer/pages/index/IndexAction.do?action=mapxxx&bus_direction="
				+ direction + "&bus_name=" + busName;
		List<BusList> busList = JSON.parseObject(WebUtil.sendGet(url), AllBusPosition.class).getBusList();
		System.out.println(
				"request busmishu server for all buses position: busName = " + busName + ", direction = " + direction);
		List<Integer> list = new ArrayList<>();
		for (BusList bus : busList) {
			int index = (int) Double.parseDouble(bus.getDz()) - 1;
			list.add(index);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(JSON.toJSONString(list));
	}

}
