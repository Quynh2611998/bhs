import axios from 'axios';
import { useEffect, useState, useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';
import useModal from '../../common/common-function/useModal';
import CreateOption from './CreateOption';
import DeleteOption from './DeleteOption';
import EditOption from './EditOption';



function Option() {
    const [items, setItems] = useState([]);
    const [date, setDate] = useState('');
    const [services, setServices] = useState([]);
    const $ = require('jquery');
    const { isShowing, isShowing2, isShowing3, toggle, toggle2, toggle3 } = useModal();
    const idRef = useRef();
    const optionNameRef = useRef();

    const handleGetInfo = (id, optionName) => {
        idRef.current = id;
        optionNameRef.current = optionName

    }
    const data = [idRef.current]

    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-all-list-option_admin').then(res => {
            if (res.status === 200) {
                setItems(res.data)
                $("#OptionTable").DataTable();
            }
        })
    }, [date])

    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-all-list-services').then(res => {
            if (res.status === 200) {
                setServices(res.data)
            }
        })
    }, [])
    
    return (
        <main>
            <div className="container-fluid px-4">
                <h1 className="mt-4">Option Management</h1>
                <ol className="breadcrumb mb-4">
                    <li className="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                    <li className="breadcrumb-item active">Option Management</li>
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
                        <table id="OptionTable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Option Name</th>
                                    <th>Service</th>
                                    <th>Price</th>
                                    <th>Created Date</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                {items && items.map(item => (
                                    <tr key={item.id}>
                                        <th>{item.id}</th>
                                        <th>{item.optionName}</th>
                                        <th>{item.serviceName}</th>
                                        <th>{item.price}</th>
                                        <th>{new Date(item.createdDate).toLocaleString('vi-vn').slice(10)}</th>
                                        <th>
                                            <button type='button' className='btn btn-primary btn-icon ' onClick={() => { handleGetInfo(item.id, item.optionName); toggle2() }}>
                                                <i className="fa fa-pencil-square-o icon-btn" aria-hidden="true"></i>
                                                <span className='btn-text'>Edit</span>
                                            </button>
                                            &nbsp;
                                            <button type='button' className='btn btn-danger btn-icon ' onClick={() => { handleGetInfo(item.id, item.optionName); toggle3() }}>
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

            {isShowing && <CreateOption
                hide={toggle}
                setDate={() => setDate(new Date())}
                services={services}
            />}

            {isShowing2 && <EditOption
                hide={toggle2}
                setDate={() => setDate(new Date())}
                id={idRef.current}
                services={services}
                options={items}
            />}

            {isShowing3 && <DeleteOption
                hide={toggle3}
                setDate={() => setDate(new Date())}
                data={data}
                name={optionNameRef.current}
            />}

        </main>
    )
}
export default Option;