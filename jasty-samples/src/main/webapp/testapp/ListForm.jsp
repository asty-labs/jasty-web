<%@ taglib prefix="jst" uri="http://asty-labs.com/jsp" %>

<style>
    .list a {
        display: block;
    }
</style>

<h1>Item list</h1>
<hr/>
<jst:textBox id="searchText" />
<jst:button onClick="searchClicked" text="Search" />
<hr/>
<div class="list" style="border: 1px solid black;width: 400px; height: 100px;overflow: auto;">
</div>
<jst:button onClick="addClicked" text="Add..." />




