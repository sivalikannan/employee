(() => {
  const toggle = document.querySelector('[data-toggle-password]');
  const pwd = document.getElementById('password');
  if (toggle && pwd) {
    toggle.addEventListener('click', () => {
      const isPwd = pwd.getAttribute('type') === 'password';
      pwd.setAttribute('type', isPwd ? 'text' : 'password');
      toggle.textContent = isPwd ? 'Hide' : 'Show';
    });
  }
})();

