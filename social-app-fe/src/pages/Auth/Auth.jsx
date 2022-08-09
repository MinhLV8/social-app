import React, { useState } from "react";
import "./Auth.css";

const Auth = () => {
    const [isLogin, setIsLogin] = useState(true);
    const [data, setData] = useState({ username: "", password: "", confirmpass: "" })
    const handlerChange = () => {
        setIsLogin(!isLogin)
    }

    const handlerSubmit = () => {

    }
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
                <form className="infoForm authForm">
                    <h3>{isLogin ? "Đăng nhập" : "Đăng ký tài khoản"}</h3>
                    {!isLogin && (
                        <div>
                            <input
                                type="text"
                                className="infoInput"
                                placeholder="First Name"
                                name="firstname"
                            />
                            <input
                                type="text"
                                className="infoInput"
                                name="lastname"
                                placeholder="Last Name"
                            />
                        </div>
                    )}
                    <div>
                        <input
                            type="text"
                            placeholder="Username/Sđt/Email"
                            className="infoInput"
                            name="username"
                        />
                    </div>
                    <div>
                        <input
                            type="password"
                            className="infoInput"
                            placeholder="Password"
                            name="password"
                        />
                        {!isLogin && (
                            <input
                                type="text"
                                className="infoInput"
                                name="confirmpass"
                                placeholder="Confirm Password"
                            />
                        )}
                    </div>
                    <div>
                        <span style={{ fontSize: "12px", cursor: "pointer" }} onClick={handlerChange}>
                            {isLogin ? "Chưa có tài khoản? Đăng ký ngay" : "Đã có tài khoản. Đăng nhập!"}
                        </span>
                        <button onClick={handlerSubmit} className="button infoButton">{isLogin ? "Đăng nhập" : "Đăng ký"}</button>
                    </div>
                </form>
            </div>
        </div>
    );
};
export default Auth;
