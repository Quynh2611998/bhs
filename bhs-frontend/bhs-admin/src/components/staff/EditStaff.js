import axios from 'axios';
import React from 'react';
import ReactDOM from 'react-dom';
import { useEffect, useState, useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';


function EditStaff({ hide, id, stylists, setDate }) {

    const staffNameRef = useRef();
    const phoneRef = useRef();
    const genderRef = useRef();
    const stylist = stylists.find(stylist => id === stylist.id)
    const [name, setName] = useState(stylist.stylistName);
    const [phone, setPhone] = useState(stylist.phoneNumber);
    const [gender, setGender] = useState(stylist.gender);

    function HandleEditStaff(event) {
        event.preventDefault();
        const data = {
            stylistName: staffNameRef.current.value ? staffNameRef.current.value : null,
            phoneNumber: phoneRef.current.value ? phoneRef.current.value : null,
            gender: gender ? gender : null
        }
        axios.put(SERVER_API_URL + 'api/admin/update-hair-stylist/' + id, data).then(res => {
            if (res.status === 200) {
                setDate()
                hide()
            }
        })

    }
    return (
        <div className='modal-container'>
            <div className='modal-overlay' />
            <div className='modal-wrapper'>
                <div className='modal-header'>
                    <button type="button" className="btn-close" aria-label="Close" onClick={hide}></button>
                </div>
                <div className='modal-body text-start'>
                    <form onSubmit={HandleEditStaff} className='row p-3' >
                        <h3 className='mb-3'>Create Staff</h3>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Staff Name:</label>
                            <input ref={staffNameRef} value={name} onChange={e => setName(e.target.value)}  maxLength={255} required type="text" className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Phone:</label>
                            <input ref={phoneRef} value={phone} onChange={e => setPhone(e.target.value)}  type="text" pattern="[0][0-9]{9}" required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Gender:</label>
                            <div className='col-lg-9 col-md-9 col-xs-12 form-control border-0'>
                                <div className="form-check form-check-inline">
                                    <input ref={genderRef} className="form-check-input fs-5 " type="radio" name="inlineRadioOptions" required id="inlineRadio1" checked={gender === 'male' || gender === 'Male'} onChange={e => setGender(e.target.value)} value="male"></input>
                                    <label className="form-check-label fs-5" htmlFor="inlineRadio1">Male</label>
                                </div>
                                <div className="form-check form-check-inline">
                                    <input className="form-check-input fs-5" type="radio" name="inlineRadioOptions" required id="inlineRadio2" checked={gender === 'female' || gender === 'Female'} onChange={e => setGender(e.target.value)} value="female"></input>
                                    <label className="form-check-label fs-5" htmlFor="inlineRadio2">Female</label>
                                </div>
                                <div className="form-check form-check-inline">
                                    <input className="form-check-input fs-5" type="radio" name="inlineRadioOptions" required id="inlineRadio3" checked={gender === 'other' || gender == 'Other'} onChange={e => setGender(e.target.value)} value="other" ></input>
                                    <label className="form-check-label fs-5" htmlFor="inlineRadio3">Other</label>
                                </div>
                            </div>
                        </div>
                        <div className='modal-control mb-4'>
                            <button type='submit' className='btn btn-primary mx-4 ' style={{ width: "5rem" }} onClick={() =>HandleEditStaff} >Save</button>
                            <button type='button' className='btn btn-secondary mx-4 w-10' style={{ width: "5rem" }} onClick={hide}>Cancel</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    )
}
export default EditStaff;





