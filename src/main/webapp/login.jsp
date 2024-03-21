<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<%@include file="component/allcss.jsp"%>
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
	<%@include file="component/navbar.jsp"%>

	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 text-center">Login</p>
						<form action="login" method="post">
							<div class="mb-3">
								<label class="form-label">Username</label> 
								<input name="username" type="email" class="form-control" required />
								<%
								if (request.getAttribute("usernameError") != null) {
								%>
								<div class="text-danger"><%= request.getAttribute("usernameError") %></div>
								<%
								}
								%>
							</div>

							<div class="mb-3">
								<label class="form-label">Password</label> <input 
									name="password" type="password" class="form-control" required />
								<%
								if (request.getAttribute("passwordError") != null) {
								%>	
									<div class="text-danger"><%=request.getAttribute("passwordError")%></div>
								<%
								}
								%>
							</div>

							<button type="submit" class="btn btn-success text-white col-md-12">Login</button>
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
