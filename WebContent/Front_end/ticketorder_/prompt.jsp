 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS start-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
<!-- Bootstrap CSS end-->
<title>購票須知</title>
</head>
<body>
	<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Header.jsp" />

	<!-- 工作區開始 -->
	<div class="container">
		<div class="row justify-content">

			<div class="col-12">
				<h1>購票須知</h1>
				<pre style="font-size:20px">
退、換票說明 :
購票後欲辦理退、換票,請於該場次開演前30分鐘辦理,影城並酌收手續費20元/張,逾時非歸屬戲院責任者,恕不接受辦理退
、換票。
信用卡購票觀眾辦理退、換票,請持原信用卡及簽單於該場次開演時間前30分鐘辦理,影城並酌收手續費20元/張,逾時非歸
屬戲院責任者,恕不接受辦理退、換票。
若遇天候因素而暫停營業,觀眾可於影城恢復正常營業後7日內憑當日未使用之原票券至影城櫃檯辦理退、換票。
票券遺失恕不補發,亦無法辦理退、換票。
						
外食相關說明 :
為維持觀影品質,請勿攜帶氣味嗆辣之食物進場。
請勿攜帶酒精性之飲品進場。
請勿攜帶具有熱湯、食用時發出聲響足以影響他人觀影之食物進場。
				 
觀影注意事項 :
為保護智慧財產,所有影廳將嚴密監控,並拒絕任何錄影器材在廳內錄影。
公共場所全面禁菸。
請勿嚼食檳榔。
請勿攜帶寵物入場。
				</pre>

			</div>
		</div>
	</div>
	<!-- 工作區結束 -->
	
	<jsp:include page="/FrontHeaderFooter/FrontBootstrapHeaderFooter/Footer.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS start-->
	<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS end-->

</body>
</html>