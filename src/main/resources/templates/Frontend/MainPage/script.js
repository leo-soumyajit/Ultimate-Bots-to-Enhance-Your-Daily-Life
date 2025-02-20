// Theme Toggle Functionality
function toggleTheme() {
    const body = document.body;
    const themeIcon = document.getElementById('theme-icon');
    const isDarkMode = body.classList.toggle('dark-mode');

    // Change the icon based on the theme
    themeIcon.textContent = isDarkMode ? '🌜' : '🌞';

    // Save the theme preference in localStorage
    localStorage.setItem('isDarkMode', isDarkMode);
}

// Load theme on page load
window.onload = function() {
    const isDarkMode = localStorage.getItem('isDarkMode') === 'true';
    const body = document.body;
    const themeToggle = document.getElementById('theme-toggle');
    const themeIcon = document.getElementById('theme-icon');

    if (isDarkMode) {
        body.classList.add('dark-mode');
        themeToggle.checked = true;
        themeIcon.textContent = '🌜';
    } else {
        themeIcon.textContent = '🌞';
    }
};
