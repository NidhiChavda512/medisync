<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.medisync.models.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@include file="../component/allcss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
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
	// Function to display error message below input fields
	function displayError(inputId, errorMessage) {
		var errorDiv = document.getElementById(inputId + "-error");
		errorDiv.innerHTML = errorMessage;
	}

	// Function to clear error message below input fields
	function clearError(inputId) {
		var errorDiv = document.getElementById(inputId + "-error");
		errorDiv.innerHTML = "";
	}
	
	// To check wheather the checkbox of the lab test is ckecked or not.
    function EnableDisableTextBox(checkLabTest) {
        var labTestName = document.getElementById("labTestName");
        labTestName.style.display = checkLabTest.checked ? "block" : "none";
        
       // labTestName.display = checkLabTest.checked ? false : true;
        //if (!labTestName.display) {
        	//labTestName.focus();
       // }
    }

	// Function to validate input fields
</script>
</head>
<body>
	<%@ include file="/doctor/navbar.jsp"%>
	<div class="container-fluid p-3">
		<div class="row">
			<div class="col-md-5 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 fw-bold text-center text-success">Add Prescription</p>
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
						//String patientName = request.getParameter("patientName");
						%>
						<form action="add-prescription-servlet" method="post"
							onsubmit="return validateForm()">

							<div class="mb-3">
								<label for="inputEmail4" class="form-label">Select
									Patient Name</label> <select class="form-control" name="patientId"
									required>
									<option value="">-- Select Patient --</option>
									<%
										if (request.getAttribute("patientNames") != null) {
											List<Patient> patientNames = (List<Patient>) request.getAttribute("patientNames");
											for (Patient patient : patientNames) {
												//out.println(patient.getName());
									%>
										<option value="<%= patient.getPatientId() %>" id="fullname"> <%= patient.getName() %></option>
			
										<%
										}
										}
										%>
									
								</select>
							</div>

							<div class="mb-3">
								<label class="form-label">Medicine Name</label> <input
									type="text" required name="medname" id="medname"
									class="form-control">
								<div id="" class="error-message"></div>
							</div>

							<div class="mb-3">
								<label class="form-label">Duration (in days)</label> <input
									type="number" required name="duration" id="duration"
									class="form-control">
								<div id="" class="error-message"></div>
							</div>

							<div class="mb-3">
								<label class="form-label">Symptoms</label>
								<textarea required name="symptoms" id="symptoms"
									class="form-control">
								</textarea>
								<div id="" class="error-message"></div>
							</div>

							<div class="mb-3">
								<label class="form-label">Diagnosis</label>
								<textarea required name="diagnosis" id="diagnosis"
									class="form-control">
								</textarea>
								<div id="" class="error-message"></div>
							</div>

							<!-- <div class="mb-3">
								
								<input type="checkbox" id="LabTest" name="labTest" value="Is Lab Test Required?">
								<label class="form-label">Is Lab Test Required?</label> 
								<div id="" class="error-message"></div>
							</div>-->



							<div class="mb-3">
								<label for="checkLabTest" class="form-label"> <input
									type="checkbox" id="checkLabTest"
									onclick="EnableDisableTextBox(this)" /> Is Lab Test Required?
								</label>
							</div>
							<br />

							<div id="labTestName" class="mb-3" style="display: none">
								Lab Test Name: <input type="text" id="labTestName" name="LabTestName" />
							</div>

							<center><button type="submit" class="btn btn-primary button-color">Submit</button></center>
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