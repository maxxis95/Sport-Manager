<?php
/**
 * CakePHP(tm) : Rapid Development Framework (http://cakephp.org)
 * Copyright (c) Cake Software Foundation, Inc. (http://cakefoundation.org)
 *
 * Licensed under The MIT License
 * For full copyright and license information, please see the LICENSE.txt
 * Redistributions of files must retain the above copyright notice.
 *
 * @copyright Copyright (c) Cake Software Foundation, Inc. (http://cakefoundation.org)
 * @link      http://cakephp.org CakePHP(tm) Project
 * @since     0.2.9
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 */
namespace App\Controller;

use Cake\Core\Configure;
use Cake\Network\Exception\NotFoundException;
use Cake\View\Exception\MissingTemplateException;
use Cake\I18n\Time;
use Cake\I18n\FrozenTime;
// use Cake\I18n\Date;
use Cake\I18n\FrozenDate;
use Cake\ORM\TableRegistry;

/**
 * Static content controller
 *
 * This controller will render views from Template/Pages/
 *
 * @link http://book.cakephp.org/3.0/en/controllers/pages-controller.html
 */
class PagesController extends AppController
{

    /**
     * Displays a view
     *
     * @return void|\Cake\Network\Response
     * @throws \Cake\Network\Exception\NotFoundException When the view file could not
     *   be found or \Cake\View\Exception\MissingTemplateException in debug mode.
     */
    public function home()
    {
		$this->viewBuilder()->layout('ajax');
		
		// Time::setJsonEncodeFormat('yyyy-MM-dd HH:mm:ss');  // For any mutable DateTime
		// Date::setJsonEncodeFormat('yyyy-MM-dd HH:mm:ss');  // For any mutable Date
		FrozenTime::setJsonEncodeFormat('yyyy-MM-dd HH:mm:ss');  // For any immutable DateTime
		FrozenDate::setJsonEncodeFormat('yyyy-MM-dd HH:mm:ss');  // For any immutable Date
		
		$response = [];
		$response["statusCode"] = $response["message"] = $response["data"] = null;

		if(!empty($this->request->query)){
			$fromClient = $this->request->query;
		} else if (!empty($this->request->data)){
			$fromClient = $this->request->data;
		} else {
			echo "<a href='/log_file.txt'>log_file.txt</a><br>";
			echo "<a href='/users'>Users CRUD</a>";
			die(); 
			// $fromClient["method"] = "getData"; 
			// $fromClient["table_name"] = "Appointments";
			// $fromClient["search_by"] = "place_id;date <=";
			// $fromClient["value"] = "2;1485528644";
			
			// $fromClient["method"] = "setData";
			// $fromClient["table_name"] = "Places";
			// $fromClient["data"] = "{\"address\":\"gv 4\",\"name\":\"Novotvorena sportska dvorana\",\"firstName\":\"cc\",\"user_id\":1,\"lastName\":\"bv\",\"phone\":\"99\"}";
			// $fromClient["data"] = "{\"address\":\"gv\",\"email\":\"gv\",\"firstName\":\"cc\",\"id\":0,\"lastName\":\"bv\",\"phone\":\"99\",\"extra\":[{\"address\":\"gv\",\"email\":\"gv\",\"firstName\":\"cc\",\"id\":0,\"lastName\":\"bv\",\"phone\":\"99\"}]}";
			
			/* $errorMsg = "[ERROR] No GET/POST parameters!";
			$this->logAndRespond($errorMsg); */
		}
		
		if(!empty($fromClient["data"])){
			$data = json_decode($fromClient["data"], true);
		} else {
			$data = null;
		}
		
		
		$timestamp = date('d.m.Y. H:i:s', time());
		$logFile = "[$timestamp] ";
		if(!empty($fromClient["data"]) && $data == NULL ){
			$logFile .= "[ERROR] Could not parse json data! Use json validator! ";
		}
		$logFile .= json_encode($fromClient) . "\n\n\n\n". file_get_contents('log_file.txt');
		file_put_contents('log_file.txt', $logFile);
		$logFile = "";
		
		if($fromClient["method"]=="getData"){
			$workingTable = TableRegistry::get($fromClient["table_name"]);
			$query = $workingTable->find("all");

			if(!empty($fromClient["search_by"])){
				
				if(strpos($fromClient["search_by"], ";")){
					$where_arguments = explode(";", $fromClient["search_by"]);
					$values = explode(";", $fromClient["value"]);
					
					foreach ($values as $key => $value){

						if(strpos($where_arguments[$key], "date") !== false){ 
							$values[$key] = date("Y-m-d", $values[$key]);
							// $values[$key] = date("Y-m-d H:i:s", $values[$key]);
						}
						$query->where([$where_arguments[$key] => $values[$key]])->limit(1);
					}
					
				} else {
					$query->where([$fromClient["search_by"] => $fromClient["value"]])->limit(1);
					
				}
				

				$query = $query->toArray();
				if(!empty($query)){
					$query = $query[0];
				}
			} else {
				$query = $query->toArray();
			}
			
		
			if(count($query)!=0){
				$response = $this->convertNames($query, true);
			} else {
				$errorMsg = "[ERROR] No record found!";
				$this->logAndRespond($errorMsg);
			}

			$this->logAndRespond(null, json_encode($response), null, 200);

		} else if($fromClient["method"]=="setData"){
			$data = $this->convertNames($data, false);
			$workingTable = TableRegistry::get($fromClient["table_name"]);
			$newEntity = $workingTable->newEntity();
			$workingTable->patchEntity($newEntity, $data);
			
			if(!$workingTable->save($newEntity)){
				$errorMsg = "[ERROR] Unable to save entity!";
				$this->logAndRespond($errorMsg);
			}
			
			$this->logAndRespond("User saved successfully!", null, null, 200);
		
		} else {
			$errorMsg = "[ERROR] Unknown method!";
			$this->logAndRespond($errorMsg);
		}
		die();
    }
	
	
	private function convertNames($data, $toCamelCase){
		foreach ($data as $key=>$value){

				if(is_object($value) && get_class($value) == "Cake\I18n\FrozenTime") {
					$value = $value->format('h:i:s');
					// die();
					
				}
				if(is_array($value)){
					$i++;
					$data[$key] = $this->convertNames($value, $toCamelCase);
				} else if(is_object($value) && get_class($value) != "Cake\I18n\FrozenTime") {

					$data[$key] = $this->convertNames($value->toArray(), $toCamelCase);
					
				} else {
					
					if($toCamelCase){
						$fixed_name = \Cake\Utility\Inflector::camelize($key);
						$fixed_name = lcfirst($fixed_name);
						
						// $fixed_name = $this->CapsTo_($key ,1);
						// $fixed_name = lcfirst($fixed_name);
					} else {
						$fixed_name = \Cake\Utility\Inflector::underscore($key);
					}
					
					if($fixed_name!=$key){
						$data[$fixed_name] = $value;
						unset($data[$key]);
					}
				}
		}

		return $data;
	}
	private function logAndRespond($msg, $data = null, $table=null, $statusCode=404){
		$logFile = "";
		$response = [];
		$logFile .= $msg."\n". file_get_contents('log_file.txt');
		file_put_contents('log_file.txt', $logFile);
		
		$response["statusCode"] = $statusCode;
		$response["message"] = $msg;
		$response["data"] = $data;
		$response["table"] = $table;
		
		echo json_encode($response);
		
		die();
	}
	
	private function logAndExit($msg){
		$logFile = "";
		
		$logFile .= $msg. file_get_contents('log_file.txt');
		file_put_contents('log_file.txt', $logFile);
		die();
	}
	
	private function CapsTo_($key, $direction = NULL){
		if($direction == NULL){
			$key = preg_replace('/\B([A-Z])/', '_$1', $key);
			return $key = preg_replace('/\B(I_D)/', 'ID', $key);
		} else {
			return str_replace('_','',ucwords($key,'_'));
		}
		
	}
	
	public function pictureUpload(){
		$this->viewBuilder()->layout('ajax');

		if($this->request->data != null){
			
			$debug = '<pre>'.print_r($this->request->data["method"],1).'</pre><hr>';
				file_put_contents("photo_upload.log", $debug, FILE_APPEND);
			
			
			$data = $this->request->data;
			$data['photo_url'] = $data["upload"];
			$filename = $data ['photo_url'] ['name'];
			$newFilename = hash_hmac ( 'sha256', $filename, 'alo' ) . rand(1, 100) . '.jpg';
			
			$destination = WWW_ROOT . "img/uploads/users/" . $newFilename;
			$urlLink = 'uploads/users/' . $newFilename;
			
			if (!move_uploaded_file ( $data ['photo_url'] ['tmp_name'], $destination )) {
				$errorMsg = "[ERROR] Unable to upload picture!";
				$this->logAndRespond($errorMsg);
			}
			
			if(strpos($data["method"], "changeUserPicture")){
				$table_name = "Users";
			} else {
				$errorMsg = "[ERROR] Invalid change picture method! ".$data["method"];
				$this->logAndRespond($errorMsg);
			}
			
			$workingTable = TableRegistry::get($table_name);
			$entity = $workingTable->get($data["id"]);
			if($entity == null){
				$errorMsg = "[ERROR] Record not found in table Users! ".$data["method"];
				$this->logAndRespond($errorMsg);
			}
			$entity->img = "http://sportmanager.fitforev.lin25.host25.com/img/".$urlLink;
			if($workingTable->save($entity)){
				$debug = '<pre>'.print_r($this->request->data,1).'</pre><hr>';
				file_put_contents("photo_upload.log", $debug, FILE_APPEND);
				
				$this->logAndRespond($entity->img, null, null, 200);				
			} else {
				$errorMsg = "[ERROR] Unable to update database record!";
				$this->logAndRespond($errorMsg);
			}
			
		} else {
			echo file_get_contents("photo_upload.log");
		}

		die();
	}
}
