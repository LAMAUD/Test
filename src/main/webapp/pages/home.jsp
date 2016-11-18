<%@include file="/template/header.jsp"%>




<div class="container col-lg-12">
	<div class="col-lg-4">
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
	<div class="col-lg-4"></div>
</div>

</body>
</html>