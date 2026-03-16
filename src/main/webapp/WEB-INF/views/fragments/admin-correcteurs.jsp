<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<section class="admin-section">
    <div class="form-card admin-form">
        <h3 id="form-title">
            <c:choose>
                <c:when test="${not empty param.editId}">Modifier l'enregistrement</c:when>
                <c:otherwise>Ajouter un Correcteur</c:otherwise>
            </c:choose>
        </h3>
        <form action="/admin/correcteurs/save" method="post">
            <input type="hidden" name="id" value="${param.editId}" />
            <div class="form-group">
                <label>Nom du correcteur</label>
                <input type="text" name="nom" required value="${param.editVal1}" />
            </div>
            <div class="form-actions">
                <button type="submit" class="btn-submit">Enregistrer</button>
                <c:if test="${not empty param.editId}">
                    <a href="?tab=correcteurs" class="btn-cancel" style="text-align: center; text-decoration: none;">Annuler</a>
                </c:if>
            </div>
        </form>
    </div>

    <div class="table-card">
        <table class="admin-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th class="actions">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${correcteurs}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.nom}</td>
                        <td class="actions">
                            <a href="?tab=correcteurs&editId=${item.id}&editVal1=${item.nom}" class="btn-icon edit" style="text-decoration:none;">✏️</a>
                            <a href="/admin/correcteurs/delete/${item.id}" class="btn-icon delete" style="text-decoration:none;">🗑️</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</section>
