import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter } from "react-router-dom";
import "./index.css";
import App from "./App";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import "fontawesome-4.7/css/font-awesome.min.css";
import "datatables.net-dt/js/dataTables.dataTables";
import "datatables.net-dt/css/jquery.dataTables.min.css";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import "@szhsin/react-menu/dist/index.css";
import { BookingProvider } from './components/context/BookingContext';
import { BookingExistProvider } from './components/context/BookingExistContext';
import { BookingOptionProvider } from './components/context/BookingOptionContext';

ReactDOM.render(
  <BrowserRouter>
    <BookingProvider>
      <BookingExistProvider>
       <BookingOptionProvider>
       <App />
       </BookingOptionProvider>
      </BookingExistProvider>
    </BookingProvider>
  </BrowserRouter>,
  document.getElementById("root")
);
