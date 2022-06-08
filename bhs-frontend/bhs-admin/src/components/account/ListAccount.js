import axios from "axios";
import { useEffect, useState, useRef } from "react";
import { SERVER_API_URL } from "../../common/common-constant";
import LockAccountModal from "./LockAccount";
import UnockAccountModal from "./UnlockAccount";
import useModal from "../../common/common-function/useModal";
import { Link } from "react-router-dom";

function ListAccount() {
    const [items, setItems] = useState([]);
    const [date, setDate] = useState("");
    const $ = require("jquery");
    const { isShowing, isShowing2, toggle, toggle2 } = useModal();
    const idRef = useRef();
    const userNameRef = useRef();
    const handleGetInfo = (id, userName) => {
        idRef.current = id;
        userNameRef.current = userName;
    };
    const data = {
        id: idRef.current,
        userName: userNameRef.current,
    };

    useEffect(() => {
        axios.get(SERVER_API_URL + "api/admin/get-all-list-account").then((res) => {
            if (res.status === 200) {
                setItems(res.data);
                $("#AccountTable").DataTable();
            }
        })
       
    }, [date])


    return (
        <main>
            <div className="container-fluid px-4">
                <h1 className="mt-4">Account Management</h1>
                <ol className="breadcrumb mb-4">
                    <li className="breadcrumb-item">
                        <a href="index.html">Dashboard</a>
                    </li>
                    <li className="breadcrumb-item active">Account Management</li>
                </ol>
                <div className="card mb-4">
                    <div className="card-header">
                        <i className="fa fa-table" aria-hidden="true"></i>
                        DataTable
                    </div>
                    <div className="card-body">
                        <table id="AccountTable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>UserName</th>
                                    <th>Role</th>
                                    <th>Create</th>
                                    <th>Lock Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                {items && items.map((item) => (
                                    <tr key={item.id}>
                                        <th>{item.id}</th>
                                        <th>
                                            <Link
                                                to={`/detail-user/${item.id}`}
                                                style={{ textDecoration: "none" }}
                                            >
                                                {item.username}
                                            </Link>
                                        </th>
                                        <th>{item.role}</th>
                                        <th>{new Date(item.createdDate).toLocaleString('vi-vn').slice(10)}</th>
                                        <th>{item.locked.toString()}</th>
                                        <th>
                                            <button
                                                type="button"
                                                className="btn btn-danger btn-icon "
                                                onClick={() => {
                                                    handleGetInfo(item.id, item.username);
                                                    toggle();
                                                }}
                                                disabled={item.locked === true}
                                            >
                                                <i
                                                    className="fa fa-lock icon-btn"
                                                    aria-hidden="true"
                                                ></i>
                                                <span className="btn-text">Lock</span>
                                            </button>
                                            &nbsp;
                                            <button
                                                type="button"
                                                className="btn btn-primary btn-icon "
                                                onClick={() => {
                                                    handleGetInfo(item.id, item.username);
                                                    toggle2();
                                                }}
                                                disabled={item.locked === false}
                                            >
                                                <i
                                                    className="fa fa-unlock  icon-btn"
                                                    aria-hidden="true"
                                                ></i>
                                                <span className="btn-text">Unlock</span>
                                            </button>
                                        </th>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <LockAccountModal
                isShowing={isShowing}
                hide={toggle}
                data={data}
                setDate={() => setDate(new Date())}
            />
            <UnockAccountModal
                isShowing={isShowing2}
                hide={toggle2}
                data={data}
                setDate={() => setDate(new Date())}
            />
        </main>
    );
}
export default ListAccount;
