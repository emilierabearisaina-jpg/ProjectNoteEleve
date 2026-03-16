<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<section class="admin-section">
    <div class="form-card admin-form">
        <h3 id="form-title">
            <c:choose>
                <c:when test="${not empty param.editId}">Modifier l'enregistrement</c:when>
                <c:otherwise>Ajouter une Note</c:otherwise>
            </c:choose>
        </h3>
        <form action="/admin/notes/save" method="post">
            <input type="hidden" name="id" value="${param.editId}" />

            <div class="form-row" style="margin-bottom: 12px;">
                <div class="form-group" style="flex:1;">
                    <label>Élève</label>
                    <select name="idEleve" required>
                        <option value="">Sélectionner...</option>
                        <c:forEach var="e" items="${eleves}">
                            <option value="${e.id}" <c:if test="${param.editVal1 == e.id}">selected</c:if>>${e.nom}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group" style="flex:1;">
                    <label>Matière</label>
                    <select name="idMatiere" required>
                        <option value="">Sélectionner...</option>
                        <c:forEach var="m" items="${matieres}">
                            <option value="${m.id}" <c:if test="${param.editVal2 == m.id}">selected</c:if>>${m.nom}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-row" style="margin-bottom: 12px;">
                <div class="form-group" style="flex:1;">
                    <label>Correcteur</label>
                    <select name="idCorrecteur" required>
                        <option value="">Sélectionner...</option>
                        <c:forEach var="c" items="${correcteurs}">
                            <option value="${c.id}" <c:if test="${param.editVal3 == c.id}">selected</c:if>>${c.nom}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group" style="flex:1;">
                    <label>Note (/20)</label>
                    <input type="number" step="0.01" min="0" max="20" name="noteVal" required value="${param.editVal4}" />
                </div>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn-submit">Enregistrer</button>
                <c:if test="${not empty param.editId}">
                    <a href="?tab=notes" class="btn-cancel" style="text-align: center; text-decoration: none;">Annuler</a>
                </c:if>
            </div>
        </form>
    </div>

    <div class="table-card">
        <table class="admin-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Élève</th>
                    <th>Matière</th>
                    <th>Prof</th>
                    <th>Note</th>
                    <th class="actions">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${notes}">
                    <tr>
                        <td>${item.id}</td>

                        <c:set var="eleveNom" value="" />
                        <c:forEach var="e" items="${eleves}">
                            <c:if test="${e.id == item.idEleve}">
                                <c:set var="eleveNom" value="${e.nom}" />
                            </c:if>
                        </c:forEach>

                        <c:set var="matNom" value="" />
                        <c:forEach var="m" items="${matieres}">
                            <c:if test="${m.id == item.idMatiere}">
                                <c:set var="matNom" value="${m.nom}" />
                            </c:if>
                        </c:forEach>

                        <c:set var="profNom" value="" />
                        <c:forEach var="p" items="${correcteurs}">
                            <c:if test="${p.id == item.idCorrecteur}">
                                <c:set var="profNom" value="${p.nom}" />
                            </c:if>
                        </c:forEach>

                        <!-- Affichage propre -->
                        <td>${eleveNom}</td>
                        <td>${matNom}</td>
                        <td>${profNom}</td>
                        <td style="font-weight:bold; color:var(--success)">${item.note}</td>
                        <td class="actions">
                            <a href="?tab=notes&editId=${item.id}&editVal1=${item.idEleve}&editVal2=${item.idMatiere}&editVal3=${item.idCorrecteur}&editVal4=${item.note}"
                                class="btn-icon edit" style="text-decoration:none;">✏️</a>
                            <a href="/admin/notes/delete/${item.id}" class="btn-icon delete" style="text-decoration:none;">🗑️</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</section>
