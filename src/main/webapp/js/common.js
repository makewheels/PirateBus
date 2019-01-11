var baseUrl = "http://192.168.99.193/bus";
//var baseUrl = "https://qbserver.cn/bus";

//MTA
var _mtac = {};
(function() {
    var mta = document.createElement("script");
    mta.src = "//pingjs.qq.com/h5/stats.js?v2.0.4";
    mta.setAttribute("name", "MTAH5");
    mta.setAttribute("sid", "500666633");
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(mta, s);
})();
