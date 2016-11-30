<%@ page language="java" contentType="text/html; charset=UTF-8 "
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:url var="urlResources" value="/resources/bootstrap" />
<title>Compta</title>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<title>Creative - Start Bootstrap Theme</title>

<!-- Bootstrap Core CSS -->
<link href="${urlResources}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlResources}/vendor/bootstrap/css/bootstrap-select.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${urlResources}/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>

<!-- Plugin CSS -->
<link href="${urlResources}/vendor/magnific-popup/magnific-popup.css"
	rel="stylesheet">
<link
	href="${urlResources}/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">

<!-- Theme CSS -->
<link href="${urlResources}/css/creative.min.css" rel="stylesheet">
<link rel="stylesheet" href="${urlResources}/style.css" />
<link rel="stylesheet" href="${urlResources}/sidebar.css" />
<!--     jQuery -->
<script src="${urlResources}/vendor/jquery/jquery.min.js"></script>
</head>
<body>

	<nav id="mainNav" class="navbar navbar-fixed-top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> Menu <i
						class="fa fa-bars"></i>
				</button>
				<a class="navbar-brand page-scroll" href="/test/home/initForm">Compte</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a class="page-scroll"
						href="/test/categorisationLibelle/initForm">Cat - Libelle</a></li>
					<li><a class="page-scroll" href="/test/user/initForm">User</a></li>
					<li><a class="page-scroll" href="/test/category/initForm">Catégorie</a></li>
					<li><a class="page-scroll" href="/test/transaction/initForm">Transactions</a></li>
					<li><a class="page-scroll" href="/test/csv/initForm">Import</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>