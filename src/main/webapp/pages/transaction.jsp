	
	<%@include file="/template/header.jsp"%>
	
	<div class="jumbotron">
	</div>
		<div class="col-lg-7">
			<c:url var="createTransaction" value="/transaction/save"/>
			<form:form action="${ createTransaction }" method="POST" commandName="transaction" class="form-horizontal">
				<form:errors path="*" cssClass="alert alert-danger" element="div"></form:errors>

				
				<div class="form-group">
					<label class="control-label col-lg-4" for="depense"> <spring:message code="label.depense.transaction" text="default text" /></label>
					<div class="col-lg-8">
						<form:input type="number" step="0.01" id="depense" path="depense" placeholder="" /> 
						<form:errors path="depense" element="div" /> 
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-4" for="recette"> <spring:message code="label.recette.transaction" text="default text" /></label>
					<div class="col-lg-8">
						<form:input type="number" step="0.01" id="recette" path="recette" placeholder="" /> 
						<form:errors path="recette" element="div" /> 
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-4" for="description"> <spring:message code="label.description.transaction" text="default text" /></label>
					<div class="col-lg-8">
						<form:input type="text" id="description" path="description" placeholder="description" /> 
						<form:errors path="description" element="div" /> 
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-4" for="date"> <spring:message code="label.date.transaction" text="default text" /></label>
					<div class="col-lg-8">
						<form:input type="date" id="date" path="date" placeholder="" /> 
						<form:errors path="date" element="div" /> 
					</div>
				</div>
			
			
				<div class="form-group">
					<label class="control-label col-lg-4" for="category"> <spring:message code="choose.categorie.transaction"
								text="default text" /> 
					</label>
					<div class="col-lg-8">
						<form:select id="category" name="category" path="category.id">
							<form:option value="" label="--- Choisir une Categorie---" />
							<form:options items="${categories}" itemValue="id" itemLabel="name" />
						</form:select>
					</div>
				</div>
			
				
				<div class="col-lg-offset-5 col-lg-12">
					<a href="<c:url  value="/home/initForm" />">Retour</a>
					<button type="submit" class="btn btn-default">Créer</button>
				</div>
			
			</form:form>
		</div>
	
		<div class="ajax col-lg-5">	
			<h4>Editer les transactions sur une période donnée</h4>
			</br>
			<label class="control-label"><spring:message code="label.date.debut.affichage" text="default text" /></label>
			<input  id="ip" type="date" name="" value=""  placeholder="01/01/1970"/>
		    <label class="control-label"><spring:message code="label.date.fin.affichage" text="default text" /></label>
		    <input id="op" type="date" name="" value="" placeholder="01/01/2050" /><br></br>
		    <label for="filtre_categorie">Filtrer par Catégorie</label>
		    <select name="filtre_categorie" size="1" id="filtre_categorie">
		    	<option value="">---Filtrer par Categorie---</option>
		    	<c:forEach var="category" items="${categories}">
		    		<option value="${category.getName()}">${category.getName()}</option>
		    	</c:forEach>
		    </select></br></br>
		    <input type="button" class="btn btn-default" value="Liste Transactions" name="Liste Transactions" id="call"/>
		    
		    
		    
		</div>

		<div class="col-lg-12" id="output"></div>
		
 		<script type="text/javascript">
	        $(document).ready(function() {
	            $('#call').click(ajax);
	        });
	       
	    </script>
	
	<%@include file="/template/footer.jsp"%>