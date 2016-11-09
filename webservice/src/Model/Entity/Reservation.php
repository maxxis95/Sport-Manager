<?php
namespace App\Model\Entity;

use Cake\ORM\Entity;

/**
 * Reservation Entity
 *
 * @property int $id
 * @property \Cake\I18n\Time $created
 * @property int $sport_id
 * @property int $appointment_id
 * @property int $team_id
 * @property int $submitted
 * @property int $confirmed
 *
 * @property \App\Model\Entity\Sport $sport
 * @property \App\Model\Entity\Appointment $appointment
 * @property \App\Model\Entity\Team $team
 */
class Reservation extends Entity
{

    /**
     * Fields that can be mass assigned using newEntity() or patchEntity().
     *
     * Note that when '*' is set to true, this allows all unspecified fields to
     * be mass assigned. For security purposes, it is advised to set '*' to false
     * (or remove it), and explicitly make individual fields accessible as needed.
     *
     * @var array
     */
    protected $_accessible = [
        '*' => true,
        'id' => false
    ];
}
