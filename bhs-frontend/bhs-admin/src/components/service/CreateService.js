import axios from 'axios';
import React from 'react';
import { useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';


function CreateService({ hide, setDate, categories }) {

    const categoryRef = useRef();
    const serviceRef = useRef();

    function HandleCreateService(event) {
        event.preventDefault();
        const data = {
            categoryId: categoryRef.current.value ? categoryRef.current.value : null,
            serviceName: serviceRef.current.value ? serviceRef.current.value : null,
        }
        axios.post(SERVER_API_URL + 'api/admin/create-service', data).then(res => {
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
                    <form onSubmit={HandleCreateService} className='row p-3' >
                        <h3 className='mb-3'>Create Service</h3>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Category:</label>
                            <select ref={categoryRef} className='col-lg-9 col-md-9 col-xs-12 form-select'>
                                {categories && categories.map(category => (
                                    <option key={category.id} value={category.id}>{category.categoryName}</option>
                                ))}
                            </select>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Service Name:</label>
                            <input ref={serviceRef} type="text"  maxLength={255} required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='modal-control mb-4'>
                            <button type='submit' className='btn btn-primary mx-4 ' style={{ width: "5rem" }} onClick={() => HandleCreateService} >Save</button>
                            <button type='button' className='btn btn-secondary mx-4 w-10' style={{ width: "5rem" }} onClick={hide}>Cancel</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    )
}
export default CreateService;





