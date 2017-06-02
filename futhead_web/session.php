<?php
   include("connect_db.php");
   session_start();
   
   $user_check = $_SESSION['login_user'];
   $user_type = $_SESSION['type'];   
   $ses_sql = $dbh->query("select username from login where username = '$user_check' ");
   
   $row = $ses_sql->fetch(PDO::FETCH_ASSOC);
   
   $login_session = $row['username'];
   
   if(!isset($_SESSION['login_user'])){
      header("location:login.php");
   }
?>