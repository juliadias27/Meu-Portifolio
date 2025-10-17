document.getElementById("adminForm").addEventListener("submit", function(e) {
  e.preventDefault();
  
  const code = document.getElementById("adminCode").value;
  const email = document.getElementById("adminEmail").value;
  const password = document.getElementById("adminPassword").value;

  if (code !== "FABRICA2024") {
    alert("Código de acesso inválido!");
    return;
  }

  if (email && password) {
    alert("Login bem-sucedido! Redirecionando para admin...");
    window.location.href = "admin.html";
  }
});
