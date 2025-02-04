<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.medisync.models.*"%>

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
			alert('<%=successMsg%>
		');
	</script>
	<%
	session.removeAttribute("SuccessMessage");
	}
	%>
	<%@ include file="/doctor/navbar.jsp"%>

	<div class="container-fulid">
		<p class="text-center fs-2 text-white"></p>
	</div>
	<div class="container p-3" name="div">
		<div class="row">
			<div class="col-md-9 mx-auto">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 fw-bold text-center text-success">Patient
							Appointment List</p>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Appointment id</th>
									<th scope="col">Patient Name</th>
									<th scope="col">Appointment Date</th>
									<th scope="col">Time Slot</th>
									<!-- <th scope="col">Symptoms</th> -->
									<th scope="col">Status</th>
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
									<td><%=appointment.getAppointment_date()%></td>
									<td><%=appointment.getAppointment_slot()%></td>
									<td><%=appointment.getStatus()%></td>
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


	<footer class="footer"><%@include file="/component/footer.jsp"%></footer>

</body>
</html>