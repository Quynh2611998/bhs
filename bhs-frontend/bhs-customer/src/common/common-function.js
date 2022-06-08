export function getAuthToken() {
  const cookies = document.cookie.split(";");
  for (var i = 0; i < cookies.length; i++) {
    if (cookies[i].includes("JWT-TOKEN")) {
      const token = cookies[i].split("=")[1];
      if (token.length > 0) {
        return token;
      }
    }
  }
  return null;
}

export function setAuthenCookie(data) {
  document.cookie = "JWT-TOKEN=" + data.token + ";path =/ ";
  document.cookie = "BHS-AUTHORITY=" + data.role + ";path =/ ";
  console.log(data);
}

// export function setUserNameCookie(data) {
//   document.cookie = "JWT-TOKEN=" + data.token + ";path =/ ";
//   document.cookie = "BHS-AUTHORITY=" + data.role + ";path =/ ";
// }

export function removeAuthenCookie() {
  document.cookie = "JWT-TOKEN=;path =/ ";
  document.cookie = "BHS-AUTHORITY=;path =/ ";
}

export function getNonAuthenticationApi() {
  let lstApi = [];
  lstApi.push("/api/authenticate");
  lstApi.push("/api/register");
  lstApi.push("/api/ad-user/activate");
  lstApi.push("/api/search-option-by-keyword");
  lstApi.push("/api/view-option-at-home-page");
  lstApi.push('/api/ad-user/reset-password/init');
  lstApi.push("/api/ad-user/reset-password/finish");
  lstApi.push("/api/get-object-home-page");
  lstApi.push("/api/get-detail-option-by-id/{id}");
  lstApi.push("/api/get-images-by-option-id/{id}");

  return lstApi;
}

export function readCurrentUrlSegment() {
  const fullpath = window.location.pathname;
  return fullpath;
}

export function formatDateAsStringDDMMYYYY(date) {
  const parseDate = new Date(date);
  const year = parseDate.getFullYear();
  const month =
    parseDate.getMonth() + 1 < 10
      ? "0" + (parseDate.getMonth() + 1)
      : parseDate.getMonth() + 1;
  const day =
    parseDate.getDate() < 10 ? "0" + parseDate.getDate() : parseDate.getDate();
  return year + "-" + month + "-" + day;
}

export function formatDateTimeAsStringWithMilisec(date) {
  const parseDate = new Date(date);
  const yyyy = parseDate.getFullYear();
  const mm =
    parseDate.getMonth() + 1 < 10
      ? "0" + (parseDate.getMonth() + 1)
      : parseDate.getMonth() + 1;
  const dd =
    parseDate.getDate() < 10 ? "0" + parseDate.getDate() : parseDate.getDate();
  const hh =
    parseDate.getHours() < 10
      ? "0" + parseDate.getHours()
      : parseDate.getHours();
  const mms =
    parseDate.getMinutes() < 10
      ? "0" + parseDate.getMinutes()
      : parseDate.getMinutes();
  const ss =
    parseDate.getSeconds() < 10
      ? "0" + parseDate.getSeconds()
      : parseDate.getSeconds();
  return yyyy + "-" + mm + "-" + dd + " " + hh + ":" + mms + ":" + ss;
}

export function convertImageToBase64(file) {
  return new Promise((resolve, reject) => {
    const fileReader = new FileReader();
    fileReader.readAsDataURL(file);
    fileReader.onload = () => {
      resolve(fileReader.result);
    };
    fileReader.onerror = (error) => {
      reject(error);
    };
  });
}
