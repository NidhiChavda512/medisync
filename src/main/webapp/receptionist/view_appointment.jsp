<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"--%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Appointment</title>
<%
	response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = login.jsp");
%>
<%@include file="/component/allcss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
.footer {
    position: fixed;
    bottom: 0;
    width: 100%;
    background-color: #f5f5f5;
    padding: 10px 0;
    text-align: center;
}

/*.backImg {
	background: linear-gradient(rgba(0, 0, 0, .4), rgba(0, 0, 0, .4)),
		url("img/hospital.jpg");
	height: 20vh;
	width: 100%;
	background-size: cover;
	background-repeat: no-repeat;
}*/
</style>
</head>
<body>
	<%
		String u = (String) request.getSession().getAttribute("username");
    	if (u != null ) {
    %>
	<!--<c:if test="${empty userObj }">
		<c:redirect url="user_login.jsp"></c:redirect>
	</c:if>-->
	<%@include file="../component/navbar.jsp"%>

	<div class="container-fulid">
		<p class="text-center fs-2 text-white"></p>
	</div>
	<div class="container p-3">
		<div class="row">
			<div class="col-md-9 mx-auto">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 fw-bold text-center text-success">Appointment
							List</p>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Full Name</th>
									<th scope="col">Appoint Date</th>
									<th scope="col">Appointment Time</th>
									<th scope="col">Doctor Name</th>
									<th scope="col">Status</th>

								</tr>
							</thead>
						</table>

					</div>
				</div>
			</div>
			
			
		</div>
	</div>

	<div class="footer">
        <%@include file="/component/footer.jsp"%>
    </div>
    <%
    	}else{
        	response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    %>
</body>
</html>
