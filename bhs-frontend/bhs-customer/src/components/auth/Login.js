import { useRef, useEffect } from "react";
import React from "react";
import RegisterModal from "./RegisterModal";
import { Link, useHistory } from "react-router-dom";
import axios from "axios";
import { SERVER_API_URL } from "../../common/common-constant";
import { Redirect, useParams } from "react-router-dom/cjs/react-router-dom.min";
import {
  removeAuthenCookie,
  setAuthenCookie,
} from "../../common/common-function";
import { toast } from "react-toastify";

function Login({ setIsLoginPage, setIsNotLoginPage }) {
  const param = useParams();
  const routingHistory = useHistory();

  useEffect(() => {
    try {
      setIsLoginPage();
    } catch (error) {
      console.log(error);
    }
  }, []);

  if (param.activeKey !== null && param.activeKey !== undefined) {
    axios
      .get(SERVER_API_URL + "api/ad-user/activate?key=" + param.activeKey)
      .then((res) => {
        if (res.status === 200) {
          toast.success('Activation success! You can login to your account now');
        } else {
          toast.error('Activation Link Expired! Please register a new account');
        }
      });
  }
  removeAuthenCookie();
  const usernameRef = useRef();
  const passwordRef = useRef();

  function loginHandle(event) {
    event.preventDefault();
    const data = {
      username: usernameRef.current.value ? usernameRef.current.value : null,
      password: passwordRef.current.value ? passwordRef.current.value : null,
    };
    axios.post(SERVER_API_URL + "api/authenticate", data).then((res) => {
      if (res.status === 200) {
        setAuthenCookie(res.data);
        routingHistory.replace("/");
        setIsNotLoginPage();
        console.log(res);
      }
    });
  }

  return (
    <div className="container-fluid">
      <div className="row">
        <div className="col-lg-8 col-md-8 col-xs-12">
          <div style={{ marginTop: "15%" }} className="text-center">
            <h2 className="fw-bold" style={{ color: "#0d6efd" }}>
              Welcome to my salon
            </h2>
            <h5>My website helps you book something very easy!</h5>
            <h6>Your life will become easy!</h6>
          </div>
        </div>
        <div className="col-lg-4 col-md-4 col-xs-12">
          <div className="container">
            <div className="mt-4 p-5 bg-light text-white rounded">
              <form onSubmit={loginHandle}>
                <input
                  ref={usernameRef}
                  type="email"
                  className="form-control"
                  placeholder="Email address"
                  required
                />
                <br />
                <input
                  ref={passwordRef}
                  type="password"
                  className="form-control"
                  placeholder="Password"
                  required
                />
                <br />
                <button
                  type="submit"
                  className="form-control btn btn-primary"
                  onClick={loginHandle}
                >
                  Login
                </button>
              </form>
              <br />
              <br />
              <div className="row text-center">
                <Link to="/reset-password/init">Forgotten password</Link>
              </div>
              <br />
              <div className="dropdown-divider"></div>
              <br />
              <button
                type="button"
                className="form-control btn btn-success"
                data-bs-toggle="modal"
                data-bs-target="#registerModal"
              >
                {" "}
                Create new account
              </button>
              <br />
            </div>
          </div>
        </div>
      </div>
      <RegisterModal />
    </div>
  );
}

export default Login;
