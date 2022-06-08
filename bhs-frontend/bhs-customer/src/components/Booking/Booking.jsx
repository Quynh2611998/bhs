import React, { useRef } from "react";
import { useEffect, useState, useContext } from "react";
import { SERVER_API_URL } from "../../common/common-constant";
import { Validator } from "../../common/validator";
import axios from "axios";
import moment, { duration } from "moment";
import { useHistory } from "react-router-dom";
import { BookingContext } from '../context/BookingContext';
import { BookingExistContext } from '../context/BookingExistContext';
import { BookingOptionContext } from '../context/BookingOptionContext';
import Slider from "react-slick";

function Booking() {
  const routingHistory = useHistory();
  const [userProfile, setUserProfile] = useState([]);
  const [items, setItems] = useState([]);
  const [options, setOptions] = useState([]);
  const [checkedName, setCheckedName] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const [totalTime, setTotalTime] = useState(0);
  const [finalPrice, setFinalPrice] = useState(0);
  const firstNameRef = useRef();
  const lastNameRef = useRef();
  const noteRef = useRef();
  const phoneNumberRef = useRef();
  const dateTimeRef = useRef();
  const [slotChecked, setSlotChecked] = useState('');
  const [checked, setChecked] = useState([]);
  const endTimeDayRef = useRef();
  const durationRef = useRef();
  const staffId = useRef(-1);
  const [staff, setStaff] = useState([]);
  const [listTimeSlot, setLstTimeSlot] = useState([]);
  const [timeSlotDisable, setTimeSlotDisable] = useState([]);
  const [vouchers, setVouchers] = useState([]);
  const [voucherChecked, setVoucherChecked] = useState([]);
  const [categories, setCategories] = useState([]);
  const [services, setServices] = useState([]);
  const [discount, setDiscount] = useState();
  const [lstServiceId, setLstServiceId] = useState([]);
  const [lstOptionIdOfServiceSelected, setLstOptionIdOfServiceSelected] = useState([]);
  const [categoryId, setCategoryId] = useState();
  const userIdRef = useRef();
  const disabledTime = ['12:00', '12:30'];
  const passedTime = moment(new Date(new Date().getTime()).toLocaleTimeString(), 'HH:mm').format('HH:mm');
  const context = useContext(BookingContext);
  const bookingExistContext = useContext(BookingExistContext);
  const bookingOptionContext = useContext(BookingOptionContext);
  const optionSelectedRef = useRef();
  const categorySelectedRef = useRef();
  const serviceSelectedRef = useRef();
  const $ = require("jquery");
  let settings = {
    dots: false,
    infinite: false,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 1,
  };
  useEffect(() => {
    if (bookingOptionContext.bookingOption)
      axios
        .get(SERVER_API_URL + `api/get-option/${bookingOptionContext.bookingOption}`)
        .then((result) => {
          if (result.status === 200) {
            handleCheck(result.data[0].categoryId, result.data[0].serviceId, bookingOptionContext.bookingOption, result.data[0].optionName, result.data[0].price, result.data[0].duration);
          }
        }
        )
        .catch((err) => { });
  }, [])

  // useEffect(() => {
  //   axios
  //     .get(SERVER_API_URL + "api/get-all-list-option_admin")
  //     .then((result) => {
  //       setOption(result.data);
  //     })
  //     .catch((err) => { });
  // }, []);

  useEffect(() => {
    axios
      .get(SERVER_API_URL + "api/user-profile/get-user-profile")
      .then((result) => {
        userIdRef.current = result.data.userId;
        setUserProfile(result.data);
        axios
          .get(SERVER_API_URL + `api/get-voucher-user-is-actived-by-user-id/${result.data.userId}`)
          .then((result) => {
            setVouchers(result.data);
          })
      })
      .catch((err) => { });
  }, [])

  useEffect(() => {
    axios
      .get(SERVER_API_URL + "api/get-all-list-hair-stylists")
      .then((result) => {
        setStaff(handleShuffleStaff(result.data));
      })
      .catch((err) => { });
  }, []);

  useEffect(() => {
    axios
      .get(SERVER_API_URL + "api/get-all-list-categories")
      .then((result) => {
        setCategories(result.data);
        axios
          .get(SERVER_API_URL + `api/get-all-service-by-category-id/${result.data[0].id}`)
          .then((result) => {
            setServices(result.data.content);
            axios
              .get(SERVER_API_URL + `api/get-all-option-by-service-id/${result.data.content[0].id}`)
              .then((result) => {
                setOptions(result.data.content);
                setLstOptionIdOfServiceSelected(result.data.content.map(option => option.id));
              })

          })

      })
      .catch((err) => { });
  }, []);

  useEffect(() => {
    if (categories.length > 0) {
      let categoryParent = document.getElementById('categories-wrapper');
      // let serviceParent = document.getElementsByClassName('slick-slide')[0];
      //  let lstServices = document.getElementsByClassName('service-select');
      categoryParent.firstChild.classList.add('category-selected');
      // servicesParent[0].firstChild.firstChild.classList.add('service-selected');
      //  if(lstServices){
      //    lstServices.
      //  }
      // console.log(serviceParent) ;


    }
    if (services.length > 0) {
      let lstServices = document.getElementsByClassName('service-select');
      console.log(lstServices[0])
    }

  }, [categories])

  useEffect(() => {

    if (services.length > 0) {
      let lstServices = document.getElementsByClassName('service-select');
      lstServices[0].classList.add('service-selected')
    }

  }, [services])


  function handleGetServiceAndOptionByCategory(id) {
    handleDeleteBoderService();
    axios
      .get(SERVER_API_URL + `api/get-all-service-by-category-id/${id}`)
      .then((result) => {
        setServices(result.data.content);
        axios
          .get(SERVER_API_URL + `api/get-all-option-by-service-id/${result.data.content[0].id}`)
          .then((result) => {
            setOptions(result.data.content);
            setLstOptionIdOfServiceSelected(result.data.content.map(option => option.id));
          })

      })
  }

  function handleGetOptionByService(id) {
    console.log(id)
    axios
      .get(SERVER_API_URL + `api/get-all-option-by-service-id/${id}`)
      .then((result) => {
        setOptions(result.data.content);
        setLstOptionIdOfServiceSelected(result.data.content.map(option => option.id));
      })

  }


  function handleShuffleStaff(array) {
    let currentIndex = array.length, randomIndex;

    // While there remain elements to shuffle.
    while (currentIndex != 0) {

      // Pick a remaining element.
      randomIndex = Math.floor(Math.random() * currentIndex);
      currentIndex--;
      // And swap it with the current element.
      [array[currentIndex], array[randomIndex]] = [
        array[randomIndex], array[currentIndex]];
    }

    return array;
  }

  function time_convert(num) {
    var hours = Math.floor(num / 60);
    var minutes = num % 60;
    return hours + ":" + minutes;
  }

  function handleSubmit() {

    let endTime = handleCalEndTime(slotChecked);
    const data = {
      userProfileId: userProfile.id,
      hairStylistId: staffId.current,
      optionBookingMappingId: checked,
      dateTime: dateTimeRef.current.value ? dateTimeRef.current.value : null,
      startTime: slotChecked ? slotChecked : null,
      endTime: endTime,
      status: 'booked',
      totalDuration: totalTime,
      totalPrice: finalPrice,
      voucherOptionId: null,
      voucherUserId: parseInt(voucherChecked) ? parseInt(voucherChecked) : null,
      note: noteRef.current.value ? noteRef.current.value : null,
      phoneNumer: phoneNumberRef.current.value ? phoneNumberRef.current.value : null
    };
    axios
      .post(SERVER_API_URL + "api/user-create-booking", data)
      .then((result) => {
        if (result.status === 200) {
          context.setDate(new Date());
          bookingOptionContext.setBookingOption();
          routingHistory.replace("/");
        } else {
          changeStaff(staffId.current);
          uncheckedSelectTime()

        }
      })
      .catch((err) => { });
  }

  const handleCheck = (categoryIdOfOption, serviceId, id, optionName, price, time) => {

    console.log(categoryIdOfOption, serviceId, id, optionName, price, time)
    if (!categoryId) {
      setCategoryId(categoryIdOfOption)

    } else {
      if (categoryId !== categoryIdOfOption) {

        console.log(categoryId !== categoryIdOfOption)
        setCategoryId(categoryIdOfOption)
        setChecked([]);
        setCheckedName([]);
        setTotalTime(0);
        setTotalPrice(0);
        setFinalPrice(0);

      }

    }

    if (!lstServiceId.includes(serviceId)) {
      setLstServiceId(prev => [...prev, serviceId])

      setChecked((prev) => [...prev, id]);

      setCheckedName((prev) => [...prev, optionName]);

      setTotalPrice(prev => prev + price)

      setFinalPrice(prev => prev + price)

      setTotalTime(prev => prev + time)

    } else {
      if (checked.includes(id)) {
        setLstServiceId(lstServiceId.filter((item) => item != serviceId));
      }

      setChecked((prev) => {
        if (checked.includes(id)) {
          return checked.filter((item) => item != id);
        } else {
          let optionId = [...prev].find(item =>
            lstOptionIdOfServiceSelected.includes(item)
          )
          if (optionId) {
            return [...prev.filter((item) => item != optionId), id];
          } else {

            return [...prev.slice(0, -1), id];
          }
        }
      });

      setCheckedName((prev) => {
        if (checked.includes(id)) {
          return checkedName.filter((item) => item != optionName);
        } else {
          let optionId = checked.find(item =>
            lstOptionIdOfServiceSelected.includes(item)
          )
          if (optionId) {
            let option = options.find(item => item.id === optionId)
            if (option) {
              return [...prev.filter((item) => item != option.optionName), optionName]
            }
          } else {
            return [...prev.slice(0, -1), optionName];

          }

        }
      });

      setTotalPrice(prev => {
        if (checked.includes(id)) {
          return prev - price
        } else {
          let optionId = checked.find(item =>
            lstOptionIdOfServiceSelected.includes(item)
          )
          if (optionId) {
            let option = options.find(item => item.id === optionId)
            if (option) {
              return prev - option.price + price
            }
          } else {
            return prev + price
          }
        }
      })

      setFinalPrice(prev => {
        if (checked.includes(id)) {
          return prev - price
        } else {
          let optionId = checked.find(item =>
            lstOptionIdOfServiceSelected.includes(item)
          )
          if (optionId) {
            let option = options.find(item => item.id === optionId)
            if (option) {
              return prev - option.price + price
            }
          } else {
            return prev + price
          }
        }
      })

      setTotalTime(prev => {
        if (checked.includes(id)) {
          return prev - time
        } else {
          let optionId = checked.find(item =>
            lstOptionIdOfServiceSelected.includes(item)
          )
          if (optionId) {
            let option = options.find(item => item.id === optionId)
            if (option) {
              return prev - option.duration + time
            }
          } else {
            return prev + time
          }
        }
      })
    }



  }

  console.log(lstServiceId, lstOptionIdOfServiceSelected, checked)

  function handleVoucherCheck(id, discount) {
    setVoucherChecked(() => {
      if (voucherChecked.includes(id)) {
        return []
      } else {
        return id
      }
    })
    setDiscount((prev) => {
      if (voucherChecked.includes(id)) {
        return prev - discount
      } else {
        return discount
      }
    })
    setFinalPrice((prev) => {
      if (voucherChecked.includes(id)) {
        return prev + discount
      } else {
        return totalPrice - discount
      }
    })
  }

  function handleSelectCategory(id) {
    handleDeleteBoderCategory();
    const categorySelect = document.getElementById('category' + id);
    categorySelect.classList.add('category-selected');
    // categorySelectedRef.current = 'category' + id;

  }

  function handleDeleteBoderCategory() {
    // if (categorySelectedRef.current) {
    //   if (document.getElementById(categorySelectedRef.current).classList.contains('category-selected')) {
    //     document.getElementById(categorySelectedRef.current).classList.remove("category-selected");
    //   }
    // }
    let lstElementCategory = document.getElementsByClassName('category-select');
    Array.from(lstElementCategory).forEach(item => {
      if (item.classList.contains('category-selected')) {
        item.classList.remove('category-selected');
      }
    })
  }
  function handleSelectService(id) {
    handleDeleteBoderService();
    const serviceSelect = document.getElementById('service' + id);
    serviceSelect.classList.add('service-selected');
    serviceSelectedRef.current = 'service' + id;

  }

  function handleDeleteBoderService() {
    // if (serviceSelectedRef.current) {
    //   if (document.getElementById(serviceSelectedRef.current).classList.contains('service-selected')) {
    //     document.getElementById(serviceSelectedRef.current).classList.remove("service-selected");
    //   }
    // }
    let lstElementCategory = document.getElementsByClassName('service-select');
    Array.from(lstElementCategory).forEach(item => {
      if (item.classList.contains('service-selected')) {
        item.classList.remove('service-selected');
      }
    })
  }

  function handleSelectOption(id) {
    if (lstOptionIdOfServiceSelected.includes(id)) {

      if (checked.includes(id)) {
        console.log('1');
        const optionSelect = document.getElementById('option' + id);
        if (optionSelect.classList.contains('option-selected')) {
          optionSelect.classList.remove('option-selected');
        } else {
          optionSelect.classList.add('option-selected');
          handleDeleteBoderOption();
          optionSelectedRef.current = 'option' + id;
        }
      }
    }



  }

  function handleDeleteBoderOption() {
    console.log(optionSelectedRef.current)
    if (optionSelectedRef.current) {
      if (document.getElementById(optionSelectedRef.current).classList.contains('option-selected')) {
        console.log('3')
        document.getElementById(optionSelectedRef.current).classList.remove("option-selected");
      }
    }
  }

  function handleSaveName() {
    let stylist = staff.find(item => item.id == staffId.current)
    if (stylist) {
      document.getElementById('getStaffName').value = stylist.stylistName;

    } else {
      document.getElementById('getStaffName').value = '';
    }
  }

  useEffect(() => {
    if (items) {
      items.forEach(item => setTimeSlotDisable(prev => {
        return (prev.concat(createTimeSot(item.startTime, item.endTime, 30)));
      }))
    }

    return () => {
      setTimeSlotDisable([]);
    }
  }, [items])

  function createTimeSot(fromTime, toTime, timeDuration) {
    let startTime = moment(fromTime, "HH:mm");
    let endTime = moment(toTime, "HH:mm");
    let arr = [];
    while (startTime < endTime) {
      arr.push(new moment(startTime).format("HH:mm"));
      startTime.add(timeDuration, "minutes");
    }
    return arr;
  }

  function changeStaff(id) {

    if (id != -1) {
      staffId.current = id;
      axios
        .get(SERVER_API_URL + `api/user-get-stylist-schedule-booking-by-stylist-id/${id}/${dateTimeRef.current.value}`)
        .then((result) => {
          if (result.status === 200) {
            setItems(result.data);
          }
          // TODO Re-call get-all-list-shift API
          getShift();
        })
        .catch((err) => {
        });
    } else {
      staffId.current = -1
      setLstTimeSlot([]);
    }



  };
  // useEffect(() => {
  function getShift() {
    axios
      .get(SERVER_API_URL + "api/get-all-list-shift")
      .then((result) => {
        endTimeDayRef.current = result.data[0].endTime;
        durationRef.current = result.data[0].duration;
        setLstTimeSlot(
          createTimeSot(
            result.data[0].startTime,
            result.data[0].endTime,
            result.data[0].duration
          )
        );
      })
      .catch((err) => { });
  }
  // }, []);
  function handleCalEndTime(slotChecked) {
    let startTime = moment(slotChecked, 'HH:mm');
    let moment12h = moment('12:00', 'HH:mm');
    let endTimeMoment = moment(slotChecked, 'HH:mm').add(totalTime, 'minutes');
    let tempMinute = 0;
    let endTime = moment(endTimeMoment, 'HH:mm');
    let temp = moment(endTimeMoment, 'HH:mm').get('minutes');
    let endTimeDayTemp = moment(endTimeDayRef.current, 'HH:mm');

    if (temp > 0 && temp <= 30) {
      tempMinute = 30 - temp;
      endTime = moment(endTimeMoment, 'HH:mm').add(tempMinute, 'minutes').format('HH:mm');
    } else if (temp == 0) {
      endTime = new moment(endTimeMoment).format('HH:mm')
    } else {
      tempMinute = 60 - temp;
      endTime = moment(endTimeMoment, 'HH:mm').add(tempMinute, 'minutes').format('HH:mm');
    }

    return endTime;
  }

  function handlecheckTimeSlot(slotChecked) {
    let startTime = moment(slotChecked, 'HH:mm').format('HH:mm');
    let endtime = handleCalEndTime(slotChecked);
    let endTimeDay = moment(endTimeDayRef.current, 'HH:mm').format('HH:mm');
    let startLunchBreak = moment('12:00', 'HH:mm').format('HH:mm');
    // endtime = moment(endtime, 'HH:mm').add(durationRef.current, 'minutes').format('HH:mm');
    console.log(endtime,endTimeDay)
    if (timeSlotDisable.includes(endtime) || endtime > endTimeDay || startTime < startLunchBreak && endtime > startLunchBreak) {
      console.log(endtime,endTimeDay)
      alert("Duration is not enough, please choose another time !!!");
      var radio = document.querySelector('input[type=radio][name=timeSlots]:checked');
      radio.checked = false;
    }

  }
  function uncheckedSelectTime() {
    var radio = document.querySelector('input[type=radio][name=timeSlots]:checked');
    if (radio) {
      radio.checked = false;
    }
  }

  Validator({
    form: '#form-1',
    formGroupSelector: '.form-group',
    errorSelector: '.form-message',
    rules: [
      Validator.isRequired('#firstname'),
      Validator.isRequired('#lastname'),
      Validator.isRequired('#phone-number'),
      Validator.isPhoneNumber('#phone-number'),
      Validator.isRequired('#date-time')

    ],
    onSubmit: function () {
      handleSubmit();
    }
  });


  return (
    <div className="container">
      <div className="col w-75">
        <h1>Booking</h1>
        <h6>To reserve seats please complete and submit the booking form.</h6>
      </div>
      <hr
        style={{
          marginTop: "1rem",
          marginBottom: "1rem",
          border: "0",
          borderTop: "1px solid rgba(0, 0, 0, 0.1)",
        }}
      />
      <form className="form" id="form-1">
        <div className="row">
          <div className="col-lg-6 col-md-6 col-xs-12">
            <div className="mb-2 form-group">
              <label htmlFor="">
                Full Name
              </label>
              <input
                id="firstname"
                type="text"
                placeholder="First Name"
                className="form-control"
                name="firstName"
                ref={firstNameRef}
                defaultValue={userProfile.firstName}
                maxLength={255}
                readOnly
              />
              <div className="form-message">

              </div>
            </div>
            <div className="mb-2 form-group">
              <label></label>
              <input
                id="lastname"
                type="text"
                className="form-control"
                placeholder="Last Name"
                ref={lastNameRef}
                defaultValue={userProfile.lastName}
                maxLength={255}
                readOnly
              />
              <div className="form-message">
              </div>
            </div>
            <div className="mb-2">
              <label>Email</label>
              <input
                placeholder="example@gmail.com"
                type="text"
                className="form-control"
                defaultValue={userProfile.email}
                readOnly
                maxLength={255}
              />
            </div>
            <div className="mb-2 form-group">
              <label  >Phone Number</label>
              <input id="phone-number" type="text" className="form-control" ref={phoneNumberRef} defaultValue={userProfile.phoneNumber || ''} />
              <div className="form-message">
              </div>
            </div>
            <div className="mb-2 form-group">
              <label>Date/Time</label>
              <input
                id="date-time"
                className='form-control'
                type="date"
                name="birthday"
                ref={dateTimeRef}
                defaultValue={new Date(new Date().getTime() - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0, 10)}
                min={new Date(new Date().getTime() - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0, 10)}
                onChange={() => changeStaff(staffId.current)}
                required
              />
              <div className="form-message">
              </div>
            </div>
            <div className="mb-2 form-group">
              <label>Choose option</label>
              <div className="input-group" style={{ zIndex: '0' }}>
                <div className="input-group-prepend">
                  <button
                    style={{ backgroundColor: "#fe5a3a", color: 'white' }}
                    className="btn btn-outline-secondary"
                    type="button"
                    data-bs-toggle="modal"
                    data-bs-target="#exampleModal"
                  >
                    Choose
                  </button>
                </div>
                <input
                  type="text"
                  id="getOption"
                  className="form-control"
                  placeholder=""
                  aria-label=""
                  aria-describedby="basic-addon1"
                  value={checkedName || ''}
                  onChange={e => setCheckedName(e.target.value)}
                  readOnly
                />
                <div className="form-message">
                </div>
              </div>
            </div>
            <div className="mb-2 form-group">
              <label>Choose stylist</label>
              <div className="input-group mb-3" style={{ zIndex: '0' }}>
                <div className="input-group-prepend">
                  <button
                    style={{ backgroundColor: "#fe5a3a", color: 'white' }}
                    className="btn btn-outline-secondary"
                    type="button"
                    data-bs-toggle="modal"
                    data-bs-target="#staticBackdrop"
                    disabled={checked.length == 0}
                  >
                    Choose
                  </button>
                </div>
                <input
                  id="getStaffName"
                  type="text"
                  className="form-control"
                  placeholder=""
                  aria-label=""
                  aria-describedby="basic-addon1"
                  readOnly

                />
                <div className="form-message">
                </div>
              </div>
            </div>
            <div className="mb-2">
              <label>Additional Message</label>
              <textarea
                ref={noteRef}
                className="form-control"
                id="exampleFormControlTextarea1"
                rows="3"

              ></textarea>
            </div>
          </div>
          <div className="col-lg-6 col-md-6 col-xs-12">

            <div className="mb-2">
              <label>Total Time</label>
              <input
                type="text"
                className="form-control"
                value={time_convert(totalTime)}
                readOnly
              />
            </div>
            <div className="mb-2 form-group">
              <label>Time slot</label>
              <input
                type="text"
                className="form-control"
                value={slotChecked}
                readOnly

              />
              <div className="form-message">
              </div>
            </div>
            <div className="mb-2">
              <label>Total Service Price </label>
              <input
                type="text"
                className="form-control"
                value={totalPrice.toLocaleString('vi', { style: 'currency', currency: 'VND' })}
                readOnly
              />
            </div>
            <div className="mb-2 form-group">
              <label>Voucher Discount</label>
              <div className="input-group" style={{ zIndex: '0' }}>
                <div className="input-group-prepend">
                  <button
                    style={{ backgroundColor: "#fe5a3a", color: 'white' }}
                    className="btn btn-outline-secondary"
                    type="button"
                    data-bs-toggle="modal"
                    data-bs-target="#voucherModal"
                    disabled={checked.length == 0}
                  >
                    Choose
                  </button>
                </div>
                <input
                  type="text"
                  className="form-control"
                  placeholder=""
                  aria-label=""
                  aria-describedby="basic-addon1"
                  value={discount ? discount.toLocaleString('vi', { style: 'currency', currency: 'VND' }) : ''}
                  onChange={e => setDiscount(e.target.value)}
                  readOnly
                />
                <div className="form-message">
                </div>
              </div>
            </div>
            <div className="mb-2">
              <label>Total Price</label>
              <input
                type="text"
                className="form-control"
                value={finalPrice.toLocaleString('vi', { style: 'currency', currency: 'VND' })}
                readOnly
              />
            </div>
            <div className="mb-2">
              <label className="fw-bold fst-italic">NOTES:</label>
              <div className="form-control h-100">
                <p>
                  1. Please fill in all required information.
                  <br />
                  2. Please check the information carefully before booking.
                  <br />
                  3. Enter your correct phone number so we can contact to you.
                  <br />
                  4. Please arrive at the scheduled time.
                  <br />
                  5. If you arrive more than 10 minutes late, we can reschedule your appointment for a better experience.
                </p>
              </div>
              <div className="form-check  form-group">
                <input name="confirm" className="form-check-input" required type="checkbox" value="" id="flexCheckChecked" />
                <label className="form-check-label" htmlFor="flexCheckChecked">
                  I have read all the notes.
                </label>
                <div className="form-message">
                </div>
              </div>
            </div>

          </div>
          <div className="row">
            <div className="col-12">
              <div className="mt-3 mb-5 ">
                <button
                  style={{ backgroundColor: "#fe5a3a", color: 'white' }}
                  className="btn btn-lg btn-block"
                >
                  BOOK
                </button>
              </div>
            </div>
          </div>
        </div>
      </form>

      {/*--- Service Modal--- */}

      <div
        className="modal fade"
        id="exampleModal"
        tabIndex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog modal-lg modal-dialog-centered">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="staticBackdropLabel">
                Choose service
              </h5>
            </div>
            <div className="modal-body ">

              <div className="row">
                <div id="categories-wrapper" className="category-select-area d-flex">
                  {categories && categories.map(category => (
                    <div key={category.id} id={'category' + category.id} className="category-select col-6" onClick={() => { handleSelectCategory(category.id); handleGetServiceAndOptionByCategory(category.id) }} >
                      <h4>{category.categoryName}</h4>
                    </div>
                  ))}
                </div>
                <hr
                  style={{
                    marginTop: "1rem",
                    marginBottom: "1rem",
                    border: "0",
                    borderTop: "1px solid rgba(0, 0, 0, 0.1)",
                  }}
                />
              </div>
              <div className="row">
                <div id="services-wrapper" className="service-select-area mt-2 p-0 ">
                  <Slider {...settings} >
                    {services && services.map(service => (
                      <div key={service.id} id={'service' + service.id} className="service-select w-75 " onClick={() => { handleSelectService(service.id); handleGetOptionByService(service.id) }}>
                        <h6 className="fs-5 w-100" style={{
                          whiteSpace: "nowrap",
                          overflow: "hidden",
                          textOverflow: "ellipsis",
                        }}>
                          {service.serviceName}
                        </h6>
                      </div>
                    ))}
                  </Slider>
                  <hr
                    style={{
                      marginTop: "1rem",
                      marginBottom: "1rem",
                      border: "0",
                      borderTop: "1px solid rgba(0, 0, 0, 0.1)",
                    }}
                  />
                </div>
              </div>
              <div className="row">
                <div className="option-select-area form-modal">
                  {options && options.map((option, index) => (
                    <div className="form-check text-start" key={index} >
                      <input
                        style={{ cursor: 'pointer' }}
                        className="form-check-input"
                        type="radio"
                        name="flexRadioDefault"
                        id="flexRadioDefault1"
                        value={option.id}
                        checked={checked.includes(option.id)}
                        onClick={() => handleCheck(option.categoryId, option.serviceId, option.id, option.optionName, option.price, option.duration)}
                        onChange={e => e}
                      />
                      <div
                        className="option-content p-2 w-100 mb-3"
                        id={"option" + option.id}
                      // onClick={() => {handleCheck(option.serviceId, option.id, option.optionName, option.price, option.duration)}}
                      >
                        <div>Option Name: {option.optionName}</div>
                        <div>Price: {option.price.toLocaleString('vi', { style: 'currency', currency: 'VND' })}</div>
                        <div>Duration: {option.duration} minutes</div>
                      </div>
                    </div>
                  ))}
                </div>
              </div>


            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-primary"
                data-bs-dismiss="modal"
                style={{ backgroundColor: '#fe5a3a' }}

              >
                Close
              </button>
            </div>
          </div>
        </div>
      </div>

      {/* ---Stylist/Time slot Modal--- */}

      <div className="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="staticBackdropLabel1" aria-hidden="true">
        <div className="modal-dialog modal-dialog-centered" >
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="staticBackdropLabel1">Choose stylist/Time slot</h5>
              <select
                className="form-select w-50"
                aria-label="Default select example"
                onChange={(e) => { changeStaff(e.target.value); uncheckedSelectTime() }}
                defaultValue={staffId.current}
              >
                <option value="-1">Choose stylist</option>
                {staff && staff.map((item, index) => (
                  <option key={index} value={item.id}>
                    {item.stylistName}
                  </option>
                ))}
              </select>
            </div>
            <div className="modal-body">
              <div className="d-flex flex-wrap">
                {listTimeSlot && listTimeSlot.map((item, index) => (
                  <div key={index} className="form-check">
                    <input
                      className="form-check-input me-1"
                      type="radio"
                      name="timeSlots"
                      id="exampleRadios1"
                      value={item}
                      disabled={timeSlotDisable.includes(item) || disabledTime.includes(item) || new Date(new Date().toISOString().slice(0, 10) + ' ' + passedTime) > new Date(dateTimeRef.current.value + ' ' + item)}
                      onChange={e => { handlecheckTimeSlot(e.target.value); setSlotChecked(item) }}
                    />
                    <label
                      className="form-check-label me-1"
                      htmlFor="exampleRadios1"
                    >
                      {item}
                    </label>
                  </div>
                ))}
              </div>
            </div>
            <div className="modal-footer">
              <button type="button" onClick={handleSaveName} data-bs-dismiss="modal" style={{ backgroundColor: '#fe5a3a' }} className="btn btn-primary">Close</button>
            </div>
          </div>
        </div>
      </div>

      {/* ----Voucher Modal---- */}

      <div className="modal fade" id="voucherModal" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="voucherModalLabel1" aria-hidden="true">
        <div className="modal-dialog modal-dialog-centered" >
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="voucherModalLabel1">Choose Voucher</h5>
            </div>
            <div className="modal-body form-modal ">
              {vouchers && vouchers.map((voucher, index) => (
                <div className="form-check text-start d-flex align-items-center " key={index}>
                  <input
                    className="form-check-input me-2 flex-shrink-1 "
                    type="radio"
                    value={voucher.id}
                    id="defaultCheck1"
                    checked={voucherChecked.includes(voucher.id)}
                    onClick={e => handleVoucherCheck(e.target.value, voucher.discount)}
                    onChange={e => e}
                  />
                  <div className="voucher-content p-2 w-100" style={{ border: '1px solid #d2d2d2' }} >
                    <div>Voucher Name: {voucher.voucherUserName}</div>
                    <div>Discount: {voucher.discount.toLocaleString('vi', { style: 'currency', currency: 'VND' })}</div>
                    <div>Start Time: {new Date(voucher.dayStart).toLocaleString('vi-vn').slice(10)}</div>
                    <div>End Time: {new Date(voucher.dayExpire).toLocaleString('vi-vn').slice(10)}</div>
                  </div>
                </div>
              ))}
            </div>
            <div className="modal-footer">
              <button type="button" onClick={handleSaveName} data-bs-dismiss="modal" style={{ backgroundColor: '#fe5a3a' }} className="btn btn-primary">Close</button>
            </div>
          </div>
        </div>
      </div>

      {/* ----Exit Booking Modal --- */}

      {bookingExistContext.booking && <div className='modal-container'>
        <div className='modal-overlay' />
        <div className='modal-wrapper'>
          <div className='modal-header'>
            <h5 className='mb-0'>Booking status</h5>
          </div>
          <div className='modal-body'>

            <p className='fs-5 text'>
              You already have an appointment, you can not create more appointments
            </p>
          </div>
          <div className='modal-control mb-4'>
            <button type='button' onClick={() => routingHistory.replace("/")} className='btn mx-4 w-10' style={{ width: "5rem", backgroundColor: "#fe5a3a", color: 'white' }} >Ok</button>
          </div>
        </div>
      </div>}
    </div>
  );

}

export default Booking;
