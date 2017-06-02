<html>
<head>
    <title>Add New Team</title>
    <meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="index.css" />
</head>
<body style="text-align:center">
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
<h2>Add New Team</h2>
<?php
    require('connect_db.php');
?>

<form method="POST" action="store.php">
    <p>
        <label for="teamid"><b>Team ID: </b></label>
        <input id="teamid" name="teamid" type="text" value="" required="true">
    </p>
    <p>
        <label for="tname"><b>Team Name: </b></label>
        <input id="tname" name="tname" type="text" value="" required="true">
    </p>
    <p>
        <label for="league"><b>League: </b></label>
        <input id="league" name="league" type="text" value="" required="true">
    </p>
    <p>
        <input type="submit" value="Submit">
    </p>
</form>

</body>
</html>
