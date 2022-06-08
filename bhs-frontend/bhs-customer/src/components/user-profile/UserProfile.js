import axios from "axios";
import { useEffect, useRef } from "react";
import { defaultUserImage, SERVER_API_URL } from "../../common/common-constant";
import {
  convertImageToBase64,
  formatDateAsStringDDMMYYYY,
} from "../../common/common-function";
import { toast } from "react-toastify";
import { Validator } from "../../common/validator";
function UserProfile() {
  const idRef = useRef();
  const firstNameRef = useRef();
  const lastNameRef = useRef();
  const phoneNumberRef = useRef();
  const emailRef = useRef();
  const dobRef = useRef();
  const imageSrcRef = useRef();
  let id = null;
  let selectedGender = "";
  let imagePreview = defaultUserImage;

  const oldPasswordRef = useRef();
  const newPasswordRef = useRef();
  const rePasswordRef = useRef();

  function handleRadioChange(event) {
    selectedGender = event.target.value;
  }

  function getSelectedGender(genderValue) {
    selectedGender = genderValue;
    var e = document.getElementById(genderValue);
    if (e) {
      e.checked = true;
    }
  }

  async function handleFileRead(event) {
    const file = event.target.files[0];
    const preview = document.getElementById("preview");
    if (file) {
      imagePreview = await convertImageToBase64(file);
      preview.src = imagePreview;
    } else {
      preview.src = defaultUserImage;
    }
  }

  function saveUserProfile() {
    const data = {
      id: id,
      firstName: firstNameRef.current.value,
      lastName: lastNameRef.current.value,
      phoneNumber: phoneNumberRef.current.value,
      gender: selectedGender,
      dateOfBirth: new Date(dobRef.current.value),
      profileImage: imagePreview,
    };
    axios
      .put(SERVER_API_URL + "api/user-profile/update-user-profile", data)
      .then((res) => {
        loadData();
      });
  }

  function changePassword() {
    const data = {
      oldPassword: oldPasswordRef.current.value,
      newPassword: newPasswordRef.current.value,
      rePassword: rePasswordRef.current.value,
    };
    axios.post(SERVER_API_URL + "api/change-password", data).then((res) => {});
  }

  useEffect(() => {
    loadData();
  }, []);

  function loadData() {
    axios
      .get(SERVER_API_URL + "api/user-profile/get-user-profile")
      .then((res) => {
        id = res.data.id;
        dobRef.current.value = formatDateAsStringDDMMYYYY(res.data.dateOfBirth);
        imageSrcRef.current.src = res.data.profileImage;
        firstNameRef.current.value = res.data.firstName;
        lastNameRef.current.value = res.data.lastName;
        phoneNumberRef.current.value = res.data.phoneNumber;
        emailRef.current.value = res.data.email;
        getSelectedGender(res.data.gender);
      });
  }

  useEffect(() => {
    Validator({
      form: "#form-user-profile",
      formGroupSelector: ".form-group",
      errorSelector: ".form-message",
      rules: [
        Validator.isRequired("#firstname"),
        Validator.isRequired("#lastname"),
        Validator.isRequired("#phone-number"),
        Validator.isPhoneNumber("#phone-number"),
        Validator.isRequired("#date-time"),
      ],
      onSubmit: function () {
        saveUserProfile();
      },
    });

    Validator({
      form: "#form-password",
      formGroupSelector: ".form-group",
      errorSelector: ".form-message",
      rules: [
        Validator.isRequired("#old-password"),
        Validator.isRequired("#password"),
        Validator.isRequired("#re-password"),
        Validator.isConfirmed("#re-password", function () {
          return document.querySelector("#form-password #password").value;
        }),
      ],
      onSubmit: function () {
        changePassword();
      },
    });
  }, []);

  return (
    <div className="col-lg-12 col-md-12 col-sm-12 col-xs-12">
      <br />
      <div className="row">
        <div className="col-lg-3 col-md-3 col-sm-12 col-xs-12">
          <br />
          <br />
          <div className="row">
            <div className="text-center">
              <img
                ref={imageSrcRef}
                className="rounded mx-auto d-block"
                alt="..."
                style={{ width: "250px", height: "250px" }}
              />
            </div>
          </div>
        </div>
        <div className="col-lg-9 col-md-9 col-sm-12 col-xs-12">
          <div className="row">
            <div className="col-8">
              <form id="form-user-profile">
                <label>Profile:</label>
                <div
                  style={{
                    border: "thin solid black",
                    borderRadius: "5px 5px 5px 5px",
                    padding: "15px",
                  }}
                >
                  <br />

                  <div className="row">
                    <div className="col-12">
                      <label>Username:</label>
                      <input ref={emailRef} className="form-control" readOnly />
                      <input ref={idRef} hidden />
                    </div>
                  </div>
                  <br />
                  <div className="row">
                    <div className="col-6 ">
                      <div className="form-group">
                        <label>First name:</label>
                        <input
                          id="firstname"
                          ref={firstNameRef}
                          maxLength={255}
                          type="text"
                          className="form-control"
                        />
                        <div className="form-message"></div>
                      </div>
                    </div>
                    <div className="col-6  ">
                      <div className="form-group">
                        <label>Last name:</label>
                        <input
                          id="lastname"
                          ref={lastNameRef}
                          maxLength={255}
                          type="text"
                          className="form-control"
                        />
                        <div className="form-message"></div>
                      </div>
                    </div>
                  </div>
                  <br />
                  <div className="row">
                    <div className="col-12">
                      <div className="form-group">
                        <label>Phone number:</label>
                        <input
                          id="phone-number"
                          ref={phoneNumberRef}
                          type="text"
                          className="form-control"
                        />
                        <div className="form-message"></div>
                      </div>
                    </div>
                  </div>
                  <br />
                  <div className="row">
                    <label>Gender:</label>
                    <div className="col-4">
                      <input
                        name="gender"
                        type="radio"
                        id="M"
                        value="M"
                        onChange={handleRadioChange}
                      />{" "}
                      Male
                    </div>
                    <div className="col-4">
                      <input
                        name="gender"
                        type="radio"
                        id="F"
                        value="F"
                        onChange={handleRadioChange}
                      />{" "}
                      Female
                    </div>
                    <div className="col-4">
                      <input
                        name="gender"
                        type="radio"
                        id="O"
                        value="O"
                        onChange={handleRadioChange}
                      />{" "}
                      Other
                    </div>
                  </div>
                  <br />
                  <div className="row">
                    <div className="col-12">
                      <div className="form-group">
                        <label>Date of birth:</label>
                        <input
                          id="date-time"
                          ref={dobRef}
                          className="form-control"
                          type="date"
                        />
                        <div className="form-message"></div>
                      </div>
                    </div>
                  </div>
                  <br />
                  <div className="row justify-content-center">
                    <div className="col-6">
                      <button className="btn btn-primary form-control">
                        Update
                      </button>
                    </div>
                  </div>
                </div>
              </form>
              <br />
              <form id="form-password">
                <label>Change password:</label>
                <div
                  style={{
                    border: "thin solid black",
                    borderRadius: "5px 5px 5px 5px",
                    padding: "15px",
                  }}
                >
                  <div className="row">
                    <div className="col-12">
                      <div className="form-group">
                        <label>Old Password:</label>
                        <input
                          id="old-password"
                          ref={oldPasswordRef}
                          maxLength={255}
                          className="form-control"
                          type="password"
                        />
                        <div className="form-message"></div>
                      </div>
                    </div>
                  </div>
                  <br />
                  <div className="row">
                    <div className="col-12">
                      <div className="form-group">
                        <label>New Password:</label>
                        <input
                          id="password"
                          ref={newPasswordRef}
                          maxLength={255}
                          className="form-control"
                          type="password"
                        />
                        <div className="form-message"></div>
                      </div>
                    </div>
                  </div>
                  <br />
                  <div className="row">
                    <div className="col-12">
                      <div className="form-group">
                        <label>Re-Enter New Password:</label>
                        <input
                          id="re-password"
                          ref={rePasswordRef}
                          maxLength={255}
                          className="form-control"
                          type="password"
                        />
                        <div className="form-message"></div>
                      </div>
                    </div>
                  </div>
                  <br />
                  <div className="row justify-content-center">
                    <div className="col-6">
                      <button className="btn btn-primary form-control">
                        Change Password
                      </button>
                    </div>
                  </div>
                </div>
              </form>
            </div>
            <div className="col-4">
              <div className="row">
                <div className="text-center">
                  <br />
                  <br />
                  <img
                    id="preview"
                    src={imagePreview}
                    className="rounded mx-auto d-block"
                    alt="..."
                    style={{ width: "200px", height: "200px" }}
                  />
                  <br />
                  <br />
                  <div className="row justify-content-center">
                    <div className="col-8">
                      <input
                        id="imageInput"
                        type="file"
                        className="form-control"
                        accept="image/*"
                        onChange={handleFileRead}
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default UserProfile;
