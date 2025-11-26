class DashboardController {
    constructor() {
        this.init();
    }

    async init() {
        if (!authService.requireAuth()) {
            return;
        }

        this.displayUserInfo();

        await this.loadDashboardData();
    }

    displayUserInfo() {
        const user = authService.getCurrentUser();
        const userNameElement = document.getElementById('userName');
        if (userNameElement && user) {
            userNameElement.textContent = user.nombre || user.email;
        }
    }

    async loadDashboardData() {
        try {
            const [plataformas, cuentas, perfiles] = await Promise.all([
                plataformaService.getAll(),
                cuentaService.getAll(),
                perfilService.getAll()
            ]);

            this.updateStats({
                plataformas: plataformas.length,
                cuentas: cuentas.length,
                perfiles: perfiles.length,
                suscriptores: 0 
            });

            this.renderPlataformas(plataformas);

            this.renderCuentasRecientes(cuentas.slice(0, 5));

        } catch (error) {
            console.error('Error cargando datos del dashboard:', error);
            notifications.error('Error al cargar los datos del dashboard');
        }
    }

    updateStats(stats) {
        document.getElementById('totalPlataformas').textContent = stats.plataformas;
        document.getElementById('totalCuentas').textContent = stats.cuentas;
        document.getElementById('totalPerfiles').textContent = stats.perfiles;
        document.getElementById('totalSuscriptores').textContent = stats.suscriptores;
    }

    renderPlataformas(plataformas) {
        const container = document.getElementById('plataformasContainer');
        
        if (!plataformas || plataformas.length === 0) {
            container.innerHTML = `
                <div style="text-align: center; padding: 40px; color: #6b7280;">
                    <p>No hay plataformas registradas</p>
                    <a href="plataformas/crear.html" class="btn btn-primary" style="margin-top: 16px;">
                        + Crear Primera Plataforma
                    </a>
                </div>
            `;
            return;
        }

        container.innerHTML = plataformas.map(plataforma => `
            <div class="platform-card" onclick="window.location.href='cuentas/lista.html?plataforma=${plataforma.id}'">
                <div class="platform-icon">${this.getPlatformIcon(plataforma.nombre)}</div>
                <div class="platform-name">${plataforma.nombre}</div>
                <div class="platform-status">
                    <span class="badge ${plataforma.estado === 'ACTIVO' ? 'badge-success' : 'badge-danger'}">
                        ${plataforma.estado}
                    </span>
                </div>
            </div>
        `).join('');
    }

    renderCuentasRecientes(cuentas) {
        const container = document.getElementById('cuentasContainer');
        
        if (!cuentas || cuentas.length === 0) {
            container.innerHTML = `
                <div style="text-align: center; padding: 40px; color: #6b7280;">
                    <p>No hay cuentas registradas</p>
                </div>
            `;
            return;
        }

        container.innerHTML = `
            <table class="data-table">
                <thead>
                    <tr>
                        <th>Email</th>
                        <th>Plataforma</th>
                        <th>Perfiles</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    ${cuentas.map(cuenta => `
                        <tr>
                            <td>${cuenta.email}</td>
                            <td>${cuenta.plataformaNombre || 'N/A'}</td>
                            <td>${cuenta.cantidadPerfiles || 0}/4</td>
                            <td>
                                <span class="badge ${cuenta.estado === 'ACTIVO' ? 'badge-success' : 'badge-danger'}">
                                    ${cuenta.estado}
                                </span>
                            </td>
                            <td>
                                <button class="btn btn-sm" onclick="verDetallesCuenta(${cuenta.id})">
                                    Ver
                                </button>
                            </td>
                        </tr>
                    `).join('')}
                </tbody>
            </table>
        `;
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
            'Paramount+': '🎪'
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

function verDetallesCuenta(cuentaId) {
    window.location.href = `cuentas/detalles.html?id=${cuentaId}`;
}

function handleLogout() {
    if (confirm('¿Estás seguro de que deseas cerrar sesión?')) {
        authService.logout();
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new DashboardController();
});