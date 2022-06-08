import axios from 'axios';
import moment from 'moment';
import React, { useEffect } from 'react';
import { useState, useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';
import 'select2/dist/js/select2'
import 'select2/dist/css/select2.css'

function EditBooking({ hide, setDate, users, stylists, options, timeSlots, endTimeDay, bookingDetail, dateTime, optionBooking, bookingMappingId }) {
    const [status, setStatus] = useState(bookingDetail.status);
    const dateTimeRef = useRef();
    const [note, setNote] = useState(bookingDetail.note);
    const [userId, setUserId] = useState(bookingDetail.userProfileId);
    const [stylistId, setStylistId] = useState(bookingDetail.hairStylistId);
    const [checked, setChecked] = useState(optionBooking);
    const [totalPrice, setTotalPrice] = useState(bookingDetail.totalPrice);
    const [totalTime, setTotalTime] = useState(bookingDetail.totalDuration);
    const [slotChecked, setSlotChecked] = useState(new moment(bookingDetail.startTime, 'HH:mm').format('HH:mm'));
    const [phoneNumber, setPhoneNumber] = useState(bookingDetail.phoneNumber);
    const userIdRef = useRef();
    const disabledTime = ['12:00', '12:30'];
    const $ = require('jquery');

    useEffect(() => {
        $('#selectUser').select2();
    }, [])


    async function handleCreateBooking(event) {
        event.preventDefault();
        let endTime = handleCalEndTime();
        const data = {
            userProfileId: userIdRef.current.value ? userIdRef.current.value : null,
            hairStylistId: stylistId,
            optionBookingMappingId: checked,
            dateTime: dateTimeRef.current.value ? dateTimeRef.current.value : null,
            startTime: slotChecked,
            endTime: endTime,
            status: status ? status : null,
            totalDuration: totalTime,
            totalPrice: totalPrice,
            voucherOptionId: null,
            voucherUserId: null,
            note: note ? note : null,
            phoneNumer: phoneNumber ? phoneNumber : null
        }
        console.log(data)

        await axios.post(SERVER_API_URL + 'api/delete-booking-option-mapping', bookingMappingId).then(res => {
         
        })
        axios.put(SERVER_API_URL + `api/admin/update-booking/${bookingDetail.id}`, data).then(res => {
            if (res.status == 200) {
                setDate();
                hide();
            }
        })

    }

    function handleCalEndTime() {
        let startTime = moment(slotChecked, 'HH:mm');
        let moment12h = moment('12:00', 'HH:mm');
        let endTimeMoment = moment(slotChecked, 'HH:mm').add(totalTime, 'minutes');
        let tempMinute = 0;
        let endTime = moment(endTimeMoment, 'HH:mm');
        let temp = moment(endTimeMoment, 'HH:mm').get('minutes');
        let endTimeDayTemp = moment(endTimeDay, 'HH:mm');
        if (startTime < moment12h) {

            if (endTime > moment12h) {
                endTime = new moment(moment12h).format('HH:mm');
            }
            else {
                if (temp > 0 && temp <= 30) {
                    tempMinute = 30 - temp;
                    endTime = moment(endTimeMoment, 'HH:mm').add(tempMinute, 'minutes').format('HH:mm');
                } else if (temp == 0) {
                    endTime = new moment(endTimeMoment).format('HH:mm')
                } else {
                    tempMinute = 60 - temp;
                    endTime = moment(endTimeMoment, 'HH:mm').add(tempMinute, 'minutes').format('HH:mm');
                }
            }
        } else {
            if (endTime > endTimeDayTemp) {
                endTime = new moment(endTimeDayTemp).format('HH:mm');

            } else {
                if (temp > 0 && temp <= 30) {
                    tempMinute = 30 - temp;
                    endTime = moment(endTimeMoment, 'HH:mm').add(tempMinute, 'minutes').format('HH:mm');
                } else if (temp == 0) {
                    endTime = new moment(endTimeMoment).format('HH:mm')
                } else {
                    tempMinute = 60 - temp;
                    endTime = moment(endTimeMoment, 'HH:mm').add(tempMinute, 'minutes').format('HH:mm');
                }
            }
        }

        return endTime;
    }

    const handleCheck = (id, price, time) => {
        setChecked(prev => {
            if (checked.includes(id)) {
                return checked.filter(item => item != id)
            } else {
                return [...prev, id]
            }
        })
        setTotalPrice(prev => {
            if (checked.includes(id)) {
                return prev - price
            } else {
                return prev + price
            }
        })
        setTotalTime(prev => {
            if (checked.includes(id)) {
                return prev - time
            } else {
                return prev + time
            }
        })
    }
    function checkRequired() {
        if ($('input[type=checkbox]:checked').length > 0) {  // the "> 0" part is unnecessary, actually
            $('input[type=checkbox]').prop('required', false);
        } else {
            $('input[type=checkbox]').prop('required', true);
        }

    }
    return (
        <div className='modal-container'>
            <div className='modal-overlay' />
            <div className='modal-wrapper'>
                <div className='modal-header'>
                    <button type="button" className="btn-close" aria-label="Close" onClick={hide}></button>
                </div>
                <div className='modal-body text-start'>
                    <form onSubmit={handleCreateBooking} className='row p-3 form-modal' >
                        <h3 className='mb-3'>Edit Booking</h3>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>User:</label>
                            <div className='col-lg-9 col-md-9 col-xs-12 p-0'>
                                <select id='selectUser' ref={userIdRef} defaultValue={userId} className='w-100' >
                                    {users && users.map(user => (
                                        <option key={user.id} value={user.id} >{user.userName}</option>
                                    ))}
                                </select>
                            </div>

                        </div>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Stylist:</label>
                            <select value={stylistId} onChange={e => setStylistId(e.target.value)} className='col-lg-9 col-md-9 col-xs-12 form-select'>
                                {stylists && stylists.map(stylists => (
                                    <option key={stylists.id} value={stylists.id} >{stylists.stylistName}</option>
                                ))}
                            </select>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Option:</label>
                            <div className="col-lg-9 col-md-9 col-xs-12 d-flex align-content-start flex-wrap">
                                {options && options.map((option, index) => (
                                    <div className="form-check me-2" key={index}>
                                        <input className="form-check-input" type="checkbox" value={option.id} checked={checked.includes(option.id)} onChange={() => { checkRequired(); handleCheck(option.id, option.price, option.duration) }} />
                                        <label className="form-check-label" htmlFor="flexCheckChecked">
                                            {option.optionName}
                                        </label>
                                    </div>
                                ))}
                            </div>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Duration:</label>
                            <input type="number" disabled value={totalTime} onChange={e => setTotalTime(e.target.value)} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>DateTime:</label>
                            <input type="date" ref={dateTimeRef} defaultValue={dateTime} min={new Date().toISOString().slice(0, 10)} required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Time Slot:</label>
                            <div className="col-lg-9 col-md-9 col-xs-12 d-flex align-content-start flex-wrap">
                                {timeSlots && timeSlots.map((slot, index) => (
                                    <div className="form-check me-2" key={index}>
                                        <input className="form-check-input" type="radio" required disabled={disabledTime.includes(slot)} checked={slot === slotChecked} value={slot} onChange={e => setSlotChecked(e.target.value)} name="flexRadioDefault" id="flexRadioDefault1" />
                                        <label className="form-check-label" htmlFor="flexRadioDefault1">
                                            {slot}
                                        </label>
                                    </div>
                                ))}
                            </div>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Voucher Option:</label>
                            <input type="number" disabled className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Voucher User:</label>
                            <input type="number" disabled className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Total Price:</label>
                            <input type="number" value={totalPrice} onChange={e => setTotalPrice(e.target.value)} disabled className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Phone Number:</label>
                            <input value={phoneNumber || ''} onChange={e => setPhoneNumber(e.target.value)} type="text" pattern="[0][0-9]{9}"  className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Note:</label>
                            <input type="text" value={note || ''} onChange={e => setNote(e.target.value)} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Status:</label>
                            <select value={status} onChange={e => setStatus(e.target.value)} className='col-lg-9 col-md-9 col-xs-12 form-select'>
                                <option value={'booked'}>booked</option>
                                <option value={'in progress'}>in progress</option>
                                <option value={'finished'}>finished</option>
                                <option value={'cancelled'}>cancelled</option>
                            </select>
                        </div>
                        <div className='modal-control mb-4'>
                            <button type='submit' className='btn btn-primary mx-4 ' style={{ width: "5rem" }} onClick={() => handleCreateBooking} >Save</button>
                            <button type='button' className='btn btn-secondary mx-4 w-10' style={{ width: "5rem" }} onClick={hide}>Cancel</button>

                        </div>
                    </form>
                </div>

            </div>
        </div>
    )
}

export default EditBooking;




