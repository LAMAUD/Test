<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<c:url var="urlResources" value="/resources/bootstrap" />
<title>Update</title>

<!-- Bootstrap Core CSS -->
<link href="${urlResources}/vendor/bootstrap/css/bootstrap.min.css"
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

<!-- Theme CSS -->
<link href="${urlResources}/css/creative.min.css" rel="stylesheet">
<link rel="stylesheet" href="${urlResources}/style.css" />
<!--     jQuery -->
<script src="${urlResources}/vendor/jquery/jquery.min.js"></script>
<script src="${urlResources}/style.js"></script>

</head>
<body>

	<div class="col-lg-7">
		<c:url var="createTransaction" value="/transaction/updateCategorie" />
		<form:form action="${ createTransaction }" method="POST"
			commandName="transaction" class="form-horizontal">
			<form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>

			<div class="form-group">
				<label class="control-label col-lg-4" for="id"> id </label>
				<div class="col-lg-8">
					<form:input type="number" id="id" path="id"
						placeholder="${transaction.getId() }" readonly="true" />
					<form:errors path="depense" element="div" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-4" for="depense"> <spring:message
						code="label.depense.transaction" text="default text" /></label>
				<div class="col-lg-8">
					<form:input type="number" step="0.01" id="depense" path="depense"
						placeholder="${transaction.getDepense() }" readonly="true" />
					<form:errors path="depense" element="div" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-4" for="recette"> <spring:message
						code="label.recette.transaction" text="default text" /></label>
				<div class="col-lg-8">
					<form:input type="number" step="0.01" id="recette" path="recette"
						placeholder="${transaction.getRecette() }" readonly="true" />
					<form:errors path="recette" element="div" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-4" for="description"> <spring:message
						code="label.description.transaction" text="default text" /></label>
				<div class="col-lg-8">
					<form:input type="text" id="description" path="description"
						placeholder="${transaction.getDescription() }" readonly="true" />
					<form:errors path="description" element="div" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-4" for="date"> <spring:message
						code="label.date.transaction" text="default text" /></label>
				<div class="col-lg-8">
					<form:input type="date" id="date" path="date"
						placeholder="${transaction.getDate() }" readonly="true" />
					<form:errors path="date" element="div" />
				</div>
			</div>


			<div class="form-group">
				<label class="control-label col-lg-4" for="category"> <spring:message
						code="choose.categorie.transaction" text="default text" />
				</label>
				<div class="col-lg-8">
					<form:select id="category" name="category" path="category.id">
						<form:options items="${categories}" itemValue="id"
							itemLabel="name" />
					</form:select>
				</div>
			</div>


			<div class="col-lg-offset-5 col-lg-12">
				<a href="#" onclick="window.close();">Retour</a>
				<button type="submit" class="btn btn-default" >Créer</button>
			</div>
	${script}
		</form:form>
	</div>



</body>
</html>