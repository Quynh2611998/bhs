import './App.css';
import 'react-toastify/dist/ReactToastify.css';
import { Route, Switch, useHistory } from 'react-router-dom';
import { toast, ToastContainer, Zoom } from 'react-toastify';
import { useEffect, useState } from 'react';
import { getAuthToken } from "./common/common-function"
import ListAccountPage from './components/account/ListAccount';
import Navbar from './components/layout/Navbar';
import SlideNav from './components/layout/SlideNav'
import Footer from './components/layout/Footer';
import Home from './components/home/Home';
import PrivateRoute from './components/auth/PrivateRoute';
import Login from './components/auth/Login';
import { initRequestInterceptor, initResponseInterceptor } from './common/axios-interceptor';
import CategoryPage from './components/category/Category';
import ServicePage from './components/service/Service'
import OptionPage from './components/option/Option'
import StaffPage from './components/staff/Staff'
import DetailUser from './components/account/DetailUser'
import SchedulePage from './components/schedule/Schedule';
import VoucherOptionPage from './components/voucher/VoucherOption';
import VoucherUserPage from './components/voucher/VoucherUser';
import BookingPage from './components/booking/Booking';


function App() {

  const routingHistory = useHistory();
  const isAuthenticate = getAuthToken() ? true : false;

  /* Axios interceptor for request and response */

  useEffect(() => {
    initRequestInterceptor(routingHistory, toast);
    initResponseInterceptor(routingHistory, toast);
  }, [])

  /* Axios interceptor for request and response */
  const [isAuthent, setIsAuthent] = useState(isAuthenticate);
  const [style, setStyle] = useState({ paddingLeft: '0px' });

  useEffect(() => {
    if (isAuthent) {
      setStyle({ paddingLeft: '255px' })
    } else {
      setStyle({ paddingLeft: '0px' })
    }
  }, [isAuthent])

  return (
    <div className="app">

      <div className='body sb-nav-fixed' >

        {isAuthent && <Navbar
          // isAuthent ={isAuthent} 
          setUnAuthent={() => setIsAuthent(false)}
        //  render={() => <Login setAuthent = {() =>setIsAuthent(true)} />}
        />}

        <div id="layoutSidenav">
          {isAuthent && <SlideNav
          // isAuthent ={isAuthent} 
          // setUnAuthent = {() => setIsAuthent(false)}
          />}

          <div id="layoutSidenav_content" style={style}>
            <Switch>
              <Route path='/login' render={() => <Login setAuthent={() => setIsAuthent(true)} setUnAuthent={() => setIsAuthent(false)} />} />
              <PrivateRoute exact path='/' component={Home} />
              <PrivateRoute path='/listAccount' component={ListAccountPage} />
              <PrivateRoute path='/category' component={CategoryPage} />
              <PrivateRoute path='/service' component={ServicePage} />
              <PrivateRoute path='/option' component={OptionPage} />
              <PrivateRoute path='/staff' component={StaffPage} />
              <PrivateRoute path='/detail-user/:user_id' component={DetailUser} />
              <PrivateRoute path='/voucher-option' component={VoucherOptionPage} />
              <PrivateRoute path='/voucher-user' component={VoucherUserPage} />
              <PrivateRoute path='/schedule' component={SchedulePage} />
              <PrivateRoute path='/booking' component={BookingPage} />
            </Switch>
            <Footer />
          </div>
        </div>
        <ToastContainer draggable={false} transition={Zoom} autoClose={3000} />
        {/* Declare route to page in Switch block code */}

        {/* Declare route to page in Switch block code */}

      </div>
    </div>
  );
}

export default App;
