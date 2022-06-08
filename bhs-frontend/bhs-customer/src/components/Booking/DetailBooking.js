import React, { useEffect, useRef, useState, useContext } from "react";
import "./style.css";
import { SERVER_API_URL } from "../../common/common-constant";
import axios from "axios";
import { useParams } from "react-router-dom";
import { BookingContext } from "../context/BookingContext";
function DetailBooking() {
  const { user_id } = useParams();
  const [listBooking, setListBooking] = useState([]);
  const [detailBooking, setDetailBooking] = useState([]);
  const [lstOptionBooking, setLstOptionBooking] = useState([]);
  const [bookingMapping, setBookingMapping] = useState([]);
  const [bookingStatus, setBookingStatus] = useState([]);
  const [bookingId, setBookingId] = useState();
  const [date, setDate] = useState("");
  const context = useContext(BookingContext);
  const dateTimeRef = useRef();
  const totalPriceRef = useRef(0);

  useEffect(() => {
    if (user_id) {
      axios
        .get(SERVER_API_URL + `api/user-get-booking-by-user-id/${user_id}`)
        .then((result) => {
          setListBooking(result.data.dateTime);
          sortTable();
        })
        .catch((err) => {});
    }
  }, [date]);
  function sortTable() {
    var table, i, x, y;
    table = document.getElementById("table");
    var switching = true;

    // Run loop until no switching is needed
    while (switching) {
      switching = false;
      var rows = table.rows;

      // Loop to go through all rows
      for (i = 1; i < rows.length - 1; i++) {
        var Switch = false;

        // Fetch 2 elements that need to be compared
        x = rows[i].getElementsByTagName("td")[0];
        y = rows[i + 1].getElementsByTagName("td")[0];
        // Check if 2 rows need to be switched
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          // If yes, mark Switch as needed and break loop
          Switch = true;
          break;
        }
      }
      if (Switch) {
        // Function to switch rows and mark switch as completed
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
      }
    }
  }

  function handleDetail(item, itemOptions, dateTime, totalPrice) {
    setDetailBooking(item);
    setLstOptionBooking(itemOptions);
    dateTimeRef.current = dateTime;
    totalPriceRef.current = totalPrice;
  }
  console.log(totalPriceRef.current);
  function handleCancel() {
    axios
      .put(SERVER_API_URL + `api/user-update-booking/${bookingId}`)
      .then((result) => {
        setDate(new Date());
        context.setDate(new Date());
        // axios
        //   .get(SERVER_API_URL + `api/user-get-booking-by-user-id/${user_id}`)
        //   .then((result) => {
        //     setListBooking(result.data.dateTime);
        //   })
        //   .catch((err) => {});
        // const turnOff = document.getElementById("staticBackdrop");
        // console.log(turnOff);
        // turnOff.classList.remove("show");
        // console.log(turnOff);
        // turnOff.modal("hide");
      })
      .catch((err) => {});
  }
  function getTotalPrice(option) {
    if (!option) return;
    // this.reduce((a, b) => a + (b["totalPrice"] || 0), 0);
    return option
      .reduce((n, { price }) => n + price, 0)
      .toLocaleString("it-IT", {
        style: "currency",
        currency: "VND",
      });
  }
  return (
    <>
      <div>
        <div className="container w-75 px-0 py-5 ">
          <div className="detail-booking-content bg-white row rounded-3 ">
            <div className="row justify-content-around">
              <div className="col-4">
                <h5
                  style={{
                    marginTop: "30px",
                    color: "#222222",
                    textAlign: "left",
                    fontWeight: "bold",
                  }}
                >
                  LỊCH SỬ ĐẶT CHỖ
                </h5>
              </div>
              <div className="col-4"></div>
              <hr
                style={{
                  marginBottom: "1rem",
                  marginLeft: "25px",
                  border: "0",
                  borderTop: "1px solid rgba(0, 0, 0, 0.1)",
                }}
              />
            </div>
            <div className="row">
              <table
                style={{
                  width: "100%",
                  border: "solid 1px rgba(0, 0, 0, 0.12)",
                }}
                className="table table-sm m-3 "
                id="table"
              >
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Ngày đặt</th>
                    <th scope="col">Dịch vụ</th>
                    <th scope="col">Tổng tiền</th>
                    <th scope="col">Trạng thái</th>
                  </tr>
                </thead>
                <tbody>
                  {listBooking &&
                    listBooking.map((items, index) =>
                      items.booking.map((item) => (
                        <tr key={item.bookingId}>
                          <td
                            style={{
                              verticalAlign: "middle",
                              color: "#00a79b",
                            }}
                            scope="row"
                          >
                            {item.bookingId}
                          </td>
                          <td style={{ verticalAlign: "middle" }}>
                            {new Date(items.dateTime).toLocaleDateString(
                              "vi-vn"
                            )}
                          </td>
                          <td>
                            <div>
                              <p className="text-service">
                                {item.option.length} dịch vụ
                              </p>
                            </div>
                            <div>
                              <p className="text-address">
                                km29, Đại lộ Thăng Long , Hòa Lạc
                              </p>
                            </div>
                            <button
                              className="btn-detail"
                              tabIndex="0"
                              type="button"
                              data-bs-toggle="modal"
                              data-bs-target="#exampleModal"
                              onClick={() =>
                                handleDetail(
                                  item.option[0],
                                  item.option,
                                  items.dateTime,
                                  item.totalPrice
                                )
                              }
                            >
                              <span className="btn-detail-text">Chi tiết</span>
                            </button>
                            {item.status === "booked" && (
                              <button
                                className="btn-cancel"
                                tabIndex="0"
                                type="button"
                                data-bs-toggle="modal"
                                data-bs-target="#staticBackdrop"
                                onClick={() => setBookingId(item.bookingId)}
                              >
                                <span className="btn-detail-text">Huỷ</span>
                              </button>
                            )}
                          </td>
                          <td
                            style={{
                              color: "#fe5a3a",
                              verticalAlign: "middle",
                            }}
                          >
                            {item.totalPrice.toLocaleString("it-IT", {
                              style: "currency",
                              currency: "VND",
                            })}
                          </td>
                          <td style={{ verticalAlign: "middle" }}>
                            {item.status}
                          </td>
                        </tr>
                      ))
                    )}
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div
          className="modal fade"
          id="exampleModal"
          tabIndex="-1"
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
        >
          <div className="modal-dialog">
            {detailBooking && (
              <div className="modal-content">
                <div className="modal-header border-bottom-0">
                  <button
                    type="button"
                    className="btn-close"
                    data-bs-dismiss="modal"
                    aria-label="Close"
                  ></button>
                </div>
                <div className="modal-body text-start text-black p-4">
                  <h4 className="mb-2" style={{ color: "#35558a" }}>
                    Chi tiết đơn đặt chỗ
                  </h4>
                  <hr
                    className="mt-2 mb-4"
                    style={{
                      height: "0",
                      backgroundColor: "transparent",
                      opacity: "0.75",
                      borderTop: "2px dashed #9e9e9e",
                    }}
                  />

                  <div className="d-flex ">
                    <p className="fw-bold mb-0 me-2">
                      <i
                        className="fa fa-calendar-plus-o"
                        aria-hidden="true"
                      ></i>
                    </p>
                    <p className="text mb-0">
                      {new Date(dateTimeRef.current).toLocaleDateString(
                        "vi-vn"
                      )}
                    </p>
                  </div>

                  <div className="d-flex">
                    <p className="fw-bold mb-0 me-2">
                      <i className="fa fa-clock-o" aria-hidden="true"></i>
                    </p>
                    <p className="text mb-0">{detailBooking.startTime}</p>
                  </div>

                  <div className="d-flex">
                    <p className="fw-bold mb-0 me-2">
                      <i className="fa fa-map-marker" aria-hidden="true"></i>
                    </p>
                    <p className="text-muted mb-0">
                      km29, Đại lộ Thăng Long , Hòa Lạc
                    </p>
                  </div>
                  <div className="d-flex">
                    <p className="fw-bold mb-0 me-2">
                      <i className="fa fa-usd" aria-hidden="true"></i>
                    </p>
                    <p className="text-muted mb-0">Thanh toán tại salon</p>
                  </div>
                  <div className="d-flex">
                    <p className="fw-bold mb-0 me-2">
                      <i className="fa fa-mobile" aria-hidden="true"></i>
                    </p>
                    <p className="text-muted mb-0">
                      {detailBooking.phoneNumber}
                    </p>
                  </div>
                  <div className="d-flex">
                    <p className="fw-bold mb-0 me-2">
                      <i className="fa fa-scissors" aria-hidden="true"></i>
                    </p>
                    <p className="text-muted mb-0">
                      {detailBooking.stylistName}
                    </p>
                  </div>
                  <hr
                    className="mt-2 mb-4"
                    style={{
                      height: "0",
                      backgroundColor: "transparent",
                      opacity: "0.75",
                      borderTop: "2px dashed #9e9e9e",
                    }}
                  />
                  <div>
                    <div className="d-flex justify-content-between">
                      <p
                        style={{ color: "#808285" }}
                        className="fw-bold mb-0 me-2"
                      >
                        Dịch vụ
                      </p>
                      <p
                        style={{ color: "#808285" }}
                        className="fw-bold mb-0 me-2"
                      >
                        Thành tiền
                      </p>
                    </div>
                    {lstOptionBooking &&
                      lstOptionBooking.map((op, index) => {
                        return (
                          <div
                            key={index}
                            className="d-flex justify-content-between"
                          >
                            <p style={{ fontWeight: "500" }} className="mb-0">
                              {op.optionName}
                            </p>
                            <p
                              style={{
                                color: "#fe5a3a",
                                marginLeft: "31%",
                                fontWeight: "500",
                              }}
                              className="mb-0"
                            >
                              {op.price.toLocaleString("it-IT", {
                                style: "currency",
                                currency: "VND",
                              })}
                            </p>
                          </div>
                        );
                      })}
                  </div>
                  <hr
                    className="mt-2 mb-4"
                    style={{
                      height: "0",
                      backgroundColor: "transparent",
                      opacity: "0.75",
                      borderTop: "2px dashed #9e9e9e",
                    }}
                  />
                  <div className="d-flex justify-content-between">
                    <p style={{ color: "#808285" }} className="fw-bold">
                      Tổng tiền
                    </p>
                    <p className="fw-bold" style={{ color: "#fe5a3a" }}>
                      {totalPriceRef.current.toLocaleString("it-IT", {
                        style: "currency",
                        currency: "VND",
                      })}
                    </p>
                  </div>
                </div>
                <div className="modal-footer d-flex justify-content-center border-top-0 py-4"></div>
              </div>
            )}
          </div>
        </div>
        <div
          className="modal fade"
          id="staticBackdrop"
          data-bs-backdrop="static"
          data-bs-keyboard="false"
          tabIndex="-1"
          aria-labelledby="staticBackdropLabel"
          aria-hidden="true"
        >
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h5
                  style={{ fontSize: "1.125rem", fontWeight: "600" }}
                  className="modal-title"
                  id="staticBackdropLabel"
                >
                  Huỷ đặt chỗ
                </h5>
              </div>
              <div className="modal-body">
                <span>Bạn có chắc chắn muốn huỷ đơn đặt chỗ này?</span>
              </div>
              <div className="modal-footer">
                <button
                  type="button"
                  className="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  HUỶ BỎ
                </button>
                <button
                  type="button"
                  className="btn btn-primary"
                  data-bs-dismiss="modal"
                  onClick={() => handleCancel()}
                >
                  ĐỒNG Ý
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default DetailBooking;
