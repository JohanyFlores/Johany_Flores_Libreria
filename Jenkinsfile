@Library('obsschool-sharedlib') _

pipeline {
    agent any
    environment {
        BRANCH_NAME = 'main'
    }
    stages {
        stage('Análisis de Calidad') { 
            steps {
                staticAnalysis(abortPipeline: false) 
            }
        }
    }
}