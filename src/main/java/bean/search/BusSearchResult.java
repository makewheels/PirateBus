package bean.search;

/**
 * 搜索公交结果
 * 
 * @author Administrator
 *
 */
public class BusSearchResult {
	// 公交的id
	private String busName;
	// 展示的公交名
	private String showName;

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

}
