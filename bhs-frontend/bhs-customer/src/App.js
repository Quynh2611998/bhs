import "./App.css";
import "react-toastify/dist/ReactToastify.css";
import { Route, Switch, useHistory } from "react-router-dom";
import { toast, ToastContainer, Zoom } from "react-toastify";
import Login from "./components/auth/Login";
import Navbar from "./components/layout/Navbar";
import Footer from "./components/layout/Footer";
import ResetPasswordInit from "./components/auth/ResetPasswordInit";
import ResetPasswordFinish from "./components/auth/ResetPasswordFinish";
import {
  initRequestInterceptor,
  initResponseInterceptor,
} from "./common/axios-interceptor";
import PrivateRoute from "./components/auth/PrivateRoute";
import HomePage from "./components/layout/HomePage";
import UserProfile from "./components/user-profile/UserProfile";
import { useEffect, useState } from "react";
import Header from "./components/layout/Header";
import { getAuthToken } from "./common/common-function";
import Booking from "./components/Booking/Booking";
import ListService from "./components/listService/ListService";
import ListVoucher from "./components/listVoucher/ListVoucher";
import DetailOption from "./components/detailOption/DetailOption";
import Contact from "./components/contact/Contact";
import DetailBooking from "./components/Booking/DetailBooking";

function App() {
  const routingHistory = useHistory();
  const isAuthenticate = getAuthToken() ? true : false;
  /* Axios interceptor for request and response */
  initRequestInterceptor(routingHistory, toast);

useEffect( () => {
  initResponseInterceptor(routingHistory, toast);
},[])
    // initRequestInterceptor(routingHistory, toast);
    // initResponseInterceptor(routingHistory, toast);

  /* Axios interceptor for request and response */
  


  const [isLoginPage, setIsLoginPage] = useState(false);

  return (
    <div className="app">
      {!isLoginPage && <Navbar />}
      {/* {isAuthent && <Navbar setLogOut={() => setIsAuthent(false)} />} */}
      {/* <Navbar/> */}
      {isLoginPage && <Header />}
      <div className="body">
        <ToastContainer draggable={false} transition={Zoom} autoClose={3000}  />
        {/* Declare route to page in Switch block code */}
        <Switch>
          <Route
            path="/login"
            render={() => (
              <Login
                setIsLoginPage={() => setIsLoginPage(true)}
                setIsNotLoginPage={() => setIsLoginPage(false)}
              />
            )}
          />
          <Route path="/active/:activeKey" component={Login} />
          <Route path="/reset-password/init" component={ResetPasswordInit} />
          <Route path="/reset-password/finish/:resetKey" component={ResetPasswordFinish} />
          <Route exact path="/" render={() => (
            <HomePage
              setIsNotLoginPage={() => setIsLoginPage(false)}
            />
          )} />
          <Route path="/contact" component={Contact} />
          <Route path="/search" component={HomePage} />
          <Route
            path="/detail-option/:option_id"
            render={() => (
              <DetailOption
                setIsNotLoginPage={() => setIsLoginPage(false)}
              />
            )}
          
          />
          <PrivateRoute path="/user-profile" component={UserProfile} />
          <PrivateRoute path="/booking" component={Booking} />
          <PrivateRoute path="/list-service" component={ListService} />
          <PrivateRoute path="/list-voucher" component={ListVoucher} />
          <PrivateRoute
            path="/detail-booking/:user_id"
            component={DetailBooking}
          />
        </Switch>
        {/* Declare route to page in Switch block code */}
      </div>
      <Footer />
    </div>
  );
}

export default App;

