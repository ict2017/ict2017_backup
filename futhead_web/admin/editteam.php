<?php
   include('../session.php');
?>
<html>
<head>
    <title>Edit Player Information</title>
    <meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="index.css" />
</head>
<body>
	<div id="welcome_form" >
      Welcome <?php echo $login_session; ?> &nbsp;|&nbsp;
      <a href = "../logout.php">Sign Out</a>
	  </div>
	<div id="big_wrapper">
		<header id="top_header">
			<h1>FIFA Player Database</h1>
		</header>
 
<nav id="top_menu" >
  <ul>
	<li><a href=index.php>Home</a></li>
	<li><a href=add.php>Add New Player</a></li>
	<li><a href=addteam.php>Add New Team</a></li>
	<li><a href=position.php>Position</a></li>
	<li><a href=team.php>Team</a></li>
	        <div id= "search_bar" align= "right">
            <form action="search.php" method="get">
                Search by Name: <input type="text" name="search" />
                <input type="submit" name="ok" value="search" />
            </form>
        </div>
  </ul>
</nav>
<h2>Edit Team Information</h2>

<?php
    require('connect_db.php');

    if (isset($_GET['id']))
        $teamid = $_GET['id'];
    else
        die('Missing Team ID');
	
	$sql = "SELECT * FROM team WHERE teamid = '$teamid' ";

	$result = $dbh->prepare($sql);
	$result->execute();
	$row = $result->fetch(PDO::FETCH_ASSOC);
if (!$row)
    die('Invalid Team ID.');
?>

<form method="POST" action="save.php">
    <p><b>
        <label for="teamid"><b>Team ID: </b></label>
		<input id="oldteamid" name="oldteamid" type="hidden" value="<?php echo $row['teamid'] ;?>">
        <input id="teamid" name="teamid" type="text" value="<?php echo $row['teamid'] ;?>">
    </p>
    <p>
        <label for="tname"><b>Team Name: </b></label>
        <input id="tname" name="tname" type="text" value="<?php echo $row['team name'] ;?>">
    </p>
    <p>
        <label for="league"><b>League: </b></label>
        <input id="league" name="league" type="text" value="<?php echo $row['league'] ;?>">
    </p>
    <p>
        <input type="submit" value="Submit">
    </p></b>
</form>

</body>
</html>