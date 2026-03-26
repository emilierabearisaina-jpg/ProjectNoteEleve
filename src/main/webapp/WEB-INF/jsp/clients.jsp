<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Clients</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <nav class="navbar">
        <div class="nav-brand">Fourrage d'Eau</div>
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/clients" class="nav-item">Clients</a>
            <a href="${pageContext.request.contextPath}/demandes" class="nav-item">Demandes</a>
        </div>
    </nav>

    <div class="main-content">
        <h1>Gestion des Clients</h1>

    <div class="form-container">
        <h3>Ajouter un nouveau Client</h3>
        <form action="${pageContext.request.contextPath}/clients/add" method="post">
            <label>Nom :</label><br>
            <input type="text" name="nom" required><br>
            
            <label>Contact :</label><br>
            <input type="text" name="contact" required><br>
            
            <button type="submit" class="btn">Enregistrer le Client</button>
        </form>
    </div>

    <h3>Liste des Clients</h3>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Contact</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="client" items="${clients}">
                <tr>
                    <td>${client.id}</td>
                    <td>${client.nom}</td>
                    <td>${client.contact}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/clients/delete/${client.id}" class="btn btn-danger" onclick="return confirm('Êtes-vous sûr ?')">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    </div>
</body>
</html>
