// Function to toggle theme
function toggleTheme() {
    const body = document.body;
    const themeToggle = document.getElementById('theme-toggle');
    const themeIcon = document.getElementById('theme-icon');

    if (themeToggle.checked) {
        body.classList.add('dark-mode');
        themeIcon.textContent = '🌜';
        localStorage.setItem('theme', 'dark');
    } else {
        body.classList.remove('dark-mode');
        themeIcon.textContent = '🌞';
        localStorage.setItem('theme', 'light');
    }
}

// Load theme on page load
window.onload = function() {
    const savedTheme = localStorage.getItem('theme') || 'light';
    const body = document.body;
    const themeToggle = document.getElementById('theme-toggle');
    const themeIcon = document.getElementById('theme-icon');

    if (savedTheme === 'dark') {
        body.classList.add('dark-mode');
        themeToggle.checked = true;
        themeIcon.textContent = '🌜';
    } else {
        themeIcon.textContent = '🌞';
    }
};

// Function to summarize text
async function summarizeText() {
    const inputText = document.getElementById('inputText').value.trim();
    const summaryText = document.getElementById('summaryText');
    const summaryContainer = document.getElementById('summaryContainer');
    const loading = document.getElementById('loading');
    const summarizeBtn = document.getElementById('summarizeBtn');

    if (inputText === '') {
        alert('Please enter some text to summarize.');
        return;
    }

    // Prepare UI for loading state
    summarizeBtn.disabled = true;
    loading.style.display = 'flex';
    summaryContainer.style.display = 'none';

    try {
        const response = await fetch(`http://localhost:8000/TextSummarize?inputContent=${encodeURIComponent(inputText)}`);

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const result = await response.json();
        // Set summary text content without injecting HTML
        summaryText.textContent = result.content;
        summaryContainer.style.display = 'block';
    } catch (error) {
        console.error('Error summarizing text:', error);
        summaryText.textContent = 'An error occurred. Please try again.';
        summaryContainer.style.display = 'block';
    } finally {
        // Reset UI after loading
        loading.style.display = 'none';
        summarizeBtn.disabled = false;
    }
}
