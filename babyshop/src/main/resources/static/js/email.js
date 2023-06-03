


function sendOTP() {
    const email = document.getElementById('cf_email');
    const otpverify = document.getElementById('otpEmail');


    let otp_val = Math.floor(100000 + Math.random() * 900000); // 6 digits

    let emailSubject = `${otp_val} là mã xác nhận Email của bạn`;
    let emailbody = `
    <div> <span style="color:rgb(0,0,0);font-size:15px"> </span> <p><span style="color:rgb(0,0,0);font-size:15px">Chào bạn<br></span></p> <p><span style="color:rgb(0,0,0);font-size:15px">Bạn đang Hoàn thành xác nhận đổi Email, Mã xác nhận là </span><span style="color:rgb(0,0,0)"><strong><span style="color:rgb(78,164,220);font-size:15px">${otp_val}</span></strong><span style="font-size:15px">.</span></span> </p> <p><span style="color:rgb(0,0,0);font-size:15px">Vui lòng hoàn thành xác nhận trong vòng 30 phút.<br></span></p> <span style="color:rgb(0,0,0);font-size:15px"> </span> <div> <span style="color:rgb(0,0,0);font-size:15px"> </span> <p><span style="color:rgb(0,0,0);font-size:15px">Baby Shop</span></p> <span style="color:rgb(0,0,0)"> </span> <h5><span style="color:rgb(119,119,119);font-size:13px">Đây là thư từ hệ thống, vui lòng không trả lời thư. </span></h5> </div> </div>
`;

    Email.send({
        SecureToken: "d266e8c0-1407-4897-9617-987d9b8995b4",
        To: email.value,
        From: "duydao0221@gmail.com",
        Subject: emailSubject,
        Body: emailbody
    }).then(
        //if success it returns "OK";
        message => {
            if (message === "OK") {
                alert("OTP sent to your email " + email.value);

                // now making otp input visible
                otpverify.removeAttribute('disabled');
                const otpConfirm = document.getElementById('otp_confirm');
                const otpInvalid = document.getElementById('invalid_otp');
                otpConfirm.addEventListener('click', () => {
                    // now check whether sent email is valid
                    if (otpverify.value == otp_val) {
                        alert("Email address verified...");
                    }
                    else {
                        otpInvalid.style.display = 'block';
                    }
                })
            }
        }
    );

}