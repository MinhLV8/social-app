import React, { useState } from "react";
import "./Auth.css";

const Auth = () => {
  const [isLogin, setIsLogin] = useState(true);
  const [data, setData] = useState({
    firstname: "",
    lastname: "",
    username: "",
    password: "",
    confirmpass: "",
  });
  const [confirmPass, setcomfirmPass] = useState(true);
  const handlerLogin = () => {
    setIsLogin(!isLogin);
  };
  const handlerChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const handlerSubmit = (e) => {
    data.password !== data.confirmpass && e.preventDefault();
    setcomfirmPass(false);
  };

  return (
    <div className="Auth">
      <div className="a-left">
        <img src="../../assets/icons/care-2387662-1991058.png" alt="" />
        <div className="Webname">
          <h1>Unitech Social Media</h1>
          <h6>Khám phá những ý tưởng trên khắp thế giới "động vật".</h6>
        </div>
      </div>
      <div className="a-right">
        <form className="infoForm authForm" onSubmit={handlerSubmit}>
          <h3>{isLogin ? "Đăng nhập" : "Đăng ký tài khoản"}</h3>
          {!isLogin && (
            <div>
              <input
                type="text"
                className="infoInput"
                placeholder="First Name"
                name="firstname"
                required
                onChange={handlerChange}
              />
              <input
                type="text"
                className="infoInput"
                name="lastname"
                placeholder="Last Name"
                required
                onChange={handlerChange}
              />
            </div>
          )}
          <div>
            <input
              type="text"
              placeholder="Username/Sđt/Email"
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
              placeholder="Password"
              name="password"
              required
              value={data.password}
              onChange={handlerChange}
            />
            {!isLogin && (
              <input
                type="password"
                className="infoInput"
                name="confirmpass"
                placeholder="Confirm Password"
                required
                onChange={handlerChange}
              />
            )}
          </div>
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
              {isLogin
                ? "Chưa có tài khoản? Đăng ký ngay"
                : "Đã có tài khoản. Đăng nhập!"}
            </span>
            <button className="button infoButton">
              {isLogin ? "Đăng nhập" : "Đăng ký"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
export default Auth;
