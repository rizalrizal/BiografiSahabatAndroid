<?php
    error_reporting(0);
	header('Content-type: application/json; charset=utf-8');
	header('Access-Control-Allow-Origin: *'); 
    include "config.php";
    $response = array();
    $page = $_GET['page'];
	
	

    if ($page == 'tampil') {
        $response['biografi'] = array();
        $query = "SELECT id,nama,julukan,isi FROM tb_biografi";
        $result = mysqli_query($conn, $query);
        $count = mysqli_num_rows($result);
        if ($count > 0) {
			 $tempArray = array();
            while ($data = mysqli_fetch_array($result)) {
               
                $tempArray['id']   = $data['id'];
                $tempArray['nama']   = $data['nama'];
                $tempArray['julukan'] = $data['julukan'];
                $tempArray['isi'] = $data['isi'];
                $response['biografi'][] = $tempArray;
            }
        } else {
            $tempArray = array();
            $tempArray['id']  = "";
            $tempArray['nama']   = "";
            $tempArray['julukan'] = "";
            $tempArray['isi'] = "";
            $response['biografi'][] = $tempArray;
        }
    } else if ($page == 'simpan') {
        $nama = $_GET['nama'];
        $julukan = $_GET['julukan'];
        $isi = $_GET['isi'];
        $response['ResponseSimpan'] = array();
        $query = "INSERT INTO tb_biografi (nama,julukan,isi) VALUES ('$nama','$julukan','$isi')";
        $result = mysqli_query($conn, $query);
        if ($result) {
            $tempArray = array();
            $tempArray['message'] = "sukses";
            $response['ResponseSimpan'][] = $tempArray;
        } else {
            $tempArray = array();
            $tempArray['message'] = "gagal";
            $response['ResponseSimpan'][] = $tempArray;
        }
    } else if ($page == 'ubah') {
        $nama = $_GET['nama'];
        $julukan = $_GET['julukan'];
        $isi = $_GET['isi'];
        $id = $_GET['id'];
        $response['ResponseUbah'] = array();
        $query = "UPDATE tb_biografi SET nama='$nama',julukan='$julukan',isi='$isi' WHERE id='$id'";
        $result = mysqli_query($conn, $query);
        if ($result) {
            $tempArray = array();
            $tempArray['message'] = "sukses";
            $response['ResponseUbah'][] = $tempArray;
        } else {
            $tempArray = array();
            $tempArray['message'] = "gagal";
            $response['ResponseUbah'][] = $tempArray;
        }
    } else if ($page == 'hapus') {
        $id = $_GET['id'];
        $response['ResponseHapus'] = array();
        $query = "DELETE FROM tb_biografi WHERE id='$id'";
        $result = mysqli_query($conn, $query);
        if ($result) {
            $tempArray = array();
            $tempArray['message'] = "sukses";
            $response['ResponseHapus'][] = $tempArray;
        } else {
            $tempArray = array();
            $tempArray['message'] = "gagal";
            $response['ResponseHapus'][] = $tempArray;
        }
    }else if($page=='login'){
		$response['ResponseLogin'] = array();
		$username = $_GET['username'];
		$password = $_GET['password'];
        $query = "SELECT * FROM tb_login where username ='$username' and password='$password'";
        $result = mysqli_query($conn, $query);
        $count = mysqli_num_rows($result);
        if ($count > 0) {
            $tempArray = array();
            $tempArray['message'] = "sukses";
            $response['ResponseLogin'][] = $tempArray;
        } else {
            $tempArray = array();
            $tempArray['message'] = "gagal";
            $response['ResponseLogin'][] = $tempArray;
        }
	
	
	}
	// The function unicode encode
					function utf8_encode_deep(&$input) {
					    if (is_string($input)) {
					        $input = utf8_encode($input);
					    } else if (is_array($input)) {
					        foreach ($input as &$value) {
					            utf8_encode_deep($value);
					        }

					        unset($value);
					    } else if (is_object($input)) {
					        $vars = array_keys(get_object_vars($input));

					        foreach ($vars as $var) {
					            utf8_encode_deep($input->$var);
					        }
					    }
					}
					// $structure is now utf8 encoded
					utf8_encode_deep($response);
	
	echo json_encode($response, JSON_HEX_QUOT | JSON_HEX_TAG | JSON_HEX_AMP | JSON_HEX_APOS | JSON_NUMERIC_CHECK | JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES | JSON_PRESERVE_ZERO_FRACTION | JSON_UNESCAPED_UNICODE | JSON_PARTIAL_OUTPUT_ON_ERROR);
	
?>
