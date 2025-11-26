const Validator = {
    isValidEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    },

    isNotEmpty(value) {
        return value !== null && value !== undefined && value.trim() !== '';
    },

    minLength(value, min) {
        return value.length >= min;
    },

    maxLength(value, max) {
        return value.length <= max;
    },

    isNumber(value) {
        return !isNaN(value) && !isNaN(parseFloat(value));
    },

    isPositive(value) {
        return this.isNumber(value) && parseFloat(value) > 0;
    },

    isValidUrl(url) {
        try {
            new URL(url);
            return true;
        } catch (e) {
            return false;
        }
    },

    showError(inputId, message) {
        const input = document.getElementById(inputId);
        const errorElement = document.getElementById(`${inputId}Error`);
        
        if (input) {
            input.classList.add('error');
        }
        
        if (errorElement) {
            errorElement.textContent = message;
            errorElement.classList.add('show');
        }
    },

    clearError(inputId) {
        const input = document.getElementById(inputId);
        const errorElement = document.getElementById(`${inputId}Error`);
        
        if (input) {
            input.classList.remove('error');
        }
        
        if (errorElement) {
            errorElement.textContent = '';
            errorElement.classList.remove('show');
        }
    },

    clearAllErrors() {
        const errorMessages = document.querySelectorAll('.error-message');
        const errorInputs = document.querySelectorAll('.error');
        
        errorMessages.forEach(el => {
            el.textContent = '';
            el.classList.remove('show');
        });
        
        errorInputs.forEach(el => {
            el.classList.remove('error');
        });
    },

    validateForm(formId, rules) {
        this.clearAllErrors();
        let isValid = true;

        for (const [fieldId, fieldRules] of Object.entries(rules)) {
            const input = document.getElementById(fieldId);
            if (!input) continue;

            const value = input.value.trim();

            if (fieldRules.required && !this.isNotEmpty(value)) {
                this.showError(fieldId, fieldRules.requiredMessage || 'Este campo es requerido');
                isValid = false;
                continue;
            }

            if (fieldRules.email && value && !this.isValidEmail(value)) {
                this.showError(fieldId, fieldRules.emailMessage || 'Email inválido');
                isValid = false;
                continue;
            }

            if (fieldRules.minLength && !this.minLength(value, fieldRules.minLength)) {
                this.showError(fieldId, 
                    fieldRules.minLengthMessage || 
                    `Mínimo ${fieldRules.minLength} caracteres`
                );
                isValid = false;
                continue;
            }

            if (fieldRules.custom) {
                const customResult = fieldRules.custom(value);
                if (customResult !== true) {
                    this.showError(fieldId, customResult);
                    isValid = false;
                }
            }
        }

        return isValid;
    }
};

window.Validator = Validator;