<?php 
$subtitle = getRandomSubtitle();
?>
<div id="titlePanel">
    <div id="icon">
    	<img src="<?php echo HOST;?>images/blue_ascii_muffin.png" alt="ascii muffin" >
    </div>
    <div id="welcomeText">
	    <h5>Welcome To</h5>
	    <span>
			<img src="<?php echo HOST;?>images/welcome_text_blue.png" style="width:350px;border:0;" alt="TechMuffin">
  			<h4><?php print($subtitle["phrase"]);?></h4>
  		</span>
	</div>
</div>