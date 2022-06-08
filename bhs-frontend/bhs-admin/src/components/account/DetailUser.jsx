import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { SERVER_API_URL } from "../../common/common-constant";

function DetailUser() {
  const [user, setUser] = useState();
  const { user_id } = useParams();
  useEffect(() => {
    console.log(user_id);
    axios
      .get(
        SERVER_API_URL + `api/user-profile/get-user-profile-by-id/${user_id}`
      )
      .then(({ data }) => {
        setUser(data);
        console.log(data);
      })
      .catch((err) => {});
  }, []);
  return (
    <>
      {user && (
        <main>
          <div className="container-fluid px-4">
            <h1 className="mt-4">Detail User</h1>
            <ol className="breadcrumb mb-4">
              <li className="breadcrumb-item">
                <a href="index.html">Dashboard</a>
              </li>
              <li className="breadcrumb-item active">Detail User</li>
            </ol>
            {user && (
              <div className="card mb-4">
                <div className="card-body">
                  <div className="container rounded bg-white mt-5 mb-5">
                    <div className="row">
                      <div className="col-md-3 border-right"></div>
                      <div className="col-md-5 border-right">
                        <div className="p-3 py-5">
                          <img
                            className="rounded-circle mt-5"
                            src={user.profileImage}
                            style={{ width: "100px" }}
                          />
                          <div className="d-flex justify-content-between align-items-center mb-3">
                            <h4 className="text-right">User Detail</h4>
                          </div>
                          <div className="row mt-2">
                            <div className="col-md-6">
                              <label className="labels">First Name</label>
                              <input
                                type="text"
                                className="form-control"
                                placeholder="First Name"
                                defaultValue={user.firstName}
                                readOnly={true}
                              />
                            </div>
                            <div className="col-md-6">
                              <label className="labels">Last Name</label>
                              <input
                                type="text"
                                className="form-control"
                                placeholder="Last Name"
                                defaultValue={user.lastName}
                                readOnly={true}
                              />
                            </div>
                          </div>
                          <div className="row mt-3">
                            <div className="col-md-12">
                              <label className="labels">Gender</label>
                              <input
                                type="text"
                                className="form-control"
                                placeholder="Gender"
                                defaultValue={user.gender}
                                readOnly={true}
                              />
                            </div>
                            <div className="col-md-12">
                              <label className="labels">Phone Number</label>
                              <input
                                type="text"
                                className="form-control"
                                placeholder="Phone Number"
                                defaultValue={user.phoneNumber}
                                readOnly={true}
                              />
                            </div>
                            <div className="col-md-12">
                              <label className="labels">Email </label>
                              <input
                                type="text"
                                className="form-control"
                                placeholder="Email"
                                defaultValue={user.email}
                                readOnly={true}
                              />
                            </div>
                            <div className="col-md-12">
                              <label className="labels">Date Of Birth</label>
                              <input
                                type="text"
                                className="form-control"
                                placeholder="Date Of Birth"
                                defaultValue={new Date(
                                  user.dateOfBirth
                                ).toLocaleString("vi-vn")}
                                readOnly={true}
                              />
                            </div>
                          </div>
                          <div className="row mt-3">
                            <div className="col-md-6">
                              <label className="labels">Create Date</label>
                              <input
                                type="text"
                                className="form-control"
                                placeholder="Create Date"
                                defaultValue={new Date(
                                  user.createdDate
                                ).toLocaleString("vi-vn")}
                                readOnly={true}
                              />
                            </div>
                            <div className="col-md-6">
                              <label className="labels">Create By</label>
                              <input
                                type="text"
                                className="form-control"
                                placeholder="Create By"
                                defaultValue={user.createdBy}
                                readOnly={true}
                              />
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            )}
          </div>
        </main>
      )}
    </>
  );
}

export default DetailUser;
