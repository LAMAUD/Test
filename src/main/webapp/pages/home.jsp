<%@page import="java.util.HashMap"%>
<%@include file="/template/header.jsp"%>







<div class="col-lg-12 contre-lg-12">

	<%@include file="/pages/ajaxHome/ResumeExpense.jsp"%>


</div>
<div class="col-lg-12 contre-lg-12">

	<%@include file="/pages/ajaxHome/budget.jsp"%>


</div>





<script type="text/javascript">
	$(document).ready(function() {
		$('#filtre_mois').click(ajaxPiechart());
		$('#filtre_moisDepRec').click(ajaxRatioDepRec());
	});
</script>



<%@include file="/template/footer.jsp"%>