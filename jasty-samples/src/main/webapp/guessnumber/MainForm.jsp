<%@ taglib prefix="jst" uri="http://asty-labs.com/jsp" %>

<h1>Guess a number</h1>
<div id="${currentForm.id}_stats">
    <jsp:include page="MainForm_stats.jsp"/>
</div>
<jst:textBox id="guessEntryField" />
<jst:button onClick="processGuess" text="Submit Your Guess" />
<jst:button onClick="startNewGame" text="Start a New Game" />




