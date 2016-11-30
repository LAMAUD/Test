

<%@include file="/template/header.jsp"%>

<div class="container col-lg-12 padNY text-center" style="background-image: url('<c:url value="/resources/images/NY.jpg"/>'); background-size: 1263px 543px; color: #F05F40;">
	<c:url var="createUser" value="/user/save" />
	<form:form action="${ createUser }" method="POST" commandName="user">
		<form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>


		<div class="form-group">
			<label for="nom"> <spring:message code="label.nom.user"
					text="default text" /></label>
			<form:input type="text" id="nom" path="name" placeholder="nom" />
			<form:errors path="name" element="div" />
		</div>

		<div class="form-group">
			<label for="password"> <spring:message
					code="label.password.user" text="default text" /></label>
			<form:input type="password" id="password" path="password"
				placeholder="password" />
			<form:errors path="password" element="div" />
		</div>

		<div>
			<a class="btn btn-default" href="<c:url  value="/client/list.do" />">Retour</a>
			<button class="btn btn-default" type="submit">Créer</button>
		</div>

	</form:form>
</div>
<div class="col-lg-12">
	<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>Uuid</th>
					<th>Nom</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${ requestScope.users }">
					<tr>
						<td><c:out value="${user.getId()}" /></td>
						<td><c:out value="${user.getNom()}" /></td>
						<td class="action"><a
							href="<c:url value="/user/remove"><c:param name="id" value="${ user.id }" /></c:url>">
								<img src="<c:url value="/resources/images/supprimer.jpg"/>"
								width="20" height="20" alt="Supprimer" />
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>