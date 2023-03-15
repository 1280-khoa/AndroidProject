<?php
include  "connect.php";
$iduser = $_POST['iduser'];
$query = 'SELECT * FROM `donhang` WHERE `iduser`='.$iduser.'';
$data = mysqli_query($conn, $query);
$result = array();
while ($row = mysqli_fetch_assoc($data)) {
	$truyvan = 'SELECT * FROM `chitietdonhang`INNER JOIN xemoi ON chitietdonhang.idve = xemoi.Maxe WHERE chitietdonhang.iddonhang ='.$row['id'].'';
	$data1 = mysqli_query($conn, $truyvan);
	$item = array();
	while ($row1 = mysqli_fetch_assoc($data1)) {
		$item[] = $row1;
	}
	// code...
	$row['item'] = $item;
	$result[] = ($row);
}
if (!empty($result)) {
	// code...
	$arr = [
			'success' => true,
			'message' => "ThanhCong",
			'result' => $result
	];
}else{
	$arr = ['success' => false ,
			'message' => "KhongThanhCong",
			'result' => $result
	];
}
print_r(json_encode($arr));
?>