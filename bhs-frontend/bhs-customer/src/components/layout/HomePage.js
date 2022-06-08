import {
  banner1,
  banner2,
  banner3,
  beforeAfter1,
  beforeAfter2,
  beforeAfter3,
  beforeAfter4,
  beforeAfter5,
  beforeAfter6,
  moment1,
  moment2,
  moment3,
  moment4,
  moment5,
  moment6,
  imgItemDefault,
  SERVER_API_URL,
} from "../../common/common-constant";
import { Link, useLocation } from "react-router-dom";
import axios from "axios";
import { useEffect, useRef, useState } from "react";
import ReactPagination from "react-paginate";
import Slider from "react-slick";

function HomePage({ setIsNotLoginPage }) {
  const userIdRef = useRef();
  const [booking, setBooking] = useState([]);
  const [images, setImages] = useState([]);
  const firstImageRef = useRef();
  const { search } = useLocation();
  const searchParams = new URLSearchParams(search);
  const keyword = searchParams.get("keyword");
  const [pageCount, setPageCount] = useState(0);

  useEffect(() => {
    try {
      setIsNotLoginPage();
    } catch (error) {
      console.log(error);
    }
  }, []);
  
  useEffect(() => {
    if (keyword) {
      console.log("searchPage", keyword);
      axios
        .get(SERVER_API_URL + "api/search-option-by-keyword?keyword=" + keyword)
        .then((result) => {
          setOption(result.data);
        })
        .catch((err) => { });
    }
  }, [keyword]);
  let settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 1,
  };
  const [option, setOption] = useState([]);

  useEffect(() => {
    axios
      .get(SERVER_API_URL + "api/user-profile/get-user-profile")
      .then((result) => {
        userIdRef.current = result.data.id;
        axios
          .get(
            SERVER_API_URL + `api/get-booking-by-user-id/${userIdRef.current}`
          )
          .then((result) => {
            if (result.status === 200) {
              setBooking(result.data);
            }
          })
          .catch((err) => {});
      })
      .catch((err) => {});

    return () => {
      setBooking([]);
    };
  }, []);

  useEffect(() => {
    if (keyword) return;
    axios
      .get(SERVER_API_URL + "api/view-option-at-home-page?page=0&size=4")
      .then((result) => {
        setOption(result.data.content);
        setPageCount(result.data.totalPages);
      })
      .catch((err) => { });
  }, [keyword]);

  function handleClick(data) {
    axios
      .get(
        SERVER_API_URL +
        `api/view-option-at-home-page?page=${data.selected}&size=4`
      )
      .then((result) => {
        setOption(result.data.content);
      })
      .catch((err) => { });
  }
  return (
    <>
      <div className="container-fluid p-0">
        <div
          id="carouselExampleCaptions"
          className="carousel slide slider"
          data-bs-ride="carousel"
        >
          <div className="carousel-indicators">
            <button
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to="0"
              className="active"
              aria-current="true"
              aria-label="Slide 1"
            ></button>
            <button
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to="1"
              aria-label="Slide 2"
            ></button>
            <button
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide-to="2"
              aria-label="Slide 3"
            ></button>
          </div>
          <div className="carousel-inner w-100">
            <div className="carousel-item active">
              <img src={banner1} className="d-block w-100" alt="Event" />
              <div className="carousel-caption d-none d-md-block"></div>
            </div>
            <div className="carousel-item">
              <img src={banner3} className="d-block w-100" alt="banner" />
              <div className="carousel-caption d-none d-md-block"></div>
            </div>
            <div className="carousel-item">
              <img src={banner2} className="d-block w-100" alt="..." />
              <div className="carousel-caption d-none d-md-block"></div>
            </div>
          </div>
          <button
            className="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="prev"
          >
            <span
              className="carousel-control-prev-icon"
              aria-hidden="true"
            ></span>
            <span className="visually-hidden">Previous</span>
          </button>
          <button
            className="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="next"
          >
            <span
              className="carousel-control-next-icon"
              aria-hidden="true"
            ></span>
            <span className="visually-hidden">Next</span>
          </button>
          <div className="booking-btn-area d-flex align-items-center ">
            <div className="booking-area-title" style={{ color: "#fff" }}>
              <h4 className="text-uppercase">Easy appointment booking</h4>
              <span className="d-block">Book an appointment quickly</span>
              <span className="d-block">No prepayment required</span>
              <span className="d-block">Free Cancellation</span>
            </div>

            <Link to="/booking">
              <button
                style={{
                  backgroundColor: "#ffc107",
                  borderRadius: "20px",
                  border: "1px solid #ffc109",
                  width: "200px",
                }}
                className="fs-4 ms-4 fw-bold btn-booking "
              >
                Book Now
              </button>
            </Link>
          </div>
        </div>
      </div>
      <div
        className="home-salon-moment w-75 m-auto mt-4 rounded pb-3"
        style={{ backgroundColor: "#fff" }}
      >
        <div className="home-moment-content-title">
          <h2 className="ms-2">Salon moment</h2>
          <p className="ms-2">Top beautiful moments at the salon</p>
          <div className="row mx-2 my-4">
            <Slider {...settings}>
              <div className="card img-hover-zoom wrapper-img">
                <img
                  src={moment1}
                  className="card-img-top img-thumbnail img-item"
                  alt="..."
                />
              </div>
              <div className="card img-hover-zoom wrapper-img">
                <img
                  src={moment5}
                  className="card-img-top img-thumbnail img-item"
                  alt="..."
                />
              </div>
              <div className="card img-hover-zoom wrapper-img">
                <img
                  src={moment3}
                  className="card-img-top img-thumbnail img-item"
                  alt="..."
                />
              </div>
              <div className="card img-hover-zoom wrapper-img">
                <img
                  src={moment4}
                  className="card-img-top img-thumbnail img-item"
                  alt="..."
                />
              </div>
              <div className="card img-hover-zoom wrapper-img">
                <img
                  src={moment2}
                  className="card-img-top img-thumbnail img-item"
                  alt="..."
                />
              </div>
              <div className="card img-hover-zoom wrapper-img">
                <img
                  src={moment6}
                  className="card-img-top img-thumbnail img-item"
                  alt="..."
                />
              </div>
            </Slider>
          </div>
        </div>
      </div>
      <div
        className="home-salon-moment w-75 m-auto mt-4 rounded pb-3 "
        style={{ backgroundColor: "#fff" }}
      >
        <div className="home-before-after-content-title">
          <h2 className="ms-2">Before & After</h2>
          <p className="ms-2">The changes are amazing</p>
          <div className="row mx-2 my-4">
            <Slider {...settings}>
              <div className="card img-hover-zoom">
                <img
                  src={beforeAfter1}
                  className="card-img-top img-thumbnail"
                  alt="..."
                />
              </div>
              <div className="card img-hover-zoom">
                <img
                  src={beforeAfter2}
                  className="card-img-top img-thumbnail"
                  alt="..."
                />
              </div>
              <div className="card img-hover-zoom">
                <img
                  src={beforeAfter3}
                  className="card-img-top img-thumbnail"
                  alt="..."
                />
              </div>
              <div className="card img-hover-zoom">
                <img
                  src={beforeAfter4}
                  className="card-img-top img-thumbnail"
                  alt="..."
                />
              </div>
              <div className="card img-hover-zoom">
                <img
                  src={beforeAfter5}
                  className="card-img-top img-thumbnail"
                  alt="..."
                />
              </div>
              <div className="card img-hover-zoom">
                <img
                  src={beforeAfter6}
                  className="card-img-top img-thumbnail"
                  alt="..."
                />
              </div>
            </Slider>
          </div>
        </div>
      </div>
      <div
        id="options"
        className="home-options-content w-75 m-auto mt-4 rounded pb-3"
        style={{ backgroundColor: "#fff" }}
      >
        <div className="home-options-content-title">
          <h2 className="ms-2">Options</h2>
        </div>
        <div className="row mx-2 my-4">
          {option.map((item) => (
            <div
              key={item.optionId || item.id}
              className="col-lg-3 col-md-6 col-xs-12"
            >
              <Link
                to={`/detail-option/${item.optionId || item.id}`}
                className="text-decoration-none"
                style={{ color: "#000" }}
              >
                <div className="card card-item ">
                  <div className="wrapper-img">
                    <img
                      src={item.src || imgItemDefault}
                      className="card-img-top img-item p-1"
                      style={{ objectFit: "cover" }}
                      alt="..."
                    />
                  </div>
                  <div className="card shadow-sm w-100">
                    <div className="card-body">
                      <h6
                        style={{
                          whiteSpace: "nowrap",
                          overflow: "hidden",
                          textOverflow: "ellipsis",
                        }}
                      >
                        {item.optionName}
                      </h6>
                      <p
                        className="mb-1"
                        style={{ color: "#afaaaa", fontSize: "14px" }}
                      >
                        {" "}
                        {item.duration} minutes
                      </p>
                      <p className="mb-1 fs-4" style={{ color: "#fc5a31" }}>
                        {" "}
                        {item.price.toLocaleString("vi", {
                          style: "currency",
                          currency: "VND",
                        })}
                      </p>
                    </div>
                  </div>
                </div>
              </Link>
            </div>
          ))}
        </div>

        {!keyword && (
          <ReactPagination
            previousLabel={"previous"}
            nextLabel={"next"}
            breakLabel={"..."}
            pageCount={pageCount}
            marginPagesDisplayed={3}
            pageRangeDisplayed={3}
            onPageChange={handleClick}
            containerClassName={"pagination justify-content-center"}
            pageClassName={"page-item"}
            pageLinkClassName={"page-link"}
            previousClassName={"page-item"}
            previousLinkClassName={"page-link"}
            nextClassName={"page-item"}
            nextLinkClassName={"page-link"}
            breakClassName={"page-item"}
            breakLinkClassName={"page-link"}
            activeClassName={"active"}
            backgroundColor={"#6610f2"}
          />
        )}
      </div>
    </>
  );
}

export default HomePage;
