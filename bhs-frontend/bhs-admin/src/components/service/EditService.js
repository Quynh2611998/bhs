import axios from 'axios';
import React from 'react';
import ReactDOM from 'react-dom';
import { useEffect, useState, useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';


function EditService({ hide, id, setDate, categories, items }) {
    // const [item, setItem] = useState([]);
    const serviceNameRef = useRef();
    const categoryRef = useRef();

    const item = items.find(item => {
        return id === item.id
    }
    )
    const [service, setService] = useState(item.serviceName);
    const [categoryId, setCategoryId] = useState(item.categoryId);

    // useEffect(() => {
    //     axios.get(SERVER_API_URL + 'api/get-all-list-categories').then(res => {
    //         setItems(res.data);
    //     })
    // },[])

    // useEffect(() => {
    //     axios.get(SERVER_API_URL + 'api/get-service/'+id).then(res => {
    //         setService(res.data.serviceName);
    //         setCategoryId(res.data.categoryId)
    //     }
    //      )
    // },[])
    function handleEditService(event) {
        event.preventDefault();
        const data = {
            categoryId: categoryRef.current.value ? categoryRef.current.value : null,
            serviceName: serviceNameRef.current.value ? serviceNameRef.current.value : null,
        }
        axios.put(SERVER_API_URL + 'api/admin/update-service/' + id, data).then(res => {
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
                    <form onSubmit={handleEditService} className='row p-3' >
                        <h3 className='mb-3'>Edit Service</h3>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Category:</label>
                            <select ref={categoryRef} value={categoryId} onChange={e => setCategoryId(e.target.value)} className='col-lg-9 col-md-9 col-xs-12 form-select'>
                                {categories && categories.map(category => (
                                    <option key={category.id} value={category.id} >{category.categoryName}</option>
                                ))}
                            </select>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Service Name:</label>
                            <input type="text" ref={serviceNameRef}  maxLength={255} value={service} required onChange={e => setService(e.target.value)} className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='modal-control mb-4'>
                            <button type='submit' className='btn btn-primary mx-4 ' style={{ width: "5rem" }} onClick={() => handleEditService} >Update</button>
                            <button type='button' className='btn btn-secondary mx-4 ' style={{ width: "5rem" }} onClick={hide}>Cancel</button>
                        </div>
                    </form>

                </div>

            </div>
        </div>
    )
}
export default EditService;