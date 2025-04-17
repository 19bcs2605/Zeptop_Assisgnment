document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    const profileForm = document.getElementById('profileForm');
    const csvUploadForm = document.getElementById('csvUploadForm');

    if (loginForm) {
        loginForm.addEventListener('submit', function(event) {
            event.preventDefault();
            // Handle login logic here
            const formData = new FormData(loginForm);
            fetch('/login', {
                method: 'POST',
                body: formData
            }).then(response => {
                if (response.ok) {
                    window.location.href = '/profile';
                } else {
                    alert('Login failed. Please check your credentials.');
                }
            });
        });
    }

    if (registerForm) {
        registerForm.addEventListener('submit', function(event) {
            event.preventDefault();
            // Handle registration logic here
            const formData = new FormData(registerForm);
            fetch('/register', {
                method: 'POST',
                body: formData
            }).then(response => {
                if (response.ok) {
                    alert('Registration successful! Please log in.');
                    window.location.href = '/login';
                } else {
                    alert('Registration failed. Please try again.');
                }
            });
        });
    }

    if (profileForm) {
        profileForm.addEventListener('submit', function(event) {
            event.preventDefault();
            // Handle profile update logic here
            const formData = new FormData(profileForm);
            fetch('/profile/update', {
                method: 'POST',
                body: formData
            }).then(response => {
                if (response.ok) {
                    alert('Profile updated successfully!');
                } else {
                    alert('Profile update failed. Please try again.');
                }
            });
        });
    }

    if (csvUploadForm) {
        csvUploadForm.addEventListener('submit', function(event) {
            event.preventDefault();
            // Handle CSV upload logic here
            const formData = new FormData(csvUploadForm);
            fetch('/csv/upload', {
                method: 'POST',
                body: formData
            }).then(response => {
                if (response.ok) {
                    alert('CSV uploaded successfully!');
                } else {
                    alert('CSV upload failed. Please try again.');
                }
            });
        });
    }
});