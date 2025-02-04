<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.medisync.models.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment</title>
<%@include file="/component/allcss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.3);
}

.footer {
	position: fixed;
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

    if (inputDate < currentDate) {
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

	<div class="container-fulid">
		<p class="text-center fs-2 text-white"></p>
	</div>
	<div class="container p-3 center-div">
		<div class="row">
			<div class="col-md-5 mx-auto">
				<div class="card paint-card ">
					<div class="card-body">
						<p class="fs-4 fw-bold text-center text-success">Schedule Appointment</p>
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
						<form action="add-appointment" method="post"
							onsubmit="return validateDate();">





							<div class="mb-3">
								<label for="inputEmail4" class="form-label">Select
									Patient Name</label> <select class="form-control" name="patientName"
									required>
									<option value="">-- Select Patient --</option>

									<%
									if (request.getAttribute("patientNames") != null) {
										Map<Integer, String> patientNames = (Map<Integer, String>) request.getAttribute("patientNames");
										Iterator<Integer> it = patientNames.keySet().iterator(); //keyset is a method
										while (it.hasNext()) {
											int key = (int) it.next();
									%>
									<option value="<%=key%>"><%=patientNames.get(key)%></option>
									<%
									}
									}
									%>
								</select>
							</div>


							<div class="mb-3">
								<label for="appoint_date" class="form-label">Appointment
									Date</label> <input id="appoint_date" type="date" class="form-control"
									required name="appoint_date" />
								<div id="appoint_date-error" class="error-message"></div>

							</div>

							<div class="mb-3">
								<label for="inputEmail4" class="form-label">Appointment
									Time</label> <select class="form-control" name="appoint_slot" required>
									<option value="">--Select Time--</option>
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
								<label for="inputEmail4" class="form-label">Select
									Doctor Name</label> <select class="form-control" name="doctorName"
									required>
									<option value="">-- Select Doctor --</option>
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
									%>
								</select>

							</div>

							<center>
								<button type="submit" class="btn button-color">Submit</button>
							</center>

						</form>
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
