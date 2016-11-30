<%@include file="/template/header.jsp"%>

<div class="container col-lg-12 contre-lg-12">
	<div class="col-lg-12 padNY text-center"
		style="background-image: url('<c:url value="/resources/images/NY.jpg"/>'); background-size: 1263px 543px; color: #F05F40;">
		<a href="/test/categorisationLibelle/import" class="btn btn-default">Import
			Table ==> Lien Categorie / Libelle</a> <a
			href="/test/categorisationLibelle/export" class="btn btn-default">Export
			Table ==> Lien Categorie / Libelle</a>
	</div>


	<div class="col-lg-12 table-responsive">
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
</div>
<%@include file="/template/footer.jsp"%>