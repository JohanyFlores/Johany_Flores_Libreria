def call(Map config = [:]) {
    // Parámetro manual del ejercicio 3 
    def manualAbort = config.get('abortPipeline', false)
    
    // Variable de entorno para la rama (Ejercicio 4) 
    def branchName = env.BRANCH_NAME ?: "unknown"

    // Timeout de 5 minutos [cite: 169]
    timeout(time: 5, unit: 'MINUTES') { 
        script {
            // Imprime el mensaje de ejecución
            sh 'echo "Ejecución de las pruebas de calidad de código"'
            echo "Rama actual detectada: ${branchName}"

            // Implementación de la Heurística
            if (manualAbort == true) {
                echo "Abortando: Parámetro manual 'abortPipeline' es True."
                error "Análisis fallido (Corte manual)."
            } 
            else if (branchName == "master" || branchName == "main") {
                echo "Abortando: No se permite fallo en rama principal (${branchName})."
                error "Análisis fallido en rama protegida."
            } 
            else if (branchName.startsWith("hotfix")) {
                echo "Abortando: No se permite fallo en rama de parche crítico (${branchName})."
                error "Análisis fallido en rama hotfix."
            } 
            else {
                echo "Continuando: Rama '${branchName}' permitida para pruebas."
            }
        } 
    } 
} 