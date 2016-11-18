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
		<script src="${urlResources}/vendor/jquery/jquery.js"></script>


    <title>Creative - Start Bootstrap Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="${urlResources}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${urlResources}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>

    <!-- Plugin CSS -->
    <link href="${urlResources}/vendor/magnific-popup/magnific-popup.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="${urlResources}/css/creative.min.css" rel="stylesheet">
	<link rel="stylesheet" href="${urlResources}/style.css"/>	
		
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