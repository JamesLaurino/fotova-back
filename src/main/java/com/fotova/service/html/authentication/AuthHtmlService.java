package com.fotova.service.html.authentication;

import org.springframework.stereotype.Service;

@Service
public class AuthHtmlService {

    public String buildSuccessResetPassword() {
        return """
        <!DOCTYPE html>
                    <html>
                    <head>
                    <meta charset="UTF-8">
                    <title>Password reset</title>
                    <meta http-equiv="refresh" content="5;url=http://localhost:4200/login">
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
                        <a href="http://localhost:4200/login">
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
                                window.location.href = "http://localhost:4200/login";
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
        <html>
        <head>
            <meta charset="UTF-8">
            <title>Password reset fail</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background: linear-gradient(135deg, #F44336, #B71C1C);
                    color: white;
                    text-align: center;
                    padding-top: 100px;
                }
                .card {
                    background: white;
                    color: #333;
                    padding: 40px;
                    margin: auto;
                    width: 400px;
                    border-radius: 12px;
                    box-shadow: 0 10px 30px rgba(0,0,0,0.2);
                }
                h1 {
                    color: #B71C1C;
                }
                a {
                    display: inline-block;
                    margin-top: 20px;
                    padding: 12px 24px;
                    background-color: #F44336;
                    color: white;
                    text-decoration: none;
                    border-radius: 8px;
                    font-weight: bold;
                }
                a:hover {
                    background-color: #C62828;
                }
            </style>
        </head>
        <body>
            <div class="card">
                <h1>Password reset fail ❌</h1>
                <p>An error occur during the treatment.</p>
                <a href="http://localhost:4200">
                    Back to Fotova-Creation
                </a>
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
                    <meta http-equiv="refresh" content="5;url=http://localhost:4200/login">
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
                        <a href="http://localhost:4200/login">
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
                                window.location.href = "http://localhost:4200/login";
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
        <html>
        <head>
            <meta charset="UTF-8">
            <title>Registering fail</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background: linear-gradient(135deg, #F44336, #B71C1C);
                    color: white;
                    text-align: center;
                    padding-top: 100px;
                }
                .card {
                    background: white;
                    color: #333;
                    padding: 40px;
                    margin: auto;
                    width: 400px;
                    border-radius: 12px;
                    box-shadow: 0 10px 30px rgba(0,0,0,0.2);
                }
                h1 {
                    color: #B71C1C;
                }
                a {
                    display: inline-block;
                    margin-top: 20px;
                    padding: 12px 24px;
                    background-color: #F44336;
                    color: white;
                    text-decoration: none;
                    border-radius: 8px;
                    font-weight: bold;
                }
                a:hover {
                    background-color: #C62828;
                }
            </style>
        </head>
        <body>
            <div class="card">
                <h1>Registering fail ❌</h1>
                <p>An error occur during the treatment.</p>
                <a href="http://localhost:4200">
                    Back to Fotova-Creation
                </a>
            </div>
        </body>
        </html>
        """;
    }
}
