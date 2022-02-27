def call() {
    pipeline {


        agent {
            label "${BUILD_LABEL}"
        }

        triggers {

            pollSCM('*/2 * * * *')


        }


        stages{
            stage('Compile the code') {
                steps{
                    sh 'echo compile the ${COMPONENT}  code'
                }
            }
            stage('Test the code') {
                steps{
                    sh 'echo test the code'
                }
            }
            stage('Deploy the code') {
                steps{
                    sh 'echo deploy the code'
                }
            }
        }

        post {
            always {
                cleanWs()
            }
        }
    }


}


