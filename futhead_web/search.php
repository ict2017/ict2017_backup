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
if (isset($_REQUEST['ok'])){
	$search = addslashes($_GET['search']);
if (empty($search)) //dung onclick dc
    echo "Please Input Keyword To Search";
 else{
$sql = "SELECT * FROM player left outer join playfor ON player.id = playfor.id left outer join team ON team.teamid = playfor.teamid WHERE LOWER(player.name) LIKE LOWER('%$search%') ORDER BY player.id ASC";

$stmt  = $dbh->prepare($sql);
//$stmt ->bindValue(':search' , '%' . $search . '%', PDO::PARAM_STR);
$stmt ->execute();
$result = $stmt ->fetchAll();
if ($stmt ->rowCount() > 0) { 
echo " <b>Showing Player result(s) with keyword \"$search</b>\": <br><br>";

foreach( $result as $row ) {
echo "<tr>
<td>{$row['id']}</td>
<td>{$row['name']}</td>
<td>{$row['nation']}</td>
<td>{$row['position']}</td>
<td>{$row['team name']}</td>
<td>{$row['squadnum']}</td>
<td>{$row['league']}</td>
<td>{$row['ovr']}</td>
<td><a href=\"edit.php?id={$row['id']}\">Edit</a></td>
<td><a href=\"delete.php?id={$row['id']}\" onClick=\"return confirm('Delete This Player?')\">Delete</a></td>
</tr>";
}
                } else {
echo 'There is nothing to show';
}
			} 
        }
        ?>
		</table>
    </body>
</html>		