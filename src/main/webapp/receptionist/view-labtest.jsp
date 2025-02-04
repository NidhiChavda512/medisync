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
<title>View Labtest</title>
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
	position:fixed;
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
	String labResultSuccessMsg = (String) request.getAttribute("labResultSuccess");
	
	if (labResultSuccessMsg!=null) {
	%>
	<script>
			alert('<%=labResultSuccessMsg%>');
	</script>
	<%
	session.removeAttribute("labResultSuccessMsg");
	}
	%>
	<%@include file="navbar.jsp"%>

	<div class="container-fulid">
		<p class="text-center fs-2 text-white"></p>
	</div>
	<div class="container p-3 " name="div">
		<div class="row">
			<div class="col-md-9 mx-auto">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 fw-bold text-center text-success">LabTest List</p>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">labTest ID</th>
									<th scope="col">Patient Name</th>
									<th scope="col">Doctor Name</th>
									<th scope="col">Test Name</th>
									<th scope="col">Test Date</th>
									<th scope="col">Test Result</th>
									<th scope="col" colspan="2">Action</th>
								</tr>
							</thead>
							<tbody>
								<%
								if (request.getAttribute("labtestList") != null) {
									List<LabTest> labtestList = (List<LabTest>) request.getAttribute("labtestList");
									for (LabTest  labtest:labtestList) {
								%>
								<tr>
									<td><%=labtest.getLabTestId()%></td>
									<td><%=labtest.getPatient().getName()%></td>
									<td><%=labtest.getDoctor().getName()%></td>
									<td><%=labtest.getLabTestName()%></td>
									<td>
									<%
									
									if(labtest.getTestDate()== null )
										out.println("-");
									else
										out.println(labtest.getTestDate());
									%>
										</td>
									<td>
									<%
									
									if(labtest.getTestResult()== null  || labtest.getTestResult().isEmpty())
										out.println("-");
									else
										out.println(labtest.getTestResult());
									%>
									
									<td>
										<form
											action="<%=request.getContextPath()%>/fetch-labtest"
											method="post">

											<input type="hidden" name="id"
												value="<%=labtest.getLabTestId()%>">
											<button type="submit" class="btn btn-sm btn-success">Edit</button>	
										</form>
										<a href="delete-labtest?id=<%=labtest.getLabTestId()%>" class="btn btn-sm btn-danger" onclick=" return confirm('Are You Sure You Want to Delete labtest?')">Delete</a>
									</td>
								</tr>
								<%
									}
									
								} else { 
									if (session.getAttribute("labtestListError")!=null) {
										String patientListError = (String) session.getAttribute("labtestListError");
										
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
