pipeline {
    agent any

    stages {
        stage('stage 1') {
            steps {
                echo 'build'
                sh '''echo Now we are builiding the project
                echo build stage successfully completed'''
            }
        }
        stage('stage 2') {
            steps {
                echo 'test'

            }
        }
        stage('stage 3') {
            steps {
                echo 'deploy'
            }
        }
    }
}
