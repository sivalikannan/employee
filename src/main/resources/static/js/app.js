(() => {
  const toast = document.querySelector('.toast');
  if (toast) {
    setTimeout(() => toast.remove(), 3500);
  }

  document.querySelectorAll('[data-confirm]').forEach((btn) => {
    btn.addEventListener('click', (e) => {
      const msg = btn.getAttribute('data-confirm') || 'Are you sure?';
      if (!confirm(msg)) e.preventDefault();
    });
  });

  const q = document.getElementById('q');
  const count = document.getElementById('count');
  if (q) {
    const rows = Array.from(document.querySelectorAll('.table .tr')).filter(r => !r.classList.contains('th'));
    const renderCount = (n) => {
      if (count) count.textContent = `${n} result${n === 1 ? '' : 's'}`;
    };
    renderCount(rows.length);
    q.addEventListener('input', () => {
      const term = q.value.trim().toLowerCase();
      let visible = 0;
      rows.forEach((r) => {
        const text = r.textContent.toLowerCase();
        const ok = !term || text.includes(term);
        r.style.display = ok ? '' : 'none';
        if (ok) visible++;
      });
      renderCount(visible);
    });
  }
})();

