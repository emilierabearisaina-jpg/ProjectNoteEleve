<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Administration – NoteEleve</title>
    <link rel="stylesheet" href="/css/style.css" />
</head>

<body>

    <div class="page-wrapper admin-wrapper">

        <header class="site-header admin-header">
            <div class="header-left">
                <h1>⚙️ Administration</h1>
                <p>Gestion des entités de la base de données</p>
            </div>
            <a href="/" class="btn-return">← Retour à l'accueil</a>
        </header>

        <!-- NAVIGATION PAR ONGLET -->
        <div class="tabs">
            <a href="?tab=eleves" class="tab-link <c:if test="${activeTab == 'eleves'}">active</c:if>">👨‍🎓 Élèves</a>
            <a href="?tab=matieres" class="tab-link <c:if test="${activeTab == 'matieres'}">active</c:if>">📚 Matières</a>
            <a href="?tab=correcteurs" class="tab-link <c:if test="${activeTab == 'correcteurs'}">active</c:if>">👨‍🏫 Correcteurs</a>
            <a href="?tab=notes" class="tab-link <c:if test="${activeTab == 'notes'}">active</c:if>">📝 Notes</a>
        </div>

        <div class="admin-content">

            <!-- ==================== INCLUSION DES ONGLETS ==================== -->
            <c:choose>
                <c:when test="${activeTab == 'eleves'}">
                    <jsp:include page="fragments/admin-eleves.jsp" />
                </c:when>
                
                <c:when test="${activeTab == 'matieres'}">
                    <jsp:include page="fragments/admin-matieres.jsp" />
                </c:when>

                <c:when test="${activeTab == 'correcteurs'}">
                    <jsp:include page="fragments/admin-correcteurs.jsp" />
                </c:when>

                <c:when test="${activeTab == 'notes'}">
                    <jsp:include page="fragments/admin-notes.jsp" />
                </c:when>
                
                <c:otherwise>
                    <!-- Fallback -->
                    <jsp:include page="fragments/admin-eleves.jsp" />
                </c:otherwise>
            </c:choose>

        </div>
    </div>

</body>

</html>