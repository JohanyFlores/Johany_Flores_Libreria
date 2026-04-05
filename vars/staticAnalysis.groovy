//staticAnalysis.groovy

def call(Map config = [:]) {
    def shouldAbort = config.abortPipeline ?: false

    // Timeout de 5 minutos
    timeout(time: 5, unit: 'MINUTES') {

        script {
            // Imprime el mensaje de ejecución
            sh 'echo "Ejecución de las pruebas de calidad de código"'
            
            // Lógica de decisión
            if (shouldAbort == true) {
                echo "Quality Gate fallido. Abortando pipeline..."
                error "Pipeline detenido por fallo en análisis estático."
            } else {
                echo "Análisis finalizado con éxito (o advertencias ignoradas). Continuando..."
            }
        }
    }
}