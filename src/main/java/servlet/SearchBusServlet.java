package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.search.BusSearchResult;
import bus.BusHelper;

public class SearchBusServlet extends HttpServlet {
	private static final long serialVersionUID = -8305691947876202609L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String keyword = request.getParameter("keyword");
		if (keyword == null || keyword.equals("")) {
			response.getWriter().write("[]");
			return;
		}
		String folderPath = getServletContext().getRealPath("/WEB-INF/classes/stationinfo/my");
		List<String> busNameList = BusHelper.getBusNameList(folderPath);
		List<BusSearchResult> searchResults = new ArrayList<>();
		for (String busName : busNameList) {
			if (busName.startsWith(keyword)) {
				BusSearchResult busSearchResult = new BusSearchResult();
				busSearchResult.setBusName(busName);
				String space = "";
				if (busName.length() == 1) {
					space = "&nbsp;&nbsp;&nbsp;&nbsp;";
				} else if (busName.length() == 2) {
					space = "&nbsp;&nbsp;";
				}
				busSearchResult.setShowName(busName + space + "路");
				searchResults.add(busSearchResult);
			}
		}
		response.getWriter().write(JSON.toJSONString(searchResults));
	}

}
