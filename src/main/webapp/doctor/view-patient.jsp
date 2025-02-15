
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page isELIgnored="false"%>
<%@ page import="java.util.*" %>
<%@ page import="com.medisync.models.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Patient to Doctor</title>
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
	String successMsg = (String) session.getAttribute("SuccessMessage");
	if (successMsg!=null) {
	%>
	<script>
			alert('<%=successMsg%>');
	</script>
	<%
	session.removeAttribute("SuccessMessage");
	//int loggedInDoctorId = (int)session.getAttribute("doctorId");
	}
	%>
	<%@ include file="/doctor/navbar.jsp"  %>
	
	<div class="container-fulid">
		<p class="text-center fs-2 text-white"></p>
	</div>
	<div class="container p-3" name="div">
		<div class="row">
			<div class="col-md-9 mx-auto">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 fw-bold text-center text-success">Patient List</p>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Patient ID</th>
									<th scope="col">Full Name</th>
									<th scope="col">Date of Birth</th>
									<th scope="col">Gender</th>
									<th scope="col">Mobile No</th>
									<th scope="col">Email</th>
									<th scope="col">Registration Date</th>
								</tr>
							</thead>
							<tbody>
								<%
								if (request.getAttribute("patientList") != null) {
									List<Patient> patientList = (List<Patient>) request.getAttribute("patientList");
									for (Patient patient : patientList) {
								%>
								<tr>
									<td><%=patient.getPatientId()%></td>
									<td><%=patient.getName()%></td>
									<td><%=patient.getDateOfBirth()%></td>
									<td><%=patient.getGender()%></td>
									<td><%=patient.getContactNo()%></td>
									<td><%=patient.getEmail()%></td>
									<td><%=patient.getRegistrationDate()%></td>
									
								</tr>
								<%
									}
									
								} else { 
									if (session.getAttribute("patientListError")!=null) {
										String patientListError = (String) session.getAttribute("patientListError");
										
								%>
										<tr>
										<td><h3>No Result Found</h3></td>
										</tr>
										<%
											session.removeAttribute("SuccessMessage");
										}
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>


	<footer  class="footer"><%@include file="/component/footer.jsp"%></footer>
	
</body>
</html>