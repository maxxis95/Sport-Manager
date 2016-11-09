<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Form->postLink(
                __('Delete'),
                ['action' => 'delete', $teamsUser->id],
                ['confirm' => __('Are you sure you want to delete # {0}?', $teamsUser->id)]
            )
        ?></li>
        <li><?= $this->Html->link(__('List Teams Users'), ['action' => 'index']) ?></li>
        <li><?= $this->Html->link(__('List Teams'), ['controller' => 'Teams', 'action' => 'index']) ?></li>
        <li><?= $this->Html->link(__('New Team'), ['controller' => 'Teams', 'action' => 'add']) ?></li>
        <li><?= $this->Html->link(__('List Users'), ['controller' => 'Users', 'action' => 'index']) ?></li>
        <li><?= $this->Html->link(__('New User'), ['controller' => 'Users', 'action' => 'add']) ?></li>
    </ul>
</nav>
<div class="teamsUsers form large-9 medium-8 columns content">
    <?= $this->Form->create($teamsUser) ?>
    <fieldset>
        <legend><?= __('Edit Teams User') ?></legend>
        <?php
            echo $this->Form->input('team_id', ['options' => $teams]);
            echo $this->Form->input('user_id', ['options' => $users]);
            echo $this->Form->input('confirmed');
        ?>
    </fieldset>
    <?= $this->Form->button(__('Submit')) ?>
    <?= $this->Form->end() ?>
</div>
