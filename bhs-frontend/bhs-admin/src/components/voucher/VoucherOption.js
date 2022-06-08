import axios from 'axios';
import { useEffect, useState, useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';
import useModal from '../../common/common-function/useModal';
import CreateVoucherOption from './CreateVoucherOption';
import EditVoucherOption from './EditVoucherOption';
import DeleteVoucherOption from './DeleteVoucherOption';

function VoucherOption() {

    const [items, setItems] = useState([]);
    const [options, setOptions] = useState([]);
    const [optionMapping, setOptionMapping] = useState([]);
    const [date, setDate] = useState('');
    const $ = require('jquery');
    const { isShowing, isShowing2, isShowing3, toggle, toggle2, toggle3 } = useModal();
    const idRef = useRef();
    const voucherNameRef = useRef();
    const optionMappingRef = useRef([]);
    const [voucher, setVoucher] = useState([]);
    const listVoucherOptionMapRef = useRef();

    const handleGetInfo = async (id, voucherName,isEdit) => {
        idRef.current = id;
        voucherNameRef.current = voucherName
        if(isEdit){
             listVoucherOptionMapRef.current = optionMapping.filter(item => item.voucherOptionId === id)
            optionMappingRef.current = listVoucherOptionMapRef.current.map(item => item.optionId)
            await axios.get(SERVER_API_URL + `api/get-one-voucher-option/${id}`).then(res => {
                if (res.status === 200) {
                    setVoucher(res.data);
                }
    
            })
            toggle2(); 
        }
       
    }

    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-all-list-voucher-option').then(res => {
            if (res.status === 200) {
                setItems(res.data)
                $("#VoucherOptionTable").DataTable();
            }
        })
    }, [date])

    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-all-list-option_admin').then(res => {
            if (res.status === 200) {
                setOptions(res.data)
            }
        })
    }, [])
    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-all-list-voucher-option-mapping').then(res => {
            if (res.status === 200) {
                setOptionMapping(res.data)
            }
        })
    }, [date])
console.log(optionMapping)
    return (
        <main>
            <div className="container-fluid px-4">
                <h1 className="mt-4">Voucher Option Management</h1>
                <ol className="breadcrumb mb-4">
                    <li className="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                    <li className="breadcrumb-item active">Voucher Option Management</li>
                </ol>
                <button type='button' className='btn btn-primary btn-icon mb-3' onClick={toggle}>
                    <i className="fa fa-plus-circle icon-btn" aria-hidden="true"></i>
                    <span className='btn-text'>Create</span>
                </button>
                <div className="card mb-4">
                    <div className="card-header">
                        <i className="fa fa-table me-2" aria-hidden="true"></i>
                        DataTable
                    </div>
                    <div className="card-body">
                        <table id="VoucherOptionTable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Voucher Name</th>
                                    <th>Discount</th>
                                    <th>Day Start</th>
                                    <th>Day Expire</th>
                                    <th>Created Date</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                {items && items.map(item => (
                                    <tr key={item.id}>
                                        <th>{item.id}</th>
                                        <th>{item.name}</th>
                                        <th>{item.discount}</th>
                                        <th>{new Date(item.dayStart).toLocaleString('vi-vn').slice(10)}</th>
                                        <th>{new Date(item.dayExpire).toLocaleString('vi-vn').slice(10)}</th>
                                        <th>{new Date(item.createdDate).toLocaleString('vi-vn').slice(10)}</th>
                                        <th>
                                            <button type='button' className='btn btn-primary btn-icon ' onClick={() => handleGetInfo(item.id, item.name,true)}>
                                                <i className="fa fa-pencil-square-o icon-btn" aria-hidden="true"></i>
                                                <span className='btn-text'>Edit</span>
                                            </button>
                                            &nbsp;
                                            <button type='button' className='btn btn-danger btn-icon ' onClick={() => { handleGetInfo(item.id, item.name,false); toggle3() }}>
                                                <i className="fa fa-trash icon-btn" aria-hidden="true"></i>
                                                <span className='btn-text'>Delete</span>
                                            </button>
                                        </th>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            {isShowing && <CreateVoucherOption
                hide={toggle}
                setDate={() => setDate(new Date())}
                options={options}

            />}

            {isShowing2 && <EditVoucherOption
                hide={toggle2}
                setDate={() => setDate(new Date())}
                options={options}
                voucher={voucher}
                optionMapping={optionMappingRef.current}
                listVoucherOptionMap = {listVoucherOptionMapRef.current}
            />}

            {isShowing3 && <DeleteVoucherOption
                hide={toggle3}
                setDate={() => setDate(new Date())}
                id = {idRef.current}
                name = {voucherNameRef.current}
            />}

        </main>
    )
}

export default VoucherOption;