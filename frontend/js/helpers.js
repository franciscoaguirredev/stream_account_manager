class HttpClient {
    constructor() {
        this.baseURL = API_CONFIG.BASE_URL;
    }

    async get(endpoint) {
        try {
            const response = await fetch(buildUrl(endpoint), {
                method: 'GET',
                headers: API_CONFIG.DEFAULT_HEADERS
            });

            if (!response.ok) {
                throw await this.handleError(response);
            }

            return await response.json();
        } catch (error) {
            console.error('Error en GET:', error);
            throw error;
        }
    }

    async post(endpoint, data) {
        try {
            const response = await fetch(buildUrl(endpoint), {
                method: 'POST',
                headers: API_CONFIG.DEFAULT_HEADERS,
                body: JSON.stringify(data)
            });

            if (!response.ok) {
                throw await this.handleError(response);
            }

            return await response.json();
        } catch (error) {
            console.error('Error en POST:', error);
            throw error;
        }
    }

    async put(endpoint, data) {
        try {
            const response = await fetch(buildUrl(endpoint), {
                method: 'PUT',
                headers: API_CONFIG.DEFAULT_HEADERS,
                body: JSON.stringify(data)
            });

            if (!response.ok) {
                throw await this.handleError(response);
            }

            return await response.json();
        } catch (error) {
            console.error('Error en PUT:', error);
            throw error;
        }
    }

    async delete(endpoint) {
        try {
            const response = await fetch(buildUrl(endpoint), {
                method: 'DELETE',
                headers: API_CONFIG.DEFAULT_HEADERS
            });

            if (!response.ok) {
                throw await this.handleError(response);
            }

            if (response.status === 204) {
                return { success: true };
            }

            return await response.json();
        } catch (error) {
            console.error('Error en DELETE:', error);
            throw error;
        }
    }

    async handleError(response) {
        let errorMessage = 'Error en la petición';
        
        try {
            const errorData = await response.json();
            errorMessage = errorData.message || errorData.error || errorMessage;
        } catch (e) {
            errorMessage = `Error ${response.status}: ${response.statusText}`;
        }

        return new Error(errorMessage);
    }
}

const httpClient = new HttpClient();
window.httpClient = httpClient;

const Utils = {
    formatDate(date) {
        if (!date) return '';
        const d = new Date(date);
        return d.toLocaleDateString('es-CO', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit'
        });
    },

    formatCurrency(amount) {
        return new Intl.NumberFormat('es-CO', {
            style: 'currency',
            currency: 'COP'
        }).format(amount);
    },

    debounce(func, wait) {
        let timeout;
        return function executedFunction(...args) {
            const later = () => {
                clearTimeout(timeout);
                func(...args);
            };
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
        };
    },

    showLoading(element) {
        if (element) {
            element.classList.add('loading');
            element.disabled = true;
        }
    },

    hideLoading(element) {
        if (element) {
            element.classList.remove('loading');
            element.disabled = false;
        }
    }
};

window.Utils = Utils;