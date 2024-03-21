<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index page</title>
<%@ include file="component/allcss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@ include file="component/navbar.jsp"%>
	<div id="carouselExampleIndicators" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="0" class="active" aria-current="true"
				aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="images/bg3.jpg" class="d-block w-100" alt="..."
					height="500px"></img>
			</div>
			<div class="carousel-item">
				<img src="images/bg2.jpg" class="d-block w-100" alt="..."
					height="500px">
			</div>
			<div class="carousel-item">
				<img src="images/bg1.jpg" class="d-block w-100" alt="..."
					height="500px">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
		<div class="container p-3">
			<p class="text-center fs-2 ">Key Features</p>

			<div class="row">
				<div class="col-md-8 p-20">
					<div class="row">
						<div class="col-md-6">
							<div class="card paint-card">
								<div class="card-body">
									<p class="fs-5">User-friendly Interface</p>
									<p>Intuitive design and navigation for easy use by medical
										staff and administrators.</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="card paint-card">
								<div class="card-body">
									<p class="fs-5">Secure Patient Data Storage</p>
									<p>Robust encryption and security protocols to protect
										sensitive patient information</p>
								</div>
							</div>
						</div>
						
						<div class="col-md-6 mt-2">
							<div class="card paint-card">
								<div class="card-body">
									<p class="fs-5">Appointment Scheduling</p>
									<p>Integrated appointment scheduling system for managing
										patient appointments, reminders, and follow-ups.</p>
								</div>
							</div>
						</div>
						<div class="col-md-6 mt-2">
							<div class="card paint-card">
								<div class="card-body">
									<p class="fs-5">Access Controls and Permissions</p>
									<p>Role-based access controls to restrict access to patient
										records for ensuring data privacy and compliance.</p>
								</div>
							</div>
						</div>
						

					</div>
				</div>
				<div class="col-md-3">
					<img alt="" height="300px" width="500px" src="img/bg4.jpg">
				</div>

			</div>
		</div>

		<hr>
	</div>
	<%@ include file="component/footer.jsp"%>
</body>
</html>