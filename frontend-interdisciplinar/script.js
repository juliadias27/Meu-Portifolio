
    // Ativar link clicado no menu
    document.querySelectorAll(".navbar-nav .nav-link").forEach(link => {
      link.addEventListener("click", function () {
        document.querySelectorAll(".navbar-nav .nav-link").forEach(l => l.classList.remove("active"));
        this.classList.add("active");
      });
    });

    // Capturar busca
    const form = document.getElementById("searchForm");
    const input = document.getElementById("searchInput");

    form.addEventListener("submit", function(e) {
      e.preventDefault();
      alert("Buscando por: " + input.value);
    });

    // Dropdown do usuário
    document.addEventListener('DOMContentLoaded', function() {
    const btn = document.getElementById('userDropdownBtn');
    const menu = document.getElementById('userDropdownMenu');

    btn.addEventListener('click', function(e) {
        e.stopPropagation();
        menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
    });

    document.addEventListener('click', function() {
        menu.style.display = 'none';
    });
    });

    document.querySelectorAll('.dropdown-item-user').forEach(item => {
  item.addEventListener('mouseenter', function() {
    document.querySelectorAll('.dropdown-item-user').forEach(i => i.classList.remove('active'));
    this.classList.add('active');
  });
  item.addEventListener('mouseleave', function() {
    this.classList.remove('active');
  });
});

window.addEventListener('scroll', function() {
  const navbar = document.querySelector('.navbar');
  if (window.scrollY > 10) {
    navbar.classList.add('transparent-navbar');
  } else {
    navbar.classList.remove('transparent-navbar');
  }
});
