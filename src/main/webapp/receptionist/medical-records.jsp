<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.medisync.models.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Medical Records</title>
<!-- Include your CSS and JavaScript files here -->
<%@include file="/component/allcss.jsp"%>
<style>
.footer {
	position: scrollable;
	bottom: 0;
	width: 100%;
	background-color: #f5f5f5;
	text-align: center;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<%@include file="/doctor/navbar.jsp"%>
	<p class="fs-4 fw-bold text-center text-success">Medical Records</p>

	<!-- Date Filter Form -->
	<center>
	<form action="show-medical-records" method="get">

		<label for="startDate">Start Date:</label> <input type="date"
			id="startDate" name="startDate"> 
			<label for="endDate">End Date:</label> <input type="date" id="endDate" name="endDate"><%--  <input
			type="submit" value="Filter">
			<a href="<%= request.getContextPath()%>/medical-records" class="btn btn-sm">Back</a> --%>
	</form>
	<br></center>

	<%
	if (session.getAttribute("PatientID") != null) {
		System.out.println("In JSP Page : " + session.getAttribute("PatientID"));
	} else {
		System.out.println("In JSP Page");
	}
	List<MedicalRecord> medicalRecordList = (List<MedicalRecord>) request.getAttribute("MedicalRecordList");
	if (medicalRecordList != null && !medicalRecordList.isEmpty()) {
		for (MedicalRecord medicalRecord : medicalRecordList) {
	%>
<center>
	<div class="card " style="width: 30rem;">
  <div class="card-body">
    <div id="medicalRecords"
		style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">
		<p>
			<strong>Record ID:</strong>
			<%=medicalRecord.getRecordId()%></p>
		<input type="hidden"
			value="<%=medicalRecord.getPatient().getPatientId()%>"
			id="patientId" />
		<p>
			<strong>Patient:</strong>
			<%=medicalRecord.getPatient().getName()%></p>
		<p>
			<strong>Doctor:</strong> Dr.
			<%=medicalRecord.getPrescription().getDoctor().getName()%></p>
		<p>
			<strong>Prescription:</strong>
			<%=medicalRecord.getPrescription().getPrescriptionId()%></p>
		<p>
			<strong>Prescription Date:</strong>
			<%=medicalRecord.getPrescription().getDatePrescribed()%></p>
		<p>
			<strong>Lab Test:</strong>
			<%=medicalRecord.getLabTest().getLabTestName()%></p>
		<p>
			<strong>Lab Test Date:</strong>
			<strong>
			<%
			if (medicalRecord.getLabTest().getTestDate() == null)
				out.println("-");
			else
				out.println(medicalRecord.getLabTest().getTestDate());
			%>
			</strong>
		</p>
		<p>
			<strong>Lab Test Result:</strong>
			<strong>
			<%
			if (medicalRecord.getLabTest().getTestResult() == null || medicalRecord.getLabTest().getTestResult().isEmpty())
				out.println("-");
			else
				out.println(medicalRecord.getLabTest().getTestResult());
			%>
			</strong>
			</p>
		<p>
			<strong>Symptoms:</strong>
			<%=medicalRecord.getSymptoms()%></p>
		<p>
			<strong>Recommended Medicine:</strong>
			<%=medicalRecord.getPrescription().getMedicineName()%></p>
		<p>
			<strong>Duration for Medicine (In Days):</strong>
			<%=medicalRecord.getPrescription().getDuration()%></p>
		<p>
			<strong>Diagnosis:</strong>
			<%=medicalRecord.getDiagnosis()%></p>
	</div>
    
  </div>
</div>

</center>		<script>
		$(document).ready(function() {
			$('#filterForm').submit(function(event) {
				event.preventDefault();
				var startDate = $('#startDate').val();
				var endDate = $('#endDate').val();
				var patientId = $('#patientId').val();
				if (startDate !== '' && endDate !== '' && patientId !== '') {
					filterRecords(startDate, endDate, patientId);
				} else {
					showAllRecords();
				}
			});

			// Function to filter records based on start and end dates
			function filterRecords(startDate, endDate) {
				$.ajax({
					type : 'GET',
					url : 'show-medical-records-by-date',
					data : {
						startDate : startDate,
						endDate : endDate
					},
					success : function(data) {
						$('#medicalRecords').html(data);
					},
					error : function(xhr, status, error) {
						console.error(xhr.responseText);
					}
				});
			}

			// Function to show all records without any filters
			function showAllRecords() {
				$.ajax({
					type : 'GET',
					url : 'show-medical-records',
					success : function(data) {
						$('#medicalRecords').html(data);
					},
					error : function(xhr, status, error) {
						console.error(xhr.responseText);
					}
				});
			}

			// Event listener for date inputs to trigger filtering
			$('#startDate, #endDate').change(function() {
				var startDate = $('#startDate').val();
				var endDate = $('#endDate').val();
				if (startDate !== '' && endDate !== '') {
					filterRecords(startDate, endDate);
				} else {
					showAllRecords();
				}
			});
		});
	</script>
	<%
	}
	} else {
	%>
	<p>No medical records found.</p>
	<%
	}
	%>
<footer class="footer"><%@include file="/component/footer.jsp"%></footer>
</body>
</html>