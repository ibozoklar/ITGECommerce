import React, { useState } from "react";
import ReactDOM from "react-dom";
import App from "../app";

export default function LoginContent() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const requestData = {
        email: email,
        password: password
      };

      const response = await fetch(
        "http://localhost:9092/auth/login",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(requestData)
        }
      );

      if (response.ok) {
        const data = await response.json();
        // İstek başarılıysa yanıtı işleyebilirsiniz
        console.log(data);

        // Başarılı giriş durumunda App bileşenini render et
        ReactDOM.render(<App />, document.getElementById("root"));
      } else {
        // İstek başarısız olduğunda hata durumuyla ilgili işlemleri yapabilirsiniz
        throw new Error("Login request failed");
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <main>
      <div className="content">
        <div className="loginContent">
          <div className="ctop">
            <div className="user-menu">
              <span className="username">Kullanıcı Adı:</span> <br />
              <input
                className="placeholder-input"
                type="email"
                id="email"
                placeholder="Kullanıcı adınızı giriniz."
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              <br />
              <br />
              <span className="password">Şifre:</span> <br />
              <input
                className="placeholder-input"
                type="password"
                id="password"
                placeholder="Şifrenizi giriniz."
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              <br />
              <br />
              <div className="cbottom">
                <button onClick={handleLogin}>GİRİŞ</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  );
}