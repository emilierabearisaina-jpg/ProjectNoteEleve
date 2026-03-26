<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Saisie de Devis</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .error-message { color: red; font-size: 0.9em; display: none; margin-top: 5px; }
        .success-message { color: green; font-size: 1em; margin-bottom: 15px; }
        .info-group { display: flex; flex-wrap: wrap; gap: 20px; background: #f9f9f9; padding: 15px; border-radius: 8px; margin-bottom: 20px; }
        .info-item { flex: 1; min-width: 150px; }
        .info-item label { font-weight: bold; color: #555; }
        .info-item span { display: block; margin-top: 5px; color: #333; }
        table.dynamic-table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table.dynamic-table th, table.dynamic-table td { padding: 10px; border: 1px solid #ddd; text-align: left; }
        table.dynamic-table th { background-color: #f2f2f2; }
        .total-container { text-align: right; margin-top: 20px; font-size: 1.2em; font-weight: bold; }
        .btn-add { background-color: #28a745; color: white; border: none; padding: 5px 10px; cursor: pointer; border-radius: 4px; }
        .btn-remove { background-color: #dc3545; color: white; border: none; padding: 5px 10px; cursor: pointer; border-radius: 4px; }
    </style>
</head>
<body>

    <nav class="navbar">
        <div class="nav-brand">Fourrage d'Eau</div>
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/clients" class="nav-item">Clients</a>
            <a href="${pageContext.request.contextPath}/demandes" class="nav-item">Demandes</a>
            <a href="${pageContext.request.contextPath}/devis/new" class="nav-item active">Devis</a>
        </div>
    </nav>

    <div class="main-content">
        <h1>Nouveau Devis</h1>

        <c:if test="${param.success}">
            <div class="success-message">Le devis a été enregistré avec succès !</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/devis/save" method="post" id="devisForm">
            <div class="form-container" style="max-width: 100%;">
                <h3>Information de la Demande</h3>
                <div style="margin-bottom: 20px;">
                    <label>ID Demande :</label>
                    <input type="number" name="demandeId" id="demandeId" placeholder="Entrez l'ID de la demande" required style="width: 200px;">
                    <span id="demandeError" class="error-message">ID Demande inexistant !</span>
                </div>

                <div class="info-group">
                    <div class="info-item">
                        <label>Client :</label>
                        <span id="clientNom">-</span>
                    </div>
                    <div class="info-item">
                        <label>Date Demande :</label>
                        <span id="demandeDate">-</span>
                    </div>
                    <div class="info-item">
                        <label>Lieu :</label>
                        <span id="demandeLieu">-</span>
                    </div>
                    <div class="info-item">
                        <label>District :</label>
                        <span id="demandeDistrict">-</span>
                    </div>
                </div>

                <div style="margin-bottom: 20px;">
                    <label>Type de Devis :</label>
                    <select name="typeDevisId" required>
                        <option value="">-- Choisir un type --</option>
                        <c:forEach var="t" items="${typesDevis}">
                            <option value="${t.id}">${t.libelle}</option>
                        </c:forEach>
                    </select>

                    <label style="margin-left: 20px;">Date Devis :</label>
                    <input type="date" name="date" required value="<%= java.time.LocalDate.now() %>">
                </div>

                <h3>Détails du Devis</h3>
                <table class="dynamic-table" id="detailsTable">
                    <thead>
                        <tr>
                            <th>Libellé</th>
                            <th style="width: 150px;">PU</th>
                            <th style="width: 100px;">Quantité</th>
                            <th style="width: 150px;">Total</th>
                            <th style="width: 50px;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text" name="detailsLibelle" required style="width: 95%;"></td>
                            <td><input type="number" name="detailsPu" class="pu-input" step="0.01" required value="0" style="width: 90%;"></td>
                            <td><input type="number" name="detailsQtt" class="qtt-input" step="0.1" required value="1" style="width: 90%;"></td>
                            <td><span class="line-total">0.00</span></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <button type="button" class="btn-add" id="addRow" style="margin-top: 10px;">+ Ajouter une ligne</button>

                <div class="total-container">
                    Montant Total : <span id="grandTotal">0.00</span>
                </div>

                <div style="margin-top: 30px; text-align: right;">
                    <button type="submit" class="btn" id="submitBtn">Enregistrer Devis</button>
                </div>
            </div>
        </form>
    </div>

    <script>
        document.getElementById('demandeId').addEventListener('blur', function() {
            const id = this.value;
            const errorSpan = document.getElementById('demandeError');
            const submitBtn = document.getElementById('submitBtn');
            
            if (!id) return;

            fetch('${pageContext.request.contextPath}/devis/api/demande/' + id)
                .then(response => {
                    if (!response.ok) throw new Error('Not found');
                    return response.json();
                })
                .then(data => {
                    console.log('Demande trouvée:', data);
                    document.getElementById('clientNom').textContent = data.clientNom;
                    document.getElementById('demandeDate').textContent = data.date;
                    document.getElementById('demandeLieu').textContent = data.lieu;
                    document.getElementById('demandeDistrict').textContent = data.district;
                    errorSpan.style.display = 'none';
                    submitBtn.disabled = false;
                })
                .catch(err => {
                    console.error('Erreur lors de la récupération de la demande:', err);
                    document.getElementById('clientNom').textContent = '-';
                    document.getElementById('demandeDate').textContent = '-';
                    document.getElementById('demandeLieu').textContent = '-';
                    document.getElementById('demandeDistrict').textContent = '-';
                    errorSpan.style.display = 'inline';
                    submitBtn.disabled = true;
                });
        });

        document.getElementById('addRow').addEventListener('click', function() {
            const tbody = document.querySelector('#detailsTable tbody');
            const newRow = document.createElement('tr');
            newRow.innerHTML = `
                <td><input type="text" name="detailsLibelle" required style="width: 95%;"></td>
                <td><input type="number" name="detailsPu" class="pu-input" step="0.01" required value="0" style="width: 90%;"></td>
                <td><input type="number" name="detailsQtt" class="qtt-input" step="0.1" required value="1" style="width: 90%;"></td>
                <td><span class="line-total">0.00</span></td>
                <td><button type="button" class="btn-remove">X</button></td>
            `;
            tbody.appendChild(newRow);
            
            // Add listeners to new inputs
            attachListeners(newRow);
        });

        document.getElementById('detailsTable').addEventListener('click', function(e) {
            if (e.target.classList.contains('btn-remove')) {
                e.target.closest('tr').remove();
                calculateGrandTotal();
            }
        });

        function attachListeners(row) {
            row.querySelector('.pu-input').addEventListener('input', calculateRowTotal);
            row.querySelector('.qtt-input').addEventListener('input', calculateRowTotal);
        }

        function calculateRowTotal(e) {
            const row = e.target.closest('tr');
            const pu = parseFloat(row.querySelector('.pu-input').value) || 0;
            const qtt = parseFloat(row.querySelector('.qtt-input').value) || 0;
            const total = pu * qtt;
            row.querySelector('.line-total').textContent = total.toFixed(2);
            calculateGrandTotal();
        }

        function calculateGrandTotal() {
            let grandTotal = 0;
            document.querySelectorAll('.line-total').forEach(span => {
                grandTotal += parseFloat(span.textContent) || 0;
            });
            document.getElementById('grandTotal').textContent = grandTotal.toFixed(2);
        }

        // Initial listeners
        document.querySelectorAll('#detailsTable tbody tr').forEach(attachListeners);
    </script>
</body>
</html>
