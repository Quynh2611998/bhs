import { useRef } from 'react'
import { toast } from 'react-toastify';
import axios from 'axios';
import { SERVER_API_URL } from '../../common/common-constant';


function RegisterModal() {

    const firstNameRef  = useRef();
    const lastNameRef   = useRef();
    const emailRef      = useRef();
    const passwordRef   = useRef();
    const rePasswordRef = useRef();
    let selectedGender  = '';

    function handleRadioChange(event) {
        selectedGender = event.target.value;
    }

    function registerRequestHandle(event) {
        event.preventDefault();
        const registerData = {
            firstName:  firstNameRef.current.value,
            lastName:   lastNameRef.current.value,
            email:      emailRef.current.value,
            password:   passwordRef.current.value,
            rePassword: rePasswordRef.current.value,
            gender:     selectedGender,
        };
        if (registerData.password === registerData.rePassword) {
            axios.post(SERVER_API_URL + 'api/register', registerData).then(res => {

            });
        } else {
            toast.error('Password Miss match');
        }
        
    }
       
    return (
        <div className="modal fade" tabIndex="-1" id="registerModal">
            <div className="modal-dialog">
                <div className="modal-content">
                    <div className="modal-header">
                        <h1 className="modal-title">Sign up</h1>
                        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form onSubmit={registerRequestHandle}>
                        <div className="modal-body">
                            <div className="row">
                                <div className="col-6">
                                    <input  ref={firstNameRef} type="text" 
                                            className="form-control" id="firstName" 
                                           maxLength={255} placeholder="First Name" required/>
                                </div>
                                <div className="col-6">
                                    <input  ref={lastNameRef} type="text" 
                                            className="form-control" id="lastName" 
                                            maxLength={255}
                                            placeholder="Last Name" required/>
                                </div>
                            </div>
                            <br />
                            <div className="row">
                                <div className="col-12">
                                    <input  ref={emailRef} type="text" 
                                            className="form-control" id="email" 
                                            maxLength={255}
                                            placeholder="Email Address" required/>
                                            
                                </div>
                            </div>
                            <br />
                            <div className="row">
                                <div className="col-12">
                                    <input  ref={passwordRef} type="password" 
                                            className="form-control" id="password" 
                                            maxLength={255}
                                            placeholder="Password" required/>
                                </div>
                            </div>
                            <br />
                            <div className="row">
                                <div className="col-12">
                                    <input  ref={rePasswordRef} type="password" 
                                            className="form-control" id="rePassword" 
                                            maxLength={255}
                                            placeholder="Re-Enter Password" required/>
                                </div>
                            </div>
                            <br />
                            <div className="row">
                                <p>Gender:</p>
                            </div>
                            <div className="row">
                                <div className="col-4">
                                    <input name="gender" type='radio' value="M" onChange={handleRadioChange} required/> Male
                                </div>
                                <div className="col-4">
                                    <input name="gender" type='radio' value="F" onChange={handleRadioChange} required/> Female
                                </div>
                                <div className="col-4">
                                    <input name="gender" type='radio' value="O" onChange={handleRadioChange} required/> Other
                                </div>
                            </div>
                        </div>
                        <div className="row text-center">
                            <div className="col-12">
                                <button className="btn btn-success text-center" style={{ marginBottom: '15px', width: '50%' }}
                                >Sign Up</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default RegisterModal