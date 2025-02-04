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
	
</head>

<body>
	<%@include file="/doctor/navbar.jsp"%>
	<div class="container-fluid p-3">
		<div class="row">
			<div class="col-md-5 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 fw-bold text-center text-success">Update Prescription</p>
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
						if (request.getAttribute("PrescriptionEdit") != null) {
							Prescription prescription = (Prescription) request.getAttribute("PrescriptionEdit");
						%>

						<form action="fetch-prescription" method="post"
							onsubmit="return validateForm()">
							<input type="hidden" id="prescriptionId" name="id"
								value="<%=prescription.getPrescriptionId()%>" class="form-control">

							<div class="mb-3">
								<label class="form-label">Patient Name</label> <input
									type="text" readonly name="patientName" id="patientName"
									value="<%=prescription.getPatient().getName()%>"
									class="form-control"> <input type="hidden"
									name="patientId" id="patientId"
									value="<%=prescription.getPatient().getPatientId()%>">
							</div>

							<div class="mb-3">
								<label class="form-label">Date</label> <input type="date" readonly
									required name="presdate" id="presdate" value="<%=prescription.getDatePrescribed()%>" class="form-control">
								<div id="" class="error-message"></div>
							</div>

							<div class="mb-3">
								<label class="form-label">Medicine Name</label> <input
									type="text" required name="medName" id="medName"
									class="form-control" value="<%=prescription.getMedicineName()%>">
								<div id="" class="error-message"></div>
							</div>

							<div class="mb-3">
								<label class="form-label">Duration (in days)</label> <input
									type="number" required name="duration" id="duration" value="<%=prescription.getDuration()%>"
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