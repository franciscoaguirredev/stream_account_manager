const API_CONFIG = {
    BASE_URL: 'http://localhost:8080/api',
    
    ENDPOINTS: {
        LOGIN: '/auth/login',
        LOGOUT: '/auth/logout',
        
        ADMINISTRADORES: '/administradores',
        
        PLATAFORMAS: '/plataformas',
        PLATAFORMA_BY_ID: (id) => `/plataformas/${id}`,
        
        CUENTAS: '/cuentas',
        CUENTA_BY_ID: (id) => `/cuentas/${id}`,
        CUENTAS_BY_PLATAFORMA: (plataformaId) => `/cuentas/plataforma/${plataformaId}`,
        
        PERFILES: '/perfiles',
        PERFIL_BY_ID: (id) => `/perfiles/${id}`,
        PERFILES_BY_CUENTA: (cuentaId) => `/perfiles/cuenta/${cuentaId}`,
        
        SUSCRIPTORES: '/suscriptores',
        SUSCRIPTOR_BY_ID: (id) => `/suscriptores/${id}`,
        
        SUSCRIPCIONES: '/suscripciones',
        SUSCRIPCION_BY_ID: (id) => `/suscripciones/${id}`,
        SUSCRIPCIONES_BY_SUSCRIPTOR: (suscriptorId) => `/suscripciones/suscriptor/${suscriptorId}`,
        SUSCRIPCIONES_ACTIVAS: '/suscripciones/activas',
        
        PAGOS: '/pagos',
        PAGO_BY_ID: (id) => `/pagos/${id}`,
        PAGOS_BY_SUSCRIPCION: (suscripcionId) => `/pagos/suscripcion/${suscripcionId}`,
        PAGOS_PENDIENTES: '/pagos/pendientes'
    },
    
    TIMEOUT: 30000,
    
    DEFAULT_HEADERS: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    }
};

function buildUrl(endpoint) {
    return `${API_CONFIG.BASE_URL}${endpoint}`;
}

window.API_CONFIG = API_CONFIG;
window.buildUrl = buildUrl;