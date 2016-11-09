<?php
namespace App\Controller;

use App\Controller\AppController;

/**
 * Reservations Controller
 *
 * @property \App\Model\Table\ReservationsTable $Reservations
 */
class ReservationsController extends AppController
{

    /**
     * Index method
     *
     * @return \Cake\Network\Response|null
     */
    public function index()
    {
        $this->paginate = [
            'contain' => ['Sports', 'Appointments', 'Teams']
        ];
        $reservations = $this->paginate($this->Reservations);

        $this->set(compact('reservations'));
        $this->set('_serialize', ['reservations']);
    }

    /**
     * View method
     *
     * @param string|null $id Reservation id.
     * @return \Cake\Network\Response|null
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $reservation = $this->Reservations->get($id, [
            'contain' => ['Sports', 'Appointments', 'Teams']
        ]);

        $this->set('reservation', $reservation);
        $this->set('_serialize', ['reservation']);
    }

    /**
     * Add method
     *
     * @return \Cake\Network\Response|void Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $reservation = $this->Reservations->newEntity();
        if ($this->request->is('post')) {
            $reservation = $this->Reservations->patchEntity($reservation, $this->request->data);
            if ($this->Reservations->save($reservation)) {
                $this->Flash->success(__('The reservation has been saved.'));

                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The reservation could not be saved. Please, try again.'));
            }
        }
        $sports = $this->Reservations->Sports->find('list', ['limit' => 200]);
        $appointments = $this->Reservations->Appointments->find('list', ['limit' => 200]);
        $teams = $this->Reservations->Teams->find('list', ['limit' => 200]);
        $this->set(compact('reservation', 'sports', 'appointments', 'teams'));
        $this->set('_serialize', ['reservation']);
    }

    /**
     * Edit method
     *
     * @param string|null $id Reservation id.
     * @return \Cake\Network\Response|void Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($id = null)
    {
        $reservation = $this->Reservations->get($id, [
            'contain' => []
        ]);
        if ($this->request->is(['patch', 'post', 'put'])) {
            $reservation = $this->Reservations->patchEntity($reservation, $this->request->data);
            if ($this->Reservations->save($reservation)) {
                $this->Flash->success(__('The reservation has been saved.'));

                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The reservation could not be saved. Please, try again.'));
            }
        }
        $sports = $this->Reservations->Sports->find('list', ['limit' => 200]);
        $appointments = $this->Reservations->Appointments->find('list', ['limit' => 200]);
        $teams = $this->Reservations->Teams->find('list', ['limit' => 200]);
        $this->set(compact('reservation', 'sports', 'appointments', 'teams'));
        $this->set('_serialize', ['reservation']);
    }

    /**
     * Delete method
     *
     * @param string|null $id Reservation id.
     * @return \Cake\Network\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->request->allowMethod(['post', 'delete']);
        $reservation = $this->Reservations->get($id);
        if ($this->Reservations->delete($reservation)) {
            $this->Flash->success(__('The reservation has been deleted.'));
        } else {
            $this->Flash->error(__('The reservation could not be deleted. Please, try again.'));
        }

        return $this->redirect(['action' => 'index']);
    }
}
