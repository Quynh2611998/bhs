import React from "react";

function Contact() {
  return (
    <div>
      <div style={{ marginLeft: "5%", textAlign: "center" }}>
        <h1>Hãy nói ra suy nghĩ của bạn</h1>
        <h4>Liên lạc với chúng tôi nếu bạn có bất kì câu hỏi nào!.</h4>
        <h5>Cùng chúng tôi thay đổi thế giới</h5>
        <h5>chúng tôi cần bạn!!!</h5>
      </div>
      <hr
        style={{
          marginTop: "1rem",
          marginBottom: "1rem",
          border: "0",
          borderTop: "1px solid rgba(0, 0, 0, 0.1)",
        }}
      />
      <div className="row justify-content-around">
        <div className="col-4">
          <label>
            <h3>Bạn cần sự trợ giúp từ chúng tôi?</h3>
          </label>
        </div>
        <div className="col-4">
          <label>
            <h3>Address</h3>
          </label>
        </div>
      </div>
      <div className="row justify-content-around">
        <div className="col-4">
          <div className="form-floating">
            <textarea
              className="form-control"
              placeholder="Bạn nghĩ gì?"
              style={{ height: "100px" }}
            ></textarea>
          </div>
        </div>
        <div className="col-4">
          <hr
            style={{
              marginTop: "0rem",
              marginBottom: "1rem",
              border: "0",
              borderTop: "1px solid rgba(0, 0, 0, 0.1)",
            }}
          />
          <label>
            <h6>Hotline: 0123456789</h6>
          </label>
        </div>
      </div>
      <div style={{ marginTop: "2%" }} className="row justify-content-around">
        <div className="col-4">
          <button type="button" className="btn btn-success w-100">
            Liên lạc với chúng tôi
          </button>
        </div>
        <div className="col-4">
          <label>
            <h6>
              Lorem Ipsum is simply dummy text of the printing and typesetting
              industry.
            </h6>
          </label>
        </div>
      </div>
      <div style={{ marginTop: "2%" }} className="row justify-content-around">
        <div className="col-4"></div>
        <div className="col-4">
          <label>
            Address:<h6> 102 Ba Đình, Hà Nội.</h6>
          </label>
        </div>
      </div>
      <div style={{ marginTop: "2%" }} className="row justify-content-around">
        <div className="col-4"></div>
        <div className="col-4">
          Email:{" "}
          <label>
            <h6>Salon@gmail.com</h6>
          </label>
          <hr
            style={{
              marginTop: "0rem",
              marginBottom: "1rem",
              border: "0",
              borderTop: "1px solid rgba(0, 0, 0, 0.1)",
            }}
          />
          Phone:{" "}
          <label>
            <h6>0987587443</h6>
          </label>
          <br />
          Fax:{" "}
          <label>
            <h6>0943857699</h6>
          </label>
        </div>
      </div>
      <div style={{ marginTop: "2%" }} className="row justify-content-around">
        <div className="col-4"></div>
        <div className="col-4">
          Map:
          <div>
            <iframe
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.4855345757996!2d105.52487025004558!3d21.013249985938096!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31345b465a4e65fb%3A0xaae6040cfabe8fe!2zVHLGsOG7nW5nIMSQ4bqhaSBI4buNYyBGUFQ!5e0!3m2!1svi!2s!4v1649697138348!5m2!1svi!2s"
              style={{ border: "0", width: "100%", height: "450" }}
              allowFullScreen=""
              loading="lazy"
              referrerPolicy="no-referrer-when-downgrade"
            ></iframe>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Contact;
