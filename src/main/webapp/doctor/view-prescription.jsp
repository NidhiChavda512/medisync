
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="com.medisync.models.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Prescription to Doctor</title>
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
	margin-bottom: 8px;
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
	if (successMsg != null) {
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
							Prescription List</p>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Prescription Id</th>
									<th scope="col">Patient Name</th>
									<th scope="col">Medicine Name</th>
									<th scope="col">Prescribed Date</th>
									<!--<th scope="col">Appointment Date</th>-->
									<th scope="col">Duration</th>
									<th scope="col">Action</th>
									<!--<th scope="col">LabTest</th>-->
								</tr>
							</thead>

							<tbody>
								<%
								if (request.getAttribute("prescriptionList") != null) {
									List<Prescription> prescriptionList = (List<Prescription>) request.getAttribute("prescriptionList");
									for (Prescription prescription : prescriptionList) {
								%>
								<tr>

									<td><%=prescription.getPrescriptionId()%></td>
									<td><%=prescription.getPatient().getName()%></td>
									<td><%=prescription.getMedicineName()%></td>
									<td><%=prescription.getDatePrescribed()%></td>
									<td><%=prescription.getDuration()%></td>
									<td><form
											action="<%=request.getContextPath()%>/fetch-prescription"
											method="post">

											<input type="hidden" name="id"
												value="<%=prescription.getPrescriptionId()%>"> <input
												type="hidden" name="patientName"
												value="<%=prescription.getPatient().getName()%>"> <input
												type="hidden" name="medName"
												value="<%=prescription.getMedicineName()%>"> <input
												type="hidden" name="datePrescribed"
												value="<%=prescription.getDatePrescribed()%>"> <input
												type="hidden" name="duration"
												value="<%=prescription.getDuration()%>">
											<a class="btn btn-sm button-color"
												href="<%=request.getContextPath()%>/edit-prescription?id=<%=prescription.getPrescriptionId()%>">Edit</a>
										</form></td>
									<%-- <td><a class="btn btn-sm button-color"
										href="<%=request.getContextPath()%>/fetch-prescription?id=<%=prescription.getPrescriptionId()%>">Edit</a></td>--%>
									<%--<td><a class="btn btn-sm button-color"
										href="<%=request.getContextPath()%>/view-labtest?id=<%=prescription.getPrescriptionId()%>">LabTest</a>

									</td>--%>

								</tr>
								<%
								}

								} else {
								if (session.getAttribute("prescriptionListError") != null) {
								String prescriptionListError = (String) session.getAttribute("prescriptionListError");
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