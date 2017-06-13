<?php
   include('../session.php');
   if ($user_type != "Admin")
	   die("You Need Admin Role To View This Page!");
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

<?php
require('connect_db.php');

if (isset($_GET['id']))
        $teamid = $_GET['id'];
    else
        die('Missing Player ID');
	
	$sql = "SELECT * FROM player left outer join playfor on player.id = playfor.id left outer join team on team.teamid = playfor.teamid WHERE team.teamid = '$teamid' ORDER BY player.id ";

	$result = $dbh->prepare($sql);
	$result->execute();
	if(!$result->fetch(PDO::FETCH_ASSOC))
	    die('Invalid Team ID or There Is No Player In That Team.');

	$result->execute();
?>
        <table border="1"; width=100%>
            <tr>
                <th>ID</th>
                <th>Player Name</th>
				<th>Nation</th>
				<th>Position</th>
				<th>Team</th>
				<th>Squad Number</th>
				<th>League</th>
				<th>OVR</th>
				<th>Edit Player</th>
				<th>Delete Player</th>
            </tr>
<?php
foreach ($result->fetchall() as $row) {
echo "<tr>
<td><a href=\"playerinfo.php?id={$row['id']}\">{$row['id']}</a></td>
<td><a href=\"playerinfo.php?id={$row['id']}\">{$row['name']}</a></td>
<td><a href=\"nationinfo.php?name={$row['nation']}\">{$row['nation']}</a></td>
<td><a href=\"position.php\">{$row['position']}</a></td>
<td><a href=\"teaminfo.php?id={$row['teamid']}\">{$row['team name']}</a></td>
<td><a href=\"squadnum.php?sn={$row['squadnum']}\">{$row['squadnum']}</a></td>
<td><a href=\"leagueinfo.php?name={$row['league']}\">{$row['league']}</a></td>
<td><a href=\"ovr.php?ovr={$row['ovr']}\">{$row['ovr']}</a></td>
<td><a href=\"edit.php?id={$row['id']}\">Edit</a></td>
<td><a href=\"delete.php?id={$row['id']}\" onClick=\"return confirm('Delete This Player?')\">Delete</a></td>
</tr>";
}
?>
        </table>
    </body>
</html>

