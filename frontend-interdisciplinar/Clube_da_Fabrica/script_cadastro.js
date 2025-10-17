const form = document.getElementById('registerForm');
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirmPassword');
const errorMsg = document.getElementById('errorMsg');
const submitBtn = document.getElementById('submitBtn');

form.addEventListener('submit', function (e) {
  e.preventDefault();

  if (password.value !== confirmPassword.value) {
    errorMsg.textContent = 'As senhas não coincidem';
    return;
  }

  errorMsg.textContent = '';
  submitBtn.disabled = true;
  submitBtn.textContent = 'Cadastrando...';

  setTimeout(() => {
    alert('Conta criada com sucesso!');
    window.location.href = 'index.html'; // simula redirecionamento após cadastro
  }, 1500);
});

confirmPassword.addEventListener('input', () => {
  if (password.value !== confirmPassword.value) {
    errorMsg.textContent = 'As senhas não coincidem';
    submitBtn.disabled = true;
  } else {
    errorMsg.textContent = '';
    submitBtn.disabled = false;
  }
});
