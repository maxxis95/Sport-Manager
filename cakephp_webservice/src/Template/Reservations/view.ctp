<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('Edit Reservation'), ['action' => 'edit', $reservation->id]) ?> </li>
        <li><?= $this->Form->postLink(__('Delete Reservation'), ['action' => 'delete', $reservation->id], ['confirm' => __('Are you sure you want to delete # {0}?', $reservation->id)]) ?> </li>
        <li><?= $this->Html->link(__('List Reservations'), ['action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New Reservation'), ['action' => 'add']) ?> </li>
        <li><?= $this->Html->link(__('List Sports'), ['controller' => 'Sports', 'action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New Sport'), ['controller' => 'Sports', 'action' => 'add']) ?> </li>
        <li><?= $this->Html->link(__('List Appointments'), ['controller' => 'Appointments', 'action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New Appointment'), ['controller' => 'Appointments', 'action' => 'add']) ?> </li>
        <li><?= $this->Html->link(__('List Teams'), ['controller' => 'Teams', 'action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New Team'), ['controller' => 'Teams', 'action' => 'add']) ?> </li>
    </ul>
</nav>
<div class="reservations view large-9 medium-8 columns content">
    <h3><?= h($reservation->id) ?></h3>
    <table class="vertical-table">
        <tr>
            <th scope="row"><?= __('Sport') ?></th>
            <td><?= $reservation->has('sport') ? $this->Html->link($reservation->sport->name, ['controller' => 'Sports', 'action' => 'view', $reservation->sport->id]) : '' ?></td>
        </tr>
        <tr>
            <th scope="row"><?= __('Appointment') ?></th>
            <td><?= $reservation->has('appointment') ? $this->Html->link($reservation->appointment->id, ['controller' => 'Appointments', 'action' => 'view', $reservation->appointment->id]) : '' ?></td>
        </tr>
        <tr>
            <th scope="row"><?= __('Team') ?></th>
            <td><?= $reservation->has('team') ? $this->Html->link($reservation->team->name, ['controller' => 'Teams', 'action' => 'view', $reservation->team->id]) : '' ?></td>
        </tr>
        <tr>
            <th scope="row"><?= __('Id') ?></th>
            <td><?= $this->Number->format($reservation->id) ?></td>
        </tr>
        <tr>
            <th scope="row"><?= __('Submitted') ?></th>
            <td><?= $this->Number->format($reservation->submitted) ?></td>
        </tr>
        <tr>
            <th scope="row"><?= __('Confirmed') ?></th>
            <td><?= $this->Number->format($reservation->confirmed) ?></td>
        </tr>
        <tr>
            <th scope="row"><?= __('Created') ?></th>
            <td><?= h($reservation->created) ?></td>
        </tr>
    </table>
</div>
