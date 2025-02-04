<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.medisync.models.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit LabTest</title>
<%@include file="/component/allcss.jsp"%>
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
    var inputDate = new Date(document.getElementById('labtest_date').value);
    var currentDate = new Date();

    if (inputDate > currentDate) {
        displayError("labtest_date", "Please Enter a Valid Date");
        isValid = false;
    } else {
        clearError("labtest_date");
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
						<p class="fs-4 fw-bold text-center text-success">Add lab Test</p>
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
						if (request.getAttribute("fetchlabtest") != null) {
							LabTest labtest = (LabTest) request.getAttribute("fetchlabtest");
						%>
						<form class="row g-3" action="add-labtest" method="post"
							onsubmit="return validateDate();">

							<div class="row-md-6">
								<input type="hidden" id="labtestId" name="labtestId"
									value="<%=labtest.getLabTestId()%>" class="form-control">


								<label class="form-label">Patient Name</label> <input
									type="text" readonly name="patientName" id="patientName"
									value="<%=labtest.getPatient().getName()%>"
									class="form-control"> <input type="hidden"
									name="patientId" id="patientId"
									value="<%=labtest.getPatient().getPatientId()%>"/>

							</div>

							<div class="row-md-6">

								<label class="form-label">Doctor Name</label> <input type="text"
									readonly name="doctorName" id="doctorName"
									value="<%=labtest.getDoctor().getName()%>" class="form-control">
								<input type="hidden" name="doctorId" id="doctorId"
									value="<%=labtest.getDoctor().getDoctorId()%>">


							</div>

							<div class="row-md-6">
								<label for="form_label" class="form-label">LabTest Name</label>
								<input id="labtest_name" name="labtest_name" readonly
									value="<%=labtest.getLabTestName()%>" type="text"
									class="form-control" required />
							</div>



							<div class="row-md-6">
								<label for="form_label" class="form-label">LabTest Date</label>
								<input id="labtest_date" type="date" class="form-control"
									name="labtest_date"  required />

							</div>



							<div class="row-md-6">
								<label class="form-label">LabTest Result</label>
								<textarea name="labtest_result" id="labtest_result"
									class="form-control"  required></textarea>


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
