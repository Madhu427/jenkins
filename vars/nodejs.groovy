def call() {
    pipeline {


        agent {
            label "${BUILD_LABEL}"
        }

        triggers {

            pollSCM('*/2 * * * *')


        }


        stages{
            // for node js & python no need to compile thw code skip it
//            stage('Compile the code') {
//                steps{
//                    sh 'echo compile the ${COMPONENT}  code'
//                }
//            }
            stage('Test the code') {
                steps{
                    sh 'echo test the code'
                     script {
                        common.sonarQube("cart","nodejs")
                     }
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


