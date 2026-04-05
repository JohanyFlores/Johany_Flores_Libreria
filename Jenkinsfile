@Library('obsschool-sharedlib') _

pipeline {
    agent any
    stages {
        stage('Análisis de Calidad') { 
            steps {
                staticAnalysis(abortPipeline: false) 
            }
        }
    }
}