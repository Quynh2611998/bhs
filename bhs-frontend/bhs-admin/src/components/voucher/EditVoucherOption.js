import { useEffect, useRef, useState } from "react";
import axios from 'axios';
import { SERVER_API_URL } from '../../common/common-constant';



function EditVoucherOption({ hide, setDate, options, voucher, optionMapping, listVoucherOptionMap }) {

    const nameRef = useRef();
    const discountRef = useRef();
    const timeStartRef = useRef();
    const timeExpireRef = useRef();
    const [checked, setChecked] = useState(optionMapping);
    const [name, setName] = useState(voucher.name);
    const [discount, setDiscount] = useState(voucher.discount);
    const [timeStart, setTimeStart] = useState(voucher.dayStart);
    const [timeExpire, setTimeExpire] = useState(voucher.dayExpire);
    const voucherMappingId = listVoucherOptionMap.map(item => item.id);
    const $ = require('jquery');

    const handleEditVoucherOption = async (event) => {
        event.preventDefault();
        const data = {
            name: nameRef.current.value ? nameRef.current.value : null,
            discount: discountRef.current.value ? discountRef.current.value : null,
            dayStart: timeStartRef.current.value ? timeStartRef.current.value : null,
            dayExpire: timeExpireRef.current.value ? timeExpireRef.current.value : null,
            optionId: checked ? checked : null
        }
        await axios.post(SERVER_API_URL + 'api/admin/delete-voucher-option-mapping', voucherMappingId);

        axios.put(SERVER_API_URL + `api/admin/update-voucher-option/${voucher.id}`, data).then(res => {
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
                    <form onSubmit={handleEditVoucherOption} className='row p-3 form-modal' >
                        <h3 className='mb-3'>Edit Voucher Option</h3>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Voucher Name:</label>
                            <input ref={nameRef} type="text" required value={name} onChange={e => setName(e.target.value)}  maxLength={255} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Discount:</label>
                            <input ref={discountRef} type="number" required value={discount} onChange={e => setDiscount(e.target.value)}  maxLength={20} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Time Start:</label>
                            <input ref={timeStartRef} type="date" required value={timeStart} onChange={e => setTimeStart(e.target.value)}  className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Time Expire:</label>
                            <input ref={timeExpireRef} type="date" required value={timeExpire} onChange={e => setTimeExpire(e.target.value)} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Option:</label>
                            <div className="col-lg-9 col-md-9 col-xs-12">
                                {options && options.map((option, index) => (
                                    <div className="form-check" key={index}>
                                        <input className="form-check-input" required type="checkbox" value={option.id} checked={checked.includes(option.id)} onChange={() => {checkRequired(); handleCheck(option.id)}} />
                                        <label className="form-check-label" htmlFor="flexCheckChecked">
                                            {option.optionName}
                                        </label>
                                    </div>
                                ))}
                            </div>
                        </div>
                        <div className='modal-control mb-4'>
                    <button type='submit' className='btn btn-primary mx-4 ' style={{ width: "5rem" }} onClick={() => handleEditVoucherOption} >Save</button>
                    <button type='button' className='btn btn-secondary mx-4 w-10' style={{ width: "5rem" }} onClick={hide}>Cancel</button>

                </div>
                    </form>
                </div>
               
            </div>
        </div>
    )
}
export default EditVoucherOption;