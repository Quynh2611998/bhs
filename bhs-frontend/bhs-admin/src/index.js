import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import './index.css';
import App from './App';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import 'bootstrap-icons/font/bootstrap-icons.css';
import 'fontawesome-4.7/css/font-awesome.min.css';
import './components/account/Account.css'
import './assets/css/styles.css'
import './assets/js/scripts'
import "datatables.net-dt/js/dataTables.dataTables"
import "datatables.net-dt/css/jquery.dataTables.min.css"
import 'fullcalendar/main.js'
import 'fullcalendar/main.css'



ReactDOM.render(
  <BrowserRouter>
        <App />
    </BrowserRouter>,
  document.getElementById('root')
);

