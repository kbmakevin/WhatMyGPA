<%@ include file="./partials/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<h1 class="display-4">${requestScope.operationHeader}&nbsp;Course</h1>
</div>

<form action="Courses" method="post">

	<!-- Course Code -->
	<div class=" form-group row">
		<div class="col-4 text-right">
			<label>Course Code:</label>
		</div>
		<div class="col-md-5 ">
			<input type="text" class="form-control" name="code"
				placeholder="Enter the course code"
				<c:if test="${requestScope.operationHeader eq 'Update'}">
										readonly="readonly"</c:if>
				<c:if test="${requestScope.operationHeader eq 'Update'}">
										value="${requestScope.course.code}"</c:if> />
		</div>
	</div>

	<!-- Number of Credits -->
	<div class=" form-group row">
		<div class="col-4 text-right">
			<label>Number of Credits:</label>
		</div>
		<div class="col-md-5 ">
			<input type="number" class="form-control" name="credits" min="2"
				max='4' step='1'
				placeholder="Enter an integer for the number of credits"
				<c:if test="${requestScope.operationHeader eq 'Update'}">
										value="${requestScope.course.credits}"</c:if> />
		</div>
	</div>


	<!-- buttons -->
	<div class='row'>
		<c:if test="${requestScope.operationHeader eq 'Update'}">
			<input type="submit" class="btn btn-success" name="updateCourse"
				value="Update" />
		</c:if>

		<c:if test="${requestScope.operationHeader eq 'Add'}">
			<input type="submit" class="btn btn-success" name="addCourse"
				value="Add" />
		</c:if>
		<a href="Courses" class="btn btn-danger">Cancel</a>
	</div>
	<!-- buttons -->
</form>



<%@ include file="./partials/footer.jsp"%>