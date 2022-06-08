import { useRef, useState, useEffect } from "react";
import axios from 'axios';
import { SERVER_API_URL } from '../../common/common-constant';
import 'select2/dist/js/select2.js'
import 'select2/dist/css/select2.css'


function CreateVoucherUser({ hide, setDate, accounts }) {

    const nameRef = useRef();
    const discountRef = useRef();
    const timeStartRef = useRef();
    const timeExpireRef = useRef();
    const [checked, setChecked] = useState([]);
    const accountRef = useRef();
    const $ = require('jquery');

    useEffect(() => {
        $('#selectAccount').select2({
        });

        $('#selectAccount').on('select2:select', function (e) {
            var data = e.params.data;
            console.log(data)
            setChecked(prev => [...prev, parseInt(data.id)])
        });

    }, [])

    $('#selectAccount').on('select2:unselect', function (e) {
        var data = e.params.data;
        setChecked(checked.filter(item => item != parseInt(data.id)))
    });


    console.log(checked)
    console.log(accounts)
    const HandleCreateVoucherOption = (event) => {
        event.preventDefault();
        let timeStart = new Date(timeStartRef.current.value)
        let timeEnd = new Date(timeExpireRef.current.value)
        if (timeStart >= timeEnd) {
            document.getElementById('form-message').textContent = 'Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc';
            document.getElementById('end-time').style.border = '1px solid #f33a58';
        } else {
            const data = {
                name: nameRef.current.value ? nameRef.current.value : null,
                discount: discountRef.current.value ? discountRef.current.value : null,
                dayStart: timeStartRef.current.value ? timeStartRef.current.value : null,
                dayExpire: timeExpireRef.current.value ? timeExpireRef.current.value : null,
                userId: checked ? checked : null
            }
            console.log(data)
            axios.post(SERVER_API_URL + 'api/admin/create-voucher-user', data).then(res => {
                if (res.status === 200)
                    setDate();
                hide()
            })
        }
    }
    function handleCompareTime() {
        let timeStart = new Date(timeStartRef.current.value)
        let timeEnd = new Date(timeExpireRef.current.value)
        if (timeStart >= timeEnd) {
            document.getElementById('form-message').textContent = 'Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc';
            document.getElementById('end-time').style.border = '1px solid #f33a58';
        } else {
            document.getElementById('form-message').textContent = '';
            document.getElementById('end-time').style.border = '1px solid #ced2d8';
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
                    <form onSubmit={HandleCreateVoucherOption} className='row p-3' >
                        <h3 className='mb-3'>Create Voucher User</h3>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Account:</label>
                            <div className="col-lg-9 col-md-9 col-xs-12 p-0">
                                <select id='selectAccount' className='w-100' multiple={true} required >
                                    {accounts && accounts.map(account => (
                                        <option key={account.id} value={account.id} >{account.username}</option>
                                    ))}
                                </select>
                            </div>
                        </div>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Voucher Name:</label>
                            <input ref={nameRef} type="text"  maxLength={255} required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Discount:</label>
                            <input ref={discountRef} type="number"  min={0}  max={100000000} required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Time Start:</label>
                            <input ref={timeStartRef} type="date" required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Time Expire:</label>
                            <div className="col-lg-9 col-md-9 col-xs-12 p-0 ">
                                <input id="end-time" ref={timeExpireRef} type="date" required onBlur={handleCompareTime} className='form-control'></input>
                                <div id="form-message" className="form-message" style={{ color: '#f33a58' }}>
                                </div>
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
export default CreateVoucherUser;