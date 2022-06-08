import { useRef, useState } from "react";
import axios from 'axios';
import { SERVER_API_URL } from '../../common/common-constant';



function CreateVoucherOption({ hide, setDate, options }) {

    const nameRef = useRef();
    const discountRef = useRef();
    const timeStart = useRef();
    const timeExpire = useRef();
    const [checked, setChecked] = useState([]);
    const $ = require('jquery');

    const HandleCreateVoucherOption = (event) => {
        event.preventDefault();
        const data = {
            name: nameRef.current.value ? nameRef.current.value : null,
            discount: discountRef.current.value ? discountRef.current.value : null,
            dayStart: timeStart.current.value ? timeStart.current.value : null,
            dayExpire: timeExpire.current.value ? timeExpire.current.value : null,
            optionId: checked ? checked : null
        }
        axios.post(SERVER_API_URL + 'api/admin/create-voucher-option', data).then(res => {
            if (res.status === 200)
                setDate();
            hide();
        })

    }
    const handleCheck = (id) => {
        setChecked(prev => {
            if (checked.includes(id)) {
                return checked.filter(item => item != id)
            } else {
                return [...prev, id]
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
                    <form onSubmit={HandleCreateVoucherOption} className='row p-3 form-modal' >
                        <h3 className='mb-3'>Create Voucher Option</h3>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Voucher Name:</label>
                            <input ref={nameRef} type="text"  maxLength={255} required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Discount:</label>
                            <input ref={discountRef} type="number"  maxLength={20} required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Time Start:</label>
                            <input ref={timeStart} type="date" required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Time Expire:</label>
                            <input ref={timeExpire} type="date" required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Option:</label>
                            <div className="col-lg-9 col-md-9 col-xs-12">
                                {options && options.map((option, index) => (
                                    <div className="form-check" key={index}>
                                        <input className="form-check-input" type="checkbox" required value={option.id} checked={checked.includes(option.id)} onChange={() => { checkRequired(); handleCheck(option.id) }} />
                                        <label className="form-check-label" htmlFor="flexCheckChecked">
                                            {option.optionName}
                                        </label>
                                    </div>
                                ))}
                            </div>
                        </div>
                        <div className='modal-control mb-4'>
                            <button type='submit' className='btn btn-primary mx-4 ' style={{ width: "5rem" }} onClick={() => HandleCreateVoucherOption} >Save</button>
                            <button type='button' className='btn btn-secondary mx-4 w-10' style={{ width: "5rem" }} onClick={hide}>Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}
export default CreateVoucherOption;