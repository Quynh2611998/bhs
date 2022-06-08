import React from 'react';
import { useRef } from 'react';
import RegisterModal from './RegisterModal';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { SERVER_API_URL } from '../../common/common-constant';

function ResetPassword() {

    const emailRef = useRef();    
    
    function sendResetPasswordRequest() {
        const data = { email: emailRef.current.value };
        axios.post(SERVER_API_URL + 'api/ad-user/reset-password/init', data).then(res => {});
    }

    return (
        <div className='container-fluid'>
            <div className='row'>
                <div className='col-lg-8 col-md-8 col-xs-12'>
                <div style={{ marginTop: "15%" }} className="text-center">
            <h2 className="fw-bold" style={{ color: "#0d6efd" }}>
              Welcome to my salon
            </h2>
            <h5>Forgot your password?</h5>
            <h6>Please enter your email!</h6>
          </div>
                </div>
                <div className='col-lg-4 col-md-4 col-xs-12'>
                    <div className='container'>
                        <div className='mt-4 p-5 bg-light text-white rounded'>
                            <h3 style={{ color: 'black', textAlign: 'center' }}>Forgot Password</h3>
                            <br />
                            <input ref={emailRef} type='email' className='form-control' placeholder='Enter Email' />
                            <br />
                            <button type='button' className='form-control btn btn-success'
                                onClick={sendResetPasswordRequest}> Send Reset Password Email
                            </button>
                            <br />
                            <br />
                            <div className='row text-center'>
                                <div className='col-6'>
                                    <Link to='/login'>Login</Link>
                                </div>
                                <div className='col-6'>
                                    <a href='##' className='link-dark'data-bs-toggle="modal" data-bs-target="#registerModal"> Create new account</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <RegisterModal />
        </div>
    );
}

export default ResetPassword;