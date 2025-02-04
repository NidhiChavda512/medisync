<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.medisync.models.*"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../component/allcss.jsp"%>

<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.3);
}

.footer {
	position: scrollable;
	bottom: 0;
	width: 100%;
	background-color: #f5f5f5;
	text-align: center;
}

.error-message {
	color: red;
	font-size: 12px;
}
.button-color {
	background-color: #0cd268;
	color: white;
	margin-bottom:8px;
}
</style>
<script>
	function displayError(inputId, errorMessage) {
		var errorDiv = document.getElementById(inputId + "-error");
		errorDiv.innerHTML = errorMessage;
	}

	// Function to clear error message below input fields
	function clearError(inputId) {
		var errorDiv = document.getElementById(inputId + "-error");
		errorDiv.innerHTML = "";
	}

	function validateDate() {
		var isValid = true;
		var inputDate = new Date(document.getElementById('appoint_date').value);
		var currentDate = new Date();

		if (inputDate > currentDate) {
			displayError("appoint_date", "Please Enter a Valid Date");
			isValid = false;
		} else {
			clearError("appoint_date");
		}

		return isValid;
	}
</script>



</head>

<body>
	<%@include file="navbar.jsp"%>
	<div class="container-fluid p-3">
		<div class="row">
			<div class="col-md-5 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 fw-bold text-center text-success">Update Appointment</p>
						<%
						String errorMessage = (String) session.getAttribute("errorMessage");
						if (errorMessage != null) {
						%>
						<script>
								alert('<%=errorMessage%>
							');
						</script>
						<%
						session.removeAttribute("errorMessage");
						}
						%>
						<%
						if (request.getAttribute("appointmentEdit") != null) {
							Appointment appointment = (Appointment) request.getAttribute("appointmentEdit");
						%>

						<form action="fetch-appointment" method="post"
							onsubmit="return validateForm()">
							<input type="hidden" id="appointmentId" name="appointmentId"
								value="<%=appointment.getAppointmentId()%>" class="form-control">

							<div class="mb-3">
								<label class="form-label">Patient Name</label> <input
									type="text" readonly name="patientName" id="patientName"
									value="<%=appointment.getPatient().getName()%>"
									class="form-control"> <input type="hidden"
									name="patientId" id="patientId"
									value="<%=appointment.getPatient().getPatientId()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">Appointment Date</label> <input
									type="date" required name="appoint_date" id="appoint_date"
									value="<%=appointment.getAppointment_date()%>"
									class="form-control">
								<div id="dob-error" class="error-message"></div>
							</div>

							<div class="mb-3">
								<label for="inputEmail4" class="form-label">Appointment
									Time</label> <select class="form-control" name="appoint_slot" required>
									<option value="<%=appointment.getAppointment_slot()%>"><%=appointment.getAppointment_slot()%></option>
									<option value="10:00 AM - 11:00 AM">10:00 AM - 11:00AM</option>
									<option value="11:00 AM - 12:00 PM">11:00 AM - 12:00
										PM</option>
									<option value="12:00 PM - 1:00 PM">12:00 PM - 1:00 PM</option>
									<option value="1:00 PM - 2:00 PM">1:00 PM - 2:00 PM</option>
									<option value="2:00 PM - 3:00 PM">2:00 PM - 3:00 PM</option>
									<option value="3:00 PM - 4:00 PM">3:00 PM - 4:00 PM</option>
									<option value="4:00 PM - 5:00 PM">4:00 PM - 5:00 PM</option>
									<option value="5:00 PM - 6:00 PM">5:00 PM - 6:00 PM</option>

									<%--
							Set<String> availableSlots = (Set<String>) application.getAttribute("availableSlots");
							if (availableSlots != null) {
								for (String slot : availableSlots) {
							--%>

									<%--
							}
							}
							--%>
								</select>
							</div>

							<div class="mb-3">
								<label class="form-label">Select Doctor Name</label> <select
									class="form-control" name="doctorName" required>
									<option value="<%=appointment.getDoctor().getDoctorId()%>"><%=appointment.getDoctor().getName()%></option>

									<%
									if (request.getAttribute("doctorNames") != null) {
										Map<Integer, String> doctorNames = (Map<Integer, String>) request.getAttribute("doctorNames");
										Iterator<Integer> it = doctorNames.keySet().iterator(); //keyset is a method  
										while (it.hasNext()) {
											int key = (int) it.next();
									%>
									<option value="<%=key%>"><%=doctorNames.get(key)%></option>
									<%
									}

									}

									else {
									System.out.println("doctor names not found in request attributes.");
									}
									%>
								</select>


							</div>

							<div class="mb-3">
								<label class="form-label">Status</label> <input
									type="text" readonly name="status" id="status"
									value="<%=appointment.getStatus()%>"
									class="form-control">
							</div>

							<center><button type="submit" class="btn btn-primary button-color">Submit</button></center>
						</form>
						<%
						}
						%>


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