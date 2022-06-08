import axios from "axios";
import { useRef } from "react";
import { useParams } from "react-router-dom/cjs/react-router-dom.min";
import { toast } from "react-toastify";
import { SERVER_API_URL } from "../../common/common-constant";

function ResetPasswordFinish() {

    const param = useParams();
    const passwordRef = useRef();
    const rePasswordRef = useRef();

    function resetPasswordRequest() {
        const password = passwordRef.current.value;
        const rePassword = rePasswordRef.current.value;
        if (password !== rePassword) {
            toast.error("Password miss-match");
        } else {
            const data = {
                key: param.resetKey,
                newPassword: password
            }
            axios.post(SERVER_API_URL + 'api/ad-user/reset-password/finish', data).then(res => {
            });
        }
    };

    return (
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
                        <h3 style={{ color: 'black', textAlign: 'center' }}>Reset Password</h3>
                        <br />
                        <input ref={passwordRef} type='password' className='form-control' placeholder='Enter new password' />
                        <br />
                        <input ref={rePasswordRef} type='password' className='form-control' placeholder='Re-Enter password' />
                        <br />
                        <button type='button' className='form-control btn btn-primary' onClick={resetPasswordRequest}>Reset password</button>
                        <br />
                        {/* <div className='row text-center'>
                            <div className='col-6'>
                                <Link to='/login'>Login</Link>
                            </div>
                            <div className='col-6'>
                                <a href className='link-dark' data-bs-toggle="modal" data-bs-target="#registerModal"> Create new account</a>
                            </div>
                        </div> */}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ResetPasswordFinish;