<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"--%>
<%@page isELIgnored="false"%>
<%@page isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="com.medisync.models.*"%>
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
	text-align: center;
}

.button-color {
	background-color: #0cd268;
	color: white;
	margin-bottom:8px;
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
	String appointmentSuccessMsg = (String) request.getAttribute("appointmentSuccess");
	String editAppointmentSuccessMsg = (String) request.getAttribute("editAppointmentSuccess");

	if (appointmentSuccessMsg != null) {
	%>
	<script>
			alert('<%=appointmentSuccessMsg%>');
	</script>
	<%
	request.removeAttribute("appointmentSuccess");
	}

	if (editAppointmentSuccessMsg != null) {
	%>
	<script>
				alert('<%=editAppointmentSuccessMsg%>
		');
	</script>
	<%
	request.removeAttribute("editAppointmentSuccessMsg");
	}
	%>
	<%@include file="navbar.jsp"%>





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
									<th scope="col">Appointment id</th>
									<th scope="col">Patient Name</th>
									<th scope="col">Doctor Name</th>
									<th scope="col">Appointment Date</th>
									<th scope="col">Appointment Time</th>
									<th scope="col">Status</th>
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody>
								<%
								if (request.getAttribute("appointmentList") != null) {
									List<Appointment> appointmentList = (List<Appointment>) request.getAttribute("appointmentList");
									for (Appointment appointment : appointmentList) {
								%>
								<tr>
									<td><%=appointment.getAppointmentId()%></td>
									<td><%=appointment.getPatient().getName()%></td>
									<td><%=appointment.getDoctor().getName()%></td>
									<td><%=appointment.getAppointment_date()%></td>
									<td><%=appointment.getAppointment_slot()%></td>
									<td><%=appointment.getStatus()%></td>

									<td><form
											action="<%=request.getContextPath()%>/edit-appointment"
											method="post">

											<input type="hidden" name="id"
												value="<%=appointment.getAppointmentId()%>">
											<button type="submit" class="btn btn-sm btn-success">Edit</button>	
										</form>
										<br> <a
										href="delete-appointment?id=<%=appointment.getAppointmentId()%>" class="btn btn-sm btn-danger"
										onclick=" return confirm('Are You Sure You Want to Delete appointment?')">Delete</a>
									</td>
								</tr>
								<%
								}

								} else {
								if (session.getAttribute("appointmentListError") != null) {
								String patientListError = (String) session.getAttribute("appointmentListError");
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

	<div class="footer">
		<%@include file="/component/footer.jsp"%>
	</div>

</body>
</html>
