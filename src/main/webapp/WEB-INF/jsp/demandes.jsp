<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Demandes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <nav class="navbar">
        <div class="nav-brand">Fourrage d'Eau</div>
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/clients" class="nav-item">Clients</a>
            <a href="${pageContext.request.contextPath}/demandes" class="nav-item">Demandes</a>
            <a href="${pageContext.request.contextPath}/devis/new" class="nav-item">Devis</a>
        </div>
    </nav>

    <div class="main-content">
        <h1>Gestion des Demandes de Fourrage</h1>

    <div class="form-container">
        <h3>Nouvelle Demande</h3>
        <form action="${pageContext.request.contextPath}/demandes/add" method="post">
            <label>Client :</label><br>
            <select name="client.id" required>
                <option value="">-- Choisir un client --</option>
                <c:forEach var="c" items="${clients}">
                    <option value="${c.id}">${c.nom}</option>
                </c:forEach>
            </select><br>

            <label>Date :</label><br>
            <input type="date" name="date" required><br>
            
            <label>Lieu :</label><br>
            <input type="text" name="lieu" required><br>

            <label>District :</label><br>
            <input type="text" name="district" required><br>
            
            <button type="submit" class="btn">Enregistrer la Demande</button>
        </form>
    </div>

    <h3>Liste des Demandes</h3>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Client</th>
                <th>Lieu</th>
                <th>District</th>
                <th>Statut</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="demande" items="${demandes}">
                <tr>
                    <td>${demande.id}</td>
                    <td>${demande.date}</td>
                    <td>${demande.client.nom}</td>
                    <td>${demande.lieu}</td>
                    <td>${demande.district}</td>
                    <td><span class="status-badge">${demande.currentStatus}</span></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/demandes/delete/${demande.id}" class="btn btn-danger" onclick="return confirm('Supprimer cette demande ?')">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    </div>
</body>
</html>
