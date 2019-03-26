<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<style>
			footer {
				position: fixed;
				z-index: 999;
				bottom: 0px;
			}
		</style>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
	</head>
	<body>
		<div style="height: 40px;"></div>
		<footer class="container-fluid">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<!-- Copywrite Text -->
						<div class="copy_right_text text-center">
							<p>
								Copyright @2019 All rights reserved | This template is made with
								<i class="fa fa-heart-o" aria-hidden="true"></i> by <a
									href="https://colorlib.com" target="_blank">JOIN揪影影城</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<script src="<%=request.getContextPath()%>/bootstrap/jquery-3.3.1.min.js"></script>
			<script>
				var MyPoint_footer = "/ForTicketorderServlet_ws/${memVO.member_no}";
				var host_footer = window.location.host;
				var path_footer = window.location.pathname;
				var webCtx_footer = path_footer.substring(0, path_footer.indexOf(
						'/', 1));
				var endPointURL_footer = "ws://" + host_footer + webCtx_footer
						+ MyPoint_footer;
				var webSocket_map;
				var isMe_footer = false;
				function connect_footer() {
					// create a websocket
					webSocket_map = new WebSocket(endPointURL_footer);
					webSocket_map.onopen = function(event) {
						// 			alert("i'm start.");
					};
					webSocket_map.onmessage = function(event) {
						if (isMe_footer) {
							return;
						}
						var jsonArray = JSON.parse(event.data);
						var action = jsonArray[0].action;
	
						if (action == "amember") {
							//如果是給這個會員的
							swal(jsonArray[1].message);
						}
					};
					webSocket_map.onclose = function(event) {
					};
				}
				$(document).ready(connect_footer);
			</script>
		</footer>
	</body>
</html>