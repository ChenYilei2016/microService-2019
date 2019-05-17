<%@page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>毅阳健身公共服务平台</title>
    <link rel="stylesheet" href="/MyWeb/css/base.css">
    <link rel="stylesheet" href="/MyWeb/css/main.css">
    <script src="/MyWeb/js/jquery-3.2.1.min.js"></script>
    <script src="/MyWeb/js/index.js"></script>
    <base href="/MyWeb/index/"/>
</head>
<body>
<script tpye="text/javascript">
    function con() {
        if (
            <%=request.getSession().getAttribute("a")%>
            == null
    )
        {
            if (confirm("登陆之后才能查看，确认登陆么")) {
                location.href = "../loginregister/login.jsp";
            }

        }
    else
        {
            location.href = "../demo/demo.jsp";
        }
    }
</script>

<!-- header -->
<div class="header">
    <div class="w1200 header-top clearfix">
        <div class="fl title">
            <a href="http://sports.163.com/">网易体育官网</a> | <a
                href="http://sports.sina.com.cn/">新浪体育频道</a>
        </div>
        <!-- 之后把新浪运动的网址放上去 -->
        <div class="login_box fr">
            <a href="../loginregister/login.jsp">欢迎您！<U><%=(session.getAttribute("a") == null ? "    " : session.getAttribute("a"))%>
                先生</U></a>
            | <a href="../loginregister/login.jsp">登录</a> | <a
                href="../loginregister/login.jsp">注册</a> |

            <button onclick="con()"
                    style="background-image: linear-gradient(#f5c153, #ea920d);">我的
            </button>
        </div>
    </div>

    <div class="header_logo clearfix">
        <div class="w1200 logo_content">
            <a href="#"><img src="../images/logo3.png" alt=""></a>
            <div align="right">
					<span class="detail">热门搜索：<a href="javascript:;">篮球</a> <a
                            href="javascript:;">健身场馆</a> <a href="javascript:;">健身器材</a>
					</span>
            </div>
        </div>
    </div>
</div>
<!-- header结束 -->
<!-- 内容区域 -->
<div class="content">
    <!-- 导航 -->
    <div class="wrap_nav">
        <div class="nav w1200">
            <ul class="clearfix">
                <li><a
                        href="http://localhost:8080/MyWeb/login.do?username=3&password=3&checkCode=609V"
                        id="shouye">首页</a></li>
                <li><a
                        href="https://ddsyd.tmall.com/campaign-3742-22.htm?spm=a220o.1000855.w5001-15043732798.4.29a42adeyXBFGP&scene=taobao_shop">健行商城</a>
                    <div class="list_icon">
                        <p>
                            <a href="huodongxq.html">运动器械</a>
                        </p>
                        <p>
                            <a href="huodongxq.html">报班服务</a>
                        </p>
                        <p>
                            <a href="huodongxq.html">抽奖活动</a>
                        </p>
                    </div>
                </li>
                <li><a href="../liuyanban/index.html">轨迹分享</a>
                    <div class="list_icon">
                        <p>
                            <a href="sheshixq.html">转发</a>
                        </p>
                        <p>
                            <a href="sheshixq.html">上传</a>
                        </p>
                        <p>
                            <a href="sheshixq.html">点赞</a>
                        </p>
                    </div>
                </li>
                <li><a href="huoban.jsp">合作伙伴</a>
                    <div class="list_icon">
                        <p>
                            <a href="sheshixq.html">人工客服</a>
                        </p>
                        <p>
                            <a href="sheshixq.html">退货须知</a>
                        </p>
                        <p>
                            <a href="sheshixq.html">杭州国际马拉松</a>
                        </p>
                    </div>
                </li>
                <li><a href="../liuyanban/index.html">心心相映</a>
                    <div class="list_icon">
                        <p>
                            <a href="zhidaoyuanxq.html">周围的人</a>
                        </p>
                        <p>
                            <a href="zhidaoyuanxq.html">明星榜</a>
                        </p>
                        <!-- 可以不写 -->
                        <p>
                            <a href="zhidaoyuanxq.html">杭州国际马拉松</a>
                        </p>
                    </div>
                </li>
                <li><a href="../map/map.html">健身地图</a></li>
                <li><a href="women.jsp">关于我们</a></li>
            </ul>
        </div>
    </div>
    <!-- 导航结束 -->
    <!-- banner -->
    <div class="wrap_banner">
        <img src="../images/banner.jpg" alt="">
    </div>
    <!-- banner结束 -->
    <div class="w1200 projects">
        <ul class="clearfix">
            <li align="center"><a href="#"><img
                    src="../images/project1.png" alt=""></a></li>
            <li><a href="#"><img src="../images/project2.png" alt=""></a></li>

            <li><a href="#"><img src="../images/project4.png" alt=""></a></li>
            <li><a href="#"><img src="../images/project5.png" alt=""></a></li>
            <li><a href="#"><img src="../images/project6.png" alt=""></a></li>
            <li><a href="#"><img src="../images/project7.png" alt=""></a></li>
        </ul>
    </div>
    <!-- 赛事报名和动态 -->
    <div class="enrol w1200 clearfix">
        <div class="event fl " id="event">
            <h1>
                赛事<span>报名</span><i>/全民健身，为健康加油</i> <a href="saishi.jsp"><font
                    color="green" size="3">了解更多>></font></a>

            </h1>
            <c:forEach items="${news}" var="temp">
                <div class="slide-wrap">
                    <ul class="slide-img" id="event1">
                        <li>
                            <div class="event_content clearfix">
                                <div class="one_event fl clearfix">
                                    <a href="#"><img class="fl"
                                                     src="../images/one_event_icon.png" alt=""></a>
                                    <div class="fr event_detail">
                                        <h2>
                                            <a href="#">${temp.name }</a>
                                        </h2>
                                        <p>报名时间：${temp.time}</p>
                                        <p>报名结束时间：${temp.time}</p>
                                            <%-- 		<p><c:if test="{temp.time}"></c:if></p> --%>
                                        <!-- 	2019-05-01 -->
                                        <c:set var="nowDate" value="<%=new Date()%>"></c:set>
                                        <fmt:parseDate value="${temp.time}" pattern="yyyy-MM-dd" var="endTime"/>
                                        <c:choose>
                                            <c:when test="${nowDate - endTime > 0}">
                                                <span class="STYLE1">状态：已超时</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="STYLE2">状态：进行中</span>
                                            </c:otherwise>
                                        </c:choose>
                                        <a class="my_event" href="javascript:;">我要报名</a>
                                    </div>
                                </div>
                        </li>
                    </ul>
                    <ul class="slide-tab">

                    </ul>
                </div>
            </c:forEach>

        </div>
        <div class="dynamics fr">
            <div class="dynamics_title clearfix">
                <span class="fl">资讯动态</span> <a class="fr" href="javascript:;">more
                >></a>
            </div>
            <div class="dynamics_constent">
                <div class="clearfix dynamics_text">
                    <p class="fl">
                        <a href="#">综合管理服务中心党支部学习贯彻十九...</a>
                    </p>
                    <span class="fr">2017-10-22</span>
                </div>
                <div class="clearfix dynamics_text">
                    <p class="fl">
                        <a href="#">综合管理服务中心党支部学习贯彻十九...</a>
                    </p>
                    <span class="fr">2017-10-22</span>
                </div>
                <div class="clearfix dynamics_text">
                    <p class="fl">
                        <a href="#">综合管理服务中心党支部学习贯彻十九...</a>
                    </p>
                    <span class="fr">2017-10-22</span>
                </div>
                <div class="clearfix dynamics_text">
                    <p class="fl">
                        <a href="#">综合管理服务中心党支部学习贯彻十九...</a>
                    </p>
                    <span class="fr">2017-10-22</span>
                </div>
                <div class="clearfix dynamics_text">
                    <p class="fl">
                        <a href="#">综合管理服务中心党支部学习贯彻十九...</a>
                    </p>
                    <span class="fr">2017-10-22</span>
                </div>
            </div>
        </div>
    </div>
    <!-- 赛事报名和动态结束 -->
    <!-- 经常呈现 -->
    <div class="wonderful">
        <div class="w1200">
            <h1>
                精彩<span>呈现</span><i>/全民健身，为健康加油</i>
            </h1>
            <div class="wonderful_img">
                <ul class="clearfix">
                    <li><a href="#"><img src="../images/wonderful_img.png"
                                         alt=""></a>
                        <p>
                            <a href="../xiangce/index.html">2017全民瑜伽公益活动 <span>(10张)</span></a>
                        </p></li>
                    <li><a href="#"><img src="../images/wonderful_img.png"
                                         alt=""></a>
                        <p>
                            <a href="#">2017全民瑜伽公益活动 <span>(10张)</span></a>
                        </p></li>
                    <li><a href="#"><img src="../images/wonderful_img.png"
                                         alt=""></a>
                        <p>
                            <a href="#">2017全民瑜伽公益活动 <span>(10张)</span></a>
                        </p></li>
                    <li><a href="#"><img src="../images/wonderful_img.png"
                                         alt=""></a>
                        <p>
                            <a href="#">2017全民瑜伽公益活动 <span>(10张)</span></a>
                        </p></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- 经常呈现结束 -->
    <!-- 场馆预订 -->
    <div class="book w1200 clearfix">
        <div class="event book_title fl">
            <h1>
                场馆<span>预订</span><i>/全民健身，为健康加油</i>
            </h1>
            <div class="book_nav">
                <ul class="clearfix">
                    <li><a href="#" class="on">全部</a></li>
                    <li><a href="#">游泳</a></li>
                    <li><a href="#">篮球</a></li>
                    <li><a href="#">足球</a></li>
                    <li><a href="#">网球</a></li>
                    <li><a href="#">乒乓球</a></li>
                    <li><a href="#">羽毛球</a></li>
                    <li><a href="#">瑜伽</a></li>
                </ul>
            </div>
            <div class="book_img clearfix">
                <ul>
                    <li><a href="#"><img src="../images/book_img.png" alt=""></a>
                        <h3>
                            <a href="#">杭州市体育馆XXX羽毛球场</a>
                        </h3>
                        <p>西陵区西陵二路 18号</p> <span>15525252525</span></li>
                    <li><a href="#"><img src="../images/book_img.png" alt=""></a>
                        <h3>
                            <a href="#">杭州市体育馆XXX羽毛球场</a>
                        </h3>
                        <p>西陵区西陵二路 18号</p> <span>15525252525</span></li>
                    <li><a href="#"><img src="../images/book_img.png" alt=""></a>
                        <h3>
                            <a href="#">杭州市体育馆XXX羽毛球场</a>
                        </h3>
                        <p>西陵区西陵二路 18号</p> <span>15525252525</span></li>
                </ul>
            </div>
        </div>
        <div class="dynamics fr">
            <div class="dynamics_title clearfix">
                <span class="fl">赛事直播</span> <a class="fr" href="javascript:;">more
                >></a>
            </div>
            <div class="dynamics_constent nopbook">
                <div class="clearfix book_text">
                    <a href="../video/shipin.html">体育成主科，考不是目的！最终目的是素质教育！</a>
                </div>
                <div class="clearfix book_text">
                    <a href="#">体育成主科，考不是目的！最终目的是素质教育！</a>
                </div>
                <div class="clearfix book_text">
                    <a href="#">体育成主科，考不是目的！最终目的是素质教育！</a>
                </div>
                <div class="clearfix book_text">
                    <a href="#">体育成主科，考不是目的！最终目的是素质教育！</a>
                </div>
                <div class="clearfix book_text">
                    <a href="#">体育成主科，考不是目的！最终目的是素质教育！</a>
                </div>
                <div class="clearfix book_text">
                    <a href="#">体育成主科，考不是目的！最终目的是素质教育！</a>
                </div>
                <div class="clearfix book_text">
                    <a href="#">体育成主科，考不是目的！最终目的是素质教育！</a>
                </div>
            </div>
        </div>
    </div>
    <!-- 场馆预订结束 -->
    <!-- 全民健身组织 -->
    <div class="wonderful">
        <div class="w1200">
            <h1 class="jians_icon">
                全民<span>健身组织</span><i>/全民健身，为健康加油</i>
            </h1>
            <div class="jias_groud clearfix">
                <a class="active" href="javascript:;">市级体育协会</a> <a
                    href="javascript:;">县区级体育协会</a> <a href="javascript:;">健身站点</a>
            </div>
            <div class="wonderful_img">
                <ul class="clearfix jians_ul">
                    <li><a href="#"><img src="../images/wonderful_img.png"
                                         alt=""></a>
                        <p>
                            <a href="#">杭州市体育馆羽毛球场</a>
                        </p></li>
                    <li><a href="#"><img src="../images/wonderful_img.png"
                                         alt=""></a>
                        <p>
                            <a href="#">杭州市体育馆羽毛球场</a>
                        </p></li>
                    <li><a href="#"><img src="../images/wonderful_img.png"
                                         alt=""></a>
                        <p>
                            <a href="#">杭州市体育馆羽毛球场</a>
                        </p></li>
                    <li><a href="#"><img src="../images/wonderful_img.png"
                                         alt=""></a>
                        <p>
                            <a href="#">杭州市体育馆羽毛球场</a>
                        </p></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- 全名健身组织结束 -->
    <!-- 社体指导员 -->
    <div class="enrol instructor w1200 clearfix">
        <div class="event fl" id="event">
            <h1 class="instructor_icon">
                社体<span>指导员</span><i>/全民健身，为健康加油</i>
            </h1>
            <div class="instructor_type clearfix">
                <span class="fl">运动类型:</span> <a class="active" href="javascript:;">全部</a>
                <a href="javascript:;">足球</a> <a href="javascript:;">羽毛球</a> <a
                    href="javascript:;">游泳</a> <a href="javascript:;">自行车</a> <a
                    href="javascript:;">桌球</a> <a href="javascript:;">徒步</a> <a
                    href="javascript:;">徒步</a> <a href="javascript:;">乒乓球</a>
            </div>
            <div class="slide-wrap">
                <ul class="slide-img">
                    <li>
                        <div class="event_content clearfix">
                            <div class="one_event instructor_constent fl clearfix">
                                <a href="#"><img class="fl"
                                                 src="../images/one_event_icon.png" alt=""></a>
                                <div class="fr event_detail">
                                    <p>姓名：张飞鹏</p>
                                    <p>级别：国家一级武术裁判</p>
                                    <p>城市：湖北杭州</p>
                                    <p>指导项目：武术</p>
                                </div>
                            </div>
                            <div class="one_event instructor_constent fl clearfix">
                                <a href="#"><img class="fl"
                                                 src="../images/one_event_icon.png" alt=""></a>
                                <div class="fr event_detail">
                                    <p>姓名：张飞鹏</p>
                                    <p>级别：国家一级武术裁判</p>
                                    <p>城市：湖北杭州</p>
                                    <p>指导项目：武术</p>
                                </div>
                            </div>
                        </div>
                        <div class="event_content clearfix">
                            <div class="one_event instructor_constent fl clearfix">
                                <a href="#"><img class="fl"
                                                 src="../images/one_event_icon.png" alt=""></a>
                                <div class="fr event_detail">
                                    <p>姓名：张飞鹏</p>
                                    <p>级别：国家一级武术裁判</p>
                                    <p>城市：湖北杭州</p>
                                    <p>指导项目：武术</p>
                                </div>
                            </div>
                            <div class="one_event instructor_constent fl clearfix">
                                <a href="#"><img class="fl" src="images/one_event_icon.png"
                                                 alt=""></a>
                                <div class="fr event_detail">
                                    <p>姓名：张飞鹏</p>
                                    <p>级别：国家一级武术裁判</p>
                                    <p>城市：湖北杭州</p>
                                    <p>指导项目：武术</p>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="event_content clearfix">
                            <div class="one_event instructor_constent fl clearfix">
                                <a href="#"><img class="fl"
                                                 src="../images/one_event_icon.png" alt=""></a>
                                <div class="fr event_detail">
                                    <p>姓名：张飞鹏</p>
                                    <p>级别：国家一级武术裁判</p>
                                    <p>城市：湖北杭州</p>
                                    <p>指导项目：武术</p>
                                </div>
                            </div>
                            <div class="one_event instructor_constent fl clearfix">
                                <a href="#"><img class="fl"
                                                 src="../images/one_event_icon.png" alt=""></a>
                                <div class="fr event_detail">
                                    <p>姓名：张飞鹏</p>
                                    <p>级别：国家一级武术裁判</p>
                                    <p>城市：湖北杭州</p>
                                    <p>指导项目：武术</p>
                                </div>
                            </div>
                        </div>
                        <div class="event_content clearfix">
                            <div class="one_event instructor_constent fl clearfix">
                                <a href="#"><img class="fl"
                                                 src="../images/one_event_icon.png" alt=""></a>
                                <div class="fr event_detail">
                                    <p>姓名：张飞鹏</p>
                                    <p>级别：国家一级武术裁判</p>
                                    <p>城市：湖北杭州</p>
                                    <p>指导项目：武术</p>
                                </div>
                            </div>
                            <div class="one_event instructor_constent fl clearfix">
                                <a href="#"><img class="fl"
                                                 src="../images/one_event_icon.png" alt=""></a>
                                <div class="fr event_detail">
                                    <p>姓名：张飞鹏</p>
                                    <p>级别：国家一级武术裁判</p>
                                    <p>城市：湖北杭州</p>
                                    <p>指导项目：武术</p>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
                <ul class="slide-tab">
                    <li class="slide-tab-on"></li>
                    <li class=""></li>
                </ul>
            </div>
        </div>
        <div class="dynamics fr">
            <div class="dynamics_title clearfix">
                <span class="fl">体质健康</span>
            </div>
            <div class="dynamics_constent instructor_tab">
                <table border="1">
                    <tr>
                        <td><a href="#"><img src="../images/tab1.png" alt=""></a>
                            <p>健身锻炼法</p></td>
                        <td><a href="#"><img src="../images/tab2.png" alt=""></a>
                            <p>健身锻炼法</p></td>
                        <td><a href="#"><img src="../images/tab3.png" alt=""></a>
                            <p>健身锻炼法</p></td>
                    </tr>
                    <tr>
                        <td class="active"><a href="#"><img
                                src="../images/tab4.png" alt=""></a>
                            <p>健身锻炼法</p></td>
                        <td><a href="#"><img src="../images/tab5.png" alt=""></a>
                            <p>健身锻炼法</p></td>
                        <td><a href="#"><img src="../images/tab6.png" alt=""></a>
                            <p>健身锻炼法</p></td>
                    </tr>
                    <tr>
                        <td><a href="#"><img src="../images/tab7.png" alt=""></a>
                            <p>健身锻炼法</p></td>
                        <td><a href="#"><img src="../images/tab8.png" alt=""></a>
                            <p>健身锻炼法</p></td>
                        <td><a href="#"><img src="../images/tab9.png" alt=""></a></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <!-- 社体指导员结束 -->
</div>
<!-- 内容区域 -->
<!-- fooder区域 -->
<div class="fooder">
    <div class="food_content w1200">
        <div class="food_top clearfix">
            <div class="fl food_logo">
                <img src="../images/yc.png" alt="">
                <p>
                    毅阳全民健身公共服务平台是杭州市体育局建设的公共服务类的体育综合平台，该平台集成了国民体质监测预约、活动赛事场馆在线预订、体育指导员展示、科学健身指导、全民健身服务设施GIS平台，解决了群众日常的体育锻炼需求，该平台将如何健身锻炼、在哪里健身锻炼、科学评估锻炼效果等多维度的服务融合，将提升我区居民的科学健身、体育锻炼、赛事参与等体育运动的热情，促进我市居民体质健康。</p>
            </div>
            <div class="fr clearfix">
                <div class="fl er_ma">
                    <img src="../images/er.png" alt="">
                    <p>杭州体育</p>
                </div>
                <div class="fl er_ma">
                    <img src="../images/er.png" alt="">
                    <p>运动杭州</p>
                </div>
                <div class="fl er_ma">
                    <img src="../images/er.png" alt="">
                    <p>网易新闻</p>
                </div>
            </div>
        </div>
    </div>
    <div class="address">主办单位：杭州毅阳健身平台&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;运营单位:杭州毅阳健身平台
    </div>
</div>
<!-- fooder区域结束 -->
</body>
<!-- <script type="text/javascript">
 function getResponse() {
	$.ajax({
		url : "/MyWeb/SaiShiResponse",
		success : function(result) { //result就是list
			// { a:[]  }
			 //alert(result);
			var myhtml = "";
			for ( var i = 0; i <result.length; i++){
				 myhtml = myhtml +
				 '<li>'
					+'	<div class="one_event fl clearfix">'
					+'	<a href="#"><img class="fl"	src="../images/one_event_icon.png" alt=""></a>'
					+'		<div class="fr event_detail">'
					+'			<h2>'
					+'				<a href="#">'+result[i].name +'</a>'
					+'			</h2>                          '
					+'			<p>报名时间：'+result[i].time+'</p>'
					+'			<p>报名结束时间：'+result[i].time+'</p>'
					+'			<a class="my_event" href="javascript:;">我要报名</a>'
					+'		</div>'
					+'	</div>	'
					+			'</li>'
			}
			//alert(myhtml);
			$("#event1").html(myhtml);//第一次循环就是TheTitle 第二次循环就是TheTile+ATheTitle
		},
		error: function(result){

		}
	});

}
getResponse();
	$("#shouye").click(getResponse());

	//点击了下分类

	//ajax请求后台，得到一个列表数据

	//把列表数据拼接html

	//把要显示的区域的html 变成你拼接的
</script>
 -->
</html>