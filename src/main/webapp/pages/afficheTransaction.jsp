	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
		<div class="table-responsive">
  			<table class="table">
    			<thead>
	      			<tr>
		        		<th>Recette</th>
		        		<th>Depense</th>
		        		<th>Categorie</th>
		        		<th>Desciption</th>
		        		<th>Date</th>
		        		<th>Delete</th>
	      			</tr>
    			</thead>
   				<tbody>
   					<c:forEach var="transaction" items="${ requestScope.transactions }">
   						<tr>
							<td><c:out value="${transaction.getRecette()}" /></td>
							<td><c:out value="${transaction.getDepense()}" /></td>
							<td><c:out value="${transaction.getCategory().getName()}" /></td>
							<td><c:out value="${transaction.getDescription()}" /></td>
							<td><c:out value="${transaction.getDateString()}" /></td>
							<td class="action">
		                        <a href="#" onclick="update('<c:url value="/transaction/update"><c:param name="id" value="${ transaction.id }" /></c:url>')">
		                            <img src="<c:url value="/resources/images/update.png"/>" width="20" height="20" alt="Update" />
		                        </a>
		                    </td>
<!-- 							<td class="action"> -->
<%-- 		                        <a href="<c:url value="/transaction/remove"><c:param name="id" value="${ transaction.id }" /></c:url>"> --%>
<%-- 		                            <img src="<c:url value="/resources/images/supprimer.jpg"/>" width="20" height="20" alt="Supprimer" /> --%>
<!-- 		                        </a> -->
<!-- 		                    </td> -->
						</tr>
   					</c:forEach>
   				</tbody>
 			 </table>
		</div>