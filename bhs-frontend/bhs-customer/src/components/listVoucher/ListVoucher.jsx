import React, { useState, useEffect, useRef } from "react";
import ReactPagination from "react-paginate";
import { SERVER_API_URL } from "../../common/common-constant";
import axios from "axios";

function ListVoucher() {
  const [newVoucher, setNewVoucher] = useState([]);
  const [searchVoucher, setSearchVoucher] = useState([]);
  const userIdRef = useRef();

  useEffect(() => {
    axios
      .get(SERVER_API_URL + "api/user-profile/get-user-profile")
      .then((result) => {
        userIdRef.current = result.data.userId;
        axios
          .get(
            SERVER_API_URL +
              `api/get-one-voucher-user-by-user-id/${userIdRef.current}`
          )
          .then((result) => {
            setNewVoucher(result.data);
            setSearchVoucher(result.data);
          })
          .catch((err) => {});
      })
      .catch((err) => {});
  }, []);

  function handleSearch(e) {
    const result = newVoucher.filter((voucher) =>
      voucher.voucherUserName
        .toUpperCase()
        .includes(e.target.value.toUpperCase())
    );
    setSearchVoucher(result);
  }
  return (
    <>
      <div style={{ marginLeft: "10%", marginBottom: "2%" }}>
        <div className="row justify-content-around">
          <div className="col-4">
            <h1>Voucher</h1>
          </div>
          <div className="col-4"></div>
        </div>
        <div style={{ marginTop: "2%" }} className="row justify-content-around">
          <div className="col-4"></div>
          <div className="col-4">
            <div className="input-group mb-3 w-75">
              <input
                type="text"
                className="form-control"
                placeholder="searching voucher"
                aria-label="Recipient's username"
                aria-describedby="basic-addon2"
                onChange={handleSearch}
              />
              <div className="input-group-append">
                <button className="btn btn-primary">
                  <i className="fa fa-search "></i>
                </button>
              </div>
            </div>
          </div>
        </div>
        <div className="row">
          {searchVoucher && searchVoucher.map((item, index) => (
            <div
              key={index}
              className="d-flex justify-content-around col-6"
              style={{ marginTop: "2%" }}
            >
              <div
                className="card w-75"
                // style={{ width: "auto", cursor: "pointer" }}
              >
                <div className="card-body p-2 w-100">
                  <h5 className="card-title">
                    Voucher Name: {item.voucherUserName}
                  </h5>
                  <h6 className="card-subtitle mb-2">
                    Discount:{" "}
                    {item.discount.toLocaleString("vi", {
                      style: "currency",
                      currency: "VND",
                    })}
                  </h6>
                  <h6 className="card-subtitle mb-2">
                    Start Time:{" "}
                    {new Date(item.dayStart).toLocaleString("vi-vn").slice(10)}
                  </h6>
                  <h6 className="card-subtitle mb-2">
                    End Time:{" "}
                    {new Date(item.dayExpire).toLocaleString("vi-vn").slice(10)}
                  </h6>
                  <h6 className="card-subtitle mb-2">
                    {item.isActived ? "Active" : "Not Active"}
                  </h6>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}

export default ListVoucher;
