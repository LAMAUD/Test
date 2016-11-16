
<%@include file="/template/header.jsp"%>

<h1>Import de fichiers CSV ici</h1>

<div class="container">
	<div class="col-lg-7">
		<form method="POST" action="/test/csv/upload"
			enctype="multipart/form-data">
			File to upload: <input type="file" name="file"> Name: <input
				type="text" name="name"> <input type="submit" value="Upload">
			Press here to upload the file!
		</form>
	</div>


	<div class="ajax col-lg-5">
		<h4>Editer les transactions import�es en base</h4>
		</br> <label class="control-label"><spring:message
				code="label.date.debut.affichage" text="default text" /></label> <input
			id="ip" type="date" name="" value="" placeholder="01/01/1970" /> <label
			class="control-label"><spring:message
				code="label.date.fin.affichage" text="default text" /></label> <input
			id="op" type="date" name="" value="" placeholder="01/01/2050" /><br></br>
		<input type="button" value="Liste Transactions"
			name="Liste Transactions" id="call" />

		

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
</body>
</html>