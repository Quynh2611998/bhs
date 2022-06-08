import axios from 'axios';
import { useEffect, useState, useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';
import useModal from '../../common/common-function/useModal';
import CreateStaff from './CreateStaff';
import DeleteStaff from './DeleteStaff';
import EditStaff from './EditStaff';



function Staff() {
    const [items, setItems] = useState([]);
    const [date, setDate] = useState('');
    const $ = require('jquery');
    const { isShowing, isShowing2, isShowing3, toggle, toggle2, toggle3 } = useModal();
    const idRef = useRef();
    const staffNameRef = useRef();
    const handleGetInfo = (id, staffName) => {
        idRef.current = id;
        staffNameRef.current = staffName
    }
    const data = [idRef.current];
    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-all-list-hair-stylists').then(res => {
            if (res.status === 200) {
                setItems(res.data)
                $("#StaffTable").DataTable();
            }
        })
     
    }, [date])

    return (
        <main>
            <div className="container-fluid px-4">
                <h1 className="mt-4">Staff Management</h1>
                <ol className="breadcrumb mb-4">
                    <li className="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                    <li className="breadcrumb-item active">Staff Management</li>
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
                        <table id="StaffTable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Stylist Name</th>
                                    <th>Phone</th>
                                    <th>Gender</th>
                                    <th>Created Date</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                {items && items.map(item => (
                                    <tr key={item.id}>
                                        <th>{item.id}</th>
                                        <th>{item.stylistName}</th>
                                        <th>{item.phoneNumber}</th>
                                        <th>{item.gender}</th>
                                        <th>{new Date(item.createdDate).toLocaleString('vi-vn').slice(10)}</th>
                                        <th>
                                            <button type='button' className='btn btn-primary btn-icon ' onClick={() => { handleGetInfo(item.id, item.stylistName); toggle2() }}>
                                                <i className="fa fa-pencil-square-o icon-btn" aria-hidden="true"></i>
                                                <span className='btn-text'>Edit</span>
                                            </button>
                                            &nbsp;
                                            <button type='button' className='btn btn-danger btn-icon ' onClick={() => { handleGetInfo(item.id, item.stylistName); toggle3() }}>
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
            {isShowing && <CreateStaff
                hide={toggle}
                setDate={() => setDate(new Date())}
            />}
            {isShowing2 && <EditStaff
                hide={toggle2}
                id={idRef.current}
                stylists={items}
                setDate={() => setDate(new Date())}
            />}
            {isShowing3 && <DeleteStaff
                hide={toggle3}
                data={data}
                name={staffNameRef.current}
                setDate={() => setDate(new Date())}
            />}

        </main>
    )
}
export default Staff;