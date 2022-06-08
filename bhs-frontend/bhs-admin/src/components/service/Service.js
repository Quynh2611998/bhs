import axios from 'axios';
import { useEffect, useState, useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';
import useModal from '../../common/common-function/useModal';
import CreateService from './CreateService'
import DeleteService from './DeleteService';
import EditService from './EditService';


function Service() {
    const [items, setItems] = useState([]);
    const [categories, setCategories] = useState([]);
    // const [service, setService] = useState([]);
    const [date, setDate] = useState('');
    const $ = require('jquery');
    const { isShowing, isShowing2, isShowing3, toggle, toggle2, toggle3 } = useModal();
    const idRef = useRef();
    const userNameRef = useRef();
    const handleGetInfo = (id, userName) => {
        idRef.current = id;
        userNameRef.current = userName
    }
    const data = [idRef.current];

    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-all-list-services').then(res => {
            if (res.status === 200) {
                setItems(res.data)
                $("#ServiceTable").DataTable();
            }
        })
       
    }, [date])

    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-all-list-categories').then((res) => setCategories(res.data))
        
    }, [])

    return (
        <main>
            <div className="container-fluid px-4">
                <h1 className="mt-4">Service Management</h1>
                <ol className="breadcrumb mb-4">
                    <li className="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                    <li className="breadcrumb-item active">Service Management</li>
                </ol>
                <button type='button' className='btn btn-primary btn-icon mb-3' onClick={toggle}>
                    <i className="fa fa-plus-circle icon-btn" aria-hidden="true"></i>
                    <span className='btn-text'>Create</span>
                </button>
                <div className="card mb-4">
                    <div className="card-header">
                        <i className="fa fa-table  me-2" aria-hidden="true"></i>
                        DataTable
                    </div>
                    <div className="card-body">
                        <table id="ServiceTable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Service Name</th>
                                    <th>Category</th>
                                    <th>Created Date</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                {items && items.map(item => (
                                    <tr key={item.id}>
                                        <th>{item.id}</th>
                                        <th>{item.serviceName}</th>
                                        <th>{item.categoryName}</th>
                                        <th>{new Date(item.createdDate).toLocaleString('vi-vn').slice(10)}</th>
                                        <th>
                                            <button type='button' className='btn btn-primary btn-icon ' onClick={() => { handleGetInfo(item.id, item.serviceName); toggle2() }}>
                                                <i className="fa fa-pencil-square-o icon-btn" aria-hidden="true"></i>
                                                <span className='btn-text'>Edit</span>
                                            </button>
                                            &nbsp;
                                            <button type='button' className='btn btn-danger btn-icon ' onClick={() => { handleGetInfo(item.id, item.serviceName); toggle3() }}>
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
            {isShowing && <CreateService
                hide={toggle}
                setDate={() => setDate(new Date())}
                categories={categories}
            />}
            {isShowing2 && <EditService
                hide={toggle2}
                id={idRef.current}
                items={items}
                setDate={() => setDate(new Date())}
                categories={categories}
            />}
            {isShowing3 && <DeleteService
                hide={toggle3}
                data={data}
                name={userNameRef.current}
                setDate={() => setDate(new Date())}

            />}

        </main>
    )
}
export default Service;