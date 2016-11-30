<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container col-lg-12 contre-lg-12 padNY"
	style="background-image: url('<c:url value="/resources/images/NY.jpg"/>'); background-size: 1263px 543px; color: aliceblue;">

	<div class="col-lg-4 text-center">
		<select id="filtre_moisDepRec" name="triMensuel" class="selectpicker"
			onchange="ajaxRatioDepRec()">
			<option value="all" selected>Toutes les Données</option>
			<c:forEach var="month" items="${ months }">
				<option value="${ month.getNumber() }">${ month.getName() }</option>
			</c:forEach>
		</select>
	</div>
	<div class=" col-lg-4 ">
		<h4>Résumé Recette / Dépenses</h4>
		<p>La table suivante résume vos recettes et dépenses totales</p>

		
		<!-- 		AJAX -->
		<div id="tableau" class="col-lg-12"></div>



	</div>
</div>

<div class="col-lg-12 jumbotron ">
	<div class="col-lg-12 text-center">
		<select id="filtre_mois" name="triMensuel" class="selectpicker"
			onchange="ajaxPiechart()">
			<option value="all" selected>Toutes les Données</option>
			<c:forEach var="month" items="${ months }">
				<option value="${ month.getNumber() }">${ month.getName() }</option>
			</c:forEach>
		</select>
	</div>
	<!-- 	AJAX -->
	<div class="col-lg-offset-3 col-lg-9" id="piechart_3d"
		style="width: 620px; height: 500px;"></div>
</div>


<div class="col-lg-12" id="output"></div>