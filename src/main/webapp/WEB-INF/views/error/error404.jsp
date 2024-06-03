<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>404 - 페이지를 찾을 수 없습니다</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f8ff;
            font-family: 'Arial', sans-serif;
            color: #333;
            text-align: center;
        }

        .container {
            padding: 2em;
            border-radius: 10px;
            background: #fff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 2.5em;
            margin-bottom: 0.5em;
        }

        p {
            font-size: 1.2em;
            margin-bottom: 1.5em;
        }

        a {
            text-decoration: none;
            color: #fff;
            background: #ff6347;
            padding: 0.75em 1.5em;
            border-radius: 25px;
            font-size: 1.2em;
            transition: background 0.3s ease;
        }

        a:hover {
            background: #ff4500;
        }

        .error-image {
            width: 150px;
            height: 150px;
            margin-bottom: 1em;
            background: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="black" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"/><line x1="12" y1="16" x2="12" y2="16"/><line x1="12" y1="8" x2="12" y2="12"/></svg>') no-repeat center;
            background-size: contain;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="error-image"></div>
    <h1>404! 페이지를 찾을 수 없습니다.</h1>
    <p>요청하신 페이지가 존재하지 않거나, 이동되었을 수 있습니다.</p>
    <a href="/">홈으로 돌아가기</a>
</div>
</body>
</html>

