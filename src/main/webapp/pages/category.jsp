	
	<%@include file="/template/header.jsp"%>
	
		<div>
			<c:url var="createCategory" value="/category/save"/>
			<form:form action="${ createCategory }" method="POST" commandName="category">
				<form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>

				
				<div class="form-group">
					<label for="name"> <spring:message code="label.nom.categorie" text="default text" /></label>
					<form:input type="text" id="name" path="name" placeholder="name" /> 
					<form:errors path="name" element="div" /> 
				</div>
				
				<div class="form-group">
					<label for="description"> <spring:message code="label.description.categorie" text="default text" /></label>
					<form:input type="text" id="description" path="description" placeholder="description" /> 
					<form:errors path="description" element="div" /> 
				</div>
				
				<div >
					<a href="<c:url  value="/home/initForm" />">Retour</a>
					<button type="submit">Créer</button>
				</div>
			
			</form:form>
		</div>
		
		<div class="table-responsive">
  			<table class="table">
    			<thead>
	      			<tr>
		        		<th>Uuid</th>
		        		<th>Categorie</th>
		        		<th>Description</th>
		        		<th>Delete</th>
	      			</tr>
    			</thead>
   				<tbody>
   					<c:forEach var="category" items="${ requestScope.categories }">
   						<tr>
							<td><c:out value="${category.getId()}" /></td>
							<td><c:out value="${category.getName()}" /></td>
							<td><c:out value="${category.getDescription()}" /></td>
							<td class="action">
		                        <a href="<c:url value="/category/remove"><c:param name="id" value="${ category.id }" /></c:url>">
		                            <img src="<c:url value="/resources/images/supprimer.jpg"/>" width="20" height="20" alt="Supprimer" />
		                        </a>
		                    </td>
						</tr>
   					</c:forEach>
   				</tbody>
 			 </table>
		</div>
	
	</body>
</html>