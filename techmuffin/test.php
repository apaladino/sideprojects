
<?php
	require_once('config.php');
   	require_once("util/mysql/dbutil.php");
   	require_once('util/common/common.php');
   
	echo "<html>";

	

   if( $_POST["testVal"] != null && $_POST["testVal"] != ""){
   	   $a = $_POST["testVal"];
   	   $b = filterText($_POST["testVal"]);
   	   $c = filterXSS($_POST["testVal"]);
   	  
   	   notifySubscriptions("new comic", 4);
   	  
   }else{
   	  $a = "enter a test string";
   }




?>
<p>
    <form action="test.php" method="POST">
    	<label for="testVal">Please enter a test string:</label>
    	<input type="text" name="testVal" id="testVal"/>
    	<input type="submit" value="Submit"/>
    </form>
</p>

<?php 
echo "<table>";
echo "<tr><td><strong><font color=\"red\">input value</strong></font></td><td><strong>" . $a . "</strong></td></tr>";
echo "<tr><td><strong><font color='blue'>filterText()</td><td>" . $b . "</font></strong></td></tr>";
echo "<tr><td><strong><font color='red'>filterXss()</td><td>" . $c . "</font></strong></td></tr>";

/*echo "<tr><td>FILTER_SANITIZE_EMAIL</td><td>" . filter_var($a, FILTER_SANITIZE_EMAIL) . "</td></tr>";
echo "<tr><td>FILTER_SANITIZE_ENCODED, FILTER_FLAG_STRIP_LOW</td><td>" . filter_var($a, FILTER_SANITIZE_ENCODED, FILTER_FLAG_STRIP_LOW) . "</td></tr>";
echo "<tr><td>FILTER_SANITIZE_ENCODED, FILTER_FLAG_STRIP_HIGH</td><td>" . filter_var($a, FILTER_SANITIZE_ENCODED, FILTER_FLAG_STRIP_HIGH) . "</td></tr>";
echo "<tr><td>FILTER_SANITIZE_ENCODED, FILTER_FLAG_ENCODE_LOW</td><td>" . filter_var($a, FILTER_SANITIZE_ENCODED, FILTER_FLAG_ENCODE_LOW) . "</td></tr>";
echo "<tr><td>FILTER_SANITIZE_ENCODED, FILTER_FLAG_ENCODE_HIGH</td><td>" . filter_var($a, FILTER_SANITIZE_ENCODED, FILTER_FLAG_ENCODE_HIGH) . "</td></tr>";
echo "<tr><td>FILTER_SANITIZE_MAGIC_QUOTES</td><td>" . filter_var($a, FILTER_SANITIZE_MAGIC_QUOTES) . "</td></tr>";
echo "<tr><td>FILTER_SANITIZE_NUMBER_FLOAT, FILTER_FLAG_ALLOW_FRACTION</td><td>" . filter_var($a, FILTER_SANITIZE_NUMBER_FLOAT, FILTER_FLAG_ALLOW_FRACTION) . "</td></tr>";
echo "<tr><td>FILTER_SANITIZE_NUMBER_FLOAT, FILTER_FLAG_ALLOW_THOUSAND</td><td>" . filter_var($a, FILTER_SANITIZE_NUMBER_FLOAT, FILTER_FLAG_ALLOW_THOUSAND) . "</td></tr>";
echo "<tr><td>FILTER_SANITIZE_NUMBER_FLOAT, FILTER_FLAG_ALLOW_SCIENTIFIC</td><td>" . filter_var($a, FILTER_SANITIZE_NUMBER_FLOAT, FILTER_FLAG_ALLOW_SCIENTIFIC) . "</td></tr>";
echo "<tr><td>FILTER_SANITIZE_NUMBER_INT</td><td>" . filter_var($a, FILTER_SANITIZE_NUMBER_INT) . '</td></tr>';
echo "<tr><td>FILTER_SANITIZE_SPECIAL_CHARS, FILTER_FLAG_STRIP_LOW</td><td>" . filter_var($a, FILTER_SANITIZE_SPECIAL_CHARS, FILTER_FLAG_STRIP_LOW) . '</td></tr>';
echo "<tr><td>FILTER_SANITIZE_SPECIAL_CHARS, FILTER_FLAG_STRIP_HIGH</td><td>" . filter_var($a, FILTER_SANITIZE_SPECIAL_CHARS, FILTER_FLAG_STRIP_HIGH) . '</td></tr>';
echo "<tr><td>FILTER_SANITIZE_SPECIAL_CHARS, FILTER_FLAG_ENCODE_HIGH</td><td>" . filter_var($a, FILTER_SANITIZE_SPECIAL_CHARS, FILTER_FLAG_ENCODE_HIGH) . '</td></tr>';
echo "<tr><td>FILTER_SANITIZE_STRING, FILTER_FLAG_NO_ENCODE_QUOTES</td><td>" . filter_var($a, FILTER_SANITIZE_STRING, FILTER_FLAG_NO_ENCODE_QUOTES) . '</td></tr>';
echo "<tr><td>FILTER_SANITIZE_STRING, FILTER_FLAG_STRIP_LOW</td><td>" . filter_var($a, FILTER_SANITIZE_STRING, FILTER_FLAG_STRIP_LOW) . '</td></tr>';
echo "<tr><td>FILTER_SANITIZE_STRING, FILTER_FLAG_STRIP_HIGH</td><td>" . filter_var($a, FILTER_SANITIZE_STRING, FILTER_FLAG_STRIP_HIGH) . '</td></tr>';
echo "<tr><td>FILTER_SANITIZE_STRING, FILTER_FLAG_ENCODE_LOW</td><td>" . filter_var($a, FILTER_SANITIZE_STRING, FILTER_FLAG_ENCODE_LOW) . '</td></tr>';
echo "<tr><td>FILTER_SANITIZE_STRING, FILTER_FLAG_ENCODE_HIGH</td><td>" . filter_var($a, FILTER_SANITIZE_STRING, FILTER_FLAG_ENCODE_HIGH) . '</td></tr>';
echo "<tr><td>FILTER_SANITIZE_STRING, FILTER_FLAG_ENCODE_AMP</td><td>" . filter_var($a, FILTER_SANITIZE_STRING, FILTER_FLAG_ENCODE_AMP) . '</td></tr>';
echo "<tr><td>FILTER_SANITIZE_STRIPPED</td><td>" . filter_var($a, FILTER_SANITIZE_STRIPPED) . "</td></tr>";
echo "<tr><td>FILTER_SANITIZE_URL</td><td>" . filter_var($a, FILTER_SANITIZE_URL) . "</td></tr>";
echo "<tr><td>FILTER_UNSAFE_RAW, FILTER_FLAG_STRIP_LOW</td><td>" . filter_var($a, FILTER_UNSAFE_RAW, FILTER_FLAG_STRIP_LOW) . '</td></tr>';
echo "<tr><td>FILTER_UNSAFE_RAW, FILTER_FLAG_STRIP_HIGH</td><td>" . filter_var($a, FILTER_UNSAFE_RAW, FILTER_FLAG_STRIP_HIGH) . '</td></tr>';
echo "<tr><td>FILTER_UNSAFE_RAW, FILTER_FLAG_ENCODE_LOW</td><td>" . filter_var($a, FILTER_UNSAFE_RAW, FILTER_FLAG_ENCODE_LOW) . '</td></tr>';
echo "<tr><td>FILTER_UNSAFE_RAW, FILTER_FLAG_ENCODE_HIGH</td><td>" . filter_var($a, FILTER_UNSAFE_RAW, FILTER_FLAG_ENCODE_HIGH) . '</td></tr>';
echo "<tr><td>FILTER_UNSAFE_RAW, FILTER_FLAG_ENCODE_AMP</td><td>" . filter_var($a, FILTER_UNSAFE_RAW, FILTER_FLAG_ENCODE_AMP) . '</td></tr>';
*/echo "</table></html>"
?>

<?php

	foreach($_SERVER as $key => $value){
		echo $key . "=>" . $value . "<br/>";
	}
	
	phpDebug();

?>