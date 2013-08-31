<script language="javascript">
	function onKeyPress(e){
		var keycode;
		if (window.event) keycode = window.event.keyCode;
		else if (e) keycode = e.which;
		else return false;
		return (keycode == 13);
	}
</script>
<h3>Send today's comic to a friend.</h3>
<form>
	<p>
		<input type="hidden" name="action" value="emailToFriend"/>
		<label for="yourEmail">Your Email:</label>
		<input type="text" name="yourEmail" id="yourEmail"/>
	</p>
	<p>
		<label for="theirEmail">Their Email:</label>
		<input type="text" name="theirEmail" id="theirEmail"/>
	</p>
	<input type="button" value="Submit" onclick="javascript:emailToFriend()"/>
</form>