<?PHP
    session_start();
    require_once( "../config.php");
    require_once( ROOT_PATH . "util/mysql/dbutil.php");
	require_once( ROOT_PATH . "util/common/common.php");

	if( isset($_SESSION['UserName'])){
		$_SESSION = array();
		session_destroy();
	}

	if (isset($_POST['username']) && isset($_POST['password'])) {
	
		$result = logInAdmin($_POST['username'], $_POST['password']);
		if($result){
			//start the session and register a variable
			session_start();
			$_SESSION = array();
			$_SESSION["UserName"] = $_POST['username'];
			
			// successful login
			header("Location: home.php");
		}	
	}	
?>
<html>
<head>
<title>Admin Login.</title>
</head>
	<body>
		<p>
			<h1>Please log into the Administrator App</h1>
		</p>
		<form method="POST" action="login.php">
		    Username: <input type="text" name="username" size="20"> <br/>
		    Password: <input type="password" name="password" size="20"> <br/>
		    <input type="submit" value="Login" />
		</form>
	</body>
</html>
