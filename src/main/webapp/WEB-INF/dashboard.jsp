<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookClub Login and Registration</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="col-9 mx-auto mt-4">
	<div class="d-flex justify-content-between">
		<h1 class="ms-2 text-dark">Welcome, <c:out value=" ${ user.userName }"></c:out></h1>
		<div>
			<a href="/logout" class="btn btn-dark mt-2">Logout</a>
		</div>
	</div>
	<div class="d-flex justify-content-between mt-4 mb-4">
		<h5 class="ms-2">Books from everyone's shelfs.</h5>
		<a href="/books/new" class="btn btn-dark">+ add Book to my shelf! </a>
	</div>

	<table
		class="table table-striped border border-rounded border-3 rounded border-secondary mb-5">
		<thead class="text-center">
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Author Name</th>
				<th colspan="2">Posted By</th>
			</tr>
		</thead>
		<tbody class="bg-light text-center">
			<c:forEach var="book" items="${ allBooks }">
				<tr>
					<td><c:out value="${ book.id }"></c:out></td>
					<td><a href="/books/${book.id}"><c:out
								value="${ book.title }"></c:out></a></td>
					<td><c:out value="${ book.author }"></c:out></td>
					<td><c:out value="${ book.user.userName }"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>