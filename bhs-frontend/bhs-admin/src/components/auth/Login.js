import { useEffect, useRef } from 'react'
import React from 'react';
import { Link, useHistory } from 'react-router-dom'
import axios from 'axios';
import { SERVER_API_URL } from '../../common/common-constant';
import { Redirect, useParams } from "react-router-dom/cjs/react-router-dom.min";
import { clearToken, removeAuthenCookie, setAuthenCookie } from '../../common/common-function';

function Login( {setAuthent, setUnAuthent}) {
  const param = useParams();
  const routingHistory = useHistory();

  removeAuthenCookie();
  useEffect(() => {
    setUnAuthent();
  },[])

  if (param.activeKey !== null && param.activeKey !== undefined) {
    axios.get(SERVER_API_URL + 'api/ad-user/activate?key=' + param.activeKey).then(res => {

    });
  }

  const usernameRef  = useRef();
  const passwordRef  = useRef();

  function loginHandle(event) { 
    event.preventDefault();
    const data = {
      username: usernameRef.current.value ? usernameRef.current.value : null,
      password: passwordRef.current.value ? passwordRef.current.value : null,
    }
    axios.post(SERVER_API_URL + 'api/authenticate', data)
    .then(res => {
      if (res.status === 200) {
        setAuthenCookie(res.data);
        routingHistory.replace('/');
        setAuthent();
        // window.location.reload();
      }
    });
  }

  return (
    <div className='container-fluid'>
      <div className='row d-flex justify-content-center'>
        <div className='col-lg-4 col-md-4 col-xs-12'>
          <div className='container'>
            <div className='mt-4 p-5 bg-light text-white rounded'>
              <form onSubmit={loginHandle}>
                <input ref={usernameRef} type='email' className='form-control' placeholder='Email address' required/>
                <br />
                <input ref={passwordRef} type='password' className='form-control' placeholder='Password' required/>
                <br />
                <button type='submit' className='form-control btn btn-primary' onClick={loginHandle}>Login</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
