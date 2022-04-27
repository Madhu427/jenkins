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
            PROG_LANG_NAME = "python"
            PROG_LANG_VERSION = "3"
            NEXUS = credentials('NEXUS')
        }


        stages{

            // for nodejs and python no need to compile the code
//            stage('Compile the code') {
//                steps{
//                    sh 'echo compile the ${COMPONENT}  code'

//                }
//            }

            stage('Label Builds') {
                steps {
                    script {
                        env.gitTag = GIT_BRANCH.split('/').last()
                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${gitTag}"
                    }
                }
            }

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
            stage('test cases') {
                steps{
                    sh 'echo test cases'
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

            stage('publish AMI') {
                when {
                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) }
                }
                steps {
                    script {
                        common.makeAMI()
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


