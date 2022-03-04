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

        environment {
            PROG_LANG_NAME = "golang"
            PROG_LANG_VERSION = "1.5"
            NEXUS = credentials('NEXUS')
        }



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
            stage('Publish Artifacts') {
                when {
                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) }
                }
                steps {
                    script {
                        common.prepareArtifacts()
                        common.publishArtifacts()
                    }
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


