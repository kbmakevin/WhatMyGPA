<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<title>What My GPA? | Centennial College GPA Calculator</title>

<!-- CSS Section -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel='stylesheet' href='./css/site.css' />

<!-- JavaScript Section -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script src="./scripts/core/app.js"></script>
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="Home"> <img src="./img/logo.png"
			class="pull-left" style="height: 5vh;" alt="Logo of the Online Store">
			What My GPA
		</a>
		<c:if test="${not empty sessionScope.user }">
			<span class='text-white'> | Logged in as
				${sessionScope.user.name } (${sessionScope.user.type}) </span>
		</c:if>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="Home">Home
				</a></li>
				<c:if test="${sessionScope.user.type eq 'user'}">
					<li class="nav-item"><a class="nav-link" href="Home">Update
							Profile</a></li>
					<li class="nav-item"><a class="nav-link" href="Transcript">View
							Transcript</a></li>
				</c:if>

				<c:if test="${sessionScope.user.type eq 'admin'}">
					<li class="nav-item"><a class="nav-link" href="Courses">Courses</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.user }">
					<li class="nav-item"><a class="nav-link" href="Logout">Logout</a></li>
				</c:if>
				<c:if test="${empty sessionScope.user }">
					<li class="nav-item"><a class="nav-link" href="Login">Login</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	</nav>
	<div class="container wrapper" style="margin-top: 65px">