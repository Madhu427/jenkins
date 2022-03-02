def call() {
    pipeline {


        agent {
            label "${BUILD_LABEL}"
        }

//        triggers {
//
//            pollSCM('H/2 * * * *')
//
//
//        }


        stages{
            stage('label the builds') {
                steps{
                    sh 'echo compile the ${COMPONENT}  code'
                    script {
                        def gitTag = GIT_BRANCH.split('/').last()
                        addShortText background: '', borderColor: '', color: 'red', link: '', text: "${gitTag}"
                    }

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

    }


}


