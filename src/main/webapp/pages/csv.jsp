
<%@include file="/template/header.jsp"%>




<div class="col-lg-12 jumbotron">
	<div class="col-lg-12 text-center">
		<h3>Import de fichiers CSV</h3>
	</div>
	<div class="col-lg-12 row-fluid">
		<div class="col-lg-8 ">
			<form method="POST" action="/test/csv/upload"
				enctype="multipart/form-data">
				<div class="col-lg-6">
					<label class="control-label"><c:out value="File to upload:" /><input
						class="btn btn-default" type="file" name="file"></label>
				</div>
				<div class="col-lg-6 bottom">
					<div>&nbsp;</div>
					<input class="btn btn-default" type="submit" value="Upload">
				</div>

			</form>
		</div>
		<div class="col-lg-4">
			<div>&nbsp;</div>
			<form action="/test/csv/transfert">
				<input class="btn btn-default" type="submit"
					value="Transfert Transaction vers Appli" />
			</form>
			<c:out value="${messageTransfertTransaction}"></c:out>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="ajax col-lg-12 marginTop text-center">
		<h4>Editer les transactions importées en base</h4>
		</br> <label class="control-label"><spring:message
				code="label.date.debut.affichage" text="default text" /></label> <input
			id="ip" type="date" name="" value="" placeholder="01/01/1970" /> <label
			class="control-label"><spring:message
				code="label.date.fin.affichage" text="default text" /></label> <input
			id="op" type="date" name="" value="" placeholder="01/01/2050" /><br></br>
		<input class="btn btn-default" type="button"
			value="Liste Transactions" name="Liste Transactions" id="call" />

	</div>

	<div class="col-lg-12" id="output"></div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		$('#call').click(function() {
			var regex = new RegExp('-', 'g');
			var dateDebut = $('#ip').val().replace(regex, "");
			var dateFin = $('#op').val().replace(regex, "");
			$.ajax({
				type : "GET",
				url : "/test/csv/ajax",
				data : "input=" + dateDebut + "&output=" + dateFin,
				success : function(msg) {
					$('#output').html(msg);
				}
			});
		});

	});
</script>

<%@include file="/template/footer.jsp"%>