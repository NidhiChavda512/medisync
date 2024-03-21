<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
    padding: 10px 0;
    text-align: center;
}
</style>
</head>
<body>
	<%@include file="navbar.jsp"%>

	<div class="container-fulid">
		<p class="text-center fs-2 text-white"></p>
	</div>
	<div class="container p-3 center-div">
		<div class="row">
			<div  class="col-md-5 mx-auto">
				<div class="card paint-card ">
					<div class="card-body" >
						<p class="text-center fs-3">Schedule Appointment</p>
						<form class="row g-3" action="#" method="post">

							<input type="hidden" name="userid" value="${userObj.id }">

							<div class="row-md-6">
								<label for="inputEmail4" class="form-label">Full Name</label> <input
									required type="text" class="form-control" name="fullname">
							</div>


							<div class="row-md-6">
								<label for="inputEmail4" class="form-label">Appointment
									Date</label> <input type="date" class="form-control" required
									name="appoint_date">
							</div>

							<div class="row-md-6">
								<label for="inputEmail4" class="form-label">Appointment
									Time</label> <input type="datetime" class="form-control" required
									name="appoint_time">
							</div>

							<div class="row-md-6">
								<label for="inputEmail4" class="form-label">Doctor
									Name</label> <input required class="form-control" name="doct">
							</div>
									
							<div class="row-md-6">
								<label for="inputEmail4" class="form-label">Symptoms</label> <input
									required type="text" class="form-control" name="symptoms">
							</div>
							
									<!--  <option value="">--select--</option>-->


									<%--
									DoctorDao dao = new DoctorDao(DBConnect.getConn());
									List<Doctor> list = dao.getAllDoctor();
									for (Doctor d : list) {
									--%>
									<!--  <option value="<--%=d.getId()%>"-->
									<%--=d.getFullName()--%> (
									<%--=d.getSpecialist()--%>)
									<!-- </option> -->
									<%--
									}
									--%>




								</select>
							</div>




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