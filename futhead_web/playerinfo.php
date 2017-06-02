<?php
   include('session.php');
?>
<html>
<head>
	<meta charset="UTF-8">
    <title>FIFA Player Rating Database</title>
	<meta name="author" content="WebDev">
	<link rel="stylesheet" type="text/css" href="index.css" />
</head>
<body>
	<div id="welcome_form" >
      Welcome <?php echo $login_session; ?> &nbsp;|&nbsp;
      <a href = "logout.php">Sign Out</a>
	  </div>
	<div id="big_wrapper">
		<header id="top_header">
			<h1>FIFA Player Database</h1>
		</header>
 
<nav id="top_menu" >
  <ul>
	<li><a href=index.php>Home</a></li>
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
<header >
			<h1>Player Info</h1>
</header>
<?php
require('connect_db.php');

if (isset($_GET['id']))
        $id = $_GET['id'];
    else
        die('Missing Player ID');
	
	$sql = "SELECT * FROM player left outer join playfor on player.id = playfor.id left outer join team on team.teamid = playfor.teamid WHERE player.id = '$id' ORDER BY player.id ";

	$result = $dbh->prepare($sql);
	$result->execute();
	if(!$result->fetch(PDO::FETCH_ASSOC))
	    die('Invalid Player ID or There Is No Player With That ID.');
	$result->execute();
foreach ($result->fetchall() as $row) {
echo "<b>Player ID:</b> {$row['id']} <br><br>" ;
echo "<b>Player Name:</b> {$row['name']} <br><br>" ;
echo "<b>Nation:</b> {$row['nation']} <br><br>" ;
echo "<b>Position:</b> {$row['position']} <br><br>" ;
echo "<b>Play For:</b> {$row['team name']} <br><br>" ;
echo "<b>Squad Number:</b> {$row['squadnum']} <br><br>" ;
echo "<b>Overall:</b> {$row['ovr']} <br><br>" ;
}
?>
    </body>
</html>

