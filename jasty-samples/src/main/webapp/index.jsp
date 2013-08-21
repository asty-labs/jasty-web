<%@ taglib prefix="jst" uri="http://asty-labs.com/jsp" %>
<html>
  <head>
    <title>Form samples</title>
	<script src="resources/jquery-1.7.min.js" type="text/javascript"></script>
	<script src="resources/jquery.form.js" type="text/javascript"></script>
	<script src="resources/jasty-core.js" type="text/javascript"></script>
    <script src="resources/jasty-std.js" type="text/javascript"></script>
  </head>
  <body>
	<script>
		jasty.settings.formEngineUrl = "formEngine";
	</script>
    <jst:formViewer id="myform" entryPointClass="guessnumber.MainForm" />
  </body>
</html>
