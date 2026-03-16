<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
            <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
                <!DOCTYPE html>
                <html lang="fr">

                <head>
                    <meta charset="UTF-8" />
                    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                    <title>Résultats des Élèves</title>
                    <link rel="stylesheet" href="/css/style.css" />
                </head>

                <body>

                    <div class="page-wrapper">
                        <a href="/admin" class="admin-link">⚙️ Administration</a>

                        <header class="site-header">
                            <h1>Résultats des Élèves</h1>
                            <p>Saisissez l'identifiant d'un candidat pour afficher sa note finale</p>
                        </header>

                        <!-- FORMULAIRE -->
                        <section class="form-section">
                            <form action="/" method="get" class="search-form">
                                <label for="idCandidat">ID Candidat</label>
                                <div class="form-row">
                                    <input type="number" id="idCandidat" name="idCandidat" min="1"
                                        placeholder="Ex : 1, 2, 3 …" value="${idCandidat}" required />
                                    <button type="submit">Rechercher</button>
                                </div>
                            </form>
                        </section>

                        <!-- ERREUR -->
                        <c:if test="${not empty erreur}">
                            <div class="erreur-box">
                                ⚠️
                                <c:out value="${erreur}" />
                            </div>
                        </c:if>

                        <!-- RÉSULTATS -->
                        <c:if test="${not empty resultat}">
                            <section class="resultats">

                                <!-- En-tête élève -->
                                <div class="eleve-header">
                                    <div class="avatar">
                                        <c:out value="${fn:substring(resultat.nomEleve, 0, 1)}" />
                                    </div>
                                    <div class="eleve-info">
                                        <h2>
                                            <c:out value="${resultat.nomEleve}" />
                                        </h2>
                                        <p>ID Candidat :
                                            <c:out value="${resultat.idEleve}" />
                                        </p>
                                    </div>
                                </div>

                                <!-- Note finale globale -->
                                <!-- <div class="note-finale-card">
                <div class="note-label">
                    Note Finale Globale
                    <small>Moyenne de toutes les matières</small>
                </div>
                <div class="note-badge">
                    <fmt:formatNumber value="${resultat.noteFinalGlobale}" pattern="0.00"/> / 20
                </div>
            </div> -->

                                <!-- Détail par matière -->
                                <h3 class="section-title">Note Finale par matière</h3>

                                <c:forEach var="matiere" items="${resultat.matieres}">
                                    <div class="matiere-card">

                                        <div class="matiere-header">
                                            <span class="matiere-nom">
                                                <c:out value="${matiere.nomMatiere}" />
                                            </span>
                                            <span class="matiere-note">
                                                <fmt:formatNumber value="${matiere.noteFinale}" pattern="0.00" /> / 20
                                            </span>
                                        </div>

                                        <!-- <table class="notes-table">
                                            <thead>
                                                <tr>
                                                    <th>Correcteur</th>
                                                    <th>Note</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="detail" items="${matiere.notesDetail}">
                                                    <tr>
                                                        <td>
                                                            <c:out value="${detail.nomCorrecteur}" />
                                                        </td>
                                                        <td class="note-cell">
                                                            <fmt:formatNumber value="${detail.note}" pattern="0.00" />
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table> -->

                                    </div>
                                </c:forEach>

                            </section>
                        </c:if>

                        <footer class="site-footer">
                            NoteEleve &copy; 2026 — Calculateur de notes avec règles opérateur &amp; résolution
                        </footer>

                    </div>

                </body>

                </html>