const form = document.getElementById("loginForm");
const submitBtn = document.getElementById("submitBtn");

form.addEventListener("submit", function (e) {
  e.preventDefault();
  submitBtn.disabled = true;
  submitBtn.innerText = "Entrando...";

  // Pegando valores
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  // Simulação de login
  setTimeout(() => {
    if (
      (email === "admin@clubedafabrica.com" && password === "123456") ||
      (email === "joao@email.com" && password === "123456")
    ) {
      alert("Login realizado com sucesso!");
      window.location.href = "index.html"; // redireciona para a home
    } else {
      alert("Email ou senha incorretos!");
      submitBtn.disabled = false;
      submitBtn.innerText = "Entrar";
    }
  }, 1500);
});
