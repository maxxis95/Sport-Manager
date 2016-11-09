<?php
namespace App\Controller;

use App\Controller\AppController;

/**
 * Sports Controller
 *
 * @property \App\Model\Table\SportsTable $Sports
 */
class SportsController extends AppController
{

    /**
     * Index method
     *
     * @return \Cake\Network\Response|null
     */
    public function index()
    {
        $sports = $this->paginate($this->Sports);

        $this->set(compact('sports'));
        $this->set('_serialize', ['sports']);
    }

    /**
     * View method
     *
     * @param string|null $id Sport id.
     * @return \Cake\Network\Response|null
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $sport = $this->Sports->get($id, [
            'contain' => ['Reservations']
        ]);

        $this->set('sport', $sport);
        $this->set('_serialize', ['sport']);
    }

    /**
     * Add method
     *
     * @return \Cake\Network\Response|void Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $sport = $this->Sports->newEntity();
        if ($this->request->is('post')) {
            $sport = $this->Sports->patchEntity($sport, $this->request->data);
            if ($this->Sports->save($sport)) {
                $this->Flash->success(__('The sport has been saved.'));

                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The sport could not be saved. Please, try again.'));
            }
        }
        $this->set(compact('sport'));
        $this->set('_serialize', ['sport']);
    }

    /**
     * Edit method
     *
     * @param string|null $id Sport id.
     * @return \Cake\Network\Response|void Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($id = null)
    {
        $sport = $this->Sports->get($id, [
            'contain' => []
        ]);
        if ($this->request->is(['patch', 'post', 'put'])) {
            $sport = $this->Sports->patchEntity($sport, $this->request->data);
            if ($this->Sports->save($sport)) {
                $this->Flash->success(__('The sport has been saved.'));

                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The sport could not be saved. Please, try again.'));
            }
        }
        $this->set(compact('sport'));
        $this->set('_serialize', ['sport']);
    }

    /**
     * Delete method
     *
     * @param string|null $id Sport id.
     * @return \Cake\Network\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->request->allowMethod(['post', 'delete']);
        $sport = $this->Sports->get($id);
        if ($this->Sports->delete($sport)) {
            $this->Flash->success(__('The sport has been deleted.'));
        } else {
            $this->Flash->error(__('The sport could not be deleted. Please, try again.'));
        }

        return $this->redirect(['action' => 'index']);
    }
}
