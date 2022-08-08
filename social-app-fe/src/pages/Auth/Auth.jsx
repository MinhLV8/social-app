import React from "react";
import "./Auth.css";

const Auth = () => {
    return (
        <div className="Auth">
            <div className="a-left">
                <img src="../../assets/icons/care-2387662-1991058.png" alt="" />
                <div className="Webname">
                    <h1>Unitech Social Media</h1>
                    <h6>Khám phá những ý tưởng trên khắp thế giới "động vật".</h6>
                </div>
            </div>

            <LogIn />
        </div>
    );
};

function LogIn() {
    return (
        <div className="a-right">
            <form className="infoForm authForm">
                <h3>Đăng nhập</h3>

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
                </div>

                <div>
                    <a href="/"><span style={{ fontSize: "12px" }}>
                        Chưa có tài khoản? Đăng ký ngay.
                    </span></a>
                    <button className="button infoButton">Đăng nhập</button>
                </div>
            </form>
        </div>
    );
}

function SignUp() {
    return (
        <div className="a-right">
            <form className="infoForm authForm">
                <h3>Đăng ký tài khoản.</h3>

                <div>
                    <input
                        type="text"
                        placeholder="First Name"
                        className="infoInput"
                        name="firstname"
                    />
                    <input
                        type="text"
                        placeholder="Last Name"
                        className="infoInput"
                        name="lastname"
                    />
                </div>

                <div>
                    <input
                        type="text"
                        className="infoInput"
                        name="username"
                        placeholder="Usernames"
                    />
                </div>

                <div>
                    <input
                        type="text"
                        className="infoInput"
                        name="password"
                        placeholder="Password"
                    />
                    <input
                        type="text"
                        className="infoInput"
                        name="confirmpass"
                        placeholder="Confirm Password"
                    />
                </div>

                <div>
                    <span style={{ fontSize: '12px' }}>Đã có tài khoản. Đăng nhập!</span>
                </div>
                <button className="button infoButton" type="submit">Đăng nhập</button>
            </form>
        </div>
    );
}

export default Auth;
