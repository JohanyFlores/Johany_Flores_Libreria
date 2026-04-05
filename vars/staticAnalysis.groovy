def call(Map config = [:]) {
    def manualAbort = config.get('abortPipeline', false)
    def branchName = env.BRANCH_NAME ?: "unknown"

    timeout(time: 5, unit: 'MINUTES') {
        script {
            sh 'echo "Ejecución de las pruebas de calidad de código"'
            echo "Rama actual detectada: ${branchName}"

            if (manualAbort == true || branchName == "master" || branchName == "main" || branchName.startsWith("hotfix")) {
                echo "Abortando: No se permite fallo en rama principal (${branchName})."
                error "Análisis fallido en rama protegida."
            } else {
                echo "Continuando: Rama '${branchName}' permitida."
            }
        } 
    } 
} 