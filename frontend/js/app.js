document.addEventListener('DOMContentLoaded', function() {
    if (authService && authService.isAuthenticated()) {
        window.location.href = 'pages/dashboard.html';
        return;
    }

    const loginForm = document.getElementById('loginForm');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');

    if (!loginForm) {
        console.error('Formulario de login no encontrado');
        return;
    }

    loginForm.addEventListener('submit', async function(e) {
        e.preventDefault();
        
        Validator.clearAllErrors();

        const isValid = Validator.validateForm('loginForm', {
            email: {
                required: true,
                email: true,
                requiredMessage: 'El email es requerido',
                emailMessage: 'Ingrese un email válido'
            },
            password: {
                required: true,
                minLength: 4,
                requiredMessage: 'La contraseña es requerida',
                minLengthMessage: 'La contraseña debe tener al menos 4 caracteres'
            }
        });

        if (!isValid) {
            return;
        }

        const email = emailInput.value.trim();
        const password = passwordInput.value;

        const submitButton = loginForm.querySelector('button[type="submit"]');
        const originalText = submitButton.textContent;
        submitButton.textContent = 'Iniciando sesión...';
        submitButton.disabled = true;

        try {
            const result = await authService.login(email, password);

            if (result.success) {
                notifications.success('¡Bienvenido!');
                
                setTimeout(() => {
                    window.location.href = 'pages/dashboard.html';
                }, 1000);
            }

        } catch (error) {
            notifications.error(error.message || 'Error al iniciar sesión');
            submitButton.disabled = false;
            submitButton.textContent = originalText;
        }
    });

    emailInput.addEventListener('input', function() {
        Validator.clearError('email');
    });

    passwordInput.addEventListener('input', function() {
        Validator.clearError('password');
    });
});