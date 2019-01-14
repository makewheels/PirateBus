var baseUrl = "http://192.168.99.193/bus";
// var baseUrl = "https://qbserver.cn/bus";

// fundebug.com
function loadScript(url, apikey) {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = url;
	script.setAttribute("apikey", apikey);
	document.body.appendChild(script);
}
loadScript("https://js.fundebug.cn/fundebug.1.5.1.min.js",
		"402ec2d6773bbfa7d26d10ef41cfdb3d901a10d5f5276aa17812e891a1f7eee5")

// MTA
var _mtac = {};
(function() {
	var mta = document.createElement("script");
	mta.src = "//pingjs.qq.com/h5/stats.js?v2.0.4";
	mta.setAttribute("name", "MTAH5");
	mta.setAttribute("sid", "500666633");
	var s = document.getElementsByTagName("script")[0];
	s.parentNode.insertBefore(mta, s);
})();
