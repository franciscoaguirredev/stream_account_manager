class PlataformaController {
    constructor() {
        this.plataformas = [];
        this.init();
    }

    async init() {
        if (!authService.requireAuth()) {
            return;
        }

        await this.loadPlataformas();

        this.setupSearch();

        this.setupEditForm();
    }

    async loadPlataformas() {
        try {
            this.plataformas = await plataformaService.getAll();
            this.renderPlataformas(this.plataformas);
        } catch (error) {
            console.error('Error cargando plataformas:', error);
        }
    }

    renderPlataformas(plataformas) {
        const container = document.getElementById('plataformasGrid');
        
        if (!plataformas || plataformas.length === 0) {
            container.innerHTML = `
                <div style="grid-column: 1/-1; text-align: center; padding: 60px 20px;">
                    <div style="font-size: 64px; margin-bottom: 16px;">📱</div>
                    <h3 style="color: var(--dark-color); margin-bottom: 8px;">No hay plataformas</h3>
                    <p style="color: #6b7280; margin-bottom: 24px;">Comienza agregando tu primera plataforma de streaming</p>
                    <a href="crear.html" class="btn btn-primary">+ Crear Plataforma</a>
                </div>
            `;
            return;
        }

        container.innerHTML = plataformas.map(plataforma => `
            <div class="platform-card-full">
                <div class="platform-card-header">
                    <div class="platform-card-icon">${this.getPlatformIcon(plataforma.nombre)}</div>
                    <div class="platform-card-actions">
                        <button class="btn-icon edit" onclick="plataformaController.openEditModal(${plataforma.id})" title="Editar">
                            ✏️
                        </button>
                        <button class="btn-icon delete" onclick="plataformaController.deletePlataforma(${plataforma.id})" title="Eliminar">
                            🗑️
                        </button>
                    </div>
                </div>
                <div class="platform-card-body">
                    <h3>${plataforma.nombre}</h3>
                    <p>🌐 ${plataforma.urlOficial || 'Sin URL'}</p>
                </div>
                <div class="platform-card-footer">
                    <span class="badge ${plataforma.estado === 'ACTIVO' ? 'badge-success' : 'badge-danger'}">
                        ${plataforma.estado}
                    </span>
                </div>
            </div>
        `).join('');
    }

    setupSearch() {
        const searchInput = document.getElementById('searchInput');
        if (!searchInput) return;

        searchInput.addEventListener('input', Utils.debounce((e) => {
            const query = e.target.value.toLowerCase().trim();
            
            if (!query) {
                this.renderPlataformas(this.plataformas);
                return;
            }

            const filtered = this.plataformas.filter(plataforma =>
                plataforma.nombre.toLowerCase().includes(query) ||
                (plataforma.urlOficial && plataforma.urlOficial.toLowerCase().includes(query))
            );

            this.renderPlataformas(filtered);
        }, 300));
    }

    setupEditForm() {
        const editForm = document.getElementById('editForm');
        if (!editForm) return;

        editForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            await this.updatePlataforma();
        });
    }

    async openEditModal(id) {
        try {
            const plataforma = await plataformaService.getById(id);
            
            document.getElementById('editId').value = plataforma.id;
            document.getElementById('editNombre').value = plataforma.nombre;
            document.getElementById('editUrl').value = plataforma.urlOficial || '';
            document.getElementById('editEstado').value = plataforma.estado;

            document.getElementById('editModal').classList.remove('hidden');
        } catch (error) {
            notifications.error('Error al cargar datos de la plataforma');
        }
    }

    async updatePlataforma() {
        const id = document.getElementById('editId').value;
        const data = {
            nombre: document.getElementById('editNombre').value.trim(),
            urlOficial: document.getElementById('editUrl').value.trim(),
            estado: document.getElementById('editEstado').value
        };

        try {
            await plataformaService.update(id, data);
            closeEditModal();
            await this.loadPlataformas();
        } catch (error) {
            console.error('Error actualizando plataforma:', error);
        }
    }

    async deletePlataforma(id) {
        if (!confirm('¿Estás seguro de eliminar esta plataforma? Esta acción no se puede deshacer.')) {
            return;
        }

        try {
            await plataformaService.delete(id);
            await this.loadPlataformas();
        } catch (error) {
            console.error('Error eliminando plataforma:', error);
        }
    }

    getPlatformIcon(nombre) {
        const icons = {
            'Netflix': '🎬',
            'Disney+': '🏰',
            'Disney Plus': '🏰',
            'HBO Max': '🎭',
            'Prime Video': '📺',
            'Amazon Prime': '📺',
            'Spotify': '🎵',
            'Star+': '⭐',
            'Paramount+': '🎪',
            'Apple TV': '🍎',
            'Crunchyroll': '🎌'
        };

        const nombreLower = nombre.toLowerCase();
        for (const [key, icon] of Object.entries(icons)) {
            if (nombreLower.includes(key.toLowerCase())) {
                return icon;
            }
        }

        return '📱';
    }
}

function closeEditModal() {
    document.getElementById('editModal').classList.add('hidden');
}

function handleLogout() {
    if (confirm('¿Estás seguro de que deseas cerrar sesión?')) {
        authService.logout();
    }
}

let plataformaController;