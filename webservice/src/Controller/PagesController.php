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

		if(!empty($this->request->query)){
			$fromClient = $this->request->query;
		} else if (!empty($this->request->data)){
			$fromClient = $this->request->data;
		} else {
			//testni podaci
			// $fromClient["data"] = file_get_contents('rawJson.txt');
			$fromClient["method"] = "getUserById";
			$fromClient["pass"] = 5;
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
		
		
		if(!empty($fromClient["data"]) && $data == NULL ){
			echo "[ERROR] Could not parse json data! Use json validator!";
			die();
		}
		
		if($fromClient["method"] == "getUserById"){
			
			if(!empty($fromClient["pass"])){
				$workingTable = TableRegistry::get("Users");
				$user = $workingTable->findById($fromClient["pass"])->first();
				if($user){
					$user = $user->toArray();
					foreach($user as $key => $value){
						$javaKey = $this->CapsTo_($key ,1);
						$javaKey = lcfirst($javaKey);
						
						$user[$javaKey] = $value;
						if($key!= $javaKey){
							unset($user[$key]);
						}
					} 
					echo json_encode($user);
					die();
				}
				$logFile .= "[ERROR] No user with specified id found \n". file_get_contents('log_file.txt');
				file_put_contents('log_file.txt', $logFile);
				die();
			}
		}
		
		//Parsira primljene podatke u data paremetru nazad u polje
		foreach($data as $key=>$array_member){
			$data[$key] = json_decode($data[$key], true);
			if($this->checkForTable($key)){
				$this->firstLevel($key, $data[$key]);
			}
		}

		die();
    }

	
	private function firstLevel($klj, $vr){
		$workingTable = TableRegistry::get($klj);
		$newRow = $workingTable->newEntity();
		
		$forInsert = $vr[0];
		
	
		foreach ($forInsert as $key => $value) {
			$key = $this->CapsTo_($key);
			$key = strtolower($key);
			
			if($this->checkForTable($key)){
				$newRow->set($key, $this->lastLevel($key, $value));
			} else {
				$newRow->set($key, $value);
			}

		}
		dump($newRow);die();	
	}
	
	private function lastLevel($klj, $vr){
		$workingTable = TableRegistry::get($klj);
		$newRow = $workingTable->newEntity();
		$forInsert = $vr;
	
		foreach ($forInsert as $key => $value) {
			$key = $this->CapsTo_($key);
			$key = strtolower($key);
			$newRow->set($key, $value);
		}
		
		return $newRow;
	}
	
	private function checkForTable($a){
		$targets = array('discounts', 'store', 'users','places','appointments',"teams_users","teams", "reservations", "sports");
		foreach($targets as $t) 
		{
			if ($a == $t) {
				return true;
			}
		}
		return false;
	}
	
	private function CapsTo_($key, $direction = NULL){
		if($direction == NULL){
			$key = preg_replace('/\B([A-Z])/', '_$1', $key);
			return $key = preg_replace('/\B(I_D)/', 'ID', $key);
		} else {
			return str_replace('_','',ucwords($key,'_'));
		}
		
	}
}
