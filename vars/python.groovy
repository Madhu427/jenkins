def call() {
    pipeline {


        agent {
            label "${BUILD_LABEL}"
        }

        triggers {

            pollSCM('*/2 * * * *')


        }


        stages{

            // for nodejs and python no need to compile the code
//            stage('Compile the code') {
//                steps{
//                    sh 'echo compile the ${COMPONENT}  code'

//                }
//            }
            stage('Check the code quality') {
                steps {
                    script {
                        common.sonarQube()
                    }
                }
            }
            stage('Lint checks') {
                steps {
                    sh 'echo test cases'
                }
            }
            stage('Deploy the code') {
                steps{
                    sh 'echo deploy the code'
                }
            }
        }

        post{
            always {
                cleanWs()
            }
        }

    }


}


