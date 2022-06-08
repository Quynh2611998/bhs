import axios from 'axios';
import React from 'react';
import { useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';


function CreateCategory({ hide, setDate }) {

    const categoryRef = useRef();

    function HandleCreateCategory(event) {
        event.preventDefault();
        const data = {
            categoryName: categoryRef.current.value ? categoryRef.current.value : null,
        }
        axios.post(SERVER_API_URL + 'api/admin/create-category', data).then(res => {
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
                    <form onSubmit={HandleCreateCategory} className='row p-3' >
                        <h3 className='mb-3'>Create Category</h3>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Category Name:</label>
                            <input ref={categoryRef} type="text"  maxLength={255} required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='modal-control mb-4'>
                            <button type='submit' className='btn btn-primary mx-4 ' style={{ width: "5rem" }} onClick={() => HandleCreateCategory} >Save</button>
                            <button type='button' className='btn btn-secondary mx-4 w-10' style={{ width: "5rem" }} onClick={hide}>Cancel</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    )
}
export default CreateCategory;





