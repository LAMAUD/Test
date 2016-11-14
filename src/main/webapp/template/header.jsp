<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<c:url var="urlResources" value="/resources/bootstrap" />
		<title>Compta</title>
		<script src="${urlResources}/dist/js/jquery-3.1.1.min.js"></script>

		<link rel="stylesheet" href="${urlResources}/dist/css/bootstrap.css"/>
		<link rel="stylesheet" href="${urlResources}/style.css"/>
		<script src="${urlResources}/dist/js/bootstrap.js"></script>
		
		
	</head>
	<body>
	
		<nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="/test/home/initForm">Compte</a>
		    </div>
		    <ul class="nav navbar-nav">
		      <li class="active"><a href="/test/csv/initForm">Import</a></li>
		      <li><a href="/test/user/initForm">User</a></li>
		      <li><a href="/test/transaction/initForm">Transactions</a></li> 
		      <li><a href="/test/category/initForm">Catégorie</a></li> 
		    </ul>
		  </div>
		</nav>