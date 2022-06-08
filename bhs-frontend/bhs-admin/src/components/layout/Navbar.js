import { getAuthToken, removeAuthenCookie } from "../../common/common-function";
import { Link, useHistory } from "react-router-dom";
import { useEffect, useRef, useState } from "react";


function Navbar({setUnAuthent}) {
    const routingHistory = useHistory();
  
    function logout() {
      removeAuthenCookie();
      routingHistory.replace("/login");
       setUnAuthent();
    }

    return (
        // isAuthent && (
        <nav className="sb-topnav navbar navbar-expand navbar-dark bg-dark">
         {/* Navbar Brand */}
        <Link className="navbar-brand ps-3" to="/">Booking Hair Salon</Link>
         {/* Sidebar Toggle */}
         <button className="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i className="fa fa-bars" aria-hidden="true"></i></button>
         {/* Navbar Search */}
        <form className="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
            <div className="input-group">
                <input className="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                <button className="btn btn-primary" id="btnNavbarSearch" type="button"><i className="fa fa-search" aria-hidden="true"></i></button>
            </div>
        </form>
         {/* Navbar */}
        <ul className="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
            <li className="nav-item dropdown">
                <a className="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i className="fa fa-user" aria-hidden="true"></i></a>
                <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                    <li><a className="dropdown-item" href="#!">Settings</a></li>
                    <li><a className="dropdown-item" href="#!">Activity Log</a></li>
                    <li><hr className="dropdown-divider" /></li>
                    <li><a className="dropdown-item" onClick={logout} href="#!">Logout</a></li>
                </ul>
            </li>
        </ul>
    </nav>
    // )
    ) 
}

export default Navbar;