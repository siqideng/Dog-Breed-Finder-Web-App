<%--Author: Siqi Deng--%>
<%--AndrewID: siqideng--%>

<%--This page demonstrates the welcome page of the dog search.--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>DOG FINDER</h1> <p>Created by Siqi Deng</p>
        <h2>DOG BREEDS</h2>
        <form action="getDogBreedInformation" method="GET">
            <label for="dog-breeds">Choose a dog breed:</label>
            <select name="dog-breeds" id="dog-breeds">
                <option value="Borzoi" selected>Borzoi</option>
                <option value="Boxer">Boxer</option>
                <option value="Chihuahua">Chihuahua</option>
                <option value="Collie">Collie</option>
                <option value="Dachshund">Dachshund</option>
                <option value="Dalmatian">Dalmatian</option>
                <option value="Maltese">Maltese</option>
                <option value="Otterhound">Otterhound</option>
                <option value="Poodle">Poodle</option>
                <option value="Rottweiler">Rottweiler</option>
                <option value="Saluki">Saluki</option>
                <option value="Whippet">Whippet</option>
            </select>
            <input type="submit" value="Submit" />
        </form>
    </body>
    <style>
        body {
            max-width: 500px;
            align-items: center;
            margin: auto;
            padding-top: 200px;
            background-color: beige;
            font-family: Helvetica;
        }
    </style>
</html>

