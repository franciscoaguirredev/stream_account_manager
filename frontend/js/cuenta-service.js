class CuentaService {
    
    async getAll() {
        try {
            return await httpClient.get(API_CONFIG.ENDPOINTS.CUENTAS);
        } catch (error) {
            notifications.error('Error al cargar cuentas');
            throw error;
        }
    }

    async getById(id) {
        try {
            return await httpClient.get(API_CONFIG.ENDPOINTS.CUENTA_BY_ID(id));
        } catch (error) {
            notifications.error('Error al cargar cuenta');
            throw error;
        }
    }

    async getByPlataforma(plataformaId) {
        try {
            return await httpClient.get(
                API_CONFIG.ENDPOINTS.CUENTAS_BY_PLATAFORMA(plataformaId)
            );
        } catch (error) {
            notifications.error('Error al cargar cuentas de la plataforma');
            throw error;
        }
    }

    async create(cuentaData) {
        try {
            const response = await httpClient.post(
                API_CONFIG.ENDPOINTS.CUENTAS, 
                cuentaData
            );
            notifications.success('Cuenta creada exitosamente');
            return response;
        } catch (error) {
            notifications.error('Error al crear cuenta');
            throw error;
        }
    }

    async update(id, cuentaData) {
        try {
            const response = await httpClient.put(
                API_CONFIG.ENDPOINTS.CUENTA_BY_ID(id), 
                cuentaData
            );
            notifications.success('Cuenta actualizada exitosamente');
            return response;
        } catch (error) {
            notifications.error('Error al actualizar cuenta');
            throw error;
        }
    }

    async delete(id) {
        try {
            await httpClient.delete(API_CONFIG.ENDPOINTS.CUENTA_BY_ID(id));
            notifications.success('Cuenta eliminada exitosamente');
        } catch (error) {
            notifications.error('Error al eliminar cuenta');
            throw error;
        }
    }
}

const cuentaService = new CuentaService();
window.cuentaService = cuentaService;