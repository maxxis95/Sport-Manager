<?php
namespace App\Test\TestCase\Model\Table;

use App\Model\Table\SportsTable;
use Cake\ORM\TableRegistry;
use Cake\TestSuite\TestCase;

/**
 * App\Model\Table\SportsTable Test Case
 */
class SportsTableTest extends TestCase
{

    /**
     * Test subject
     *
     * @var \App\Model\Table\SportsTable
     */
    public $Sports;

    /**
     * Fixtures
     *
     * @var array
     */
    public $fixtures = [
        'app.sports',
        'app.reservations',
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
        $config = TableRegistry::exists('Sports') ? [] : ['className' => 'App\Model\Table\SportsTable'];
        $this->Sports = TableRegistry::get('Sports', $config);
    }

    /**
     * tearDown method
     *
     * @return void
     */
    public function tearDown()
    {
        unset($this->Sports);

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
}
