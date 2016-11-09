<?php
namespace App\Test\TestCase\Model\Table;

use App\Model\Table\ReservationsTable;
use Cake\ORM\TableRegistry;
use Cake\TestSuite\TestCase;

/**
 * App\Model\Table\ReservationsTable Test Case
 */
class ReservationsTableTest extends TestCase
{

    /**
     * Test subject
     *
     * @var \App\Model\Table\ReservationsTable
     */
    public $Reservations;

    /**
     * Fixtures
     *
     * @var array
     */
    public $fixtures = [
        'app.reservations',
        'app.sports',
        'app.appointments',
        'app.places',
        'app.users',
        'app.teams'
    ];

    /**
     * setUp method
     *
     * @return void
     */
    public function setUp()
    {
        parent::setUp();
        $config = TableRegistry::exists('Reservations') ? [] : ['className' => 'App\Model\Table\ReservationsTable'];
        $this->Reservations = TableRegistry::get('Reservations', $config);
    }

    /**
     * tearDown method
     *
     * @return void
     */
    public function tearDown()
    {
        unset($this->Reservations);

        parent::tearDown();
    }

    /**
     * Test initialize method
     *
     * @return void
     */
    public function testInitialize()
    {
        $this->markTestIncomplete('Not implemented yet.');
    }

    /**
     * Test validationDefault method
     *
     * @return void
     */
    public function testValidationDefault()
    {
        $this->markTestIncomplete('Not implemented yet.');
    }

    /**
     * Test buildRules method
     *
     * @return void
     */
    public function testBuildRules()
    {
        $this->markTestIncomplete('Not implemented yet.');
    }
}
