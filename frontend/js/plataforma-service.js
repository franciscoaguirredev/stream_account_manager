class PlataformaService {
    
    async getAll() {
        try {
            return await httpClient.get(API_CONFIG.ENDPOINTS.PLATAFORMAS);
        } catch (error) {
            notifications.error('Error al cargar plataformas');
            throw error;
        }
    }

    async getById(id) {
        try {
            return await httpClient.get(API_CONFIG.ENDPOINTS.PLATAFORMA_BY_ID(id));
        } catch (error) {
            notifications.error('Error al cargar plataforma');
            throw error;
        }
    }

    async create(plataformaData) {
        try {
            const response = await httpClient.post(
                API_CONFIG.ENDPOINTS.PLATAFORMAS, 
                plataformaData
            );
            notifications.success('Plataforma creada exitosamente');
            return response;
        } catch (error) {
            notifications.error('Error al crear plataforma');
            throw error;
        }
    }

    async update(id, plataformaData) {
        try {
            const response = await httpClient.put(
                API_CONFIG.ENDPOINTS.PLATAFORMA_BY_ID(id), 
                plataformaData
            );
            notifications.success('Plataforma actualizada exitosamente');
            return response;
        } catch (error) {
            notifications.error('Error al actualizar plataforma');
            throw error;
        }
    }

    async delete(id) {
        try {
            await httpClient.delete(API_CONFIG.ENDPOINTS.PLATAFORMA_BY_ID(id));
            notifications.success('Plataforma eliminada exitosamente');
        } catch (error) {
            notifications.error('Error al eliminar plataforma');
            throw error;
        }
    }
}

const plataformaService = new PlataformaService();
window.plataformaService = plataformaService;