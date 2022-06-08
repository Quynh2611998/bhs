import axios from 'axios';
import React from 'react';
import ReactDOM from 'react-dom';
import { useState, useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';
import { convertImageToBase64 } from '../../common/common-function';


function CreateOption({ hide, setDate, services }) {
    const serviceRef = useRef();
    const optionRef = useRef();
    const priceRef = useRef();
    const durationRef = useRef();
    const imagesRef = useRef([])
    const [lstImage, setLstImage] = useState([]);


    function HandleCreateOption(event) {
        event.preventDefault();
        const data = {
            optionName: optionRef.current.value ? optionRef.current.value : null,
            price: priceRef.current.value ? priceRef.current.value : null,
            serviceId: serviceRef.current.value ? serviceRef.current.value : null,
            duration: durationRef.current.value ? durationRef.current.value : null,
            images: lstImage
        }
        axios.post(SERVER_API_URL + 'api/admin/create-option', data).then(res => {
            if (res.status) {
                setDate();
                hide()
            }
        })

    }

    async function handleFileRead(event) {
        const lstFile = event.target.files;
        let lstFileAsBase64 = [];
        if (lstFile) {
            if (lstFile.length + lstImage.length > 5) {
                alert("You can select only 5 images");
                event.preventDefault();
            } else {
                for (let i = 0; i < lstFile.length; i++) {
                    lstFileAsBase64.push({ imageSrc: await convertImageToBase64(lstFile[i]) });
                }
                if (imagesRef.current.length != 0) {
                    for (let i = 0; i < lstFileAsBase64.length; i++) {
                        imagesRef.current.push(lstFileAsBase64[i]);
                    }
                } else {
                    imagesRef.current = lstFileAsBase64;
                }
                setLstImage(JSON.parse(JSON.stringify(imagesRef.current)));
            }
        }
    }

    function handleDeleteImage(index) {
        imagesRef.current.splice(index, 1);
        setLstImage(JSON.parse(JSON.stringify(imagesRef.current)));
    }

    return (
        <div className='modal-container'>
            <div className='modal-overlay' />
            <div className='modal-wrapper'>
                <div className='modal-header'>
                    <button type="button" className="btn-close" aria-label="Close" onClick={hide}></button>
                </div>
                <div className='modal-body text-start'>
                    <form onSubmit={HandleCreateOption} className='row p-3 form-modal' >
                        <h3 className='mb-3'>Create Option</h3>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Service:</label>
                            <select ref={serviceRef} className='col-lg-9 col-md-9 col-xs-12 form-select'>
                                {services && services.map(service => (
                                    <option key={service.id} value={service.id} >{service.serviceName}</option>
                                ))}
                            </select>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Option name:</label>
                            <input ref={optionRef}  maxLength={255} type="text" required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Price:</label>
                            <input ref={priceRef}  min={0}  max={100000000} type="number" required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Duration:</label>
                            <input ref={durationRef}  min={0}  max={1000} type="number" required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Image:</label>
                            <input onChange={handleFileRead} type="file" accept="image/*" multiple={true} id="profile-img" className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>

                        {lstImage && lstImage.map((image, index) => {
                            return (
                                <div id={`img` + index} className='row  mb-3' key={index}>
                                    <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Preview:</label>
                                    <div className='col-lg-2 col-md-2 col-xs-6 p-0  '>
                                        <img src={image.imageSrc} style={{ width: "6rem", height: "6rem" }} className='me-1' />
                                    </div>
                                    <div className='col-lg-2 col-md-2 col-xs-6'>
                                        <i className="fa fa-times-circle-o text-danger fs-2 " style={{ cursor: "pointer" }} onClick={() => handleDeleteImage(index)} aria-hidden="true"></i>
                                    </div>
                                </div>
                            )
                        }
                        )}
                        <div className='modal-control mb-4'>
                            <button type='submit' className='btn btn-primary mx-4 ' style={{ width: "5rem" }} onClick={() => HandleCreateOption} >Save</button>
                            <button type='button' className='btn btn-secondary mx-4 w-10' style={{ width: "5rem" }} onClick={hide}>Cancel</button>

                        </div>
                    </form>
                </div>

            </div>
        </div>
    )
}

export default CreateOption;




