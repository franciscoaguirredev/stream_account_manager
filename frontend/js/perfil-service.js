class PerfilService {
    
    async getAll() {
        try {
            return await httpClient.get(API_CONFIG.ENDPOINTS.PERFILES);
        } catch (error) {
            notifications.error('Error al cargar perfiles');
            throw error;
        }
    }

    async getById(id) {
        try {
            return await httpClient.get(API_CONFIG.ENDPOINTS.PERFIL_BY_ID(id));
        } catch (error) {
            notifications.error('Error al cargar perfil');
            throw error;
        }
    }

    async getByCuenta(cuentaId) {
        try {
            return await httpClient.get(
                API_CONFIG.ENDPOINTS.PERFILES_BY_CUENTA(cuentaId)
            );
        } catch (error) {
            notifications.error('Error al cargar perfiles de la cuenta');
            throw error;
        }
    }

    async create(perfilData) {
        try {
            const response = await httpClient.post(
                API_CONFIG.ENDPOINTS.PERFILES, 
                perfilData
            );
            notifications.success('Perfil creado exitosamente');
            return response;
        } catch (error) {
            if (error.message.includes('máximo') || error.message.includes('4')) {
                notifications.error('No se pueden crear más de 4 perfiles por cuenta');
            } else {
                notifications.error('Error al crear perfil');
            }
            throw error;
        }
    }

    async update(id, perfilData) {
        try {
            const response = await httpClient.put(
                API_CONFIG.ENDPOINTS.PERFIL_BY_ID(id), 
                perfilData
            );
            notifications.success('Perfil actualizado exitosamente');
            return response;
        } catch (error) {
            notifications.error('Error al actualizar perfil');
            throw error;
        }
    }

    async delete(id) {
        try {
            await httpClient.delete(API_CONFIG.ENDPOINTS.PERFIL_BY_ID(id));
            notifications.success('Perfil eliminado exitosamente');
        } catch (error) {
            notifications.error('Error al eliminar perfil');
            throw error;
        }
    }
}

const perfilService = new PerfilService();
window.perfilService = perfilService;