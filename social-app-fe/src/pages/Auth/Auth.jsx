import React, { useState } from "react";
import logo from "../../assets/icons/care-2387662-1991058.png";
import "./Auth.css";
const Auth = () => {
  const [isSignup, setIsSignup] = useState(true);
  const [data, setData] = useState({
    firstName: "",
    sdt: "",
    sex: 0,
    surName: "",
    email: "",
    password: "",
    username: "",
    confirmPass: ""
  });
  const [confirmPass, setcomfirmPass] = useState(true);
  const handlerLogin = () => {
    setIsSignup(!isSignup);
    resetForm()
  };
  const handlerChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value, sdt: data.username, email: data.username });
  };

  const handlerSubmit = (e) => {
    e.preventDefault()
    if (isSignup) {
      data.password !== data.confirmPass && setcomfirmPass(false)
    }
  };
  const resetForm = () => {
    setcomfirmPass(true)
    setData({
      firstName: "",
      sdt: "",
      sex: 0,
      surName: "",
      email: "",
      password: "",
      username: "",
      confirmPass: ""
    })
  }
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
                onChange={handlerChange}
              />
            )}
          </div>
          {isSignup && (
            <div>
              <div className="infoInput">
                <input type="radio" id="r1" name="sex" />
                <label for="r1"><span></span>Nam</label>
              </div>

              <div className="infoInput">
                <input type="radio" id="r2" name="sex" />
                <label for="r2"><span></span>Nữ</label>
              </div>
              <div className="infoInput">
                <input type="radio" id="r3" name="sex" />
                <label for="r3"><span></span>Tuỳ chỉnh</label></div>
            </div>
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
            <button className="button infoButton">
              {!isSignup ? "Đăng nhập" : "Đăng ký"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
export default Auth;
