<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"--%>
<%@page isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="com.medisync.models.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Medical Records Patient List</title>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="col-3 ml-auto">
		<input type="text" id="searchInput" class="form-control"
			placeholder="Search Patient by Name or Email">
	</div>
	<script>
		$(document).ready(function() {
			$("#searchInput").on("input", function() {
				var searchValue = $(this).val();
				$.ajax({
					url : "search-patient",
					type : "GET",
					data : {
						patientName : searchValue
					},
					success : function(response) {
						// Handle the response, e.g., update the UI with search results
						$("#patientTableBody").html(response);
						console.log(this.responseText);
					}
				});
			});
		});

		function clearSearch() {
			$("#searchInput").val(""); // Clear the search input
			$.ajax({
				url : "search-patient",
				type : "GET",
				data : {
					patientName : ""
				}, // Send an empty string to indicate all records
				success : function(response) {
					// Replace the table body with the fetched result
					$("#patientTableBody").html(response);
				}
			});
		}
	</script>
	<div class="container-fulid">
		<p class="text-center fs-2 text-white"></p>
	</div>
	<div class="container p-3" name="div">
		<div class="row">
			<div class="col-md-9 mx-auto">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 fw-bold text-center text-success">Medical
							Records Patient List</p>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Patient ID</th>
									<th scope="col">Full Name</th>
									<th scope="col">Email</th>
									<th scope="col">Total Medical Records</th>
									<th scope="col">Medical Records</th>
								</tr>
							</thead>
							<tbody id="patientTableBody">
								<%
								if (request.getAttribute("patientList") != null && request.getAttribute("countMedicalRecords") != null) {
									List<Patient> patientList = (List<Patient>) request.getAttribute("patientList");
									Map<Integer, Integer> countMedicalRecords = (Map<Integer, Integer>) request.getAttribute("countMedicalRecords");

									for (Patient patient : patientList) {
								%>
								<tr>
									<td><%=patient.getPatientId()%></td>
									<td><%=patient.getName()%></td>
									<td><%=patient.getEmail()%></td>
									<%
									Iterator<Integer> it = countMedicalRecords.keySet().iterator(); //keyset is a method
									boolean gotPatient = false;

									while (it.hasNext()) {
										int key = (int) it.next();
										if (key == patient.getPatientId()) {
											gotPatient = true;
									%>
									<td><%=countMedicalRecords.get(key)%></td>

									<%
									}
									%>
									<%
									}

									if (gotPatient == false) {
									%>
									<td>0</td>
									<%
									}
									%>
									<td>
										<form
											action="<%=request.getContextPath()%>/show-medical-records"
											method="post">

											<input type="hidden" name="id"
												value="<%=patient.getPatientId()%>">
											<button type="submit" class="btn btn-sm btn-success">Show</button>	
										</form>
									</td>
										
										
								</tr>
								<%
								}
								} else {
								if (session.getAttribute("patientListError") != null) {
								String patientListError = (String) session.getAttribute("patientListError");
								%>
								<tr>
									<td><h3>No Result Found</h3></td>
								</tr>
								<%
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
