<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="./partials/header.jsp" />
<div>
	<h3>Create an account</h3>
	<form action="Register" method="POST">
		<div class="form-group">
			<label class="label">First Name</label> <input class="form-control"
				type="text" placeholder="Mike" name="fName" id="fName" required />
		</div>
		<div class="form-group">
			<label class="label">Last Name</label> <input class="form-control"
				type="text" placeholder="Zupa" name="lName" id="lName" required />
		</div>
		<div class="form-group">
			<label class="label">Username</label> <input class="form-control"
				type="text" placeholder="mike_zupa" name="username" id="username"
				required />
		</div>
		<div class="form-group">
			<label class="label">Password</label> <input class="form-control"
				type="password" name="password" id="password" required />
		</div>
		<div class="form-group">
			<label class="label">Confirm Password</label> <input
				class="form-control" type="password" name="confirmPassword"
				id="confirmPassword" required />
		</div>
		<c:if test="${not empty error }">
			<div id="errormsg" class="alert alert-danger" role="alert">
				<c:out value="${ error }"></c:out>
			</div>
		</c:if>
		<div class="form-group">
			<input type="submit" class="btn btn-primary" value="Register" /> <a
				href="Home" class="btn btn-danger">Cancel</a>
		</div>
	</form>
</div>
<jsp:include page="./partials/footer.jsp" />