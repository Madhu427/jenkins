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
                mail bcc: '', body: 'We are ready for testing on yyour confirmation we will GA.', cc: '', from: '', replyTo: '', subject: 'Test Stage', to: 'madhusudhanachary,k@gmail.com'
            }
        }
        stage('stage 3') {
            steps {
                echo 'deploy'
            }
        }
    }
}
