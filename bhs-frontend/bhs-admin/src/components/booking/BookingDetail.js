import axios from 'axios';
import moment from 'moment';
import React, { useEffect } from 'react';
import { useState, useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';


function BookingDetail({ hide, bookingDetail, dateTime,options, optionBooking }) {

    return (
        <div className='modal-container'>
            <div className='modal-overlay' />
            <div className='modal-wrapper'>
                <div className='modal-header'>
                    <button type="button" className="btn-close" aria-label="Close" onClick={hide}></button>
                </div>
                <div className='modal-body text-start'>
                    <form className='row p-3 form-modal' >
                        <h3 className='mb-3'>Booking Detail</h3>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>User:</label>
                            <input type="text"  defaultValue={bookingDetail.email} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Name:</label>
                            <input type="text"  defaultValue={bookingDetail.firstName +' '+ bookingDetail.lastName} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Stylist:</label>
                            <input type="text"  defaultValue={bookingDetail.stylistName} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Option:</label>
                            <div className="col-lg-9 col-md-9 col-xs-12 d-flex align-content-start flex-wrap">
                                {options && options.map((option, index) => (
                                    <div className="form-check me-2" key={index}>
                                        <input className="form-check-input" type="checkbox" defaultChecked={optionBooking.includes(option.id)}  />
                                        <label className="form-check-label" htmlFor="flexCheckChecked">
                                            {option.optionName}
                                        </label>
                                    </div>
                                ))}
                            </div>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Duration:</label>
                            <input type="text"  defaultValue={bookingDetail.totalDuration} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>DateTime:</label>
                            <input type="date" defaultValue={dateTime} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Start Time:</label>
                            <input type="text"  defaultValue={bookingDetail.startTime} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>End Time:</label>
                            <input type="text"  defaultValue={bookingDetail.endTime} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Voucher Option:</label>
                            <input type="text"  className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Voucher User:</label>
                            <input type="text"  className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Phone Number:</label>
                            <input type="text" defaultValue={bookingDetail.phoneNumber}  className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Total Price:</label>
                            <input type="text" defaultValue={bookingDetail.totalPrice}  className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Note:</label>
                            <input type="text"  defaultValue={bookingDetail.note} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Status:</label>
                            <input type="text"  defaultValue={bookingDetail.status} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default BookingDetail;




