class AuthService {
    constructor() {
        this.currentUser = this.loadUserFromStorage();
    }

    async login(email, password) {
        try {
            if (email && password) {
                const user = {
                    id: 1,
                    email: email,
                    nombre: 'Administrador',
                    rol: 'ADMIN'
                };

                this.saveUserToStorage(user);
                this.currentUser = user;

                return {
                    success: true,
                    user: user
                };
            } else {
                throw new Error('Credenciales inválidas');
            }

        } catch (error) {
            throw error;
        }
    }

    logout() {
        localStorage.removeItem('user');
        this.currentUser = null;
        window.location.href = '../index.html';
    }

    isAuthenticated() {
        return this.currentUser !== null;
    }

    getCurrentUser() {
        return this.currentUser;
    }

    saveUserToStorage(user) {
        localStorage.setItem('user', JSON.stringify(user));
    }

    loadUserFromStorage() {
        const userJson = localStorage.getItem('user');
        return userJson ? JSON.parse(userJson) : null;
    }

    requireAuth() {
        if (!this.isAuthenticated()) {
            window.location.href = '../index.html';
            return false;
        }
        return true;
    }
}

const authService = new AuthService();
window.authService = authService;