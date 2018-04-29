	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="./partials/header.jsp" />
<div class="col-md-6">
	<h3>Home</h3>
	${sessionScope.user.name }
</div>
<jsp:include page="./partials/footer.jsp" />