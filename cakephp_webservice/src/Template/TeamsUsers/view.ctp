<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('Edit Teams User'), ['action' => 'edit', $teamsUser->id]) ?> </li>
        <li><?= $this->Form->postLink(__('Delete Teams User'), ['action' => 'delete', $teamsUser->id], ['confirm' => __('Are you sure you want to delete # {0}?', $teamsUser->id)]) ?> </li>
        <li><?= $this->Html->link(__('List Teams Users'), ['action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New Teams User'), ['action' => 'add']) ?> </li>
        <li><?= $this->Html->link(__('List Teams'), ['controller' => 'Teams', 'action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New Team'), ['controller' => 'Teams', 'action' => 'add']) ?> </li>
        <li><?= $this->Html->link(__('List Users'), ['controller' => 'Users', 'action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New User'), ['controller' => 'Users', 'action' => 'add']) ?> </li>
    </ul>
</nav>
<div class="teamsUsers view large-9 medium-8 columns content">
    <h3><?= h($teamsUser->id) ?></h3>
    <table class="vertical-table">
        <tr>
            <th scope="row"><?= __('Team') ?></th>
            <td><?= $teamsUser->has('team') ? $this->Html->link($teamsUser->team->name, ['controller' => 'Teams', 'action' => 'view', $teamsUser->team->id]) : '' ?></td>
        </tr>
        <tr>
            <th scope="row"><?= __('User') ?></th>
            <td><?= $teamsUser->has('user') ? $this->Html->link($teamsUser->user->id, ['controller' => 'Users', 'action' => 'view', $teamsUser->user->id]) : '' ?></td>
        </tr>
        <tr>
            <th scope="row"><?= __('Id') ?></th>
            <td><?= $this->Number->format($teamsUser->id) ?></td>
        </tr>
        <tr>
            <th scope="row"><?= __('Confirmed') ?></th>
            <td><?= $this->Number->format($teamsUser->confirmed) ?></td>
        </tr>
    </table>
</div>
