package com.fotova.service.html;

import com.fotova.service.html.authentication.AuthHtmlService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("acc")
public class AuthHtmlAccService implements AuthHtmlService {

    public String buildSuccessResetPassword() {
        return """
        <!DOCTYPE html>
                    <html>
                    <head>
                    <meta charset="UTF-8">
                    <title>Password reset</title>
                    <meta http-equiv="refresh" content="5;url=http://10.0.0.1/login">
                    <style>
                        body {
                            font-family: -apple-system, BlinkMacSystemFont, sans-serif;
                            background: #f6f9fc;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            height: 100vh;
                            margin: 0;
                        }
                        .card {
                            background: white;
                            padding: 50px;
                            border-radius: 16px;
                            box-shadow: 0 20px 40px rgba(0,0,0,0.08);
                            text-align: center;
                            width: 420px;
                            animation: fadeIn 0.6s ease-in-out;
                        }

                        h1 {
                            margin-top: 20px;
                            font-size: 22px;
                            color: #32325d;
                        }

                        p {
                            color: #6b7c93;
                            margin-top: 10px;
                            font-size: 15px;
                        }

                        .checkmark {
                            width: 80px;
                            height: 80px;
                            border-radius: 50%;
                            display: inline-block;
                            border: 4px solid #4BB543;
                            position: relative;
                            animation: pop 0.4s ease-out forwards;
                            margin: 0 auto;
                        }

                        .checkmark::after {
                            content: '';
                            position: absolute;
                            top: 50%;
                            left: 50%;
                            width: 20px;
                            height: 30px;
                            border-right: 4px solid #4BB543;
                            border-bottom: 4px solid #4BB543;
                            transform: translate(-50%, -50%) rotate(45deg);
                            animation: draw 0.5s ease forwards;
                        }

                        a {
                            display: inline-block;
                            margin-top: 25px;
                            padding: 12px 25px;
                            background: #635bff;
                            color: white;
                            border-radius: 8px;
                            text-decoration: none;
                            font-weight: 600;
                            transition: background 0.2s;
                        }
 
                        a:hover {
                            background: #4f46e5;
                        }
                        .countdown {
                            margin-top: 15px;
                            font-size: 13px;
                            color: #8898aa;
                        }
                        @keyframes fadeIn {
                            from {opacity: 0; transform: translateY(10px);}
                            to {opacity: 1; transform: translateY(0);}
                        }
                        @keyframes pop {
                            from {transform: scale(0.8); opacity: 0;}
                            to {transform: scale(1); opacity: 1;}
                        }
                        @keyframes draw {
                            from {opacity: 0;}
                            to {opacity: 1;}
                        }
                    </style>
                    </head>
                    <body>
                    <div class="card">
                        <div class="checkmark"></div>
                        <h1>Password reset successfully🎉</h1>
                        <p>You can now login with your new credentials</p>
                        <div class="countdown">
                            Redirection automatique dans <span id="timer">10</span> secondes...
                        </div>
                        <a href="http://10.0.0.1/login">
                            Now you can login with your credentials
                        </a>
                    </div>
                    <script>
                        let seconds = 10;
                        const timer = document.getElementById('timer');
                        const interval = setInterval(() => {
                            seconds--;
                            timer.textContent = seconds;
                            if(seconds <= 0) {
                                clearInterval(interval);
                                window.location.href = "http://10.0.0.1/login";
                            }
                        }, 1000);
                    </script>
                    </body>
                    </html>
    """;
    }

    public String buildErrorResetPassword() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Password reset issue</title>
                    <style>
                        body{
                            margin:0;
                            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
                            background:#f6f7f9;
                            height:100vh;
                            display:flex;
                            align-items:center;
                            justify-content:center;
                        }
                        .container{
                            background:white;
                            padding:50px 60px;
                            border-radius:14px;
                            width:420px;
                            text-align:center;
                            box-shadow:0 20px 40px rgba(0,0,0,0.08);
                        }
                        .icon{
                            font-size:42px;
                            margin-bottom:20px;
                        }
                        h1{
                            font-size:24px;
                            margin-bottom:10px;
                            color:#222;
                            font-weight:600;
                        }
                        p{
                            color:#666;
                            line-height:1.5;
                            margin-bottom:30px;
                        }
                        a{
                            display:inline-block;
                            padding:12px 28px;
                            background:#111;
                            color:white;
                            text-decoration:none;
                            border-radius:8px;
                            font-weight:500;
                            transition:all .2s ease;
                        }
                        a:hover{
                            background:#333;
                        }
                        .small{
                            margin-top:18px;
                            font-size:13px;
                            color:#999;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="icon">🔒</div>
                        <h1>Password reset unavailable</h1>
                        <p>
                            The password reset link may have expired or has already been used.
                            Please request a new password reset if needed.
                        </p>
                        <a href="http://10.0.0.1">
                            Return to Fotova-Creation
                        </a>
                        <div class="small">
                            If the issue persists, please contact support.
                        </div>
                    </div>
                </body>
                </html>
        """;
    }

    public String buildSuccessRegisterHtml() {

        return """
        <!DOCTYPE html>
                    <html>
                    <head>
                    <meta charset="UTF-8">
                    <title>Registering confirmed</title>
                    <meta http-equiv="refresh" content="5;url=http://10.0.0.1/login">
                    <style>
                        body {
                            font-family: -apple-system, BlinkMacSystemFont, sans-serif;
                            background: #f6f9fc;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            height: 100vh;
                            margin: 0;
                        }
                        .card {
                            background: white;
                            padding: 50px;
                            border-radius: 16px;
                            box-shadow: 0 20px 40px rgba(0,0,0,0.08);
                            text-align: center;
                            width: 420px;
                            animation: fadeIn 0.6s ease-in-out;
                        }

                        h1 {
                            margin-top: 20px;
                            font-size: 22px;
                            color: #32325d;
                        }

                        p {
                            color: #6b7c93;
                            margin-top: 10px;
                            font-size: 15px;
                        }

                        .checkmark {
                            width: 80px;
                            height: 80px;
                            border-radius: 50%;
                            display: inline-block;
                            border: 4px solid #4BB543;
                            position: relative;
                            animation: pop 0.4s ease-out forwards;
                            margin: 0 auto;
                        }

                        .checkmark::after {
                            content: '';
                            position: absolute;
                            top: 50%;
                            left: 50%;
                            width: 20px;
                            height: 30px;
                            border-right: 4px solid #4BB543;
                            border-bottom: 4px solid #4BB543;
                            transform: translate(-50%, -50%) rotate(45deg);
                            animation: draw 0.5s ease forwards;
                        }

                        a {
                            display: inline-block;
                            margin-top: 25px;
                            padding: 12px 25px;
                            background: #635bff;
                            color: white;
                            border-radius: 8px;
                            text-decoration: none;
                            font-weight: 600;
                            transition: background 0.2s;
                        }
 
                        a:hover {
                            background: #4f46e5;
                        }
                        .countdown {
                            margin-top: 15px;
                            font-size: 13px;
                            color: #8898aa;
                        }
                        @keyframes fadeIn {
                            from {opacity: 0; transform: translateY(10px);}
                            to {opacity: 1; transform: translateY(0);}
                        }
                        @keyframes pop {
                            from {transform: scale(0.8); opacity: 0;}
                            to {transform: scale(1); opacity: 1;}
                        }
                        @keyframes draw {
                            from {opacity: 0;}
                            to {opacity: 1;}
                        }
                    </style>
                    </head>
                    <body>
                    <div class="card">
                        <div class="checkmark"></div>
                        <h1>Registering confirmed 🎉</h1>
                        <p>Thank you for your thust</p>
                        <div class="countdown">
                            Redirection automatique dans <span id="timer">10</span> secondes...
                        </div>
                        <a href="http://10.0.0.1/login">
                            Now you can login with your credentials
                        </a>
                    </div>
                    <script>
                        let seconds = 10;
                        const timer = document.getElementById('timer');
                        const interval = setInterval(() => {
                            seconds--;
                            timer.textContent = seconds;
                            if(seconds <= 0) {
                                clearInterval(interval);
                                window.location.href = "http://10.0.0.1/login";
                            }
                        }, 1000);
                    </script>
                    </body>
                    </html>
    """;
    }

    public String buildFailureRegisterHtml() {

        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Registration issue</title>
                <style>
                    body{
                        margin:0;
                        font-family:-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
                        background:#f6f7f9;
                        height:100vh;
                        display:flex;
                        align-items:center;
                        justify-content:center;
                    }
                    .container{
                        background:white;
                        padding:50px 60px;
                        border-radius:14px;
                        width:420px;
                        text-align:center;
                        box-shadow:0 20px 40px rgba(0,0,0,0.08);
                    }
                    .icon{
                        font-size:42px;
                        margin-bottom:20px;
                    }
                    h1{
                        font-size:24px;
                        margin-bottom:10px;
                        color:#222;
                        font-weight:600;
                    }
                    p{
                        color:#666;
                        line-height:1.5;
                        margin-bottom:30px;
                    }
                    a{
                        display:inline-block;
                        padding:12px 28px;
                        background:#111;
                        color:white;
                        text-decoration:none;
                        border-radius:8px;
                        font-weight:500;
                        transition:all .2s ease;
                    }
                    a:hover{
                        background:#333;
                    }
                    .small{
                        margin-top:18px;
                        font-size:13px;
                        color:#999;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="icon">👤</div>
                    <h1>Registration unavailable</h1>
                    <p>
                        We couldn't complete your registration at the moment.
                        This may happen if the email is already registered or if a temporary issue occurred.
                        Please try again or return to the homepage.
                    </p>
                    <a href="http://10.0.0.1">
                        Return to Fotova-Creation
                    </a>
                    <div class="small">
                        If the problem continues, please contact support.
                    </div>
                </div>
            </body>
            </html>
        """;
    }
}
