// Function to get response from the Cricket Bot
async function getCricketResponse() {
    const inputContent = document.getElementById('inputTextCricket').value.trim();
    const summaryText = document.getElementById('summaryTextCricket');
    const summaryContainer = document.getElementById('summaryContainerCricket');
    const loading = document.getElementById('loadingCricket');
    const cricketBtn = document.getElementById('cricketBtn');

    if (inputContent === '') {
        alert('Please enter a question related to cricket.');
        return;
    }

    // Disable the button and show loading indicator
    cricketBtn.disabled = true;
    loading.style.display = 'flex';
    summaryContainer.style.display = 'none';

    try {
        const response = await fetch(`http://localhost:8000/cricket?inputContent=${encodeURIComponent(inputContent)}`);

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        // Adjust based on your API response structure
        const result = await response.json();

        summaryText.textContent = result.content;
        summaryContainer.style.display = 'block';
    } catch (error) {
        console.error('Error fetching response:', error);
        summaryText.textContent = 'An error occurred. Please try again.';
        summaryContainer.style.display = 'block';
    } finally {
        // Enable the button and hide loading indicator
        loading.style.display = 'none';
        cricketBtn.disabled = false;
    }
}

// Function to get live cricket updates
async function getLiveUpdates() {
    const summaryText = document.getElementById('summaryTextCricket');
    const summaryContainer = document.getElementById('summaryContainerCricket');
    const loading = document.getElementById('loadingCricket');
    const liveUpdatesBtn = document.getElementById('liveUpdatesBtn');

    // Disable the button and show loading indicator
    liveUpdatesBtn.disabled = true;
    loading.style.display = 'flex';
    summaryContainer.style.display = 'none';

    try {
        const response = await fetch('http://localhost:8000/cricket/live-updates');

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        // Assuming the response is plain text
        const result = await response.text();
        summaryText.textContent = result;
        summaryContainer.style.display = 'block';
    } catch (error) {
        console.error('Error fetching live updates:', error);
        summaryText.textContent = 'An error occurred. Please try again.';
        summaryContainer.style.display = 'block';
    } finally {
        // Enable the button and hide loading indicator
        loading.style.display = 'none';
        liveUpdatesBtn.disabled = false;
    }
}

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
