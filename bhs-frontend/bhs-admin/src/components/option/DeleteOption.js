import axios from 'axios';
import React from 'react';
import ReactDOM from 'react-dom';
import { SERVER_API_URL } from '../../common/common-constant';


function DeleteOption({hide, setDate, data,name }) {
    function HandleDeleteOption(id) {
        axios.post(SERVER_API_URL + `api/admin/delete-option-by-id/${id}`, id).then(res => {
            setDate()
        })
    }
    return (
        <div className='modal-container'>
            <div className='modal-overlay'/>
            <div className='modal-wrapper w-25'>
                <div className='modal-header'>
                    <button type="button" className="btn-close" aria-label="Close" onClick={hide}></button>
                </div>
                <div className='modal-body'>
                    <div className='d-flex justify-content-center align-items-center mb-4 text-warning'>
                        <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="currentColor" className="me-4 bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img" aria-label="Warning:">
                            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                        </svg>
                        <h1 className='mb-0'>Warning!!!</h1>
                    </div>
                    <p className='fs-5 text'>
                        Do you want to delete option: {name} ?
                    </p>
                </div>
                <div className='modal-control mb-4'>
                    <button type='button' className='btn btn-danger mx-4 ' style={{width: "5rem"}}  onClick={() => {HandleDeleteOption(data);hide()}} >Delete</button>
                    <button type='button'className='btn btn-secondary mx-4 w-10' style={{width: "5rem"}} onClick={hide}>Cancel</button>
                
                </div>
            </div>
    </div>
    ) 
}
   
export default DeleteOption;

 
    

    
