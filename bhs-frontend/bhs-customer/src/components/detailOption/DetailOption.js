import React, { useEffect, useRef, useState, useContext } from "react";
import { useParams } from "react-router-dom";
import { imgItemDefault, SERVER_API_URL } from "../../common/common-constant";
import { Link } from "react-router-dom";
import axios from "axios";
import Slider from "react-slick";
import { BookingOptionContext } from "../context/BookingOptionContext";

function DetailOption({setIsNotLoginPage}) {
  const { option_id } = useParams();
  const [images, setImages] = useState([]);
  const firstImageRef = useRef();
  const [detailOption, setDetailOption] = useState([]);
  const itemImg = useRef();
  const bookingOptionContext = useContext(BookingOptionContext);

  console.log(option_id);
  useEffect(() => {
    setIsNotLoginPage();
  }, );
  useEffect(() => {
    axios
      .get(SERVER_API_URL + `api/get-images-by-option-id/${option_id}`)
      .then((result) => {
        if (result.status === 200) {
          firstImageRef.current = result.data[0].srcImage;
          setImages(result.data);
        }
      })
      .catch((err) => {});
  }, [option_id]);

  useEffect(() => {
    window.scrollTo(0, 0);
    console.log("call API");
    axios
      .get(SERVER_API_URL + `api/get-detail-option-by-id/${option_id}`)
      .then((result) => {
        if (result.status === 200) {
          setDetailOption(result.data[0]);
        }
      })
      .catch((err) => {});
  }, [option_id]);

  let settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 1,
  };
  function handleChangeImage(e) {
    document.getElementById("detail-option-big-image").src = e.target.src;
    e.target.classList.add("click-item");
    itemImg.current = e.target;
  }
  function handleDeleteBoder() {
    if (itemImg.current) {
      itemImg.current.classList.remove("click-item");
    }
  }
  return (
    <div className="container w-75 px-0 py-5">
      <div className="detail-option-content bg-white row">
        <div className="col-5 p-3">
          <div className=" wrapper-img mb-3">
            <img
              id="detail-option-big-image"
              className="img-item"
              src={firstImageRef.current || imgItemDefault}
            ></img>
          </div>
          <div>
            <Slider {...settings}>
              {images &&
                images.map((image, index) => (
                  <div className=" card card-item wrapper-img " key={index}>
                    <img
                      src={image.srcImage}
                      onClick={(e) => {
                        handleDeleteBoder();
                        handleChangeImage(e);
                      }}
                      className="card-img-top img-item img-slider-detail "
                      alt={image.altImage}
                    />
                  </div>
                ))}
            </Slider>
          </div>
        </div>
        <div className="col-7 p-3 border-start">
          <h1>{detailOption.optionName}</h1>
          <h5 style={{ fontSize: "20px" }}>
            Service: {detailOption.serviceName}
          </h5>
          <div className="row">
            <div style={{ fontSize: "25px" }} className="col">
              <h5 style={{ color: "#afaaaa", fontSize: "20px" }}>
                Duration: {detailOption.duration} minutes
              </h5>
            </div>
            <div className="row">
              <div style={{ fontSize: "25px" }} className="col">
                <h2 style={{ color: "#fc5a31" }}>
                  {detailOption.price
                    ? detailOption.price.toLocaleString("vi", {
                        style: "currency",
                        currency: "VND",
                      })
                    : ""}
                </h2>
              </div>
            </div>
          </div>
          <div className=" mt-3">
            <Link to="/booking">
              <button
                style={{
                  backgroundColor: "#ffc107",
                  borderRadius: "10px",
                  border: "1px solid #ffc109",
                }}
                onClick={() =>
                  bookingOptionContext.setBookingOption(parseInt(option_id))
                }
                className="px-3 fs-4 fw-bold btn-booking "
              >
                Book Now
              </button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export default DetailOption;
