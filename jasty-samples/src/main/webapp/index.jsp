<%@ taglib prefix="jst" uri="http://asty-labs.com/jsp" %>
<html>
  <head>
    <title>Form samples</title>
	<script src="resources/jquery-1.7.min.js" type="text/javascript"></script>
	<script src="resources/jquery.form.js" type="text/javascript"></script>
	<script src="resources/jasty-core.js" type="text/javascript"></script>
    <script src="resources/jasty-components.js" type="text/javascript"></script>
  </head>
  <body>
	<script>
		jasty.settings.formEngineUrl = "formEngine";
	</script>
    <jst:formViewer id="myform" entryPointClass="testapp.ListForm" />
  </body>
</html>
