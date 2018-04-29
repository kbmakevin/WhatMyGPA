<%@ include file="./partials/header.jsp"%>
<div class='row'>
	<h1 class="display-1">What My GPA</h1>
	<h3>This simple but very useful app will help you to calculate
		your GPA. Please login with your username and password, or if this is
		your first time, please register to proceed with GPA calculations</h3>
	<hr>
	<c:if test="${empty sessionScope.user }">
</div>
<div class='row'>
	<a class="btn btn-outline-primary" href="Login">Login</a> <a
		class="btn btn-outline-primary" href="Register">Register</a>
	</c:if>
</div>
<!-- if logged in -->
<c:if test="${not empty sessionScope.user }">
	<!-- if user -->
	<c:if test="${sessionScope.user.type eq 'user'}">
		<div class='row'>
			<h2>Welcome, ${sessionScope.user.name}!</h2>
		</div>
	</c:if>

	<!-- if admin -->
	<c:if test="${sessionScope.user.type eq 'admin'}">

		<div class='row'>
			<h2>Summary</h2>
		</div>
		<div class='row'>
			# Courses: ${requestScope.numberOfCourses } <br> # Students:
			${requestScope.numberOfUsers }
		</div>
		<br>
		<div class='row'>
			<a class="btn btn-outline-info" href="Courses">View Courses</a>
		</div>
	</c:if>

</c:if>

<%@ include file="./partials/footer.jsp"%>