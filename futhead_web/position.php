<html>
<head>
	<meta charset="UTF-8">
    <title>FIFA Player Rating Database</title>
	<meta name="author" content="WebDev">
	<link rel="stylesheet" type="text/css" href="index.css" />
</head>
<body>
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
<header id="att_header">
			<h1>Attacking Position</h1>
</header>
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
require('connect_db.php');

$sql = 'SELECT * FROM player left outer join playfor ON player.id = playfor.id left outer join team ON team.teamid = playfor.teamid WHERE position IN (\'ST\', \'CF\', \'LF\', \'RF\', \'RW\', \'LW\')
ORDER BY player.*;';
foreach ($dbh->query($sql) as $row) 
{
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
	<header id="mid_header">
			<h1>Midfield Position</h1>
</header>
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
$sql2 = 'SELECT * FROM player left outer join playfor ON player.id = playfor.id left outer join team ON team.teamid = playfor.teamid WHERE position IN (\'CDM\', \'CM\', \'LM\', \'RM\', \'CAM\')
ORDER BY player.*
';
foreach ($dbh->query($sql2) as $row) 
{
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
<header id="def_header">
			<h1>Defensive Position</h1>
</header>
	</table>
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
$sql3 = 'SELECT * FROM player left outer join playfor ON player.id = playfor.id left outer join team ON team.teamid = playfor.teamid WHERE position IN (\'CB\', \'GK\', \'LB\', \'RB\', \'LWB\', \'RWB\')
ORDER BY player.*';
foreach ($dbh->query($sql3) as $row) 
{
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

