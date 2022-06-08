import axios from 'axios';
import moment from 'moment';
import React, { useEffect } from 'react';
import { useState, useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';


function CreateBooking({ hide, setDate, users, stylists, options, timeSlots, endTimeDay }) {
    const [checked, setChecked] = useState([]);
    const [totalPrice, setTotalPrice] = useState(0);
    const [totalTime, setTotalTime] = useState(0);
    const [slotChecked, setSlotChecked] = useState('');
    const userIdRef = useRef();
    const stylistIdRef = useRef();
    const startTimeRef = useRef();
    const phoneNumberRef = useRef()
    const dateTimeRef = useRef(new Date(new Date().getTime() - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0, 10));
    const statusRef = useRef();
    const noteRef = useRef();
    const voucherOptionIdRef = useRef();
    const voucherUserIdRef = useRef();
    const adminUserId = users.find(user => user.id === 2);
    const disabledTime = ['12:00', '12:30'];
    const $ = require('jquery');

    useEffect(() => {
        $('#selectUserCreate').select2();
    }, [])

    console.log(users, dateTimeRef.current)

    function handleCreateBooking(event) {
        event.preventDefault();
        let endTime = handleCalEndTime();

        const data = {
            userProfileId: userIdRef.current.value,
            hairStylistId: stylistIdRef.current.value,
            optionBookingMappingId: checked,
            dateTime: dateTimeRef.current.value ? dateTimeRef.current.value : null,
            startTime: slotChecked,
            endTime: endTime,
            status: statusRef.current.value ? statusRef.current.value : null,
            totalDuration: totalTime,
            totalPrice: totalPrice,
            voucherOptionId: voucherOptionIdRef.current.value ? voucherOptionIdRef.current.value : null,
            voucherUserId: voucherUserIdRef.current.value ? voucherUserIdRef.current.value : null,
            note: noteRef.current.value ? noteRef.current.value : null,
            phoneNumer: phoneNumberRef.current.value ? phoneNumberRef.current.value : null
        }

        console.log(data);
        axios.post(SERVER_API_URL + 'api/admin/create-booking', data).then(res => {
            if (res.status) {
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
                        <h3 className='mb-3'>Create Booking</h3>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>User:</label>
                            <div className='col-lg-9 col-md-9 col-xs-12 p-0'>
                                <select id='selectUserCreate' ref={userIdRef} defaultValue={adminUserId.id} className='w-100' >
                                    {users && users.map(user => (
                                        <option key={user.id} value={user.id} >{user.userName}</option>
                                    ))}
                                </select>
                            </div>
                        </div>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Stylist:</label>
                            <select ref={stylistIdRef} className='col-lg-9 col-md-9 col-xs-12 form-select'>
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
                                        <input className="form-check-input" type="checkbox" value={option.id} required  checked={checked.includes(option.id)} onChange={() =>{checkRequired(); handleCheck(option.id, option.price, option.duration)}} />
                                        <label className="form-check-label" htmlFor="flexCheckChecked">
                                            {option.optionName}
                                        </label>
                                    </div>
                                ))}
                            </div>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Duration:</label>
                            <input type="number"  disabled value={totalTime} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>DateTime:</label>
                            <input ref={dateTimeRef} defaultValue={dateTimeRef.current} required type="date" min={new Date(new Date().getTime() - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0, 10)} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Time Slot:</label>
                            <div className="col-lg-9 col-md-9 col-xs-12 d-flex align-content-start flex-wrap">
                                {timeSlots && timeSlots.map((slot, index) => (
                                    <div className="form-check me-2" key={index}>
                                        <input className="form-check-input" type="radio" required  disabled={disabledTime.includes(slot)} value={slot} onChange={e => setSlotChecked(e.target.value)} name="flexRadioDefault" id="flexRadioDefault1" />
                                        <label className="form-check-label" htmlFor="flexRadioDefault1">
                                            {slot}
                                        </label>
                                    </div>
                                ))}
                            </div>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Voucher Option:</label>
                            <input ref={voucherOptionIdRef} type="number" disabled className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Voucher User:</label>
                            <input ref={voucherUserIdRef} type="number" disabled className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Total Price:</label>
                            <input type="number" value={totalPrice} disabled className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Phone Number:</label>
                            <input  ref={phoneNumberRef}  type="text" pattern="[0][0-9]{9}" className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Note:</label>
                            <input ref={noteRef} type="text" className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Status:</label>
                            <select ref={statusRef} defaultValue={'booked'} className='col-lg-9 col-md-9 col-xs-12 form-select'>
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

export default CreateBooking;




