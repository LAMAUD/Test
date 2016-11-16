<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="table-responsive">
	<table class="table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Date</th>
				<th>Numero d'opération</th>
				<th>Libelle</th>
				<th>Debit</th>
				<th>Credit</th>
				<th>Detail</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="importCSVTransactionPresentsEnBase"
				items="${ requestScope.importCSVTransactionsPresentsEnBase }">
				<tr>
					<td><c:out value="${importCSVTransactionPresentsEnBase.getId()}" /></td>
					<td><c:out value="${importCSVTransactionPresentsEnBase.getDate()}" /></td>
					<td><c:out
							value="${importCSVTransactionPresentsEnBase.getNumeroOperation()}" /></td>
					<td><c:out value="${importCSVTransactionPresentsEnBase.getLibelle()}" /></td>
					<td><c:out value="${importCSVTransactionPresentsEnBase.getDebit()}" /></td>
					<td><c:out value="${importCSVTransactionPresentsEnBase.getCredit()}" /></td>
					<td><c:out value="${importCSVTransactionPresentsEnBase.getDetail()}" /></td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>