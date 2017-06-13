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
	<link rel="stylesheet" type="text/css" href="../index.css" />
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

        <table style="text-align:center " border="1"; width=100%>
            <tr>
				<th>Team ID</th>
				<th>Team Name</th>
				<th>League</th>
				<th>Number of Players</th>
				<th>Edit Team</th>
				<th>Delete Team</th>
            </tr>
<?php
require('connect_db.php');
//$i = 1; <td>{$i}</td>
$sql = 'SELECT	team.*, COUNT(playfor.id) FROM team left outer JOIN playfor ON team.teamid = playfor.teamid GROUP BY team.teamid, team.*';

foreach ($dbh->query($sql) as $row) 
{
echo "<tr>
<td><a href=\"teaminfo.php?id={$row['teamid']}\">{$row['teamid']}</a></td>
<td><a href=\"teaminfo.php?id={$row['teamid']}\">{$row['team name']}</a></td>
<td><a href=\"leagueinfo.php?name={$row['league']}\">{$row['league']}</a></td>
<td>{$row['count']}</td>
<td><a href=\"editteam.php?id={$row['teamid']}\">Edit</a></td>
<td><a href=\"delete.php?teamid={$row['teamid']}\" onClick=\"return confirm('Delete This Team?')\">Delete</a></td>";
echo "</tr>";
//$i++;
}
?>
        </table>
    </body>
</html>

