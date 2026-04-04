def call(Map config = [:]) {
    // Extraemos el valor de forma explícita para evitar errores de transformación
    def shouldAbort = config.abortPipeline ?: false

    // Mantenemos el timeout de 5 minutos [cite: 169]
    timeout(time: 5, unit: 'MINUTES') {
        // En Jenkins Pipeline, es mejor envolver la lógica en un bloque script
        script {
            // Requisito: Imprimir el mensaje de ejecución [cite: 171]
            sh 'echo "Ejecución de las pruebas de calidad de código"'
            
            // Lógica de decisión [cite: 178, 179]
            if (shouldAbort == true) {
                echo "Quality Gate fallido. Abortando pipeline..."
                error "Pipeline detenido por fallo en análisis estático."
            } else {
                echo "Análisis finalizado con éxito (o advertencias ignoradas). Continuando..."
            }
        }
    }
}