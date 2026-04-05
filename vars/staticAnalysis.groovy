def call(Map config = [:]) {
    // Parámetro manual del ejercicio 3 
    def manualAbort = config.get('abortPipeline', false)
    
    // Variable de entorno para la rama (Ejercicio 4) 
    def branchName = env.BRANCH_NAME ?: "unknown"
    def sonarServer = 'SonarQubeServer'


    // Timeout de 5 minutos
    timeout(time: 5, unit: 'MINUTES') { 
        script {
            withSonarQubeEnv(sonarServer) {
                echo "Iniciando análisis de calidad con SonarScanner..."
                sh "sonar-scanner -Dsonar.projectKey=practica_2_johany_flores -Dsonar.sources=."
            }
           
            // Resultadod del QualityGate
            def qg = waitForQualityGate()
            echo "Resultado de SonarQube: ${qg.status}"
            echo "Rama actual detectada: ${branchName}"
 

            // Implementación de la Heurística
            if (manualAbort == true) {
                echo "Abortando: Parámetro manual 'abortPipeline' es True."
                error "Análisis fallido (Corte manual)."
            } 

            //Condición para cuando el Quality Gate falle 
            else if (qg.status != 'OK') {
                echo "Abortando: El Quality Gate ha fallado con estado ${qg.status}."
                error "Pipeline detenido por falta de calidad en el código."
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