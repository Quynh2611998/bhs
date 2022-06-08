import axios from 'axios';
import { useEffect, useState, useRef } from 'react';
import { SERVER_API_URL } from '../../common/common-constant';
import useModal from '../../common/common-function/useModal';
import CreateBooking from './CreateBooking';
import moment from 'moment'
import BookingDetail from './BookingDetail';
import EditBooking from './EditBooking';
import DeleteBooking from './DeleteBooking';



function Booking() {
    const [items, setItems] = useState([]);
    const [date, setDate] = useState('');
    const [accounts, setAccounts] = useState([]);
    const [stylists, setStylists] = useState([]);
    const [lstOptions, setLstOptions] = useState([]);
    const [optionBooking, setOptionBooking] = useState([]);
    const [voucherOptions, setVoucherOptions] = useState([]);
    const [voucherUsers, setVoucherUsers] = useState([]);
    const [dateTime, setDateTime] = useState('');
    const [lstTimeSlot, setLstTimeSlot] = useState([]);
    const [bookingDetail, setBookingDetail] = useState([]);
    const [bookingMappingId, setbookingMappingId] = useState([]);
    const $ = require('jquery');
    const { isShowing, isShowing2, isShowing3, isShowing4, toggle, toggle2, toggle3, toggle4 } = useModal();
    const idRef = useRef();
    const endTimeRef = useRef();
    const BookingNameRef = useRef();
    let options = { year: 'numeric', month: 'numeric', day: 'numeric' };


    const handleGetInfo = async (id, isEdit, isViewDetail) => {
        idRef.current = id;
   
        if (isEdit) {
            await axios.get(SERVER_API_URL + `api/get-booking-by-booking-id/${id}`).then(res => {
                if (res.status === 200) {
                    setBookingDetail(res.data[0])
                    setDateTime(res.data[0].dateTime);
                }
            })
            await axios.get(SERVER_API_URL + `api/get-booking-option-mapping-by-booking-id/${id}`).then(res => {
                if (res.status === 200) {
                    setOptionBooking(res.data.map(item => item.optionId));
                    setbookingMappingId(res.data.map(item => item.id));
                }
            })
            if (isViewDetail) {
                toggle4();
            } else {
                toggle2();
            }

        }

    }

function handleCreate() {
    accounts.length > 0 && toggle();
}
    function createTimeSot(fromTime, toTime, timeDuration) {
        let startTime = moment(fromTime, 'HH:mm');
        let endTime = moment(toTime, 'HH:mm');
        let arr = [];
        while (startTime < endTime) {
            arr.push(new moment(startTime).format('HH:mm'));
            startTime.add(timeDuration, 'minutes')
        }
        return arr;
    }

    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-all-list-shift').then(res => {
            if (res.status === 200) {
                endTimeRef.current = res.data[0].endTime;
                setLstTimeSlot(createTimeSot(res.data[0].startTime, res.data[0].endTime, res.data[0].duration));
            }
        })
    }, [])
    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/user-profile/get-all-list-user-profile').then(res => {
            if (res.status === 200) {
                setAccounts(res.data)
            }
        })
    }, [])
    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-all-list-view-booking').then(res => {
            if (res.status === 200) {
                setItems(res.data)
                $("#BookingTable").DataTable();
            }
        })

    }, [date])

    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-all-list-hair-stylists').then(res => {
            if (res.status === 200) {
                setStylists(res.data)
            }
        })
    }, [])

    useEffect(() => {
        axios.get(SERVER_API_URL + 'api/get-all-list-option_admin').then(res => {
            if (res.status === 200) {
                setLstOptions(res.data)
            }
        })
    }, [])
    return (
        <main>
            <div className="container-fluid px-4">
                <h1 className="mt-4">Booking Management</h1>
                <ol className="breadcrumb mb-4">
                    <li className="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                    <li className="breadcrumb-item active">Booking Management</li>
                </ol>
                <button type='button' className='btn btn-primary btn-icon mb-3' onClick={handleCreate}>
                    <i className="fa fa-plus-circle icon-btn" aria-hidden="true"></i>
                    <span className='btn-text'>Create</span>
                </button>
                <div className="card mb-4">
                    <div className="card-header">
                        <i className="fa fa-table me-2" aria-hidden="true"></i>
                        DataTable
                    </div>
                    <div className="card-body">
                        <table id="BookingTable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Phone Number</th>
                                    <th>Appointment Date</th>
                                    <th>Stylist</th>
                                    <th>Created Date</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                {items && items.map((item) => (
                                    <tr key={item.id}>
                                        <th>{item.id}</th>
                                        <th>{item.first_name + '  ' + item.last_name}</th>
                                        <th>{item.phone_number}</th>
                                        <th>{new Date(item.date_time).toLocaleString('vi-vn', options)}</th>
                                        <th>{item.stylist_name}</th>
                                        <th>{new Date(item.created_date).toLocaleString('vi-vn', options)}</th>
                                        <th>{item.status}</th>
                                        <th>
                                            <button type='button' className='btn btn-info btn-icon ' onClick={() => { handleGetInfo(item.id, true, true); }}>
                                                <i className="fa fa-pencil-square-o icon-btn" aria-hidden="true"></i>
                                                <span className='btn-text'>Detail</span>
                                            </button>
                                            &nbsp;
                                            <button type='button' className='btn btn-primary btn-icon ' onClick={() => { handleGetInfo(item.id, true, false) }}>
                                                <i className="fa fa-pencil-square-o icon-btn" aria-hidden="true"></i>
                                                <span className='btn-text'>Edit</span>
                                            </button>
                                            &nbsp;
                                            <button type='button' className='btn btn-danger btn-icon ' onClick={() => { handleGetInfo(item.id, false); toggle3() }}>
                                                <i className="fa fa-trash icon-btn" aria-hidden="true"></i>
                                                <span className='btn-text'>Delete</span>
                                            </button>
                                        </th>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            {isShowing && <CreateBooking
                hide={toggle}
                setDate={() => setDate(new Date())}
                users={accounts}
                stylists={stylists}
                options={lstOptions}
                timeSlots={lstTimeSlot}
                endTimeDay={endTimeRef.current}
            />}

            {isShowing2 && <EditBooking
                hide={toggle2}
                setDate={() => setDate(new Date())}
                users={accounts}
                stylists={stylists}
                options={lstOptions}
                timeSlots={lstTimeSlot}
                endTimeDay={endTimeRef.current}
                bookingDetail={bookingDetail}
                dateTime={dateTime}
                optionBooking={optionBooking}
                bookingMappingId={bookingMappingId}
            />}

            {isShowing3 && <DeleteBooking
                hide={toggle3}
                setDate={() => setDate(new Date())}
                id={idRef.current}
            />}

            {isShowing4 && <BookingDetail
                hide={toggle4}
                bookingDetail={bookingDetail}
                dateTime={dateTime}
                options={lstOptions}
                optionBooking={optionBooking}
            />}
        </main>
    )
}
export default Booking;