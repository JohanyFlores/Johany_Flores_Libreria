@Library('obsschool-sharedlib') _

pipeline {
    agent any
    environment {
        BRANCH_NAME = 'dev'
    }
    stages {
        stage('Análisis de Calidad') { 
            steps {
                staticAnalysis(abortPipeline: false) 
            }
        }
    }
}