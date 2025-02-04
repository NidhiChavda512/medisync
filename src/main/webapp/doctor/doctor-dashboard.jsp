<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Dashboard</title>
<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
int timeout = session.getMaxInactiveInterval();
response.setHeader("Refresh", timeout + "; URL = login.jsp");
%>
<%@include file="../component/allcss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}

.footer {
	position: fixed;
	bottom: 0;
	width: 100%;
	background-color: #f5f5f5;
	text-align: center;
}
</style>
</head>
<body style="background-color:#02be58;">
<%
		String successMessage = (String) session.getAttribute("successMessage");
		if (successMessage != null) {
	%>
		<script>
			alert('<%=successMessage%>');
		</script>
	<%
		session.removeAttribute("successMessage");
	}
	%>
	<%
	String u = (String) request.getSession().getAttribute("username");
	if (u != null) {
	%>

	<%@include file="/component/navbar.jsp"%>

	<div class="container p-5">
		<p class="fs-4 fw-bold text-center">Doctor Dashboard</p>


		<div class="row">
			<div class="col-md-4" onclick="window.location.href='<%=request.getContextPath()%>/view-patient-servlet-doctor';">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-circle fa-3x"></i><br> <a
							class="btn  fs-4 text-center" href="<%= request.getContextPath() %>/view-patient-servlet-doctor"> View Patient </a>
					</div>
				</div>
			</div>

			<div class="col-md-4" onclick="window.location.href='<%=request.getContextPath()%>/view-appointment';">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fa-solid fa-calendar-check fa-3x"></i><br> <a
							class="btn  fs-4 text-center" href="<%= request.getContextPath() %>/view-appointment">View Schedule
							 </a>

					</div>
				</div>
			</div>

			<div class="col-md-4" onclick="window.location.href='<%=request.getContextPath()%>/fetch-patient-and-doctor';">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fa-solid fa-capsules fa-3x"></i><br> <a
							class="btn  fs-4 text-center" href="<%= request.getContextPath() %>/fetch-patient-and-doctor">Add Prescription </a>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-4 mt-2" onclick="window.location.href='<%=request.getContextPath()%>/view-prescription';">

				<div class="card paint-card " data-bs-toggle="modal"
					data-bs-target="#exampleModal">
					<div class="card-body text-center text-success">
						<i class="fa-solid fa-file-prescription fa-3x"></i><br> <a
							class="btn  fs-4 text-center" href="<%= request.getContextPath()%>/view-prescription"> View Prescription </a>
					</div>
				</div>
			</div>
			
			<div class="col-md-4 mt-2" onclick="window.location.href='<%=request.getContextPath()%>/medical-records';">
				<div class="card paint-card " data-bs-toggle="modal"
					data-bs-target="#exampleModal">
					<div class="card-body text-center text-success">
						<i class="fa-solid fa-book-medical fa-3x"></i><br> <a
							class="btn  fs-4 text-center" href="<%= request.getContextPath() %>/medical-records"> View Medical Records </a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<%@include file="/component/footer.jsp"%>
	</div>

	<%
	} else {
	response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
	%>
</body>
</html>