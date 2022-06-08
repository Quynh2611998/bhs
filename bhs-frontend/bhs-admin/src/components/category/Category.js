import axios from 'axios';
import { useEffect, useState, useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';
import useModal from '../../common/common-function/useModal';
import CreateCategory from './CreateCategory';
import DeleteCategory from './DeleteCategory';
import EditCategory from './EditCategory';


function Category() {
    const [items, setItems] = useState([]);
    const [date, setDate] = useState('');
    const [category, setCategory] = useState({});
    const $ = require('jquery');
    const { isShowing, isShowing2, isShowing3, toggle, toggle2, toggle3 } = useModal();
    const idRef = useRef();
    const categoryNameRef = useRef();
    const handleGetInfo =  async (id, categoryName, isEdit) => {
        idRef.current = id;
        categoryNameRef.current = categoryName
        if(isEdit){
           
          await  axios.get(SERVER_API_URL +  `api/get-category/${id}`).then(res => {
                if (res.status === 200) {
                    setCategory(res.data)
                }
            })
            toggle2()
        }
    }
    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-all-list-categories').then(res => {
            
            if (res.status === 200) {
                setItems(res.data)
                $("#CategoryTable").DataTable();
            }
        })
       
    }, [date])


    return (
        <main>
            <div className="container-fluid px-4">
                <h1 className="mt-4">Category Management</h1>
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
                        <table id="CategoryTable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Category Name</th>
                                    <th>Created Date</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                {items && items.map(item => (
                                    <tr key={item.id}>
                                        <th>{item.id}</th>
                                        <th>{item.categoryName}</th>
                                        <th>{new Date(item.createdDate).toLocaleString('vi-vn').slice(10)}</th>
                                        <th>
                                            <button type='button' className='btn btn-primary btn-icon ' onClick={() => { handleGetInfo(item.id, item.categoryName,true) }}>
                                                <i className="fa fa-pencil-square-o icon-btn" aria-hidden="true"></i>
                                                <span className='btn-text'>Edit</span>
                                            </button>
                                            &nbsp;
                                            <button type='button' className='btn btn-danger btn-icon ' onClick={() => { handleGetInfo(item.id, item.categoryName); toggle3() }}>
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
            {isShowing && <CreateCategory
                hide={toggle}
                setDate={() => setDate(new Date())}
            />}
            {isShowing2 && <EditCategory
                hide={toggle2}
                setDate={() => setDate(new Date())}
                category={category}
            />}
            {isShowing3 && <DeleteCategory
                hide={toggle3}
                setDate={() => setDate(new Date())}
                name={categoryNameRef.current}
                id = {idRef.current}
            />}

        </main>
    )
}
export default Category;