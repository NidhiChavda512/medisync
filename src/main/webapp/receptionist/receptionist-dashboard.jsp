<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index page</title>
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
<script>
  document.getElementById("clickable-card").addEventListener("click", function() {
    window.location.href = "/receptionist/add-patient.jsp";
  });
</script>
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
		<p class="fs-4 fw-bold text-center">Receptionist Dashboard</p>


		<div class="row">
			<div class="col-md-4" >
				<div class="card paint-card"  id="clickable-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-circle fa-3x "></i><br> <a
							class="btn  fs-4 text-center"
							href="<%=request.getContextPath()%>/receptionist/add-patient.jsp">
							Register Patient </a>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fa-solid fa-eye fa-3x"></i><br> <a
							class="btn  fs-4 text-center"
							href="<%=request.getContextPath()%>/view-patient">View
							Patient </a>

					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fa-regular fa-calendar-check fa-3x"></i><br> <a
							class="btn  fs-4 text-center"
							href="<%=request.getContextPath()%>/fetch-doctor-patient">
							Schedule Appointment </a>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-4 mt-2">

				<div class="card paint-card " data-bs-toggle="modal"
					data-bs-target="#exampleModal">
					<div class="card-body text-center text-success">
						<i class="fa-solid fa-calendar-check fa-3x"></i><br> <a
							class="btn  fs-4 text-center"
							href="<%=request.getContextPath()%>/view-appointment"> View
							Appointment </a>
					</div>
				</div>
			</div>

			<div class="col-md-4 mt-2">
				<div class="card paint-card " data-bs-toggle="modal"
					data-bs-target="#exampleModalLabTest">
					<div class="card-body text-center text-success">
						<i class="fa-solid fa-flask fa-3x"></i><br> <a
							class="btn  fs-4 text-center"
							href="<%=request.getContextPath()%>/view-labtest">View Lab
							Test</a>
					</div>
				</div>
			</div>
			
			<div class="col-md-4 mt-2">
				<div class="card paint-card " data-bs-toggle="modal"
					data-bs-target="#exampleModalLabTest">
					<div class="card-body text-center text-success">
						<i class="fa-solid fa-book-medical fa-3x"></i><br> <a
							class="btn  fs-4 text-center"
							href="<%=request.getContextPath()%>/medical-records">Patient Medical Records</a>
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
