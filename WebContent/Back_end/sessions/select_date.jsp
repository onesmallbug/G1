<%@ page contentType="text/html; charset=Big5" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!doctype html>
<html>
<head>
<!-- Bootstrap CSS start-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->
<title>Example</title>
<script>var path = '<%= request.getContextPath()%>';</script>
<link href="<%=request.getContextPath()%>/Back_end/sessions/glDatePicker/glDatePicker.default2.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/Back_end/sessions/glDatePicker/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/Back_end/sessions/glDatePicker/glDatePicker2.js"></script>
<!-- æ­¤çèªå® -->
<style>
.parent {
	position: relative;
	float: left;
	width: 95%;
	height: 80px;
	text-align: left;
	z-index: 1;
	border: solid 0px red;
	margin: 2%;
	line-height:12px;
}

.link {
	position: absolute;
	left: 50%;
	margin-left: -50%;
	width: 100%;
	bottom: 5%;
	text-align: center;
}
</style>
<style>
a:visited, a:link {
	color: inherit;
	text-decoration: none;
}

a:hover, a:active {
	color: cyan;
	text-decoration: none;
}
</style>
</head>
<body>
	<jsp:include page="/BackHeaderFooter/Header.jsp" />
	<!-- 工作區開始 -->
	<div class="container">
		<div class="row justify-content">
			<div class="col-12">
				<div>
					<input 
					gldp-id="mydate" 
					style="width: 1000px; 
					height: 30px; 
					visibility: visible; 
					color: blue; 
					font-weight: bold;" 
					type="text" 
					id="mydate" />
				</div>
			</div>
		</div>
		<div class="row justify-content">
			<div class="col-12">
				<div>
					<div 
					gldp-el="mydate" 
					style="width: 1000px; 
					height: 600px; 
					">
					</div>
				</div>
				
			</div>
		</div>
	</div>

	<jsp:include page="/BackHeaderFooter/Footer.jsp" />
	<!-- 工作區結束 -->
	<script type="text/javascript">
		$('#mydate')
				.glDatePicker(
						{
							showAlways : true, // 預設為 false
							cssName : 'default', // 可用 'default' 或  'darkneon' 或  'flatwhite'
							//              format: 'yyyy-mm-dd',    // 預設
							//              dowOffset: 0,            // 預設
							//              allowMonthSelect: false, // 預設
							//              allowYearSelect: true,   // 預設
							//              prevArrow: '\u25c4',     // 預設
							//              nextArrow: '\u25ba',     // 預設
							dowNames : [ '<font color=red>星期日</font>', '星期一','星期二', '星期三', '星期四', '星期五','<font color=red>星期六</font>' ], //自定
							monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月','七月', '八月', '九月', '十月', '十一月', '十二月' ], //自定
			 			    <% 

								Calendar calendar = new GregorianCalendar();

							%>
							
							selectableDateRange: // 可選的日期範圍 (白色)
			 			    [
			 			        { from:	new Date(<%= calendar.get(Calendar.YEAR)%>, <%= calendar.get(Calendar.MONTH)%>, <%= calendar.get(Calendar.DAY_OF_MONTH)+1%>),  
			 			        	to: new Date(<%= calendar.get(Calendar.YEAR)+100%>, <%= calendar.get(Calendar.MONTH)%>, <%= calendar.get(Calendar.DAY_OF_MONTH)%>)  },
			 			    ],
							onClick : function(target, cell, date, data) {
								target.val(date.getFullYear()
										+ '-'
										+ (((date.getMonth() + 1) < 10) ? "0"
												+ (date.getMonth() + 1) : (date
												.getMonth() + 1))
										+ '-'
										+ ((date.getDate() < 10) ? "0"
												+ date.getDate() : date
												.getDate()));

								if (data != null) {
									alert(data.message + '\n' + date);
								}
							}

						});
	</script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->
</body>
</html>