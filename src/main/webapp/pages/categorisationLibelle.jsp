<%@include file="/template/header.jsp"%>

<div class="jumbotron text-center">
	<a href="/test/categorisationLibelle/import" class="btn btn-default">Import
		Table ==> Lien Categorie / Libelle</a>
</div>


<div class="table-responsive">
	<table class="table">
		<thead>
			<tr>
				<th>Uuid</th>
				<th>Categorie</th>
				<th>Libelle</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="categorisationLibelle"
				items="${ requestScope.categorisationLibelles }">
				<tr>
					<td><c:out value="${categorisationLibelle.getId()}" /></td>
					<td><c:out
							value="${categorisationLibelle.getCategory().getName()}" /></td>
					<td><c:out value="${categorisationLibelle.getLibelle()}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<%@include file="/template/footer.jsp"%>