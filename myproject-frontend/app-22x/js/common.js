const __restServer = "http://localhost:8888";
const __csrfToken = getCookie("XSRF-TOKEN");
let __jwtToken = localStorage.getItem("JWT-TOKEN");

document.addEventListener("DOMContentLoaded", () => {
  // DOM Tree 를 완성한 후, 렌더링 전에 호출됨
  loadHeader();
  loadFooter();

  if (__jwtToken) {
    getUserInfo();
  }
});

async function loadHeader() { // 페이지 헤더 로딩
  const response = await fetch("/header.html"); // default GET 요청
  const result = await response.text();

  let parser = new DOMParser();
  let doc = parser.parseFromString(result, "text/html");
  document.body.insertBefore(doc.querySelector("#page-header"), document.body.firstChild);

  if (__jwtToken) {
    getUserInfo();
  }
}

async function loadFooter() { // 페이지 푸터 로딩
  const response = await fetch("/footer.html"); // default GET 요청
  const result = await response.text();

  let parser = new DOMParser();
  let doc = parser.parseFromString(result, "text/html");
  document.body.appendChild(doc.querySelector("#page-footer"));
}

async function getUserInfo() { // 페이지 푸터 로딩
  const response = await fetch(`${__restServer}/auth/user-info`, {
    headers: {
      "Authorization": "Bearer " + __jwtToken
    }
  });
  const result = await response.json();
  if (result.status == "success") {
    document.querySelector("#user-name").innerHTML = result.data.name;
    document.querySelector(".logged-out").classList.add("invisible");
    document.querySelector(".logged-in").classList.remove("invisible");
  } else {
    document.querySelector(".logged-in").classList.add("invisible");
    document.querySelector(".logged-out").classList.remove("invisible");
  }
}

function logout() {
  localStorage.removeItem("JWT-TOKEN");
  __jwtToken = null;
  location.href = "/home.html";
}

function getCookie(name) {
  const cookies = document.cookie.split('; ');
  for (const cookie of cookies) {
    const [cookieName, cookieValue] = cookie.split('=');
    if (cookieName === name) {
      return decodeURIComponent(cookieValue);
    }
  }
  return null; // 해당 쿠키가 없으면 null 반환
}
