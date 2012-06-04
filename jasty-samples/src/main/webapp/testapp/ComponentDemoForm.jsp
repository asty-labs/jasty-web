<%@ taglib prefix="jst" uri="http://asty-labs.com/jsp" %>

<h1>Caption: ${model.caption}</h1>

<hr/>
Dependent combo box with dynamic options:<br/>
<jst:comboBox id="mainCombo" onChange="mainComboChanged" options="${model.mainOptions}"/>
<jst:comboBox id="detailCombo" visible="false"/>
<hr/>
Switcher, changing text box:<br/>
<jst:checkBox id="switcher" onChange="switcherChanged"/>
<jst:textBox id="text" />
<hr/>
<jst:button onClick="resetClicked" text="Reset" />
Number of resets: ${currentForm.resetCounter}




