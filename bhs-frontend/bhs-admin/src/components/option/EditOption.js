import axios from 'axios';
import React from 'react';
import ReactDOM from 'react-dom';
import { useEffect, useState, useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';
import { convertImageToBase64 } from '../../common/common-function';

function EditOption({ hide, setDate, id, services, options }) {

    const [lstImage, setLstImage] = useState([]);
    const [oldImages, setOldImages] = useState([]);
    const optionNameRef = useRef();
    const serviceIdRef = useRef();
    const priceRef = useRef();
    const durationRef = useRef();
    const imagesRef = useRef([]);
    const oldImageIdRef = useRef([]);
    const countImageRef = useRef();
    const option = options.find(option => {
        return id === option.id
    })
    const [serviceId, setServiceId] = useState(option.serviceId)
    const [optionName, setOptionName] = useState(option.optionName);
    const [price, setPrice] = useState(option.price);
    const [duration, setDuration] = useState(option.duration);

    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-images-by-option-id/' + id).then(res => setOldImages(res.data));


    }, [])
    async function handleEditOption(event) {
        event.preventDefault();
        const data = {
            serviceId: serviceIdRef.current.value ? serviceIdRef.current.value : null,
            optionName: optionNameRef.current.value ? optionNameRef.current.value : null,
            price: priceRef.current.value ? priceRef.current.value : null,
            duration: durationRef.current.value ? durationRef.current.value : null,
            images: lstImage
        }
        if (oldImageIdRef.length > 0) {
            await axios.post(SERVER_API_URL + 'api/admin/delete-images', oldImageIdRef.current)
        }
        axios.put(SERVER_API_URL + 'api/admin/update-option/' + id, data).then(res => {
            if (res.status === 200) {
                setDate()
                hide()
            }
        })
    }
    async function handleFileRead(event) {
        const lstFile = event.target.files;
        let lstFileAsBase64 = [];
        if (lstFile) {

            if (lstFile.length + lstImage.length + countImageRef.current > 5) {
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

    function handleDeleteImage(index, isNewImage) {
        if (isNewImage) {
            imagesRef.current.splice(index, 1);
            setLstImage(JSON.parse(JSON.stringify(imagesRef.current)));
        } else {

            document.getElementById(index).remove();
            oldImageIdRef.current.push(index.substr(5));
        }

    }
    function countImage() {
        countImageRef.current = document.getElementsByClassName('image-option').length;
    }
    return (
        <div className='modal-container'>
            <div className='modal-overlay' />
            <div className='modal-wrapper' >
                <div className='modal-header'>
                    <button type="button" className="btn-close" aria-label="Close" onClick={hide}></button>
                </div>
                <div className='modal-body text-start'>
                    <form onSubmit={handleEditOption} className='row p-3 form-modal' >
                        <h3 className='mb-3'>Edit Option</h3>
                        <div className='input-group row mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Service:</label>
                            <select ref={serviceIdRef} value={serviceId} onChange={e => setServiceId(e.target.value)} className='col-lg-9 col-md-9 col-xs-12 form-select'>
                                {services && services.map(service => (
                                    <option key={service.id} value={service.id}>{service.serviceName}</option>
                                ))}
                            </select>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Option name:</label>
                            <input type="text" ref={optionNameRef} value={optionName} onChange={e => setOptionName(e.target.value)}  maxLength={255} required className='col-lg-9 col-md-3 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Price:</label>
                            <input type="text" ref={priceRef}   min={0}  max={100000000} value={price} onChange={e => setPrice(e.target.value)}  required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Duration:</label>
                            <input ref={durationRef} type="number"   min={0}  max={1000} value={duration} onChange={e => setDuration(e.target.value)}  required className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>
                        <div className='input-group row  mb-3'>
                            <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Image input:</label>
                            <input onChange={handleFileRead} onClick={() => countImage()} type="file" accept="image/*" multiple={true} id="profile-img" className='col-lg-9 col-md-9 col-xs-12 form-control'></input>
                        </div>

                        {oldImages && oldImages.map((image, index) => (
                            <div id={'image' + image.imageId} className='row  mb-3 image-option ' key={index}>
                                <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Image:</label>
                                <div className='col-lg-2 col-md-2 col-xs-6'>
                                    <img src={image.srcImage} alt={image.altImage} id={image.imageId} style={{ width: "6rem", height: "6rem" }} />
                                </div>
                                <div className='col-lg-2 col-md-2 col-xs-6'>
                                    <i className="fa fa-times-circle-o text-danger fs-2 " style={{ cursor: "pointer" }} onClick={() => handleDeleteImage('image' + image.imageId, false)} aria-hidden="true"></i>
                                </div>
                            </div>
                        ))}
                        {lstImage && lstImage.map((image, index) => {
                            return (
                                <div id={`img` + index} className='row  mb-3' key={index}>
                                    <label className='col-lg-3 col-md-3 col-xs-12 fs-5 '>Preview:</label>
                                    <div className='col-lg-2 col-md-2 col-xs-6 p-0  '>
                                        <img src={image.imageSrc} style={{ width: "6rem", height: "6rem" }} className='me-1' />
                                    </div>
                                    <div className='col-lg-2 col-md-2 col-xs-6'>
                                        <i className="fa fa-times-circle-o text-danger fs-2  " style={{ cursor: "pointer" }} onClick={() => handleDeleteImage(index, true)} aria-hidden="true"></i>
                                    </div>
                                </div>
                            )
                        }
                        )}
                        <div className='modal-control mb-4'>
                            <button type='submit' className='btn btn-primary mx-4' style={{ width: "5rem" }} onClick={(e) => handleEditOption} >Update</button>
                            <button type='button' className='btn btn-secondary mx-4 w-10' style={{ width: "5rem" }} onClick={hide}>Cancel</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    )
}
export default EditOption;