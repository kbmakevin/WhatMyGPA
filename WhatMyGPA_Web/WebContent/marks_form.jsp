<%@ include file="./partials/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<h1 class="display-4">${requestScope.operationHeader}&nbsp;Course
		in Transcript</h1>
</div>

<form action="Transcript" method="post">

	<!-- Course Code -->
	<div class=" form-group row">
		<div class="col-4 text-right">
			<label>Course Code:</label>
		</div>
		<div class="col-md-5 ">
			<select name="code" class="form-control"
				<c:if test="${requestScope.operationHeader eq 'Update'}">
										readonly="readonly"</c:if>>
				<c:if test="${requestScope.operationHeader eq 'Update'}">
					<option>${requestScope.course.code }</option>
				</c:if>
				<c:if test="${requestScope.operationHeader eq 'Add'}">
					<c:forEach items="${requestScope.allCourses}" var="course">
						<option>${course.code }</option>
					</c:forEach>
				</c:if>
			</select>
		</div>
	</div>

	<!-- Grade Received -->
	<div class=" form-group row">
		<div class="col-4 text-right">
			<label>Grade Received:</label>
		</div>
		<div class="col-md-5 ">
			<input type="number" class="form-control" name="gradeReceived"
				min="0" step='1' max='100'
				placeholder="Enter the grade you received for the course"
				<c:if test="${requestScope.operationHeader eq 'Update'}">
										value="${requestScope.gradeReceived}"</c:if> />
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
		<a href="Transcript" class="btn btn-danger">Cancel</a>
	</div>
	<!-- buttons -->
</form>



<%@ include file="./partials/footer.jsp"%>