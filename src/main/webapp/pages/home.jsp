<%@page import="java.util.HashMap"%>
<%@include file="/template/header.jsp"%>



<div class="col-lg-12 contre-lg-12">

	<div class=" col-lg-2 col-md-12 col-sm-12">



		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">
				<li class="sidebar-brand"><a href="#"> Start Bootstrap </a></li>
				<li><a href="#">Dashboard</a></li>
				<li><a href="#">Shortcuts</a></li>
				<li><a href="#">Overview</a></li>
				<li><a href="#">Events</a></li>
				<li><a href="#">About</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="#">Contact</a></li>
			</ul>
		</div>


	</div>


	<div class=" col-lg-10 col-md-12 col-sm-12 contre-lg-12">

		<div class="container col-lg-12"
			style="background-image: url('<c:url value="/resources/images/NY.jpg"/>'); background-size: 1263px 543px; color: aliceblue;">

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

				<div class="col-lg-12">

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
		</div>

		<div class="col-lg-12 jumbotron ">
			<div class="col-lg-12 text-center">
				<select id="filtre_mois" name="triMensuel" onchange="ajaxPiechart()">
					<option value="all" selected>Toutes les Données</option>
					<c:forEach var="month" items="${ months }">
						<option value="${ month.getNumber() }">${ month.getName() }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-lg-offset-3 col-lg-9" id="piechart_3d"
				style="width: 620px; height: 500px;"></div>
		</div>


		<div class="col-lg-12" id="output"></div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('#filtre_mois').click(ajaxPiechart());
	});
</script>


<%@include file="/template/footer.jsp"%>