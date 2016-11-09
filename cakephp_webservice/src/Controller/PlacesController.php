<?php
namespace App\Controller;

use App\Controller\AppController;

/**
 * Places Controller
 *
 * @property \App\Model\Table\PlacesTable $Places
 */
class PlacesController extends AppController
{

    /**
     * Index method
     *
     * @return \Cake\Network\Response|null
     */
    public function index()
    {
        $this->paginate = [
            'contain' => ['Users']
        ];
        $places = $this->paginate($this->Places);

        $this->set(compact('places'));
        $this->set('_serialize', ['places']);
    }

    /**
     * View method
     *
     * @param string|null $id Place id.
     * @return \Cake\Network\Response|null
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $place = $this->Places->get($id, [
            'contain' => ['Users', 'Appointments']
        ]);

        $this->set('place', $place);
        $this->set('_serialize', ['place']);
    }

    /**
     * Add method
     *
     * @return \Cake\Network\Response|void Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $place = $this->Places->newEntity();
        if ($this->request->is('post')) {
            $place = $this->Places->patchEntity($place, $this->request->data);
            if ($this->Places->save($place)) {
                $this->Flash->success(__('The place has been saved.'));

                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The place could not be saved. Please, try again.'));
            }
        }
        $users = $this->Places->Users->find('list', ['limit' => 200]);
        $this->set(compact('place', 'users'));
        $this->set('_serialize', ['place']);
    }

    /**
     * Edit method
     *
     * @param string|null $id Place id.
     * @return \Cake\Network\Response|void Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($id = null)
    {
        $place = $this->Places->get($id, [
            'contain' => []
        ]);
        if ($this->request->is(['patch', 'post', 'put'])) {
            $place = $this->Places->patchEntity($place, $this->request->data);
            if ($this->Places->save($place)) {
                $this->Flash->success(__('The place has been saved.'));

                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The place could not be saved. Please, try again.'));
            }
        }
        $users = $this->Places->Users->find('list', ['limit' => 200]);
        $this->set(compact('place', 'users'));
        $this->set('_serialize', ['place']);
    }

    /**
     * Delete method
     *
     * @param string|null $id Place id.
     * @return \Cake\Network\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->request->allowMethod(['post', 'delete']);
        $place = $this->Places->get($id);
        if ($this->Places->delete($place)) {
            $this->Flash->success(__('The place has been deleted.'));
        } else {
            $this->Flash->error(__('The place could not be deleted. Please, try again.'));
        }

        return $this->redirect(['action' => 'index']);
    }
}
