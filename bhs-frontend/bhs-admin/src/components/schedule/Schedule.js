import { useState, useEffect, useRef } from 'react'
import axios from 'axios';
import { SERVER_API_URL } from '../../common/common-constant';
import FullCalendar from '@fullcalendar/react' // must go before plugins
import timeGridPlugin from '@fullcalendar/timegrid'; // a plugin!
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction'
import bootstrap5Plugin from '@fullcalendar/bootstrap5';

function Schedule() {

  const [items, setItems] = useState([]);
  const [schedule, setSchedule] = useState([]);
  const [shifts, setShifts] = useState([]);
  const [bookings, setBooings] = useState([]);
  const idRef = useRef();





  useEffect(() => {
    axios.get(SERVER_API_URL + 'api/get-all-list-hair-stylists').then(res => {
      if (res.status === 200) {
        setItems(res.data)
      }
    })
  }, [])

  useEffect(() => {
    axios.get(SERVER_API_URL + `api/get-all-stylists`).then(res => {
      if (res.status === 200) {
        setBooings(res.data.map(item => (
          {
            title: item.bookingId + ' - ' + item.status + ' - ' + item.firstName + ' ' + item.lastName + ' - ' + item.phoneNumber + ' - ' + item.stylistName,
            start: new Date(item.dateTime - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0, 10) + 'T' + item.startTime,
            end: new Date(item.dateTime - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0, 10) + 'T' + item.endTime,
            allDay: false
          })
        ))
      }
    })

  }, [])

  console.log(bookings)

  async function handleSelectStaff(id) {
    if (id == 0) {
      await axios.get(SERVER_API_URL + `api/get-all-stylists`).then(res => {
        if (res.status === 200) {
          setBooings(res.data.map(item => (
            {
              title: item.bookingId + ' - ' + item.status + ' - ' + item.firstName + ' ' + item.lastName + ' - ' + item.phoneNumber + ' - ' + item.stylistName,
              start: new Date(item.dateTime - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0, 10) + 'T' + item.startTime,
              end: new Date(item.dateTime - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0, 10) + 'T' + item.endTime,
              allDay: false
            })
          ))
        }
      })
    }
    else {
      await axios.get(SERVER_API_URL + `api/get-stylist-schedule-booking-by-stylist-id/${id}`).then(res => {
        if (res.status === 200) {
          setBooings(res.data.map(item => (
            {
              title: item.id + ' - ' + item.status + ' - ' + item.firstName + ' ' + item.lastName + ' - ' + item.phoneNumber,
              start: new Date(item.dateTime - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0, 10) + 'T' + item.startTime,
              end: new Date(item.dateTime - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0, 10) + 'T' + item.endTime,
              allDay: false
            })
          ))
        }
      })
    }
  }
  return (
    <main>
      <div className="container-fluid px-4">
        <h1 className="mt-4">Schedule Management</h1>
        <ol className="breadcrumb mb-4">
          <li className="breadcrumb-item"><a href="index.html">Dashboard</a></li>
          <li className="breadcrumb-item active">Schedule Management</li>
        </ol>
        <select ref={idRef} className="form-select mb-4 w-25" onChange={(e) => handleSelectStaff(e.target.value)}>
          <option value={0}>All Stylists</option>
          {items && items.map(item => (
            <option key={item.id} value={item.id} >{item.stylistName}</option>
          ))}
        </select>
        <div className="card mb-4">
          <div className="card-header">
            <i className="fa fa-table me-2" aria-hidden="true"></i>
            Schedule Table
          </div>
          <div className="card-body">
            <FullCalendar
              plugins={[timeGridPlugin, dayGridPlugin, interactionPlugin, bootstrap5Plugin]}
              themeSystem='bootstrap5'
              timeZone='local'
              initialView="timeGridWeek"
              headerToolbar={{
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay'
              }}
              eventTimeFormat={{
                hour: '2-digit',
                minute: '2-digit',
                meridiem: false
              }}
              slotMinTime='08:00:00'
              slotMaxTime='20:00:00'

              events={
                bookings
              }

              slotLabelFormat={{
                hour: 'numeric',
                minute: '2-digit',
                omitZeroMinute: true,
                meridiem: 'short'
              }}

            // editable={true}
            // selectable={true}


            />
          </div>
        </div>
      </div>
    </main>

  )
}
export default Schedule;