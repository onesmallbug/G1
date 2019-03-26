
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
	<head>
	<!-- Required meta tags -->
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Bootstrap CSS start-->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
	<!-- Bootstrap CSS end-->
	<title></title>
	</head>
	<body>
	
		<h1></h1>
	
		<!-- 工作區開始 -->
	
	
	
		<div class="container-fluid" style="margin-top: 80px;">
			<!--     	<div class="row"> -->
			<table class="table table-hover">
				<tr>
					<th scope="row">會員近期優惠活動</th>
				</tr>
				<tr>
					<td>
						<ol>
							<li>點選廣告專區之官方網站</li>
							<li>點選「會員享指定商品買一送一優惠」</li>
							<li>閱讀活動說明與注意事項（同意即可繼續使用此功能，不同意則會離開此頁面）</li>
							<li>輸入您Join影城會員帳號(email)及密碼</li>
							<li>選擇欲享優惠之活動品項</li>
							<li>確認已點選的優惠活動品項</li>
							<li>單據（優惠券）列印</li>
						</ol>
					</td>
				</tr>
			</table>
			<!--     		</div> -->
		</div>
	
		<!-- 工作區結束 -->
		<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/bootstrap/popper.min.js"></script>
<%-- 		<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script> --%>
		<!-- jQuery first, then Popper.js, then Bootstrap JS end-->
	
	</body>
</html>