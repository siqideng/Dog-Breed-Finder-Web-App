<%--Author: Siqi Deng--%>
<%--AndrewID: siqideng--%>

<%--This page demonstrates the result page of the dog search.--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Dog <%= request.getParameter("dog-breeds")%></title>
    </head>
    <body>
        <h1><%= request.getAttribute("breed")%></h1>
        <h3><%= request.getAttribute("friendly")%></h3>
        <h3><%= request.getAttribute("intelligence")%></h3>
        <h3><%= request.getAttribute("height")%></h3>
        <h3><%= request.getAttribute("weight")%></h3>
        <h3><%= request.getAttribute("life")%></h3>
        <p>credit: <%= request.getAttribute("creditBreed")%></p>
        <img src="<%= request.getAttribute("picture")%>">
        <p>Credit: https://dog.ceo/dog-api/</p>
        <form action="getDogBreedInformation" method="GET">
            <input type="submit" value="Continue" />
        </form>
    </body>
    <style>
        body {
            max-width: 500px;
            align-items: center;
            margin: auto;
            padding-top: 30px;
            background-color: beige;
            font-family: Helvetica;
        }
    </style>
</html>

