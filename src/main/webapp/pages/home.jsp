<%@page import="java.util.HashMap"%>
<%@include file="/template/header.jsp"%>


<div class="jumbotron"></div>
<div class="container  col-lg-12"
	style="background-image: url('<c:url value="/resources/images/NY.jpg"/>'); background-size: 1263px 443px; color: aliceblue;">

	<div class="col-lg-offset-4 col-lg-4 col-lg-offset-4 padNY">
		<h4>Résumé Recette / Dépenses</h4>
		<p>La table suivante résume vos recettes et dépenses totales</p>


		<div class="progress">
			<div class="progress-bar progress-bar-striped active"
				role="progressbar" aria-valuenow="40" aria-valuemin="0"
				aria-valuemax="100"
				style="width: <c:out value="${ratioDepensesRecettes}" />%">
				<c:out value="${ratioDepensesRecettes}" />
				%
			</div>
		</div>

		<div class="container col-lg-12">

			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Recettes</th>
						<th>Dépenses</th>

					</tr>
				</thead>
				<tbody>
					<tr>
						<td><c:out value="${Recettes}" /></td>
						<td><c:out value="${Depenses}" /></td>
					</tr>

				</tbody>
			</table>
		</div>
	</div>
	<%-- 	<img class="img-responsive" src="<c:url value="/resources/images/NY.jpg"/>"></img> --%>
</div>

<div class="col-lg-12">
	<select id="filtre_mois" name="triMensuel" onchange="ajaxPiechart()">
		<option value="all" selected>Toutes les Données</option>
		<c:forEach var="month" items="${ months }">
			<option value="${ month.getNumber() }">${ month.getName() }</option>
		</c:forEach>
	</select>
	<div id="piechart_3d" style="width: 620px; height: 500px;"></div>
</div>
<div class="container col-lg-12"></div>




<div class="col-lg-12" id="output"></div>




<script type="text/javascript">
	$(document).ready(function() {
		$('#filtre_mois').click(ajaxPiechart());
	});
</script>

<%@include file="/template/footer.jsp"%>