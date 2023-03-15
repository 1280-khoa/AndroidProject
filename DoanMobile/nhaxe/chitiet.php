<?php
include  "connect.php";
$page = $_POST['page'];
$total = 5;
$pos = ($page-1)*$total;
$MaHangXe = $_POST['MaHangXe'];
$query = 'SELECT * FROM `xemoi` WHERE `MaHangXe` = '.$MaHangXe.' LIMIT '.$pos.','.$total.'';
$data = mysqli_query($conn, $query);
$result = array();
while ($row = mysqli_fetch_assoc($data)) {
	// code...
	$result[]=($row);
}
if (!empty($result)) {
	// code...
	$arr = [
			'success' => true,
			'message' => "Thanh Cong",
			'result' => $result
	];
}else{
	$arr = ['success' => false ,
			'message' => "Khong Thanh Cong",
			'result' => $result];
}
print_r(json_encode($arr));
?>