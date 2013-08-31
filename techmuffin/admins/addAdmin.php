<?php
require_once(getcwd() ."/../config.php");
require_once("../util/mysql/dbutil.php");
require_once(getcwd() . "/../util/common/common.php");

function addAdmin($userName, $pass){

	$enc_pass = encrypt($pass);
	$result = "";
	$con = getDBConnection();
	$sqlStr = "INSERT into admins( USERNAME, PASSWORD, CREATED) VALUES('".
	$userName . "', '" . $enc_pass . "', curdate() )";
	mysql_select_db(DB_DATABASE, $con);
	if(mysql_query($sqlStr)){
		$result = "User: " . $useName . " successfully added to admins group.";
	}else{
		$result = "Unable to add user to admins group.";
	}
	mysql_close($con);
	return $result;
}

if( isset($_POST['adminUserName']) && isset($_POST['adminPW']) && isset($_POST['superAdminPW'])){
	$superAdminPw = encrypt($_POST['superAdminPW']);
	if( strcmp($superAdminPw,SUPER_PW) != 0){
		
		echo "Invalid super secret Admin Password.<br/>";
		echo $superAdminPw . '<br/>' . SUPER_PW;
		
	}else{
		$result = addAdmin($_POST['adminUserName'], $_POST['adminPW']);
		echo $result;
		exit;
	}
}
?>
<html>
<head>
<title>Addmin Creation.</title>
</head>
	<body>
		<p>
			<h1>Add new administrator page</h1>
		</p>
		<p>
			<h3>Please add user name and password for new admin.</h3>
		</p>
		<form method="POST" action="addAdmin.php">
		    Username: <input type="text" name="adminUserName" size="20"> <br/>
		    Password: <input type="password" name="adminPW" size="20"> <br/>
		    Super Secred Admin Password: <input type="password" name="superAdminPW" size="20"> <br/>
		    <input type="submit" value="Submit"	name="login>
		</form>
	</body>
</html>
