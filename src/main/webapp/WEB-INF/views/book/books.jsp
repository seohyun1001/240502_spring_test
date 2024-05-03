<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
href="/resources/css/bootstrap.min.css">
<title>도서 목록</title>
</head>
<body>
	<jsp:include page="../../../menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">도서 목록</h1>
		</div>
	</div>
	<div class="container">
		<div class="row align-items-center">
			<c:forEach items="${books}" var="book">
			<div class="col-md-4">
				<img src="/image/${book.imgFileName}" style="width:100%;" alt="" >
			</div>
			<div class="col-md-5">
				<h3>[${book.category}]${book.name}</h3>
				<br>
				<p>${book.description}</p>
				<p>${book.author} |
						${book.publisher} |
						${book.unitPrice}원</p>
			</div>
			<div class="col-md-2">
				<p><a href="/book/book?id=${book.id}"
				class="btn btn-secondary" role="button">
				상세정보 &raquo;</a></p>
			</div>
			<hr style="border:1px dotted gray; display:block; width:100%">
			</c:forEach>
		</div>
	</div>
		<div align="center">
			<a href="/book/addBook"
			class="btn btn-secondary" role="button">
			도서 등록 &raquo;</a>
		</div>
	<jsp:include page="../../../footer.jsp" />
</body>
</html>







