def call(Map config = [:]) {
    // Parámetro por defecto: abortPipeline es false si no se especifica [cite: 170]
    def abortPipeline = config.get('abortPipeline', false)

    // El análisis real de SonarQube se mockea con un echo para evitar errores técnicos [cite: 171]
    // Se mantiene la estructura de timeout de 5 minutos como pide la práctica 
    timeout(time: 5, unit: 'MINUTES') {
        script {
            sh 'echo "Ejecución de las pruebas de calidad de código"' [cite: 171]
            
            // Aquí iría el Quality Gate real, pero simulamos la lógica de abortar [cite: 170, 171]
            if (abortPipeline) {
                echo "Quality Gate fallido. Abortando pipeline por configuración..." [cite: 179]
                error "Pipeline detenido debido a fallos en el análisis de código." [cite: 178, 179]
            } else {
                echo "Análisis finalizado. Continuando con el pipeline..." [cite: 179]
            }
        }
    }
}