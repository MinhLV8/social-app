import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { signIn, signUp } from "../../actions/AuthAction";
import logo from "../../assets/icons/care-2387662-1991058.png";
import Loading from "../../components/Loading/Loading";
import "./Auth.css";
const Auth = () => {
  console.log("re-rendering");
  const jsonSex = {
    choices: [
      { text: "Nam", value: 1 },
      { text: "Nữ", value: 2 },
      { text: "Khác", value: -1 },
    ],
  };
  const monthList = [
    "01",
    "02",
    "03",
    "04",
    "05",
    "06",
    "07",
    "08",
    "09",
    "10",
    "11",
    "12",
  ];
  const [minh, setMinh] = useState({
    day: "01",
    month: "01",
    year: "1980",
  });
  const [daysInMonth, setDaysInMonth] = useState([]);
  const [isSignup, setIsSignup] = useState(false);
  const [data, setData] = useState({
    firstName: "",
    sdt: "",
    sex: 0,
    surName: "",
    email: "",
    password: "",
    username: "",
    dateOfBirth: "01/01/1980",
    confirmPass: "",
  });
  const dispatch = useDispatch();
  const loading = useSelector((state) => state.authReducer.loading);
  console.log("loading", loading);
  const [confirmPass, setcomfirmPass] = useState(true);
  const handlerLogin = () => {
    setIsSignup(!isSignup);
    resetForm();
  };
  const handlerChange = (e) => {
    setData({
      ...data,
      [e.target.name]: e.target.value,
      sdt: data.username,
      email: data.username,
    });
  };
  const handelSelectChange = (e) => {
    setMinh({ ...minh, [e.target.name]: e.target.value });
    setData({
      ...data,
      dateOfBirth: minh.day + "/" + minh.month + "/" + minh.year,
    });
  };
  const handlerSubmit = (e) => {
    e.preventDefault();
    if (isSignup) {
      data.password === data.confirmPass
        ? dispatch(signUp(data))
        : setcomfirmPass(false);
    } else {
      dispatch(signIn(data));
    }
  };
  const resetForm = () => {
    setcomfirmPass(true);
    setData({
      firstName: "",
      sdt: "",
      sex: 0,
      surName: "",
      email: "",
      password: "",
      username: "",
      dateOfBirth: "01/01/1980",
      confirmPass: "",
    });
  };

  const handleOnChangeSex = (e) => {
    setData({ ...data, sex: +e.target.value });
  };

  const year = (startYear) => {
    let currentYear = new Date().getFullYear(),
      years = [];
    startYear = startYear || 1980;
    while (startYear <= currentYear) {
      years.push(startYear++);
    }
    return years;
  };

  const getDaysInMonth = (month, year) => {
    month = parseInt(month) - 1;
    year = parseInt(year);
    let date = new Date(Date.UTC(year, month, 1));
    let days = [];
    while (date.getMonth() === month) {
      days.push(date.getDate());
      date.setDate(date.getDate() + 1);
    }
    return days;
  };
  useEffect(() => {
    setDaysInMonth(getDaysInMonth(minh.month, minh.year));
  }, [minh]);
  return (
    <div className="Auth">
      <div className="a-left">
        <img src={logo} alt="logo" />
        <div className="Webname">
          <h1>Unitech Social Media</h1>
          <h6>Khám phá những ý tưởng trên khắp thế giới "động vật".</h6>
        </div>
      </div>
      <div className="a-right">
        <form className="infoForm authForm" onSubmit={handlerSubmit}>
          <h3>{!isSignup ? "Đăng nhập" : "Đăng ký tài khoản"}</h3>
          {isSignup && (
            <div>
              <input
                type="text"
                className="infoInput"
                placeholder="Họ"
                name="firstName"
                required
                onChange={handlerChange}
              />
              <input
                type="text"
                className="infoInput"
                name="surName"
                placeholder="Tên"
                required
                onChange={handlerChange}
              />
            </div>
          )}
          <div>
            <input
              type="text"
              placeholder="Số điện thoại hoặc Email"
              className="infoInput"
              name="username"
              required
              value={data.username}
              onChange={handlerChange}
            />
          </div>
          <div>
            <input
              type="password"
              className="infoInput"
              placeholder="Mật khẩu"
              name="password"
              required
              autoComplete="on"
              value={data.password}
              onChange={handlerChange}
            />
            {isSignup && (
              <input
                type="password"
                className="infoInput"
                name="confirmPass"
                placeholder="Nhập lại mật khẩu"
                required
                autoComplete="on"
                onChange={handlerChange}
              />
            )}
          </div>
          {isSignup && (
            <>
              <div className="custom-select">
                <select name="day" onChange={handelSelectChange}>
                  {daysInMonth.map((value, index) => (
                    <option key={index} value={value}>
                      Ngày {value}
                    </option>
                  ))}
                </select>
                <select name="month" onChange={handelSelectChange}>
                  {monthList.map((value, index) => (
                    <option key={index} value={value}>
                      Tháng {value}
                    </option>
                  ))}
                </select>
                <select name="year" onChange={handelSelectChange}>
                  {year().map((value, index) => (
                    <option key={index} value={value}>
                      {value}
                    </option>
                  ))}
                </select>
              </div>
              <div>
                {jsonSex.choices.map((choice, index) => (
                  <React.Fragment key={index}>
                    <input
                      key={`input ${index}`}
                      type="radio"
                      id={`radio${index}`}
                      name="sex"
                      value={choice.value}
                      onChange={handleOnChangeSex}
                    />
                    <label key={`label ${index}`} htmlFor={`radio${index}`}>
                      <span key={`span ${index}`}></span>
                      {choice.text}
                    </label>
                  </React.Fragment>
                ))}
              </div>
            </>
          )}
          {!confirmPass && (
            <span style={{ color: "red", fontSize: "0.9rem" }}>
              Mật khẩu không khớp!!!
            </span>
          )}
          <div>
            <span
              style={{ fontSize: "14px", cursor: "pointer" }}
              onClick={handlerLogin}
            >
              {!isSignup
                ? "Chưa có tài khoản? Đăng ký ngay"
                : "Đã có tài khoản. Đăng nhập!"}
            </span>
            <button
              className="button infoButton"
              type="submit"
              disabled={loading}
            >
              {loading ? <Loading /> : !isSignup ? "Đăng nhập" : "Đăng ký"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
export default Auth;
