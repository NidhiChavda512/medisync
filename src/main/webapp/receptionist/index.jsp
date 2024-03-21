<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index page</title>
<%
	response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = login.jsp");
%>
<%@include file="../component/allcss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
.footer {
    position: fixed;
    bottom: 0;
    width: 100%;
    background-color: #f5f5f5;
    padding: 10px 0;
    text-align: center;
}
</style>
</head>
<body>
	<%
		String u = (String) request.getSession().getAttribute("username");
    	if (u != null ) {
    %>
    
	<%@include file="/component/navbar.jsp"%>

	<div class="container p-5">
		<p class="text-center fs-3">Receptionist Dashboard</p>


		<div class="row">
			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-circle fa-3x"></i><br> <a
							class="btn  fs-4 text-center" href="receptionist/add-patient.jsp"> Register Patient
						</a>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fa-solid fa-eye fa-3x"></i><br> <a
							class="btn  fs-4 text-center" href="view-patient">View Patient
						</a>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fa-regular fa-calendar-check fa-3x"></i><br>
						<a
							class="btn  fs-4 text-center" href="appointment.jsp">
							Schedule Appointment </a>
					</div>
				</div>
			</div>

			<div class="col-md-4 mt-2">

				<div class="card paint-card " data-bs-toggle="modal"
					data-bs-target="#exampleModal">
					<div class="card-body text-center text-success">
						<i class="fa-solid fa-calendar-check fa-3x"></i><br>
						<a
							class="btn  fs-4 text-center" href="view_appointment.jsp">
							View Appointment </a>
					</div>
				</div>

			</div>

		</div>
	</div>



	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="../addSpecialist" method="post">

						<div class="form-group">
							<label>Enter Specialist Name</label> <input type="text"
								name="specName" class="form-control">
						</div>
						<div class="text-center mt-3">
							<button type="submit" class="btn btn-primary">Add</button>
						</div>

					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>

				</div>
			</div>
		</div>
	</div>
	
	 
        <div class="footer">
        <%@include file="/component/footer.jsp"%>
    </div>
    
    <%
    	}else{
        	response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    %>
</body>
</html>
