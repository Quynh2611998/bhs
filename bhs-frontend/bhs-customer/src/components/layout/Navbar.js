import { getAuthToken, removeAuthenCookie } from "../../common/common-function";
import { useHistory, useLocation } from "react-router-dom";
import { Link } from "react-router-dom/cjs/react-router-dom.min";
import axios from "axios";
import { SERVER_API_URL } from "../../common/common-constant";
import { useEffect, useRef, useState, useContext } from "react";
import { BookingContext } from "../context/BookingContext";
import { BookingExistContext } from "../context/BookingExistContext";
import { Menu, MenuItem, SubMenu } from "@szhsin/react-menu";

/* eslint-disable jsx-a11y/anchor-is-valid */
function Navbar() {
  const history = useHistory();
  const { search } = useLocation();
  const searchParams = new URLSearchParams(search);
  const keyword = searchParams.get("keyword");
  const routingHistory = useHistory();
  const isAuthenticate = getAuthToken() ? true : false;
  const [isAuthent, setIsAuthent] = useState(isAuthenticate)
  const [userId, setUserId] = useState();
  const userIdRef = useRef();
  const [booking, setBooking] = useState();
  const [date, setDate] = useState("");
  const context = useContext(BookingContext);
  const bookingExistContext = useContext(BookingExistContext);
  const [category, setCategory] = useState([]);
  const keyWordRef = useRef(keyword);
  const [keyWord, setKeyWord] = useState(keyword || "");
  const [detailOption, setDetailOption] = useState([]);

  // useEffect(() => {
  //   setIsAuthent(isAuthenticate)
  // }, [isAuthenticate])
 
  

  function logout() {
    removeAuthenCookie();
    routingHistory.replace("/");

  }
  useEffect(() => {
    setDate(context.date);
  }, [context.date]);

  // useEffect(() => {
  //   axios
  //     .get(SERVER_API_URL + "api/user-profile/get-user-profile")
  //     .then((result) => {
  //       userIdRef.current = result.data.userId;
  //       setUserId(result.data.userId);
  //     })

  // }, [])

  useEffect(() => {
    axios
      .get(SERVER_API_URL + "api/user-profile/get-user-profile")
      .then((result) => {
        userIdRef.current = result.data.userId;
        setUserId(result.data.userId);
        axios
          .get(
            SERVER_API_URL + `api/get-booking-by-user-id/${result.data.userId}`
          )
          .then((result) => {
            if (result.status === 200) {
              setBooking(result.data[0]);
              bookingExistContext.setBooking(result.data[0]);
            }
          })
          .catch((err) => { });
      })
      .catch((err) => { });
    return () => {
      setBooking([]);
  // if(userId){
  //   axios.get(
  //     SERVER_API_URL + `api/get-booking-by-user-id/${userId}`
  //   )
  //     .then((result) => {
  //       if (result.status === 200) {
  //         setBooking(result.data[0]);
  //         bookingExistContext.setBooking(result.data[0]);
  //       }
  //     })
   
  //   };
  }
  }, [date]);


  useEffect(() => {
    axios
      .get(SERVER_API_URL + "api/get-object-home-page")
      .then((result) => {
        setCategory(result.data);
      })
      .catch((err) => { });
  }, []);

  function handleSearch(e) {
    console.log(e.target.value);

    setKeyWord(e.target.value);
  }
  function handleClick(e) {
    console.log(keyWord);
    document.getElementById('options').scrollIntoView();
    history.push({
      pathname: "search",
      search: `?keyword=${keyWord}`,
    });
  }

  function handeDeleteBooking() {
    axios
      .put(SERVER_API_URL + `api/user-update-booking/${booking.id}`)
      .then((result) => {
        if (result.status === 200) {
          setDate(new Date());
        }
      })
      .catch((err) => { });
  }
  return (
    <header>
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <div className="container-fluid flex-nowrap">
          <Link
            className="navbar-brand"
            to="/"
            style={{ fontSize: "30px", fontWeight: "1000", color: "#6610f2" }}
          >
            Hair Salon
          </Link>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item dropdown">
                {/* <li className="nav-item"> */}
                  <Menu
                    menuButton={
                      <p
                        className="nav-link active"
                        style={{ cursor: "pointer", margin: "0" }}
                      >
                        Category
                      </p>
                    }
                  >
                    {category && category.map((item, key) => (
                      <SubMenu key={key} label={item.categoryName}>
                        {item.services.map((service, key) => (
                          <SubMenu key={key} label={service.serviceName}>
                            {service.options.map((option, optionKey) => (
                              <MenuItem
                                value={option.optionId}
                                key={optionKey}
                                onClick={(e) => {
                                  console.log(e.value);
                                  // Stop the `onItemClick` of root menu component from firing
                                  e.stopPropagation = true;
                                  // Keep the menu open after this menu item is clicked
                                  // e.keepOpen = true;
                                }}
                              >
                                <Link
                                  className="nav-link active"
                                  to={`/detail-option/${option.optionId}`}
                                >
                                  {option.optionName}
                                </Link>
                              </MenuItem>
                            ))}
                          </SubMenu>
                        ))}
                      </SubMenu>
                    ))}
                  </Menu>
                {/* </li> */}
              </li>
              {/* <li className="nav-item">
                <Link className="nav-link active" to="/list-voucher">
                  Gift
                </Link>
              </li> */}
              <li className="nav-item  dropdown">
                <Link className="nav-link active" to="/contact">
                  Contact
                </Link>
              </li>
            </ul>
          </div>
          <div className="d-flex">
            <input
              className="form-control me-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
              onChange={handleSearch}
              value={keyWord}
              maxLength="50"
            />
            <button className="btn btn-outline-success" style={{ color: "#fff", backgroundColor: "#6610f2" }} onClick={handleClick}>
              Search
            </button>
          </div>

          <div className="d-flex align-items-center mx-2">

            {
              isAuthenticate ?
                <>
                  <div className="menu-appointment mx-3">
                    <i className="fa fa-calendar-plus-o fs-3" aria-hidden="true"></i>
                    {booking && (
                      <span className="menu-appointment-new-schedule fw-bold ">
                        N
                      </span>
                    )}
                    <div className="menu-appointment-wrapper rounded">
                      {booking ? (
                        <div className="appoinment-item p-4">
                          <div className="p-3 d-flex flex-column border rounded ">
                            <h5 className="appointment-title">
                              {" "}
                              {booking.lastName}'s appointment schedule:
                            </h5>
                            <span className="appointment-date">
                              <i
                                className="fa fa-calendar-check-o me-2"
                                aria-hidden="true"
                              ></i>
                              {new Date(booking.dateTime * 1).toString().slice(0, 15)}
                              , {booking.startTime}
                            </span>
                            <span className="appointment-location">
                              <i
                                className="fa fa-map-marker  me-2"
                                aria-hidden="true"
                              ></i>
                              km29, Đại lộ Thăng Long , Hòa Lạc
                            </span>
                            <span className="appointment-stylist">
                              <i className="fa fa-user  me-2" aria-hidden="true"></i>
                              Your stylist : {booking.stylistName}
                            </span>
                          </div>
                          <div className="appointment-action d-flex mt-3">
                            <button
                              id="btnCancelBooking"
                              type="button"
                              style={{ color: "#fff", backgroundColor: "#6610f2" }}
                              data-bs-toggle="modal"
                              data-bs-target="#confirmDeleteBookingModal"
                              className="btn btn-appointment-cancel ms-3"
                            >
                              Cancel
                            </button>
                          </div>
                        </div>
                      ) : (
                        <div className="no-content-appointment d-flex flex-column text-center p-3 ">
                          <i
                            className="fa fa-frown-o  mb-4"
                            style={{ fontSize: "150px", color: "#bdbdbd" }}
                            aria-hidden="true"
                          ></i>
                          <h5>No Appointment</h5>
                          <p>
                            You don't have any appointment, they'll be here when you
                            have one.
                          </p>
                        </div>
                      )}
                    </div>
                  </div>

                  <div className=" menu-user mx-3 me-5">
                    <i className="fa fa-user fs-3" aria-hidden="true"></i>
                    <div className="menu-user-wrapper">
                      <ul className="dropdown-menu-user mb-2 ">
                        <li className="py-1">
                          <Link
                            className="dropdown-item"
                            to={`/detail-booking/${userIdRef.current}`}
                          >
                            Booking Manager
                          </Link>
                        </li>
                        <li>
                          <Link className="dropdown-item" to="/list-voucher">
                            Voucher Manager
                          </Link>
                        </li>
                        <li>
                          <Link className="dropdown-item" to="/user-profile">
                            My Profile
                          </Link>
                        </li>
                        <li>
                          <hr className="dropdown-divider" />
                        </li>
                        <li>
                          <div className="dropdown-item" style={{ cursor: 'pointer' }} onClick={logout}>
                            Logout
                          </div>
                        </li>
                      </ul>
                    </div>
                  </div>
                </>
                :
                (<div className="menu-login mx-3">
                  <Link to={'/login'} className='fs-5' style={{ textDecoration: 'none', color: '#6610f2' }}>
                    <span>Login</span>
                  </Link>
                </div>)
            }

          </div>

          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
        </div>
      </nav>

      {/* <!-- Modal --> */}
      <div
        className="modal fade"
        id="confirmDeleteBookingModal"
        tabIndex="-1"
        aria-labelledby="confirmDeleteBookingModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog ">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="confirmDeleteBookingModalLabel">
                Booking deletion confirmation
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              Are you sure for cancel this booking?
            </div>
            <div className="modal-footer">
              <button
                type="button"
                onClick={handeDeleteBooking}
                data-bs-dismiss="modal"
                style={{ backgroundColor: "#6610f2" }}
                className="btn btn-primary"
              >
                Yes
              </button>
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                No
              </button>
            </div>
          </div>
        </div>
      </div>
    </header>
  );
}
export default Navbar;
